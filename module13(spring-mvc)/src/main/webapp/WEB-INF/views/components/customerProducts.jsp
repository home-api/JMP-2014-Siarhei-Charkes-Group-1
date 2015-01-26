<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${customerProducts}" var="item">
    ${item}</a><br>
</c:forEach>