package hipchat.addon

class Tenant {

    Long groupId

    String capabilitiesUrl
    String oauthId
    String oauthSecret
    Long roomId

    String jiraUrl
    String jiraUsername
    String jiraPassword

    static constraints = {
        capabilitiesUrl url: true
        roomId nullable: true
        jiraUrl nullable: true, url: true
        jiraUsername nullable: true
        jiraPassword nullable: true
    }
}
