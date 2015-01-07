package hipchat.addon

class InstallableController {

    def index() {
        Tenant tenant = new Tenant(request.JSON)
        if (tenant.save()) {
            render status: 200
        } else {
            tenant.errors.each {println it}
            render status: 400
        }
    }

    def delete(String oauthId) {
        Tenant tenant = Tenant.findByOauthId(oauthId)
        if (tenant) {
            tenant.delete(flush: true)
            render status: 200
        } else {
            render status: 404
        }
    }
}
