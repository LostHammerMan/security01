<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="../layout/top_head.jsp"/>
<body>
<h2>관리자 - 회원 수정</h2>
<div class="container">
    <form action="" method="post">
        <div class="form-group">
            <label for="user_id">유저 id</label>
            <input class="form-control" id="user_id" value="${findUser.id}" readonly>
        </div>
        <div class="form-group">
            <label for="username">username</label>
            <input class="form-control" id="username" value="${findUser.username}" readonly>
        </div>
        <div class="form-group">
            <label for="email">email</label>
            <input class="form-control" id="email" value="${findUser.email}">
        </div>
        <div class="form-group">
            <label for="user_role">권한</label>
            <input class="form-control" id="user_role" value="${findUser.role}">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


</body>
</html>