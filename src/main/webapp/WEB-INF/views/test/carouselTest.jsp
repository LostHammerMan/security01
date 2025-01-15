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
<style>
  .d-fix {
    display: flex;

    align-items: normal;
    justify-content: flex-start;
    flex-direction: column;
  }
    
</style>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
/>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<body>
<div class="main_container">
  <div class="swiper mySwiper">
    <div class="swiper-slide">슬라이드 1</div>
    <div class="swiper-slide">슬라이드 2</div>
    <div class="swiper-slide">슬라이드 3</div>
    <div class="swiper-slide">슬라이드 4</div>
    <div class="swiper-slide">슬라이드 5</div>
    <div class="swiper-slide">슬라이드 6</div>
    <div class="swiper-slide">슬라이드 7</div>
    <div class="swiper-slide">슬라이드 8</div>
  </div>
</div>  




</body>
<script>
  let mySwiper = new Swiper(".mySwiper", {
    
  });
</script>
</html>

