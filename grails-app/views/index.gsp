<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="custom-layout-01"/>
    <title>${grailsApplication.config.application.name}</title>

</head>

<body>
<h2>
    Welcome to ${grailsApplication.config.application.name + " " + grailsApplication.config.application.version}
</h2>
<sec:ifNotLoggedIn>
    Please login to gain more access
</sec:ifNotLoggedIn>


<div class="modal fade" id="formLogin" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" class="close" aria-hidden="true">&times;</button>
                <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
            </div>

            <div class="modal-body">

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#btnLogin").click(function () {
            $.get("${request.contextPath}/login/", function (htmlData) {
                $("#formLogin").find(".modal-dialog").css("width", "400px");
                $("#formLogin").find(".modal-body").html(htmlData);
                $("#formLogin").modal("show");
            });

        })
    })
</script>
</body>
</html>
