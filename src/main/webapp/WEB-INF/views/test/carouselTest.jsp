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
    width: 400px;
    height: 350px;
}

.swiper {
    width: 300px;
    height: 400px;
    position: relative;
    margin-top: 10px;
    overflow: visible;
}

.pagination-container {
  display: flex;
  align-items: center;
  margin-left: 188px;
}

.pagination-badge {
    display: flex;
    width: 36px;
    height: 18px;
    border-radius: 9px;
    background-color: lightgray;
    border: 1px solid;
    color: black;
    font-size: 10px;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    line-height: initial;
}

.swiper-pagination {
    position: relative !important;
    margin-top: 10px; /* 슬라이드와 Pagination 사이의 간격 조정 */
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
  <div class="pagination-container">
    <div class="swiper-pagination"></div>
    <div class="pagination-badge"></div>
  </div>

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
        type: 'bullets',
        
        clickable : true
      },
      autoplay: {
        delay: 2000,
        disableOnInteraction: false,
      },
    
    });

    // 스와이퍼 이벤트 핸들러 사용
    swiper.on('slideChange', function(){
      let badgeHtml = '';
      // const current = swiper.realIndex + 1;
      const current = (swiper.realIndex / 2) +1;
      const total = swiper.slides.length -4;
      const badgeElement = $('.pagination-badge');
      badgeHtml = `<div class="pagination-badge">${'${current}'} / ${'${total}'}</div>`;

      badgeElement.html(badgeHtml);
    });
    swiper.emit('slideChange');

    // const pagingBadge = new Swiper(".swiper", {
    //   pagination : {
    //     el: ".pagination-badge",
    //     type: "custom",
    //     renderCustom(pagingBadge, current, total){
    //       console.log(current + '/' + total);
    //       return `<div class="fraction">${'${current}'} / ${'${total}'}</div>`;
    //     }
    //   },

    // });

    // swiper.controller.control = pagingBadge;

    // const pagingBullet = new Swiper(".swiper", {
    //   pagination: {
    //     el: ".swiper-pagination"
    //     renderCustom: function(swiper, current, total){
    //       console.log("current = " + current);
    //       console.log("total = " + total);

    //       // Array.from(arrayLike, mapFn, thisArg).join(''): 유사 배열, 반복 가능한 객체를 배열로 변환
    //       // - arrayLike : 유사배열, 반복가능객체
    //       // - mapFn : 배열 생성하면서 각 요소 변경 위한 매핑 함수
    //       // - thisArg : mapFn 에서 사용할 this 값
    //       // - join(''): separator가 빈문자인 경우 배열 요소를 구분자 없이 연결

    //       // ( _, i ) : 
    //       // - '_' : 배열요소의 값 전달됨, 값에 관심 없음을 나타내기 위해 사용하는 관용적 이름
    //       // - 'i' : 현재 배열의 인덱스
    //       return `
    //         <div class="fraction">${'${current}'} / ${'${total}'}</div>
    //       `;
    //     },
    //   },
    // });

    // swiper.controller.control = pagingBullet;
  });

</script>

</html>

