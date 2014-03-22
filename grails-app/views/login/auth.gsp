<%--
  Created by IntelliJ IDEA.
  User: Josescalia
  Date: 11/2/13
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<div id='login'>
    <div>
        <div class='text-center' style="border-bottom:1px solid lightgray"><g:message
                code="springSecurity.login.header"/></div>

        <g:if test='${flash.message}'>
            <div class='login_message'>${flash.message}</div>
        </g:if>

        <form action='${postUrl}' method='POST' id='loginForm' class='form-inline' autocomplete='off'
              style="padding-top:20px">
            <p>
                <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                <input type='text' class='form-control' name='j_username' id='username'/>
            </p>

            <p>
                <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                <input type='password' class='form-control' name='j_password' id='password'/>
            </p>

            <p id="remember_me_holder">
                <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                       <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
            </p>

            <p>
                <a href='#' id="btnEnter" class="btn btn-default btn-info"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                <a href='#' id="btnCancel" class="btn btn-default btn-danger"><span class="glyphicon glyphicon-step-backward"></span>Cancel</a>
                %{--<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'
                       class="btn btn-default btn-info"/>--}%
            </p>
        </form>
    </div>
</div>
<script type='text/javascript'>
    $(document).ready(function () {
        $("#btnCancel").click(function(){
            $("#formLogin").modal("hide");
        });
        $("#btnEnter").click(function () {
            $.post("${postUrl}", $("#loginForm").serialize(), function (loginData) {
                var msg = "";
                if(loginData.error){
                    bootbox.alert(loginData.error,function(result){
                        return;
                    });
                }else{
                    window.location.reload();
                }

            });
        });
    });
</script>
