<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="layout/top_head.jsp"/>
<body>
<h1>로그인 페이지</h1>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">username</label>
            <input type="text" name="username" placeholder="이름 입력" class="form-control">
        </div>

        <div class="form-group">
            <label for="password">password</label>
            <input type="password" name="password" placeholder="비번 입력" class="form-control">
        </div>
        <button class="btn btn-primary">로그인</button>
        <a href="${root}joinForm" class="btn btn-danger">회원가입</a>
        <a href="${root}" class="btn btn-dark">메인화면</a>
    </form>
</body>
</html>