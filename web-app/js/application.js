if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
                $(this).fadeOut();
            });
    })(jQuery);

    //tooltip to every button
    $('.btn').mouseenter(function(){
        $(this).tooltip("show");
    });
    $(".btn").mouseleave(function(){
        $(this).tooltip("hide");
    });

    //tooltip to login link and logout link
    $("#btnLogin,#btnLogout").mouseenter(function(){
        $(this).tooltip("show");
    });
    $("#btnLogin,#btnLogout").mouseleave(function(){
        $(this).tooltip("hide");
    });





    /*------------------Generic Form Action------------------*/
    $("#btnBack").click(function(){
        window.location = "list";
    });
    $("#btnAdd").click(function(){
        $(this).button('loading');
        $.post("add",$("#formData").find("form").serialize(), function(res){
            bootbox.alert(res.messageBody, function(result){
                $(this).button('complete');
                /*if(res.messageBody.contains("ERROR")){  //caught error in google-chrome
                    bootbox.hide;
                }else{*/
                    window.location = "list";
                //}
            })
        });
    });
    $("#btnUpdate").click(function(){
        $(this).button('loading');
        $.post("update",$("#formEditData").find("form").serialize(), function(res){
            bootbox.alert(res.messageBody, function(result){
                $(this).button('complete');
                window.location = "list";
            })
        });
    });
    /*------------------End Generic Form Action------------------*/
}

//other function
function escapeSpecialChar(text) {
    return text.replace(/([\"\'])/g,'')
}

function youtubeShortTitle(text){
    if(text.length > 45){
        return text.substr(0,40)+" ... "
    }
    return text;
}