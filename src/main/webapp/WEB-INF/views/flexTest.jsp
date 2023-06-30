<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="d-flex justify-content-center">
    <p>flex Test</p>

</div>
<div class="d-flex justify-content-center">
    <div class="p-2">
        <p>Flex item 1</p>
        <p>Flex item 2</p>
    </div>
    <div class="d-flex flex-column">
        <p>Flex item 2</p>
        <p>Flex item 3</p>
        <p>flex item 4</p>
    </div>
</div>
<span>====================================</span>
<div class="d-flex flex-row-reverse">
    <div class="p-2">Flex item 1</div>
    <div class="p-2">Flex item 2</div>
    <div class="p-2">Flex item 3</div>
</div>
</body>
</html>
