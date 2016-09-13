<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Consultant Timecard Application</title>
            <link rel="stylesheet" type="text/css" href="/ConsultingAgency/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ConsultingAgency/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color:red" infoStyle="color:green" layout="table" />
            </h:panelGroup>
            <h:form>
                <h1><h:outputText value="Consultant Timecard Application" /></h1>
                <h:commandLink action="#{billable.searchSetup}" value="Search Billable Items"/>
                <br/>
                <h:commandLink action="#{billable.createSetup}" value="New Billable"/>
                <br/>
                <h:commandLink action="#{consultant.logout}" value="Logout"/>
            </h:form>

        </body>
    </html>
</f:view>
