<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<%--main header--%>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<%--community header--%>
<c:import url="/WEB-INF/views/layout/communityHeader.jsp"/>
<style>

    section {
        width: 100%;
    }
    .container_loungeContainer {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        position: relative;
        width: 100%;
        height: 100%;
        gap: 4rem;
        margin-left: auto;
        margin-right: auto;
        font-family: "Nunito", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        box-sizing: border-box;
        min-height: 100%;
    }

    .loungeContainerHeader {
        width: 100%;
        display: flex;
        justify-content: space-between;
        position: static;
        align-items: center;
        gap: 1rem;
        padding: 0 25px;
    }

    /*.container_loungeContainer {*/
    /*    margin: 0;*/
    /*    font-family: "Nunito", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";*/
    /*}*/

    .loungeContainerMain {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        justify-content: initial;
        width: 70%;
        gap: 1.5rem;
    }

    .loungeList_container {
        display: flex;
        grid-gap: 27px;
        gap: 27px;
        flex-wrap: wrap;
        margin:  0 auto;
        padding: 0 20px;
    }

    .loungeList_loungeItem {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        padding: 20px 25px 0;
        width: 300px;
        height: 340px;
        background: #fff;
        border: 2px solid #d1d1d1;
        border-radius: 30px;
        position: relative;
        cursor: pointer;
        transition: .2s ease-in
    }

    .loungeItem_badgeWrapper {
        display: flex;
        align-items: center;
        grid-gap: 3px;
        gap: 3px;
    }

    .badge_categoryWrapper {
        font-weight: 800;
        font-size: 12px;
        line-height: 16px;
        letter-spacing: -.04em;
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
        grid-gap: 8px;
        gap: 8px;
        color: #999;
        font-weight: 500;
        margin-top: 20px;
    }

    .loungeItem_title {
        font-size: 17px;
        min-height: 50px;
        line-height: 28px;
        font-weight: 900;
        /*letter-spacing: -0.5em;*/
        margin: 7px 0 10px;
        word-break: break-all;
        overflow: hidden;
    }

    ul {
        display: block;
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
        unicode-bidi: isolate;
    }
    a {
        color: inherit;
        text-decoration: none;
        text-align: -webkit-match-parent;

    }

    a:hover {
        text-decoration: none !important;
        color: inherit;
    }

    h1 {
        display: block;
        font-size: 2em;
        margin-block-start: 0.67em;
        margin-block-end: 0.67em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        font-weight: bold;
    }

    .loungeItem_boarder {
        border-top: 2px solid #F2F2F2;
        margin-top: 5rem;
    }

    .loungeItem_info {
        display: flex;
        font-size: 14px;
        font-weight: 500;
        justify-content: space-between;
        margin-top: 8px;
        align-items: center;
    }

    .loungeItem_userInfo {
        display: flex;
        grid-gap: 10px;
        gap: 10px;
        align-items: center;
    }

    .userInfo_avatar {
        display: flex;
        align-items: center;
        position: relative;
    }

    .loungeItem_viewsAndComments {
        display: flex;
        grid-gap: 10px;
        gap: 10px;
    }

    .loungeItem_views {
        display: flex;
        align-items: center;
        color: #999;
        grid-gap: 5px;
        gap: 5px;
    }

    li {
        list-style: none;
    }

    p {
        display: block;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
    }


    .loungeContainerAside {
        display: flex;
        width: 25%;
        flex-direction: column;
        align-items: initial;
        justify-content: flex-start;
        flex-shrink: 0;
        gap: 5rem;
    }

    .adImg {
        width: 70%;
        max-height: 400px;
        border-radius: 10px;

    }

    .study_searchContainer {
        display: flex;
        width: 300px;
        height: 38px;
        padding: 0 20px;
        align-items: center;
        grid-gap: 10px;
        gap: 10px;
        border-radius: 36px;
        background: #f5f5f5;
        position: relative;
    }

    .searchInput {
        border: none;
        background: transparent;
        outline: none;
        padding: 0;
        font-weight: 500;
        font-size: 16px;
        width: 100%;
    }

    .category_container {
        display: flex;
        gap: 10px;
        width: 60%;
        margin: 0 25px;
    }

    .selectedItems {
        align-items: center;
        border-radius: 36px;
        box-shadow: none;
        cursor: pointer;
        display: flex;
        flex-wrap: wrap;
        -webkit-box-pack: center;
        justify-content: center;
        min-height: 38px;
        position: relative;
        transition: 100ms;
        box-sizing: border-box;
        width: 203.328px;
        height: 38px;
        background: white;
        color: #656565;
        border: 1px solid rgb(227, 227, 227);
        outline: 0px !important;
        font-size: 16px;
        letter-spacing: 0.03em;
        padding-left: 10px;
    }

    .likeCheckBtn {
        background-color: white;
        border: 1px solid rgb(227, 227, 227);
        border-radius: 36px;
        cursor: pointer;
        letter-spacing: 0.03em;
        font-size: 16px;
        padding-left: 10px;
        width: 140.328px;
        height: 38px;
        background: white;
        color: #656565;
    }

    .toggleOn {
        border: 1px solid rgb(0, 185, 174);
        color: rgb(0, 185, 174);
    }
