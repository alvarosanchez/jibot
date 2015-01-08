package hipchat.addon

import org.codehaus.groovy.grails.commons.GrailsApplication

class CapabilitiesController {

  GrailsApplication grailsApplication

    def index() {
        render contentType: 'application/json', text:  """
{
  "name": "Jibot",
  "description": "A tiny HipChat add-on that listen for JIRA keys being mentioned on a room, and sends back the link for them plus some additional info",
  "key": "es.alvarosanchezmariscal.hipchat.jibot",
  "vendor": {
    "url": "https://www.linkedin.com/in/mariscal",
    "name": "Álvaro Sánchez-Mariscal"
  },
  "links": {
    "homepage": "https://github.com/alvarosanchez/jibot",
    "self": "${grailsApplication.config.grails.serverURL}/capabilities"
  },
  "capabilities": {
    "hipchatApiConsumer": {
      "scopes": [
        "send_notification"
      ]
    },
    "installable": {
      "callbackUrl": "${grailsApplication.config.grails.serverURL}/installable"
    },
    "configurable": {
      "url": "${grailsApplication.config.grails.serverURL}/configure"
    },
    "webhook": [
      {
        "url": "${grailsApplication.config.grails.serverURL}/webhook",
        "event": "room_message",
        "name": "Room message WebHook"
      }
    ]
  }
}
        """
    }
}
