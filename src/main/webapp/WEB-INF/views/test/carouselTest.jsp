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
.swiper-container {
    display: flex;
    flex-direction: column; /* 슬라이드를 위로 두고, Pagination을 아래로 */
    align-items: center; /* 중앙 정렬 */
}

.swiper {
    width: 400px;
    height: 350px;
    position: relative;
    margin-top: 50px;
    overflow: visible;
}

.swiper-pagination {
    position: relative;
    margin-top: 20px; /* 슬라이드와 Pagination 사이의 간격 조정 */
    text-align: center; /* 가운데 정렬 */
    width: 100%; /* 부모 요소 너비에 맞게 설정 */
    z-index: 10;
}

.swiper-slide {
    color: black;
    display: flex;
    flex-direction: column;
    width: 50%;
    padding: 20px 25px;
    gap: 10px;
    border-radius: 20px;
    border: 2px solid rgb(209, 209, 209);
    background: rgb(255, 255, 255);
}

/* :root {
  --swiper-pagination-color: var(--swiper-theme-color);
  --swiper-pagination-left: auto;
  --swiper-pagination-right: 8px;
  --swiper-pagination-bottom: 8px;
  --swiper-pagination-top: auto;
  --swiper-pagination-fraction-color: inherit;
  --swiper-pagination-progressbar-bg-color: rgba(0, 0, 0, 0.25);
  --swiper-pagination-progressbar-size: 4px;
  --swiper-pagination-bullet-size: 8px;
  --swiper-pagination-bullet-width: 8px;
  --swiper-pagination-bullet-height: 8px;
  --swiper-pagination-bullet-inactive-color: #000;
  --swiper-pagination-bullet-inactive-opacity: 0.2;
  --swiper-pagination-bullet-opacity: 1;
  --swiper-pagination-bullet-horizontal-gap: 4px;
  --swiper-pagination-bullet-vertical-gap: 6px;
} */

</style>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"
/>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<body>
<div class="swiper-container">
  <div class="swiper">
    <!-- Additional required wrapper -->
    <div class="swiper-wrapper">
      <!-- Slides -->
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">Slide 2</div>
      <div class="swiper-slide">Slide 3</div>
      <div class="swiper-slide">Slide 4</div>
      <div class="swiper-slide">Slide 5</div>
      <div class="swiper-slide">Slide 6</div>
      <div class="swiper-slide">Slide 7</div>
      <div class="swiper-slide">Slide 8</div>
    </div>
  </div>
  <div class="swiper-pagination"></div>
</div>
</body>
<script>
  $(document).ready(function(){
      const swiper = new Swiper('.swiper', {
      // Optional parameters
      direction: 'horizontal',
      loop: true,
      speed: 1400,
      slidesPerView: 2,
      spaceBetween: 5,
      slidesPerGroup: 2,

      // If we need pagination
      pagination: {
        el: '.swiper-pagination',
        clickable : true
      },
      autoplay: {
        delay: 2000,
        disableOnInteraction: false,
      }
    

      // Navigation arrows
      /* navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      }, */

      // And if we need scrollbar
      // scrollbar: {
      //   el: '.swiper-scrollbar',
      // },
    });
  });

</script>

</html>