</style>

<!-- 개발자 라운지 - 자유주제, 커리어 고민-->
<section class="pt-5 pb-5">
    <div class="container_loungeContainer">

        <main class="loungeContainerMain">
            <header class="loungeContainerHeader">
                <h1 style="font-size: 16px; font-weight: bolder;">슽디 커뮤니티에서 당신의 이야기를 나눠보세요!</h1>
                <a class="btn btn-primary ml-auto" href="${root}community/lounge/write">작성하기</a>
            </header>
            <div class="category_container">
                <select class="selectedItems">
                    <option value="RECENT">최신순</option>
                    <option value="VIEW">댓글순</option>
                    <option value="LIKE">좋아요순</option>
                </select>
                <button class="likeCheckBtn">
                    <i class="far fa-heart fas" style="width: 20px; height: auto; color: red"></i>
                            좋아요 보기
                </button>
                <div class="study_searchContainer">
                    <i class="fa-solid fa-magnifying-glass" style="font-size: 12px;"></i>
                    <input class="searchInput" id="searchInput" placeholder="제목, 내용으로 검색해보세요">
                    <i class="fa-solid fa-x seachInputCancelBtn" style="font-size: 10px; cursor: pointer;"></i>
                </div>
            </div>
            <ul class="loungeList_container">
            </ul>

        </main>
           <aside class="loungeContainerAside">
               <div>
                   <img class="adImg" src="${root}static/img/community/01.png" alt="광고1"/>
               </div>
               <div class="mt-lg-5">
                   <img class="adImg" src="${root}static/img/community/42.png" alt="광고1"/>
               </div>
           </aside>

    </div>
</section>



<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script>
    let loungeListUrl = '${root}api/loungeList';
    let likeCheckToggleBtn = $(".likeCheckBtn");
    let $orders = '';
    let lastIdx = null;

    $(document).ready(function (){

        getLoungeList('${root}api/loungeList', $orders);
        
        $('.selectedItems').on('change', function(){
            $orders = $('.selectedItems').val() || "";
            console.log("orders = " + $orders);
            checkLikeToggle();
            // getLoungeList(loungeListUrl, $orders);
        });

        likeCheckToggleBtn.click(function(){
            console.log('좋아요 보기 클릭');
            likeCheckToggleBtn.toggleClass('toggleOn');
            checkLikeToggle();
            /* if(likeCheckToggleBtn.hasClass('toggleOn')){
                console.log('좋아요 토글  on!!');
                getLoungeList(loungeListUrl);

            }else {
                console.log('좋아요 토글 off');
                getLoungeList('${root}api/loungeList/like');
            } */

        });


        // 페이지 하단에 도달했을 때 추가 데이터 로드
        $(window).scroll(function (){
           if ($(window).scrollTop() + $(window).height() >= $(document).height()){
            checkLikeToggle();
            //    getLoungeList(loungeListUrl);
           }
        });
    });

