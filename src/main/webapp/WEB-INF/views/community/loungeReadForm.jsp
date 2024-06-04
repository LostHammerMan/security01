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

    .comment_completeButton {
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

    .countAndLike {
        margin-top: 20px;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 20px;
    }

    .postCount {
        display: flex;
        align-items: center;
        gap: 5px;
    }

    .postHeart {
        display: flex;
        align-items: center;
        gap: 5px;
        /*cursor: pointer;*/
    }

    .fa-heart:hover {
        cursor: pointer;
    }

    .fa-heart {
        color: var(--gray);
    }

    .fas.fa-heart {
        color: var(--red);
    }

    .commentList_list {
        width: 100%;
        display: block;
        padding-inline-start: 40px;
        padding-inline-end: 0px;
        margin-block-start: 1em;
        margin-block-end: 1em;
    }

    .commentList_item_container {
        display: flex;
        flex-direction: column;
        padding-top: 1.5rem;
        padding-bottom: 1.5rem;
        border-bottom: 2px solid #e1e1e1;
    }

    .commentItem_header {
        display: flex;
        justify-content: space-between;
    }

    .commentItem_content {
        padding-bottom: 30px;
        font-size: 1rem;
        display: block;
    }

    .commentItem_profileWrapper {
        margin-bottom: 8px;
        display: flex;
        align-items: center;
        cursor: pointer;
    }

    .commentItem_profileImg {
        display: block;
        width: 52px;
        height: 52px;
        margin-right: 16px;
        border-radius: 50%;
        object-fit: cover;
    }

    .commentItem_profileInfo {
        display: flex;
        flex-direction: column;
    }

    .commentItem_username {
        color: #333;
        font-weight: 700;
        box-sizing: inherit;
        font-family: D2Coding, serif;
    }

    .commentItem_registerDate {
        font-size: 14px;
        line-height: 126.5%;
        letter-spacing: -.005rem;
        color: #9f9f9f;
    }

    .commentItem_content {
        padding-bottom: 30px;
        font-size: 1rem;
    }



</style>
<div class="loungeContent_wrapper">
    <section class="postHeader flex-column">
        <img src="${root}static/img/arrow-left.svg" alt="arrowLeft" style="color: #6e707e; width: 30px; height: 30px
           ; cursor: pointer" onclick="history.back(-1)">
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
            <div class="lounge_modifyBtn justify-content-end" style="margin-left: 63%">
                    <button type="submit" class="btn btn-primary">수정</button>
            </div>
        </div>

    </section>
    <div class="lounge_postContentWrapper">
        <div class="loungeContent_postContent">
            ${findLounge.content}
        </div>
        <section class="countAndLike">
            <div class="postCount">
                <img src="${root}static/img/eye.svg" alt="eye" style="width: 20px; height: auto"  />
                <span class="post_count" style="color: #6e707e; font-size: 14px; font-weight: 500">${findLounge.viewCount}</span>
            </div>
            <div class="postHeart ml-2">
<%--                <img src="${root}static/img/blackHeart.png"  alt="heart" style="width: 20px; height: auto; fill: #2F96B4"/>--%>
                <c:choose>
                    <c:when test="${isLikeCheck == true}">
                        <i class="far fa-heart fas" style="width: 20px; height: auto"></i>
                        <span class="post_heart" style="color: #6e707e; font-size: 14px; font-weight: 500">${findLounge.likeCount}</span>
                    </c:when>
                    <c:otherwise>
                        <i class="far fa-heart" style="width: 20px; height: auto"></i>
                        <span class="post_heart" style="color: #6e707e; font-size: 14px; font-weight: 500">${findLounge.likeCount}</span>
                    </c:otherwise>
                </c:choose>

            </div>
        </section>
    </div>

    <div class="mt-lg-5" style="padding-bottom: 80px">
        <div class="commentInput_input d-flex">
            <div class="comment_label mr-1">댓글</div>
            <span class="comment_count">0</span>
        </div>
        <div class="comment_inputContainer d-flex">
            <img class="comment_inputProfile" src="${root}api/profileImages/${findLounge.user.userProfile.fileName}"  alt="profile"/>
            <label for="commentInput"></label>
            <textarea class="commentInput_text ml-4" id="commentInput" name="commentInput" placeholder="댓글 입력하세요"></textarea>
        </div>
        <div class="comment_buttonWrapper">
            <button class="comment_completeButton" id="comment_completeButton" name="comment_completeButton"
            style="text-align: center">댓글 등록</button>
        </div>
        <ul class="commentList_list">
<%--            <c:forEach var="comments" items="${comments}">--%>
                <li class="commentList_item_container">
<%--                    <section class="commentItem_header">--%>
<%--                        <div class="commentItem_profileWrapper">--%>
<%--                            <img class="commentItem_profileImg" alt="프로필" src="&lt;%&ndash;${root}api/profileImages/${comments.profileFilePath}&ndash;%&gt;">--%>

<%--                            <div class="commentItem_profileInfo">--%>
<%--                                <div class="commentItem_username">&lt;%&ndash;${comments.username}&ndash;%&gt;</div>--%>
<%--&lt;%&ndash;                                <div class="commentItem_registerDate">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <fmt:parseDate value="${comments.regDate}"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </section>--%>
<%--                    <section class="commentItem_content">--%>
<%--                        <p class="commentItem_content">&lt;%&ndash;${comments.content}&ndash;%&gt;</p>--%>
<%--                    </section>--%>
                </li>
<%--            </c:forEach>--%>

        </ul>
    </div>
</div>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script>
    // // const togglingBtn = $(".fa-heart");
    // const togglingBtn = document.querySelector('.fa-heart');
    //
    // togglingBtn.addEventListener('click', function (){
    //    togglingBtn.classList.toggle('fas');
    //
    //     const element = $('#')
    // });

    $(document).ready(function (){
        getCommentList();


        // 좋아요 버튼 이벤트
        const toggleBtn = $(".fa-heart");
        toggleBtn.click(function (){
            toggleBtn.toggleClass('fas');
            let data = {'boardId' : ${findLounge.idx}};

            if (toggleBtn.hasClass('fas')){

                console.log("active")
                $.ajax({
                    url : '${root}api/addLike',
                    method : 'POST',
                    data : JSON.stringify(data),
                    contentType :'application/json',

                    success : function (response){
                        console.log("좋아요 추가 성공");
                        console.log(${findLounge.idx});
                        $.ajax({
                            url : '${root}api/addLike/${findLounge.idx}',
                            type : 'GET',
                            <%--data : '${findLounge.idx}',--%>

                            success : function (response){
                                console.log("좋아요 수 불러오기 성공");
                                console.log(response.objectData);
                                // $(".post_heart").attr("text", response.objectData)
                                $(".post_heart").text(response.objectData)

                            },
                            error : function (err){
                                console.log(err);
                            }
                        });

                    },
                    error : function (err){
                        console.log("좋아요 추가 실패")
                    }
                })
            }else {
                console.log("inactive")
                $.ajax({
                    url : '${root}api/deleteLike',
                    method : 'POST',
                    data : JSON.stringify(data),
                    contentType :'application/json',

                    success : function (response){
                        console.log("좋아요 취소 성공");

                        $.ajax({
                            url : '${root}api/addLike/${findLounge.idx}',
                            type : 'GET',
                            <%--data : '${findLounge.idx}',--%>

                            success : function (response){
                                console.log("좋아요 수 불러오기 성공");
                                console.log(response.objectData);
                                // $(".post_heart").attr("text", response.objectData)
                                $(".post_heart").text(response.objectData)

                            },
                            error : function (err){
                                console.log(err);
                            }
                        });
                    },
                    error : function (err){

                        console.log("좋아요 취소 실패")
                    }
                })
            }
        });

        // ======== 좋아요 버튼 끝 ============

        // == 댓글 등록 시작 ===
        $(document).on('click', "#comment_completeButton", function (e){
            e.preventDefault();
            e.stopImmediatePropagation();
            let commentInput = $("#commentInput").val();

            if (commentInput.trim() === ''){
                alert("댓글 입력하세요");
                return;
            }

            console.log("댓글 등록 클릭");
            let data = {
                "content" : commentInput,
                "boardIdx" : ${findLounge.idx}
            };

            console.log("boardIdx : " + ${findLounge.idx})
            console.log("content = " + data.content);

            $.ajax({
                url : '${root}api/addComment',
                method : 'POST',
                data : JSON.stringify(data),
                contentType :'application/json',
                success : function (response){
                    alert("댓글 등록");
                    console.log("댓글 등록 성공");
                    console.log("댓글 내용 :" + commentInput);
                    $("#commentInput").val('');
                    // 댓글 입력 성공 후 댓글 불러오기
                    getCommentList();
                },
                error : function (err){
                    console.log("댓글 등록 실패");
                }
            })
        });


     // 댓글 불러오기
     function getCommentList(){
            $.ajax({
                url : '${root}api/getComments/${findLounge.idx}',
                type : 'GET',
                success : function (result){
                    console.log("ajax 댓글 불러오기 성공 !!!");
                    let commentsHtml = '';

                    // console.log(result.body)
                    result.forEach(function (comment){

                        commentsHtml += '<li class="commentList_item_container">';
                        commentsHtml += '<section class="commentItem_header">';
                        commentsHtml += '<div class="commentItem_profileWrapper">';
                        commentsHtml += '<img class="commentItem_profileImg" alt="프로필" src="${root}api/profileImages/' + comment.profileFilePath + '"+' + '>';
                        commentsHtml += '<div class="commentItem_profileInfo">';
                        commentsHtml += '<div class="commentItem_username">' + comment.username + '</div>';
                        commentsHtml += '<div class="commentItem_registerDate">';
                        <%--commentsHtml += '<fmt:parseDate value=' +'"'+ comment.regDate+ '"'+ pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />';--%>
                        <%--commentsHtml += '<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm" />';--%>
                        commentsHtml += '<div class="commentItem_registerDate">' + comment.regDate;
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</section>';
                        commentsHtml += '<section class="commentItem_content">';
                        commentsHtml += '<p class="commentItem_content">'+ comment.content + '</p>';
                        commentsHtml += '</section>';
                        commentsHtml += '</li>'
                    });
                    $(".commentList_item_container").html(commentsHtml);



                },
                error : function (err){
                    console.log("ajax 댓글 불러오기 실패 ㅠㅠ")
                }
            });

     }

    });

    // 좋아요 버튼

</script>
</html>