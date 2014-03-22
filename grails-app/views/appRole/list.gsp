<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/13/14
  Time: 6:46 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta name="layout" content="custom-layout-01">
    <title></title>
</head>
<body>

<div class="page-title">
    App Role List
</div>

<div class="page-action">
    <a href="#modalForm" class="btn btn-default btn-sm btn-info" data-toggle="modal">
        <span class="glyphicon glyphicon-plus-sign"></span> Add New
    </a>
</div>

%{--datatable list here--}%
<div class="page-content">
    <table id="appRoleDT" class="table table-striped table-hover table-bordered table-condensed table-responsive ">
        <thead>
        </thead>
        <tbody></tbody>
    </table>
</div>

%{--Dialog form here--}%
<div class="modal fade" id="modalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="overflow-y:auto">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header form-title">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin:3px">&times;</button>
                <h5>Add New App Role</h5>
            </div>


            <div class="modal-body" id="formData">
                <form class="form-inline">
                    <g:render template="form"/>
                </form>
            </div>

            <div class="modal-footer">
                <g:render template="../template/include_form_footer_action_add"/>
            </div>
        </div>
    </div>
</div>

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