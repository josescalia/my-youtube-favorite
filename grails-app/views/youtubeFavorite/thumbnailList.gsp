<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/22/14
  Time: 8:40 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="custom-layout-01">
    <title></title>
</head>

<body>
<p class="text-center page-title">My Youtube Favorite List</p>



<div class="row">
    <g:each in="${thumbnailList}" var="thumbnail">
        <div class="col-md-3 thumbnail" style="margin:5px 45px">
            <table class="table table-condensed" id="${thumbnail.id}">
                <tr>
                    <td>Title</td>
                    <td class="text-bold">${thumbnail.videoTitle}</td>
                </tr>
                <tr>
                    <td>Key</td>
                    <td class="text-info">${thumbnail.videoKey}</td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td class="text-success">${thumbnail.category.categoryName}</td>
                </tr>
            </table>

            <div style="margin:15px auto" class="text-center">
                <img class="img-thumbnail" style='width:280px;height:180px;'
                     src="https://i.ytimg.com/vi/${thumbnail.videoKey}/mqdefault.jpg"/>
            </div>

            <p class="text-center">
                <g:if test="${thumbnail.available}">
                    <a href="#" class="btn btn-info btn-sm btnWatch" data-main="${thumbnail.videoKey}">Watch</a>
                </g:if>
                <a href="#" class="btn btn-danger btn-sm btn-delete" onclick="deleteYoutube('${thumbnail.id}')">Delete</a>
            </p>
        </div>
    </g:each>
</div>
<div class="pagination" style="margin-left:30px">
    <g:paginate total="${total}"/>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $(".btnWatch").click(function(){
            var key  = $(this).attr("data-main");
            window.open("https://www.youtube.com/embed/" + key, "_blank");
        });
    });

    function deleteYoutube(id) {
        bootbox.confirm("Are you sure to delete this data?", function (result) {
            if (result) {
                /*this action need to create action in the controller named delete and pass id as parameter*/
                $.post("${request.contextPath}/youtubeVideo/delete", "id=" + id, function (data) {
                    bootbox.alert(data.messageBody, function () {
                        window.location.reload();
                    });
                })
            }
        });
    }
</script>
</body>
</html>