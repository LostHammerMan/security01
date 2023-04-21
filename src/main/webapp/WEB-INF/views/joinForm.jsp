<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>

</head>
<body>
  <h1>회원가입 페이지</h1>
  <hr/>

  <form action="/join" method="post">

      <label id="username">
          <input type="text" name="username" placeholder="username">
      </label>
      <br>
      <label id="password">
          <input type="password" name="password" placeholder="password">
      </label>
      <br>
      <label id="email">
          <input type="email" name="email" placeholder="email">
      </label>
      <br>
      <button>회원가입</button><br>
      <button><a href="${root}">메인화면</a></button>

  </form>
</body>
</html>