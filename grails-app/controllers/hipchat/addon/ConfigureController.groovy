package hipchat.addon

class ConfigureController {

    JwtService jwtService

    def index() {
        String signedRequest = params['signed_request']

        Tenant tenant = jwtService.parseAndVerify(signedRequest)

        if (!tenant) {
            render status: 400
            return
        }

        [tenant: tenant]
    }

    def finishInstallation(String jiraUrl, String jiraUsername, String jiraPassword, String signedRequest) {
        Tenant tenant = jwtService.parseAndVerify(signedRequest)

        if (!tenant) {
            render status: 400
            return
        }

        tenant.jiraUrl = jiraUrl
        tenant.jiraUsername = jiraUsername
        tenant.jiraPassword = jiraPassword

        if (tenant.save(flush: true, failOnError: true)) {
            [tenant: tenant]
        } else {
            tenant.errors.each {println it}
            render status: 400
        }

    }


}
