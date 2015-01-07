class UrlMappings {

	static mappings = {

        "/installable/${oauthId}"(controller: 'installable', action: 'delete', method: 'DELETE')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
