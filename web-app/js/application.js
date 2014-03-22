if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });
    })(jQuery);
    $("#btnBack").click(function () {
        window.location = "list";
    });
    $("#btnAdd").click(function () {
        $.post("add", $("#formData").find("form").serialize(), function (res) {
            bootbox.alert(res.messageBody, function (result) {
                /*if(res.messageBody.contains("ERROR")){  //caught error in google-chrome
                 bootbox.hide;
                 }else{*/
                window.location = "list";
                //}
            })
        });
    });
    $("#btnUpdate").click(function () {
        $.post("update", $("#formEditData").find("form").serialize(), function (res) {
            bootbox.alert(res.messageBody, function (result) {
                window.location = "list";
            })
        });
    });
}

function escapeSpecialChar(text) {
    return text.replace(/([\"\'])/g,'')
}

function youtubeShortTitle(text){
    if(text.length > 45){
        return text.substr(0,40)+" ... "
    }
    return text;
}