<html>
<head>
    <meta name="layout" content="main">
</head>
<body>

<div class="aui-message aui-message-info">
    <p>Please provide the following information about your JIRA instance. It is required to access the JIRA REST API</p>
</div>

<g:form action="finishInstallation" class="aui">
    <g:hiddenField name="signedRequest" value="${params.signed_request}" />
    <div class="field-group">
        <label for="jiraUrl">URL <span class="aui-icon icon-required">(required)</span></label>
        <input class="text" type="text" id="jiraUrl" name="jiraUrl">
    </div>
    <div class="field-group">
        <label for="jiraUsername">Username <span class="aui-icon icon-required">(required)</span></label>
        <input class="text" type="text" id="jiraUsername" name="jiraUsername">
    </div>
    <div class="field-group">
        <label for="jiraPassword">Password <span class="aui-icon icon-required">(required)</span></label>
        <input class="text" type="password" id="jiraPassword" name="jiraPassword">
    </div>
    <div class="buttons-container">
        <div class="buttons">
            <input class="button submit" type="submit" value="Save">
        </div>
    </div>
</g:form>

</body>
</html>