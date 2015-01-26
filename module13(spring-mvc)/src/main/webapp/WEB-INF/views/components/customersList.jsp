<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${allCustomers}" var="item">
    <a href="/customerProducts?customerName=${item}">${item}</a><br>
</c:forEach>