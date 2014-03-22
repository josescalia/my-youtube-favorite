(function ($) {
    jsApp = function (dataTableJsonURL) {
        $(document).ready(function () {
            $('#dataRef').dataTable({
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: dataTableJsonURL,
                iDisplayLength: 10,
                aoColumns: [
                    {"sTitle": "Action",
                        "sWidth": 30,
                        "sClass": "text-center",
                        "bSortable": false,
                        "bVisible": true,
                        "fnRender": function (aaData) {
                            var id = aaData.aData[0];
                            var action = "<a onclick='useMe(\""+aaData.aData[0]+"\",\""+aaData.aData[1]+"\",\""+aaData.aData[2]+"\")' class='btn btn-info btn-sm'><span class='glyphicon glyphicon-download-alt' ></span> Use</a> ";
                            return action;
                        }
                    },
                    {"sName": "videoTitle",
                        "sTitle": "Title",
                        "sWidth": 300
                    },
                    {"sName": "videoKey",
                        "sTitle": "Key",
                        "sWidth": 100
                    }
                ]
            });
        });
    };
})(jQuery);


function useMe(id,videoTitle,videoKey) {
    $("#videoId").val(id);
    $("#videoTitle").val(videoTitle);
    $(".youtube-player").val("Loading ...please wait");
    watchVideo(videoKey,videoTitle);
    $("#dialogRef").modal("hide");
}


