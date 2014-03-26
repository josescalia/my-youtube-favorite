<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/13/14
  Time: 6:46 AM

  We Are using template here, because form and data-table is very generic<br>
  What we need to parse is only the page title and the "<b>id</b>" of the data-table<br>
  the id of the data-table is the id we will use in each java-script file to generate datatables

--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta name="layout" content="custom-layout-01">
    <title></title>
</head>
<body>
%{--include form and datatable list template--}%
<g:render template="../template/datatables_list_and_form" model="[pageTitle:'App Role',dataTableId:'appRoleDT']"/>

%{--include all datatables dependencies scripts--}%
<g:include view="template/_include_datatables_scripts_template.gsp"/>
%{--using specific datatables-fecth scripts --}%
<script type="text/javascript" src="${resource(dir: "js/app_role", file: "app_role_app.js")}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        jsApp("${request.contextPath}/appRole/dataTableList");
    })
</script>
</body>
</html>