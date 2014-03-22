(function ($) {
    jsApp = function (dataTableJsonURL) {
        $(document).ready(function () {
            $('#videoCategoryRef').dataTable({
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
                            var action = "<a onclick='useMe(\""+aaData.aData[0]+"\",\""+aaData.aData[1]+"\")' class='btn btn-info btn-sm'><span class='glyphicon glyphicon-download-alt' ></span> Use</a> ";
                            return action;
                        }
                    },
                    {"sName": "categoryName",
                        "sTitle": "Name",
                        "sWidth": "20%"
                    },
                    {"sName": "categoryDescription",
                        "sTitle": "Description",
                        "sWidth": "35%"
                    }
                ]
            });
        });
    };
})(jQuery);


function useMe(id,name) {
    $("#countryId").val(id);
    $("#countryName").val(name);
    $("#dialogRef").modal("hide");
}


