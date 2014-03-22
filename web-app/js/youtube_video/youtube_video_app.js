(function ($) {
    jsApp = function (dataTableJsonURL) {
        $(document).ready(function () {
            $('#youtubeVideoDT').dataTable({
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: dataTableJsonURL,
                iDisplayLength: 10,
                aoColumns: [
                    {"sTitle": "Action",
                        "sWidth": "12%",
                        "sClass": "center",
                        "bSortable": false,
                        "bVisible": true,
                        "fnRender": function (aaData) {
                            var id = aaData.aData[0];
                            var action = "<a href='edit?id=" + id + "' class='btn btn-info btn-sm'><span class='glyphicon glyphicon-edit' ></span> Edit</a> ";
                            action += "<a onclick='deleteData(" + id + ")' href='#' class='btn btn-danger btn-sm btnDelete'><span class='glyphicon glyphicon-trash'></span> Del</a> ";
                            return action;
                        }
                    },
                    {"sName": "feederName",
                        "sTitle": "Name",
                        "sWidth": "45%"
                    },
                    {"sName": "feederUrl",
                        "sTitle": "Key",
                        "sWidth": "10%"
                    },
                    {"sName": "category",
                        "sTitle": "Category",
                        "sWidth": "10%",
                        "fnRender" : function(aaData){
                            return aaData.aData[3].categoryName
                        }
                    },
                    {"sName": "available",
                        "sTitle": "Available",
                        "sClass":"text-center",
                        "sWidth": "10%",
                        "fnRender" : function(aaData){
                            if(aaData.aData[4] == true){
                                return "<span class='text-info'>Yes</span>";
                            }else{
                                return "<span class='text-danger'>No</span>";
                            }
                        }
                    },{"sName": "rate",
                        "sTitle": "Rating",
                        "sClass":"text-center",
                        "sWidth": "10%",
                        "fnRender" : function(aaData){
                            var star = "";
                            var count = aaData.aData[5];
                            for(var x= 0; x < count;x++){
                                star += "<span class='glyphicon glyphicon-star' style='color:#d17d0f'></span> ";
                            }
                            return star
                        }
                    }
                ]
            });
        });
    };
})(jQuery);

function deleteData(id) {
    bootbox.confirm("Are you sure to delete this data?", function (result) {
        if (result) {
            /*this action need to create action in the controller named delete and pass id as parameter*/
            $.post("delete", "id=" + id, function (data) {
                bootbox.alert(data.messageBody, function () {
                    window.location = "list";
                });
            })
        }
    });
}

