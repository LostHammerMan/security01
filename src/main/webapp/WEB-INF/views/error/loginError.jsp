
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
  <%--let url = "<c:out value="${url}" /> "--%>
  // model.addAttribute("url", request.getContextPath() + "/user/loginForm");
  let msg = "로그인이 필요한 기능입니다."
  let url = "${root}user/loginForm";
  alert(msg);
  location.href = url;
</script>