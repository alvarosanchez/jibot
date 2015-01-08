package hipchat.addon

import wslite.http.auth.HTTPBasicAuthorization
import wslite.rest.ContentType
import wslite.rest.RESTClient
import wslite.rest.Response


class WebhookController {

    def index() {
        def json = request.JSON
        Tenant tenant = Tenant.findByOauthId(json.oauth_client_id)
        if (tenant) {
            RESTClient jira = getJiraClient(tenant)
            RESTClient hipchat = getHipchatClient(tenant)
            Long roomId = json.item.room.id as Long
            String text = json.item.message.message

            Response response = jira.get(path: '/serverInfo')
            if (response.json.version.startsWith('6')) {
                String projectPattern
                try {
                    response = jira.get(path: '/application-properties?key=jira.projectkey.pattern')
                    projectPattern = response.json.value
                } catch (Exception e) {
                    projectPattern = /([A-Z]+)/
                }
                def pattern = /${projectPattern}\-(\d+)/
                def matcher = text =~ pattern

                if (matcher) {
                    matcher.each {
                        String issueKey = it[0]
                        response = jira.get(path: "/issue/${issueKey}")
                        String issueTypeIcon = response.json.fields.issuetype.iconUrl
                        String summary = response.json.fields.summary
                        def resolution = response.json.fields.resolution
                        def status = response.json.fields.status
                        def assignee = response.json.fields.assignee

                        String message = """
                            <img src="${issueTypeIcon}"/> <strong><a href="${tenant.jiraUrl}/browse/${issueKey}">${summary}</a></strong> ${resolution?"\u2713":''}
                            <ul>
                                <li><em>Status</em>: <img src="${status.iconUrl}"/> ${status.name}</li>
                                ${resolution?'': assignee ? "<li><em>Assignee</em>: <img src=\"${assignee.avatarUrls['16x16']}\" /> ${assignee.displayName}" : ''}
                            </ul>
                        """.trim()

                        sendRoomNotification(hipchat, roomId, message)

                        render status: 200
                    }
                }
            }
        }

    }

    private void sendRoomNotification(RESTClient hipchat, Long roomId, String message) {
        Response response

        try {
            response = hipchat.post(path: "/room/${roomId}/notification") {
                type ContentType.URLENC
                urlenc color: 'purple', message: message
            }
        } catch (exception) {
            exception.printStackTrace()
        }
    }

    private RESTClient getJiraClient(Tenant tenant) {
        def jira = new RESTClient("${tenant.jiraUrl}/rest/api/latest")
        jira.with {
            authorization = new HTTPBasicAuthorization(tenant.jiraUsername, tenant.jiraPassword)
            defaultAcceptHeader = 'application/json'
            defaultContentTypeHeader = 'application/json'
            defaultCharset = 'UTF-8'
        }

        return jira
    }

    private RESTClient getHipchatClient(Tenant tenant) {
        def hipchat = new RESTClient("https://api.hipchat.com/v2")
        hipchat.with {
            defaultAcceptHeader = 'application/json'
            defaultContentTypeHeader = 'application/json'
            defaultCharset = 'UTF-8'
        }
        hipchat.httpClient.defaultHeaders << [Authorization: "Bearer ${getHipchatAccessToken(tenant)}"]
        return hipchat
    }

    private String getHipchatAccessToken(Tenant tenant) {
        RESTClient hipchat = new RESTClient('https://api.hipchat.com/v2/oauth/token')
        hipchat.authorization = new HTTPBasicAuthorization(tenant.oauthId, tenant.oauthSecret)
        hipchat.post {
            urlenc grant_type: 'client_credentials', scope: 'send_notification'

        }.json.access_token
    }
}
