<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"
/>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<style>
    

    a {
        text-decoration: none;
        color: black;
    }

    a:hover {
        color: inherit;
        text-decoration: none;
    }

    .index_container {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        position: relative;
        width: 80%;
        max-width: 1200px;
        height: 100%;
        gap: 1rem;
        margin-left: auto;
        margin-right: auto;
        /* font-family: "Nunito", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji"; */
        box-sizing: border-box;
        min-height: 100%;
    }

    .index_top {
        display: flex;
        width: 100%;
        height: auto;
        gap: 80px;
        justify-content: flex-start;
    }

    .recommend_header {
        display: flex;
        justify-content: space-between;
        width: 100%;
    }
    
    .badge_category {
        border-radius: 20px;
        padding: 4px 12px;
        background: #efefef;
        color: #656565;
    }

    .loungeItem_regDate {
        display: flex;
        font-size: 14px;
        gap: 8px;
        color: #999;
        font-weight: 500;
        margin-top: 20px;
    }


    .swiper-container {
        display: flex;
        flex-direction: column; 
        align-items: center; 
        width: 100% !important;
        height: 350px;
        /* overflow: visible; */
    }

    .swiper-wrapper {
        display: flex;
        width: 100%;
        /* overflow: visible; */
    }

    .swiper {
        display: flex !important;
        width: 100% !important;
        height: 400px;
        position: relative;
        margin-top: 10px;
        /* overflow: visible; */
    }

    .swiper-slide {
        margin-left: auto;
        color: black;
        display: flex;
        flex-direction: column;
        /* width: calc(50% - 15px) !important; */ /* 여기서 문제발생, width 값을 주지 않았을 때 슬라이드 크기 적당함 */
        box-sizing: border-box;
        height: 350px;
        padding: 20px 25px;
        border-radius: 20px;
        border: 2px solid rgb(209, 209, 209);
        background: rgb(255, 255, 255);
    }

    .pagination-container {
        display: flex;
        align-items: center;
        margin-left: 188px;
        gap: 5px;
    }

    .pagination-fraction {
        display: flex;
        width: 36px;
        height: 18px;
        border-radius: 9px;
        background-color: lightgray;
        /* border: 1px solid; */
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

    .pagination-bullets {
        position: relative !important;
        margin-top: 10px; 
        text-align: center; 
        width: 100%; 
        z-index: 10;
    }

    .swiper-pagination-bullet-active {
        background-color: #586672 !important;
    }

    a.topViewPost-item {
        text-decoration-color: black;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .topViewPost_categoryWrapper {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
        margin-top: 10px;
    }

    .badge_endDate {
        padding: 2px 8px;
        border-radius: 20px;
        border: 1px solid rgb(234, 114, 111);
        color: rgb(234, 114, 111);
        font-size: 11px;
        font-style: normal;
        font-weight: 600;
        line-height: normal;
        letter-spacing: -0.56px;
    }
    
    .topViewPost-title {
        margin: 10px 0px 0px;
        color: rgb(0, 0, 0);
        font-size: 18px;
        font-style: normal;
        font-weight: bolder;
        line-height: 140%;
        letter-spacing: -1px;
        min-height: 50px;
        display: -webkit-box;
        word-break: break-all;
        overflow: hidden;
    }

    .topViewPost-contents {
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
        font-size: 13px;
    }

    .topViewPost-viewCount {
        margin-top: 45px;
        color: rgb(78, 78, 78);
        font-size: 14px;
        font-style: normal;
        font-weight: 500;
        line-height: 100%;
        letter-spacing: -0.56px;
        text-align: end;
    }

       

    .newStudy-container {
    /* flex: 1;  전체 너비 1/3 */
    width: 40%;
    min-width: 300px; 
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
    border: 2px solid #ddd; /* 확인용 */
    background-color: #fff;
    }

    .scrap_link {
        display: flex;
        gap: 12px;
        color: inherit;
        height: 100px;
    }

    .scrap_link_lounge {
        display: flex;
        gap: 12px;
        color: inherit;
        height: 85px;
    }

    .bannerWrapper {
        width: 100%;
        height: 500px;
        overflow: hidden;
        position: relative;
    }

    #bannerImg {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .carousel-inner {
        border-radius: 3rem;
        height: 450px;
        object-fit: cover; /* 이미지 크기 비율 유지 */
    }

    .index_bottom {
        display: flex;
        gap: 80px;
        background-color: #F8F9FA;
    }

    .scrap_list {
        width: auto;
        border-radius: 8px;
        display: flex;
        padding: 12px 12px;
        flex-direction: column;
        border: 1px solid #DADCE0;
        padding: 16px 12px;
        gap: 10px;
        background-color: #FFFFFF;

    }

    .scrap_item {
        /* border-bottom: 1px solid #dee2e6; */
        height: 110px;
        overflow: hidden;
    }

    .scrap_item_lounge {
        overflow: hidden;
        display: flex;
        flex-direction: column;
    }

    .scrap_title {
        display: flex;
        /* align-items: center; */
        /* gap: 10px; */
        /* overflow: hidden; */
        /* text-overflow: ellipsis; */
        /* font-family: Pretendard, -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Fira Sans, Droid Sans, Helvetica Neue, sans-serif; */
    }

    .scrap_title h5 {
        margin: 0; /* 불필요한 여백 제거 */
        white-space: nowrap; /* 텍스트를 한 줄로 표시 */
        overflow: hidden; /* 넘치는 텍스트 숨김 */
        text-overflow: ellipsis; /* 넘치는 텍스트는 '...'으로 표시 */
        font-size: 18px;
    }

    .scrap_titleAndContent_title {
        margin: 0; /* 불필요한 여백 제거 */
        white-space: nowrap; /* 텍스트를 한 줄로 표시 */
        overflow: hidden; /* 넘치는 텍스트 숨김 */
        text-overflow: ellipsis; /* 넘치는 텍스트는 '...'으로 표시 */
        font-size: 18px;
    }

    .scrap_content {
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
        font-size: 13px;
    }

    .scrap_site {
        font-size: 1.6rem;
        font-weight: 600;
        margin-bottom: 8px;
        cursor: pointer;
        color: darkgray;
    }

    .site_selected {
        color: black;
    }

    /* .scrapItem_border {
        border-right-width: 0px;
        border-bottom-width: 0px;
        border-left-width: 0px;
        border-top: 1px solid #dee2e6;
    } */

    hr.scrapItem_border {
        border: none; 
        border-top: 1px solid #dee2e6; 
        margin: 1px; 
        height: 0; 
    }

    .lounge_list {
        color: black;
        display: flex;
        flex-direction: column;
        width: 100%;
        padding: 20px 25px;
        gap: 10px;
        border-radius: 20px;
        border: 2px solid rgb(209, 209, 209);
        background: rgb(255, 255, 255);
    }
    

</style>
<link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"
/>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<main>
    <div class="index_container">
<!-- carousel -->
        <div class="carousel_container" style="margin-bottom: 20px;">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="${root}static/ad_img/advertisement4.png" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${root}static/ad_img/advertisement7.png" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${root}static/ad_img/advertisement6.png" alt="Third slide">
                </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <!--  -->
        <div class="index_top">
            <div class="swiper-container">
                    <div class="recommend_header">
                        <span style="font-size: 1.6rem; font-weight: bold; color: black;"> ✨ 당신만을 위한 추천 스터디 ✨</span>
                        <div class="pagination-container">
                            <div class="pagination-bullets"></div>
                            <div class="pagination-fraction"></div>
                         </div> 
                    </div>
                    <!-- swiper -->
                    <!-- <div class="recommend_header"> -->
                         <div class="swiper">
                            <div class="swiper-wrapper">
                                <c:forEach var="item" items="${recommendResults}">
                                    <div class="swiper-slide">
                                        <a class="topViewPost-item" href='${root}study/${item.studyIdx}'>
                                            <div class="topViewPost_categoryWrapper">
                                                <div class="badge_category">
                                                    <i class="fa-solid fa-pencil" style="color: #FFD43B;"></i>
                                                    ${item.categoryName}
                                                </div>
												
												<c:choose>
													<c:when test="${item.diffInDays le 0}">
														<!-- <div class="topViewPost_categoryWrapper"> -->
	                                                        <div class="badge_endDate"> 마감 </div>
	                                                    <!-- </div> -->
													</c:when>
													<c:otherwise>
														<div class="badge_endDate">🚨 마감 ${item.diffInDays} 일전</div>
													</c:otherwise>
												</c:choose>
                                            </div>
                                            <div class="loungeItem_regDate" style="margin-top: 10px;">마감일 | ${item.recruitDeadline}</div>
                                            <h1 class="topViewPost-title">${item.title}</h1>
                                            <span class="topViewPost-contents">${item.contents}</span>
                                            <div class="topViewPost-viewCount">👓 조회수 ${item.viewCount}회</div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    <!-- </div> -->
            </div>

            <!-- new study-->
             <!-- <div class="newStudy-container">
                <div class="newStudy-header">
                    <span>new 스터디/프로젝트</span>
                </div>
             </div> -->
        </div>

          

        <div class="index_bottom">
            <div class="scrap_container" style="width: 50%; margin-left: 30px;">
                <div class="scrap_header">
                    <p class="scrap_site">
                        <span class="site_ex site_selected" data-value="INFLEARN">인프런</span>
                        <span class="site_ex" data-value="SOUP">스프</span>
                    </p>
                </div>
                <div class="scrap_list">
                </div>
            </div>

            <div class="index_loungeContainer" style="width: 40%;">
                <div class="scrap_header">
                    <p class="scrap_site" style="color: black;">고민있어요</p>
                </div>
                <div class="lounge_list">

                </div>

            </div>
        </div>
    </div>
</main>
    
<script type="text/javascript">

    $(document).ready(function(){
        // let $site_value = '';
        let $site_value = 'INFLEARN';
        let lastIdx = null;

        getSCrapStudyList();
        getLoungeList();
        initSwiper();
        
        $('.site_ex').on('click', function(){
            console.log('사이트 클릭');
            $('.site_ex').removeClass('site_selected');
            $(this).addClass('site_selected');

            $('.scrap_list').html("");
            $site_value = '';
            $site_value = $(this).data('value');

            getSCrapStudyList();
        });

        // 페이지 하단 도달시 추가 데이터 로드
        $(window).scroll(function(){
            if($(window).scrollTop() + $(window).height() >= $(document).height()){
                getLoungeList();
            }
        });
        
        /* 크롤링한 사이트 불러오기 */
        function getSCrapStudyList(){
            $.ajax({
                    url: '${root}api/getStudyScrap',
                    method: 'GET',
                    data: {
                        site : $site_value
                    },
                    success: function(result){
                        console.log('사이트 크롤링 결과 불러오기 성공!');

                        // 변수 전역으로 선언되어 있는 경우, 이전 요청 결과 누적됨 주의
                        let itemHtml = '';
                        let grade = 1;
                        for(const [key, items] of Object.entries(result)){
                            items.forEach(function(item, index){
                                let isScrapLastItem = index === items.length -1;
                                itemHtml += `
                                <div class="scrap_item">
                                    <a class="scrap_link" href="\${item.link}">
                                            <div class="scrap_title">
                                                <div class="scrap_badge">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
                                                        <!-- 원 -->
                                                        <circle cx="16" cy="16" r="15" fill="hsl(51, 100%, 90%)" />
                                                        <text x="16" y="18" dominant-baseline="middle" text-anchor="middle" font-size="15" font-weight="600" fill="black">
                                                            \${grade}
                                                        </text>
                                                    </svg>
                                                </div>
                                            </div>
                                            <div class="scrap_titleAndContent">
                                                <div class="scrap_titleAndContent_title">
                                                    <p style="margin: 0; font-weight: bold; font-size:16px;">\${item.title}</p>
                                                </div>
                                                <div class="scrap_titleAndContent_content">
                                                    <p class="scrap_content" style="color: gray;">\${item.content}</p>
                                                </div>
                                            </div>
                                    </a>
                                    `;
                                    
                                    if(!isScrapLastItem){
                                        itemHtml += '<hr class="scrapItem_border">';
                                    }

                                    itemHtml += `</div>`;
                                
                            grade++;
                            });
                        }

                        $('.scrap_list').append(itemHtml);
                    },
                    error: function(xhr){
                        console.log('사이트 크롤링 결과 불러오기 실패!');
                    }
                });
        }
        /* 크롤링한 사이트 끝 */

        /* 라운지 리스트 불러오기 */
        function getLoungeList(){

            $.ajax({
                url: '${root}api/getLoungeListForMain',
                method: 'GET',
                data: {
                    lastIdx : lastIdx
                },
                success: function(result){
                    let loungeHtml = '';
                    let userFirstName = '';
                    console.log('라운지 리스트 불러오기 성공');

                    // result.forEach(function(loungeItem){
                    result.forEach((loungeItem, index) => {
                        // let isLastItem = index === result.length -1;
                        userFirstName = loungeItem.username.substr(0,1);
                        loungeHtml += `
                                <div class="scrap_item_lounge">
                                    <a class="scrap_link_lounge" href="${root}community/lounge/${'${loungeItem.idx}'}">
                                            <div class="scrap_title">
                                                <div class="scrap_badge">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
                                                        <!-- 원 -->
                                                        <circle cx="16" cy="16" r="15" fill="hsl(51, 100%, 90%)" />
                                                        <text x="16" y="18" dominant-baseline="middle" text-anchor="middle" font-size="15" font-weight="600" fill="black">
                                                            ${'${userFirstName}'}
                                                        </text>
                                                    </svg>
                                                </div>
                                            </div>
                                            <div class="titleAndContent">
                                                <div class="lounge_title">
                                                    <p style="margin: 0; font-weight: bold; font-size: 14px;">${'${loungeItem.title}'}</p>
                                                </div>
                                                <div class="lounge_content">
                                                    <span class="scrap_content" style="color: gray;">${'${loungeItem.content}'}</p>
                                                </div>
                                            </div>
                                            </a>
                                            <hr class="scrapItem_border">
                                </div>
                                    `;

                    });
                    lastIdx = result[result.length -1].idx;
                    $('.lounge_list').append(loungeHtml);
                },
                error: function(xhr){
                    console.log('라운지 리스트 불러오기 실패');
                }

            });
        }

        /* 라운지 리스트 끝 */

        /* 추천 스터디 목록 시작 */
        /* function getRecommedStudyList(){
            $.ajax({
                url: '${root}api/study/recommend',
                method: 'GET',
                success: function(result){
                    console.log('추천 목록 불러오기 성공');
                    let recommendHtml = '';

                    result.forEach(function(item){
                        recommendHtml += `
                        <div class="swiper-slide">${'${item.title}'}</div>

                        `;
                    });
                    $('.swiper-slide').html(recommendHtml);
                    setTimeout(initSwiper, 200);
                },
                error: function (xhr) { 
                    console.log('추천 목록 불러오기 실패');
                 }
            });
        } */

        /* 추천 스터디 목록  끝 */
        function initSwiper(){

            /* swiper 시작 */ 
            const swiper = new Swiper(".swiper", {
            direction: 'horizontal',
            loop: true,
            speed: 1400,
            slidesPerView: 4,
            spaceBetween: 15,
            slidesPerGroup: 4,
            centeredSlides: false,
            pagination: {
                el: '.pagination-bullets',
                type: 'bullets',
                clickable : true
            },
            autoplay: {
                delay: 2000,
                disableOnInteraction: true,
            },

             });

              // 화면 크기 변경시 swiper 업데이트
         swiper.update();


             // 스와이퍼 이벤트 핸들러
        swiper.on('slideChange', function(){
            let badgeHtml = '';
            const current = (swiper.realIndex /4) +1;
            const total = swiper.slides.length -12;
            const $badgeElement = $('.pagination-fraction');
            badgeHtml = `
                <div class="pagination-fraction">${'${current}'} / ${'${total}'}</div>
            `;
            $badgeElement.html(badgeHtml);

        });

            swiper.emit('slideChange');
        }
        
    });

</script>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>