function getLoungeList(loungeListUrls, orders){

// let requestUrl = '${root}api/loungeList';
// null 값만 체크함 -> 빈문자열인 경우도 처리 필요
/* if(loungeListUrl == null){
    loungeListUrl = '${root}api/loungeList';
} */

// if(!loungeListUrl){
//     loungeListUrl = '${root}api/loungeList';
// }
// checkLikeToggle();

if (lastIdx !== null){
    loungeListUrl += '?lastIdx=' + lastIdx;
}

console.log('loungeListUrl =' + loungeListUrls);
$.ajax({
    url: loungeListUrls,
    method: 'GET',
    data: {
        order : $orders
    },
    success: function (result){
        // console.log(result);
        console.log("라운지목록 불러오기 성공")
        result.forEach(function (item){
            let loungeListHtml = '';
            let itemHtml = '';

            itemHtml += `
        <a class="loungeList_loungeItem" href="${root}community/lounge/${'${item.idx}'}">
        <%--<a href="${root}community/lounge/${allLounge.idx}">--%>
            <li>
                <div class="loungeItem_badgeWrapper">
                    <div class="badge_categoryWrapper">
                        <div class="badge_category">
                            ${'${item.categoryName}'}
                        </div>
                    </div>
                </div>
                <div class="loungeItem_regDate">
                    <p class="loungeItem_regDateTitle">등록일 |</p>
                    <p>${'${item.regDate}'}</p>
                </div>
                <h1 class="loungeItem_title">${'${item.title}'}</h1>
                <div class="loungeItem_boarder"></div>
                <section class="loungeItem_info">
                    <div class="loungeItem_userInfo">
                        <div class="userInfo_avatar">
                            <img class="avatar_userImg" width="30px" height="30px" src="${root}api/profileImages/${'${item.profileFilename}'}">
                        </div>
<%--                                    ${root}api/profileImages/${allLounge.user.userProfile.fileName}--%>
                        <div style="font-weight: 800; letter-spacing: -.04em">${'${item.username}'}</div>
                    </div>
                    <div class="loungeItem_viewsAndComments">
                        <div class="loungeItem_views">
                            <i class="fa-regular fa-eye" style="color: #999999;"></i>
                            <p>${'${item.viewCount}'}</p>
                        </div>
                        <div class="loungeItem_views">
                            <i class="far fa-heart fas" style="width: 20px; height: auto; color: red"></i>
                            <p>${'${item.likeCount}'}</p>
                        </div>
                        <div class="loungeItem_views">
                            <i class="fa-regular fa-comment" style="color: #999999;"></i>
                            <p>${'${item.commentCount}'}</p>
                        </div>
                    </div>
                </section>
            </li>
        </a>
    `;

            $('.loungeList_container').append(itemHtml);

            // 마지막 항목의 id 를 lastId 에 저장
            if(result.length > 0){
                lastIdx = result[result.length -1].idx;
            }
        });


    },
    error : function (err){
        console.log("라운지 목록 불러오기 실패");
        $('.likeCheckBtn').removeClass('toggleOn');
        console.log('err = ' + err.responseJSON);
        alert(err.responseJSON.message);
    }
})
}

    function checkLikeToggle(){
        if(likeCheckToggleBtn.hasClass('toggleOn')){
                console.log('좋아요 토글  on!!');
                getLoungeList('${root}api/loungeList/like', $orders);

            }else {
                console.log('좋아요 토글 off');
                getLoungeList('${root}api/loungeList', $orders);
            }
        }
</script>
</html>
