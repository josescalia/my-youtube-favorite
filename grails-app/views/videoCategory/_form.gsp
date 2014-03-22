<table class="table">
    <tr>
        <td><label for="categoryName">Name</label> </td>
        <td><input type="text" class="form-control" id="categoryName" name="categoryName" value="${model?.categoryName}"></td>
    </tr>
    <tr>
        <td><label for="categoryDescription">Description</label> </td>
        <td>
            <textarea class="form-control" id="categoryDescription" name="categoryDescription">${model?.categoryDescription}</textarea>
        </td>
    </tr>
</table>