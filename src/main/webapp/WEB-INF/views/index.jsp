<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="layout/top_head.jsp" />
<body>
<h3>시큐리티 연습 페이지 </h3>
<hr/>
<div class="container p-3 my-3 bg-dark text-white">
<a href="${root}">메인</a>
<hr/>
<a href="${root}joinForm">회원가입</a>
<hr/>
<a href="${root}loginForm">로그인</a>
<hr/>

<a href="${root}admin">관리자 페이지</a>
<hr/>

<a href="${root}logout">로그아웃</a>
</div>
</body>
</html>