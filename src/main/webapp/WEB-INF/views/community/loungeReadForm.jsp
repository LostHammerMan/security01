<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<c:import url="/WEB-INF/views/layout/communityHeader.jsp"/>
<style>

    .wrapper {
        gap: 2.5rem;
        width: 100%;
        margin-left: 5%;
    }

    #container {
        width: 1500px;
    }

    .loungeContent_wrapper {
        max-width: 900px;
        width: 100%;
        display: flex;
        flex-direction: column;
        margin: 0 auto;
        padding: 1.5rem 1.5rem 5rem;
    }

    .postHeader {
        max-width: 900px;
        display: flex;
    }

    .loungeContent_title {
        margin-top: 40px;
        font-weight: 800;
        font-size: 36px;
        font-family: D2Coding, serif;
    }

    .lounge_userAndDate {
        margin-top: 32px;
        padding-bottom: 32px;
        border-bottom: 3px solid #F2F2F2;
        display: flex;
        align-items: center;
        gap: 15px;
    }

    .lounge_separator {
        width: 2px;
        height: 20px;
        background-color: #6e707e;
    }

    .lounge_user {
        display: flex;
        align-items: center;
        position: relative;
        font-size: 18px;
        font-weight: 700;
        font-family: inherit;
    }

    .lounge_userImg {
        display: block;
        height: 3rem;
        width: 3rem;
        margin-right: 8px;
        border-radius: 50%;
    }

    .loungeContent_wrapper {
        margin-top: 132px;
        font-size: 1.125rem;
        letter-spacing: -.004em;
        font-family: inherit;
    }

    .loungeContent_postContent {
        width: 100%;
        margin: 40px auto 0;
        font-family: inherit;
    }

    .comment_label {
        margin-bottom: 15px;
        font-size: 18px;
        font-weight: 700;
        font-family: inherit;
    }

    .comment_count {
        font-weight: 700;
        font-size: 18px;
        color: #939393;
    }

    .comment_inputProfile {
        width: 44px;
        height: 44px;
        border-radius: 50%;
    }

    .commentInput_text {
        font-family: inherit;
        padding: 1rem 1rem 1.5rem;
        border: 2px solid #e1e1e1;
        border-radius: 16px;
        width: 100%;
        min-height: 10px;
        margin-bottom: 10px;
        resize: none;
        font-size: 16px;
    }

    .comment_buttonWrapper {
        display: flex;
        justify-content: flex-end;
        margin: 16px 0 24px;
    }

    .comment_buttonComplete {
        padding: 12px 24px;
        /*height: 40px;*/
        background: #333;
        border-radius: 50px;
        font-weight: 700;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        align-items: center;
        align-content: center;
        text-align: center;
    }



</style>
<div class="loungeContent_wrapper">
    <section class="postHeader flex-column">
        <svg cursor="pointer" color=></svg>
        <div class="loungeContent_title">
            ${findLounge.title}
        </div>
        <div class="lounge_userAndDate">
            <div class="lounge_user">
                <img class="lounge_userImg" src="${root}api/profileImages/${findLounge.user.userProfile.fileName}">
                <div class="lounge_user">${findLounge.user.username}</div>
            </div>
            <div class="lounge_separator"></div>
            <div class="lounge_registerDate" style="font-size: 18px; color: #717171">
                <fmt:parseDate value="${findLounge.regDate }"
                               pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd" />
<%--                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />--%>
            </div>
            <div class="lounge_modifyBtn" style="margin-left: 60%">
                    <button type="submit" class="btn btn-primary">수정</button>
            </div>
        </div>

    </section>
    <div class="lounge_postContentWrapper">
        <div class="loungeContent_postContent">
            ${findLounge.content}
        </div>
    </div>

    <div class="mt-lg-5" style="padding-bottom: 80px">
        <div class="commentInput_input d-flex">
            <div class="comment_label mr-1">댓글</div>
            <span class="comment_count">0</span>
        </div>
        <div class="comment_inputContainer d-flex">
            <img class="comment_inputProfile" src="${root}static/img/community/artist_love_you.png"  alt="profile"/>
            <textarea class="commentInput_text ml-4" placeholder="댓글 입력하세요"></textarea>
        </div>
        <div class="comment_buttonWrapper">
            <button class="comment_buttonComplete" id="register" name="register"
            style="text-align: center">댓글 등록</button>
        </div>
    </div>
</div>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>