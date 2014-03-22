<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/21/14
  Time: 2:19 PM
--%>
<form id="formData">
    <g:render template="form"/>
</form>
<div class="text-right">
    <a href="#" id="btnAddSave" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-save"></span> Save</a>
    <a href="#" id="btnCancelAdd" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-backward"></span> Cancel</a>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#btnCancelAdd").click(function(){
            $("#dialogRef").modal("hide");
        });
        $("#btnAddSave").click(function(){
            $.ajax({
                url:"${request.contextPath}/youtubeVideo/add",
                method:"post",
                data:$("#formData").serialize(),
                success:function(res){
                    bootbox.alert(res.messageBody, function(result){
                        $("#dialogRef").modal("hide");
                    })
                }
            });
        });
    })
</script>