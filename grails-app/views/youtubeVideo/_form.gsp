<table class="table">
    <tr>
        <td><label for="videoTitle">Title</label></td>
        <td><input type="text" class="form-control" id="videoTitle" name="videoTitle" value="${model?.videoTitle}"></td>
    </tr>
    <tr>
        <td><label for="videoKey">Key</label></td>
        <td><input type="text" class="form-control" id="videoKey" name="videoKey" value="${model?.videoKey}"></td>
    </tr>
    <tr>
        <td><label for="categoryId">Category</label></td>
        <td>
            <select class="form-control width-300" id="categoryId" name="categoryId">
                <g:each in="${catList}" var="category">
                    <option value="${category.id}"
                            <g:if test="${model?.category?.id == category.id}">selected</g:if>>${category.categoryName}</option>
                </g:each>
            </select>
    </tr>
    <tr>
        <td><label for="available">Available</label></td>
        <td>
            <select class="form-control width-100" id="available" name="available">
                <option value="true" <g:if test="${model?.available == true}">selected</g:if>>Yes</option>
                <option value="false" <g:if test="${model?.available == false}">selected</g:if>>No</option>
            </select>
        </td>
    </tr>
    <tr>
        <td><label for="rate">Rate</label></td>
        <td>
            <span id="favRate"></span>
        </td>
    </tr>
    <tr>
        <td><label for="favRate">Score</label> </td>
        <td><input type="text" id="rate" value="${model?.rate}" name="rate" class="form-control" readonly style="width:40px"></td>
    </tr>
</table>
<script type="text/javascript" src="${resource(dir: "js/raty", file: 'jquery.raty.js')}"></script>
<script type="text/javascript">
    $("#favRate").raty({
        score: $("#rate").val(),
        target:"#rate",
        targetType:'number',
        targetKeep: true})

</script>



