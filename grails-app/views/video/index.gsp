<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/18/14
  Time: 5:47 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="custom-layout-01">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-md-3">
        <table class="table table-bordered table-condensed table-striped" id="dataList">
            <thead>
            <tr>
                <th>Top 10 Newest List</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
            <tfoot>

            </tfoot>
        </table>
    </div>

    <div class="col-md-9">
        <p><input type="text" id="videoTitle" readonly class="form-control"></p>

        <iframe title="YouTube video player" class="youtube-player" type="text/html"
                width="630" height="400" src="${request.contextPath}/video/empty" allowfullscreen>
        </iframe>
        %{--<div class="youtube-player"></div>--}%
    </div>
</div>

%{--Dialog form here--}%
<div class="modal fade" id="dialogRef" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="overflow-y:auto">
    <div class="modal-dialog" style="width:900px">
        <div class="modal-content">
            <div class="modal-header form-title">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin:3px">&times;</button>
                <h5>Find Others Video</h5>
            </div>


            <div class="modal-body" id="dataContent">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var tBody = "";
    var tFoot = "";
    $(document).ready(function () {
        //fetch to 20 video
        $.get("newestYoutubeVideo", function (jsonData) {
            $.each(jsonData, function (index, model) {
                tBody += "<tr>";
                tBody += "<td><a onclick='watchVideo(\""+model.videoKey +"\",\""+model.videoTitle+"\")' class='btn btn-info btn-sm' href='#'><span class='glyphicon glyphicon-film'></span></a>";
                tBody +=  " " + model.videoTitle + "</td>";
                tBody += "</tr>";
            });
            tFoot = "<tr class='text-center'><td><a href='#' onclick='findVideo()'>More Video</a> </td></tr>" ;
            $("#dataList").find("tbody").html(tBody);
            $("#dataList").find("tfoot").html(tFoot);
        })
    });

    function findVideo(){
        $(".youtube-player").attr("src","${request.contextPath}/video/loading");
        //$(".youtube-player").html("<p class='text-center'><img src='${resource(dir:'images', file:'spinner.gif' )}'/><br>Loading...!!!</p>");
        $.get("${request.contextPath}/youtubeVideo/dataTableAsRef", function(htmlData){
            $("#dataContent").html(htmlData);
            $("#dialogRef").modal("show");
        });
    }

    function watchVideo(videoKey, videoTitle){
        //$(".youtube-player").attr("<p class='text-center'><img src='${resource(dir:'images', file:'spinner.gif' )}'/><br>Loading...!!!</p>");
        $("#videoTitle").val(videoTitle);
        $(".youtube-player").attr("src","https://www.youtube.com/embed/"+videoKey);
        /*$.ajax({
            url:"player?width=845&height=500&vKey="+videoKey,
            method:"GET",
            success:function(htmlData){
                $(".youtube-player").html(htmlData)
            }

        });*/

    }
</script>
</body>
</html>