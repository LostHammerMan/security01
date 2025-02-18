
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
  // <%--let url = "<c:out value="${url}" /> "--%>
  // model.addAttribute("url", request.getContextPath() + "/user/loginForm");
  let msg = '<c:out value="${msg}" />';
  let url = '<c:out value="${msg}" />';
  alert(msg);
  location.href = url;
</script>