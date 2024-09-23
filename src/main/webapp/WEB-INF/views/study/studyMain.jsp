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
        width: 85%;
    }
    .container_loungeContainer {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        position: relative;
        width: 100%;
        height: 100%;
        gap: 1rem;
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
        width: auto;
        max-width: 300px;
        /* height: 340px; */
        height: auto;
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
        margin-right: 5px;
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
        width: 17%;
        flex-direction: column;
        align-items: initial;
        justify-content: flex-start;
        flex-shrink: 0;
        gap: 5rem;
        margin-right: 50px;
    }

    .adImg {
        width: 100%;
        max-height: 400px;
        border-radius: 10px;

    }

    .card_bottom {

    }

</style>

<!-- 개발자 라운지 - 자유주제, 커리어 고민-->
<section class="pt-5 pb-5" style="width: 100%;">
    <div class="container_loungeContainer">

        <main class="loungeContainerMain">
            <header class="loungeContainerHeader">
                <h1 style="font-size: 16px; font-weight: bolder;">찾고 있는 스터디/프로젝트 있으신가요?</h1>
                <a class="btn btn-primary ml-auto" href="${root}study/getStudyWriteForm">함께 해요</a>
            </header>
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
    $(document).ready(function (){
		getStudyList();
        
    });

    function getStudyList(){  
            $.ajax({
                url: '${root}api/study/studyList',
                method: 'GET',
                success: function(result){
                    result.forEach(function(item){
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
                            <ul class="loungeItem_positions">
                                ${'${item.recruitPositions.map(position => `<li>123</li>`)}'}
                                <%--<li>${'${item.recruitPositions.map(position => <li>123</li>)}'}</li>-->
                            </ul>
                            <ul class="loungeItem_skill">
                                <li>${'${item.skillTags}'}</li>
                            </ul> 
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
                    });
                   
                },
                error: function(err){
                    console.log('스터디 목록 불러오기 실패');
                }

            });
        }
</script>
</html>
