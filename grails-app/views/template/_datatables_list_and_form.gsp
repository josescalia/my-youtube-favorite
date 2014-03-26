<div class="page-title">
    ${pageTitle} List
</div>

<div class="page-action">
    <a href="#modalForm" class="btn btn-default btn-sm btn-info" data-toggle="modal" data-placement="top" title="Click to Add New ${pageTitle}">
        <span class="glyphicon glyphicon-plus-sign"></span> Add New
    </a>
</div>

%{--datatable list here--}%
<div class="page-content">
    <table id="${dataTableId}" class="table table-striped table-hover table-bordered table-condensed table-responsive ">
        <thead>
        </thead>
        <tbody></tbody>
    </table>
</div>


%{--Modal Dialog form here--}%
<div class="modal fade" id="modalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="overflow-y:auto">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header form-title">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin:3px">&times;</button>
                <h5>Add New ${pageTitle}</h5>
            </div>


            <div class="modal-body" id="formData">
                <form class="form-inline">
                    <g:render template="form"/>
                </form>
            </div>

            <div class="modal-footer">
                <g:render template="../template/include_form_footer_action_add"/>
            </div>
        </div>
    </div>
</div>