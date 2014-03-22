(function ($) {
    jsApp = function (dataTableJsonURL) {
        $(document).ready(function () {
            $('#videoCategoryDT').dataTable({
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
                    {"sName": "categoryName",
                        "sTitle": "Name",
                        "sWidth": "25%"
                    },
                    {"sName": "categoryDescription",
                        "sTitle": "Description",
                        "sWidth": "55%"
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

