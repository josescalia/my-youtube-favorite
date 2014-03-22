<div class="panel panel-info" style="width:45%" id="formEditData">
    <div class="panel-heading "><h5 class="panel-title text-center">Edit ${formTitle}</h5></div>
    <form class="form-inline">
        <div class="panel-body">
            <g:render template="form"/>
            %{--must hide id here--}%
            <input type="hidden" id="id" name="id" value="${model.id}">
        </div>

        <div class="panel-footer text-right">
            <g:render template="../template/include_form_footer_action_update"/>
        </div>
    </form>
</div>