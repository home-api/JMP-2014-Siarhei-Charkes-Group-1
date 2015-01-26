<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${allProducts}" var="item">
    <a href="/productInfo?productName=${item}">${item}</a><br>
</c:forEach>