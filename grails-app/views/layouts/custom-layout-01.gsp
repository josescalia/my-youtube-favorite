<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 12/16/13
  Time: 2:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${resource(dir: "css/bootstrap", file: "bootstrap.min.css")}" type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: "css/datatables", file: "dataTables.bootstrap.css")}" type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: "css/bootstrap/plugin", file: "datepicker.css")}" type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: "css", file: "custom-layout-01.css")}" type="text/css"/>
    <title>${grailsApplication.config.application.name}</title>
    <r:require module="jquery"/>

    <r:layoutResources/>

</head>

<body>
<div class="nav navbar-default navbar-inverse navbar-fixed-top">
    <ul class="nav navbar-nav ">
        <li class="navbar-brand">
            ${grailsApplication.config.application.name}&nbsp;${grailsApplication.config.application.version}
        </li>
        <sec:ifLoggedIn>
            <g:include controller="home" action="menuTopLeft"/>
        </sec:ifLoggedIn>
    </ul>
    <ul class="nav navbar-nav navbar-right">

        <g:include controller="home" action="menuTop"/>
        <sec:ifLoggedIn>
            <li>
                <a href="#">Welcome <sec:username/></a>
            </li>
            <li>
                <a href="${request.contextPath}/logout/">Logout <span
                        class="glyphicon glyphicon-log-out"></span></a>
            </li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li>
                <a href="#">Welcome Guest</a>
            </li>
            <li>
                <a href="#" id="btnLogin" data-toggle="tooltip" data-placement="bottom" title="Click to login">Login <span
                        class="glyphicon glyphicon-log-in"></span></a>
            </li>
        </sec:ifNotLoggedIn>
    </ul>
</div>

<div class="container" style="margin-top:80px">
    <g:layoutBody/>
</div>
<script type="text/javascript"
        src="${resource(plugin: "jquery", dir: "js/jquery", file: "jquery-1.8.3.min.js")}"></script>
<script type="text/javascript" src="${resource(dir: "js/bootstrap", file: "bootstrap.min.js")}"></script>
<script type="text/javascript" src="${resource(dir: "js/bootstrap/plugin", file: "bootbox.min.js")}"></script>
<script type="text/javascript" src="${resource(dir: "js/bootstrap/plugin", file: "bootstrap-datepicker.js")}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'application.js')}"></script>
<r:layoutResources/>
</body>
</html>