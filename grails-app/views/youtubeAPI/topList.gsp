<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/21/14
  Time: 8:20 AM
--%>
<!doctype html>
<html>
<head>
    <meta name="layout" content="custom-layout-01">
    <title>Search</title>
</head>

<body>

<div class="form-inline">
    <label for="query" style="width:50px">Search:</label>
    <input id="query" placeholder='Staind Video' type="text" class="form-control width-300"/>
    <button id="search-button" class="btn btn-default btn-info" onclick="search()">Search</button>
    <label for="max-results" style="width:80px">Max Results :</label>
    <select class="form-control width-80" id="max-results" name="max-results">
         <g:each in="[10,20,30,40,50,100]" var="val">
             <option value="${val}" <g:if test="${val == 20}">selected</g:if> >${val}</option>
         </g:each>
    </select>
</div>
<hr>
<div id="search-container-2">
    <h5 class="text-center text-info">Data not available</h5>
</div>
<g:render template="../template/dialog_template" model="['modalTitle': 'Add Youtube Video']"/>
<script type="text/javascript" src="${resource(dir: "js/tools", file: "youtube.js")}"></script>
<script type="text/javascript" src="${resource(dir: "js", file: "prototype-datetime.js")}"></script>
<script type="text/javascript">
    var key = "${browserKey}";
    var loadingGif = "${resource(dir:"images", file: "spinner.gif" )}";
    var appContext = "${request.contextPath}";
</script>
</body>
</html>