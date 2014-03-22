<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/18/14
  Time: 7:36 AM
--%>
%{--datatable list here--}%
<div class="page-content">
    <table id="dataRef" class="table table-striped table-hover table-bordered table-condensed table-responsive ">
        <thead>
        </thead>
        <tbody></tbody>
    </table>
</div>
<g:include view="template/_include_datatables_scripts_template.gsp"/>
<script type="text/javascript" src="${resource(dir: "js/youtube_video", file: "app_as_reference.js")}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        jsApp("${request.contextPath}/youtubeVideo/dataTableList");
    })
</script>