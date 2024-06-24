<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
    let msg = "<c:out value="${msg}" /> "
    let url = "<c:out value="${url}" /> "
    alert(msg);
    location.href = url;
</script>