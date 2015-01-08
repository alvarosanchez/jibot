<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Jibot, a HipChat addon for JIRA</title>
  <meta name="layout" content="main">
</head>
<body>

<header id="header" role="banner">
  <nav class="aui-header aui-dropdown2-trigger-group" role="navigation">
    <div class="aui-header-primary">
      <h1 id="logo" class="aui-header-logo aui-header-logo-textonly">
        <a href="${grailsApplication.config.grails.serverURL}">
          <span class="aui-header-logo-device">Jibot</span>
        </a>
      </h1>
    </div>
  </nav>
</header>


<section id="content" role="main">
  <header class="aui-page-header">
    <div class="aui-page-header-inner">
      <div class="aui-page-header-image">
        <div class="aui-avatar aui-avatar-xlarge aui-avatar-project">
          <div class="aui-avatar-inner">
            <img src="https://robohash.org/jibot"/>
          </div>
        </div>
      </div>
      <div class="aui-page-header-main">
        <h1>Jibot, a tiny HipChat addon for JIRA</h1>
        <a class="github-button" href="https://github.com/alvarosanchez/jibot" data-style="mega" data-count-href="/alvarosanchez/jibot/network" data-count-api="/repos/alvarosanchez/jibot#forks_count">Fork on GitHub</a>
        <a href="https://twitter.com/share" class="twitter-share-button" data-url="https://jibot.herokuapp.com" data-text="Meet Jibot: a @HipChat add-on for @JIRA written in #groovylang and #grailsfw" data-via="alvaro_sanchez" data-size="large" data-related="alvaro_sanchez" data-dnt="true">Share on Twitter</a>
      </div>
  </header>
  <div class="aui-page-panel">
    <div class="aui-page-panel-inner">
      <section class="aui-page-panel-content">
        <p>
          Jibot is a tiny HipChat add-on that listen for JIRA keys being mentioned on a room, and sends back the link for them plus
          some additional info.
        </p>

        <p>
          It receives webhook notifications from HipChat when a message is sent to a room. It compares the message against your
          JIRA project key pattern (regex), and if it matches, it queries the JIRA REST API to fetch the details of the issue(s).
          If anything found, it sends back to the room few details about the issue(s):
        </p>

        <p>
          <g:img file="jibot-screenshot.png"/>
        </p>

        <h2>Give it a try</h2>
        <p>
          <a target="_blank" href="https://hipchat.com/addons/install?url=https://jibot.herokuapp.com/capabilities" class="aui-button aui-button-primary">Install on your HipChat instance</a>
        </p>

        <h2>A note about security</h2>
        <div class="aui-message aui-message-warning">
          <p>
            Jibot stores on the DB your JIRA credentials in order to access the JIRA REST API. This is because JIRA only
            supports HTTP basic authentication and OAuth 1.0a. And OAuth 1.0a is a mess and will complicate a lot the
            setup.
          </p>

          <p>
            If using this instance deployed on Heroku, the credentials will be stored on a PostgreSQL database, so use
            it only for testing purposes. If you are concerned about how many cats I can kill using your credentials, grab
            the source code and deploy it yourself in your servers. It's open source!
          </p>
        </div>

        <h2>Kudos</h2>
        <ul>
          <li><a href="http://robohash.org/">RoboHash</a> icons.</li>
          <li><a href="http://connect2id.com/products/nimbus-jose-jwt">Nimbus JOSE + JWT</a> library.</li>
          <li><a href="http://groovy-lang.org/">Groovy</a> programming language.</li>
          <li><a href="https://grails.org/">Grails</a> framework.</li>
          <li><a href="https://www.atlassian.com/">Atlassian</a>.</li>
        </ul>

      </section>
    </div>
  </div>
</section>

<footer id="footer" role="contentinfo">
  <section class="footer-body" style="background: none">
    <ul>
      <li>Made with &hearts; during a cold winter weekend by <a href="https://www.linkedin.com/in/mariscal">Álvaro Sánchez-Mariscal</a></li>
    </ul>
  </section>
</footer>

<script async defer id="github-bjs" src="https://buttons.github.io/buttons.js"></script>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
</body>
</html>