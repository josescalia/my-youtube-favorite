<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/16/14
  Time: 7:29 PM
--%>
<g:each in="${dataList}" var="menuCatList">
    <g:each in="${menuCatList.entrySet()}" var="menuCat">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" >${menuCat.getKey()}  <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <g:each in="${menuCat.getValue().listIterator()}" var="menu">
                    <li><a href="${menu.getAt("menuLink")}" class="small-font">${menu.getAt("menuName")}</a></li>
                </g:each>
            </ul>
        </li>
    </g:each>
</g:each>