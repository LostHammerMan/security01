<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>

    a {
        text-decoration: none;
    }

    a:hover {
        color: inherit;
        text-decoration: none;
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

    .index_container {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        position: relative;
        width: 88%;
        height: 100%;
        gap: 1rem;
        margin-left: auto;
        margin-right: auto;
        font-family: "Nunito", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        box-sizing: border-box;
        min-height: 100%;
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
    border: none; /* 기본 브라우저 스타일 초기화 */
    border-top: 1px solid #dee2e6; /* 보이도록 명시적으로 설정 */
    margin: 1px; /* 여백 추가 */
    height: 0; /* 높이 초기화 */
}

    .lounge_list {
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

</style>
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
        <div class="index_top">

        </div>

        <div class="index_bottom">
            <div class="scrap_container" style="width: 50%;">
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
                                    <a class="scrap_link">
                                            <div class="scrap_title">
                                                <div class="scrap_badge">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
                                                        <!-- 원 -->
                                                        <circle cx="16" cy="16" r="15" fill="hsl(51, 100%, 90%)" />
                                                        <text x="16" y="18" dominant-baseline="middle" text-anchor="middle" font-size="15" font-weight="600" fill="black">
                                                            ${'${grade}'}
                                                        </text>
                                                    </svg>
                                                </div>
                                            </div>
                                            <div class="scrap_titleAndContent">
                                                <div class="scrap_titleAndContent_title">
                                                    <p style="margin: 0; font-weight: bold; font-size:16px;">${'${item.title}'}</p>
                                                </div>
                                                <div class="scrap_titleAndContent_content">
                                                    <p class="scrap_content" style="color: gray;">${'${item.content}'}</p>
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


    });

</script>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>