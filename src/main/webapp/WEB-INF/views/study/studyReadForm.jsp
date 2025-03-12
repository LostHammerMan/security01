<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>
    /* .StudyRegister_wrapper {
        display: flex;
        flex-direction: column;
        width: 100%;
        max-width: 1040px;
        padding: 60px 16px;
        margin: 0 auto;
        color: #333;
        gap: 50px;
        position: relative;
    } */

    .studyContent_wrapper {
    max-width: 900px;
    width: 100%;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    padding: 1.5rem 1.5rem 5rem;
}

.studyContent_postHeader {
    display: flex;
    flex-direction: column;
    margin-top: 3rem;
}

.study_title {
    margin-top: 40px;
    font-weight: 800;
    font-size: 36px;
    line-height: 126.5%;
    letter-spacing: -.005em;
    color: #000;
    overflow-wrap: break-word;
}

.study_userAndDate {
    margin-top: 32px;
    padding-bottom: 32px;
    border-bottom: 3px solid #f2f2f2;
    display: flex;
    grid-gap: 15px;
    gap: 15px;
    align-items: center;
}

.study_user {
    display: flex;
    align-items: center;
    position: relative;
}

.study_userImg {
    cursor: pointer;
    display: block;
    height: 3rem;
    width: 3rem;
    margin-right: 8px;
    border-radius: 50%;
    object-fit: cover;
}

.stuydy_username {
    color: #333;
    cursor: pointer;
    font-size: 18px;
    font-weight: 700;
}

.studyHeader_separator {
    width: 2px;
    height: 20px;
    background-color: #e2e2e2;
}

.study_regDate {
    font-size: 18px;
    color: #717171;
}

.studyInfo_grid {
    display: grid;
    grid-template-columns: repeat(2, 3fr);
    grid-row-gap: 24px;
    row-gap: 24px;
    margin-top: 60px;
}

.studyInfo_contentWrapper {
    display: flex;
    position: relative;
    align-items: center;
    font-weight: 700;
    font-size: 20px;
}

.studyInfo_title {
    color: #717171;
    margin-right: 36px;
}

.studyInfo_content {
    color: #333;
}

.studyInfo_remains {
    display: flex;
    margin-top: 24px;
}

.oneItem {
    flex: 0.64 1;
}

.studyInfo_positions {
    padding: 6px 10px;
    background: #f2f4f8;
    border-radius: 15px;
    font-weight: 700;
    font-size: 14px;
    line-height: 16px;
    text-align: center;
    color: #4a5e75;
}

.studyInfo_positionAndSkill {
    display: flex;
    align-items: center;
    gap: 12px;
}

.studyInfo_skill {
    width: 36px;
    height: 36px;
}

.studyInfo_positions {
    padding: 6px 10px;
    background: #f2f4f8;
    border-radius: 15px;
    font-weight: 700;
    font-size: 14px;
    line-height: 16px;
    text-align: center;
    color: #4a5e75;
}

.StudyContent_postContentWapper {
    margin-top: 132px;
    font-size: 1.125rem;
    line-height: 1.7;
    letter-spacing: -.004em;
}

.studyContent_postInfo {
    margin: 0;
    color: #333;
    font-size: 26px;
    font-weight: 700;
    padding-bottom: 24px;
    border-bottom: 3px solid #f2f2f2;
}

.studyContent_postContent {
    width: 100%;
    margin: 40px auto 0;
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

    .comment_commentInputConatainer {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-top: 100px;
}

.comment_profile {
    display: block;
    width: 44px;
    height: 44px;
    border-radius: 50%;
}

.comment_commentCount {
    margin-bottom: 15px;
    font-size: 18px;
    font-weight: 700;
}

.commentCount {
    font-weight: 700;
    font-size: 18px;
    line-height: 24px;
    color: #939393;
}

.comment_input {
    display: flex;
    gap: 15px;
}

.comment_commentText {
    font-family: inherit;
    padding: 1rem 1rem 1.5rem;
    outline: none;
    border: 2px solid #e1e1e1;
    border-radius: 16px;
    width: 100%;
    min-height: 100px;
    margin-bottom: 10px;
    resize: none;
}

.comment_buttonWrapper {
    display: flex;
    justify-content: flex-end;
    margin: 16px 0 24px;
}

.comment_completeBtn {
    display: flex;
    align-items: center;
    padding: 12px 24px;
    height: 40px;
    background: #333;
    border-radius: 50px;
    font-weight: 700;
    color: #fff;
    font-size: 16px;
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

    .commentItem_content_pre {
    font-size: 20px;
    font-weight: bold;
}

.dropdown-menu {
        border-radius: 0.5rem;
        font-size: 1rem;
        min-width: 6rem;
    }

.modal-content {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
}

.modal-header {
    justify-content: center;
    border-bottom: none;
}

.modal-body {
    text-align: center;
}

.modal_content1 {
    font-size: 22px;
    font-weight: 600;
}

.modal_content2 {
    font-size: 16px;
}

.modal-footer {
    flex-wrap: nowrap;
    text-align: center;
    border-top: none;
}

#commentModifyInput {
    width: 98%;
}

.comment_modifyWrapper {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
}

#comment_modifyCancelBtn {
    background-color: #fff;
    border-width: 1px;

    padding: 12px 24px;
    /*height: 40px;*/
    border-radius: 50px;
    font-weight: 700;
    font-size: 16px;
    cursor: pointer;
    align-items: center;
    align-content: center;
    text-align: center;
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

ul {
    list-style: none;
    margin: 0;
    padding: 0;
}

li {
    list-style: none;
}
</style>
<body>
<div class="studyContent_wrapper">
    <section class="studyContent_postHeader">
        <i class="fa-solid fa-arrow-left fa-lg" style="cursor: pointer;" onclick="history.back(-1)"></i>
        <div class="study_title">${result.title}</div>
        <div class="study_userAndDate">
            <div class="study_user">
                <img class="study_userImg" src="${root}api/profileImages/${result.userProfileImgName}">
                <div class="stuydy_username">${result.username}</div>
            </div>
            <div class="studyHeader_separator"></div>
            <div class="study_regDate">${result.formattedRegDate}</div>
            <c:if test="${loginUsername == result.username}">
                <div class="lounge_modifyBtn justify-content-end" style="margin-left: 63%">
                    <button type="button" class="btn btn-light drop1" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-ellipsis"></i>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <a class="dropdown-item" href="${root}community/lounge/modifyForm/${findLounge.idx}">
                            <i class="fa-regular fa-pen-to-square"></i>
                            수정
                        </a>
                        <a class="dropdown-item" data-toggle="modal" data-target="#loungeDeleteModal">
                            <i class="fa-solid fa-trash-can"></i>
                            삭제
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
        <section class="studyInfo_wrapper">
            <ul class="studyInfo_grid">
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">모집 구분</span>
                    <span class="studyInfo_content">${result.categoryName}</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">모집 인원</span>
                    <span class="studyInfo_content">${result.recruitedNumber}명</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">진행 방식</span>
                    <span class="studyInfo_content">${result.process}</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">예상 기간</span>
                    <span class="studyInfo_content">${result.progressPeriod}</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">모집 마감</span>
                    <span class="studyInfo_content">${result.formattedRecruitDeadline}</span>
                </li>
                <li class="studyInfo_contentWrapper">
                    <span class="studyInfo_title">연락 방법</span>
                    <span class="studyInfo_content">${result.contactAddress}</span>
                </li>
            </ul>
            <div class="studyInfo_remains">
                <li class="studyInfo_contentWrapper oneItem">
                    <span class="studyInfo_title">모집 스킬</span>
                    <ul class="studyInfo_positionAndSkill">
                        <c:forEach var="skillImgName" items="${result.skillTags}">
                            <li class="studyInfo_skills"><img class="studyInfo_skill" src="${root}static/img/study/${skillImgName}"></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="studyInfo_contentWrapper oneItem">
                    <span class="studyInfo_title">모집 분야</span>
                    <ul class="studyInfo_positionAndSkill">
                        <c:forEach var="position" items="${result.recruitPositions}">
                            <li class="studyInfo_positions">${position}</li>
                        </c:forEach>
                    </ul>
                </li>
            </div>
        </section>
    </section>
    <div class = "StudyContent_postContentWapper">
        <h2 class="studyContent_postInfo">프로젝트 소개</h2>
        <div class="studyContent_postContent">
            ${result.contents}
        </div>
    </div>
    <section class="countAndLike">
        <div class="postCount">
            <img src="${root}static/img/eye.svg" alt="eye" style="width: 20px; height: auto"  />
            <span class="post_count" style="color: #6e707e; font-size: 14px; font-weight: 500">${result.viewCount}</span>
        </div>
        <div class="postHeart ml-2">
<%--                <img src="${root}static/img/blackHeart.png"  alt="heart" style="width: 20px; height: auto; fill: #2F96B4"/>--%>
            <c:choose>
                <c:when test="${isLikeCheck == true}">
                    <i class="far fa-heart fas" style="width: 20px; height: auto"></i>
                    <span class="post_heart" style="color: #6e707e; font-size: 14px; font-weight: 500">${result.likeCount}</span>
                </c:when>
                <c:otherwise>
                    <i class="far fa-heart" style="width: 20px; height: auto"></i>
                    <span class="post_heart" id="boardLikeCount" style="color: #6e707e; font-size: 14px; font-weight: 500">${result.likeCount}</span>
                </c:otherwise>
            </c:choose>

        </div>
    </section>
    <div style="padding-bottom: 80px">
        
        <div class="comment_commentInputConatainer">
            <div class="comment_commentCount">
                댓글
                <span class="commentCount">0</span>
            </div>
            <div class="comment_input">
                <img class="comment_profile" src="${root}api/profileImages/${loginUserImgFileName.fileName}">
                <textarea class="comment_commentText" id="comment_commentText" placeholder="댓글을 입력하세요"></textarea>
            </div>
            <div class="comment_buttonWrapper">
                <button class="comment_completeBtn" id="commentCompleteBtn">댓글 등록</button>
            </div>
            <ul class="commentList_list">
            </ul>
        </div>
    </div>
</div>

<%-- 삭제 시 모달 --%>
<div class="container modalContainer">

    <!-- The Modal -->
    <div class="modal fade" id="loungeDeleteModal" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <svg xmlns="http://www.w3.org/2000/svg" width="56" height="auto" viewBox="0 0 448 512"><path d="M170.5 51.6L151.5 80h145l-19-28.4c-1.5-2.2-4-3.6-6.7-3.6H177.1c-2.7 0-5.2 1.3-6.7 3.6zm147-26.6L354.2 80H368h48 8c13.3 0 24 10.7 24 24s-10.7 24-24 24h-8V432c0 44.2-35.8 80-80 80H112c-44.2 0-80-35.8-80-80V128H24c-13.3 0-24-10.7-24-24S10.7 80 24 80h8H80 93.8l36.7-55.1C140.9 9.4 158.4 0 177.1 0h93.7c18.7 0 36.2 9.4 46.6 24.9zM80 128V432c0 17.7 14.3 32 32 32H336c17.7 0 32-14.3 32-32V128H80zm80 64V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16z"
                    style="fill: #FFA500"/></svg>
<%--                    <h4 class="modal-title" style="font-weight: bold">작성 취소</h4>--%>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">
                    <span class="modal_content1">글 삭제</span><br/>
                    <span class="modal_content2">해당 글을 삭제하시겠어요?</span>
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-light" id="deleteCancelBtn" data-dismiss="modal" style="width: 45%">취소</button>
                    <button type="button" class="btn btn-success" id="loungeDeleteBtn" style="width: 45%">삭제</button>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        getCommentList();
        getCommentListCount();

        /* 댓글 시작 */
        // 댓글 등록 이벤트
        $('#commentCompleteBtn').on('click', function(e){
            e.preventDefault();
            let commentInput = $('#comment_commentText').val();

            if(commentInput.trim === ''){
                alert('댓글을 입력해주세요');
                return;
            }

            console.log('commentInput = ' + commentInput);

            let data = {
                'content' : commentInput,
                'boardIdx' : ${result.studyIdx},
                'boardType' : 'STUDY'
            };

            $.ajax({
                url : '${root}api/comment/add',
                method : 'POST',
                data : JSON.stringify(data),
                contentType :'application/json',
                success : function(response){
                    alert("댓글 등록");
                    console.log("댓글 등록 성공");
                    console.log("댓글 내용 :" + commentInput);
                    $('#comment_commentText').val('');
                    getCommentList();
                    getCommentListCount();
                },
                error : function(xhr){
                    alert(error.defaultMessage);
                }
            });
            
        });
        /* 댓글 등록 끝 */

        let beforeIndex = null;
        // 댓글 수정
        $(document).on('click', "#commentModifyBtn", function (e) {

            e.preventDefault();
            console.log("댓글 수정 버튼 클릭");
            let currentIdx = $(this).closest(".commentList_item_container").attr("data-index");

            // 다른 댓글 수정 버튼을 누른 경우
            if (beforeIndex !== null && beforeIndex !== currentIdx){
                console.log("다른 댓글의 수정 버튼 누른경우")
                let $beforeLi = $('.commentList_item_container[data-index="' + beforeIndex + '"]');
                let $beforeTextarea = $beforeLi.find(".comment_modifyWrapper");
                let $beforePre = $beforeLi.find("pre.commentItem_content_pre");

                console.log("beforeIndex = " + beforeIndex);
                console.log("currentIdx = " + currentIdx);


                $beforePre.removeClass("d-none");
                $beforeTextarea.empty();

            }

            let $comment = $(this).closest(".commentList_item_container").find("pre.commentItem_content_pre");
            console.log("$comment.text = " + $comment.text())

            let commentModifyHtml = `
                    <textarea class="commentInput_text ml-4" id="commentModifyInput" name="commentModifyInput"></textarea>
                    <div class="modifyBtnWrapper">
                        <button class="comment_modifyCancelBtn" id="comment_modifyCancelBtn" name="comment_modifyCancelBtn" style="text-align: center">취소</button>
                        <button class="comment_completeButton" id="comment_modifyCompleteBtn" name="comment_modifyCompleteBtn" style="text-align: center">수정</button>
                    </div>
            `;

            let $textarea = $(this).closest(".commentList_item_container").find(".comment_modifyWrapper");

            $comment.addClass("d-none");
            $textarea.html(commentModifyHtml);
            $("#commentModifyInput").val($comment.text());

            beforeIndex = currentIdx;

            });

        // drop-down menu 취소 버튼 클릭 핸들러
        $(document).on("click", "#comment_modifyCancelBtn", function (){
            let $textarea = $(this).closest(".commentList_item_container").find("#commentModifyInput");
            let $commentContent = $(this).closest(".commentList_item_container").find("pre.commentItem_content_pre");
            let $btnWrapper = $(this).closest(".commentList_item_container").find(".modifyBtnWrapper");

            $commentContent.removeClass("d-none");
            $textarea.remove();
            $btnWrapper.remove();
        });

        // 댓글 수정 완료 버튼 핸들러
        $(document).on('click', "#comment_modifyCompleteBtn", function (e){
            console.log("=== 댓글 수정 완료 버튼 클릭... ===");
            let textareaVal = $(this).closest(".commentList_item_container").find("#commentModifyInput").val();
            let $commentContent = $(this).closest(".commentList_item_container").find("pre.commentItem_content_pre");


            let commentIdx = $(this).closest(".commentList_item_container").attr("data-index");


            console.log("textareaVal = " + textareaVal);
            console.log("commentIdx = " + commentIdx);

            // if (textareaVal === null){
            //     textareaVal = $commentContent.val();
            // }

            console.log("textareaVal = " + textareaVal);
            let data = {
                content: textareaVal
            }

            $.ajax({
                url : '${root}api/comment/' + commentIdx,
                type : 'PUT',
                contentType :'application/json',
                data : JSON.stringify(data),
                success : function (){
                    console.log("댓글 수정 전송 성공");
                    getCommentList();
                },
                error : function (xhr, status, error){
                    console.log("댓글 수정 전송 실패;;;;")
                    let errors = xhr.responseJSON;
                    errors.forEach(function (error){
                        alert(error.defaultMessage);
                    });
                }
            });
        });


            // 댓글 삭제 버튼 클릭
        $(document).on('click', "#commentDeleteBtn", function (e){
            console.log("=== 댓글 삭제 버튼 클릭... ===");

            let commentIdx = $(this).closest(".commentList_item_container").attr("data-index")
            console.log("commentIdx = " + commentIdx);
            let result = confirm("댓글을 삭제하시겠습니까?");
            if (result){
                $.ajax({
                    url : '${root}api/comment/' + commentIdx,
                    type : 'DELETE',
                    success : function (response){
                        alert(response.message);
                        getCommentList();
                    },
                    error : function (xhr, status, error){
                        // 서버 에러 처리
                        let response = xhr.responseJSON;
                        alert("에러 발생 : " + response.message);
                    }
                })

            }
        });

        // 댓글 목록 불러오기
        function getCommentList(){
            $.ajax({
                url : '${root}api/getComments/study/${result.studyIdx}',
                type : 'GET',
                success : function (result){
                    console.log("ajax 댓글 불러오기 성공 !!!");
                    let commentsHtml = '';

                    // console.log(result.body)
                    result.forEach(function (comment){
                        commentsHtml += '<li class="commentList_item_container" data-index="' + comment.commentId + '">';
                        commentsHtml += '<section class="commentItem_header">';
                        commentsHtml += '<div class="commentItem_profileWrapper">';
                        commentsHtml += '<img class="commentItem_profileImg" alt="프로필" src="${root}api/profileImages/' + comment.profileFilePath + '"+' + '>';
                        commentsHtml += '<div class="commentItem_profileInfo">';
                        commentsHtml += '<div class="commentItem_username">' + comment.username + '</div>';
                        commentsHtml += '<div class="commentItem_registerDate">';
                        commentsHtml += '<div class="commentItem_registerDate">' + comment.regDate;
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '<div class="commentItem_commentMore">';
                        commentsHtml += '<button type="button" class="btn btn-light drop2" data-toggle="dropdown" aria-expanded="false">';
                        commentsHtml += '<i class="fa-solid fa-ellipsis"></i>';
                        commentsHtml += '</button>';
                        commentsHtml += '<div class="dropdown-menu" aria-labelledby="dropdownMenu2">';
                        commentsHtml += '<button type="button" class="dropdown-item" id="commentModifyBtn">';
                        commentsHtml += '<i class="fa-regular fa-pen-to-square"></i>';
                        commentsHtml += '수정';
                        commentsHtml += '</button>';
                        commentsHtml += '<button type="button" class="dropdown-item" id="commentDeleteBtn">';
                        commentsHtml += '<i class="fa-solid fa-trash-can"></i>';
                        commentsHtml += '삭제';
                        commentsHtml += '</button>';

                        commentsHtml += '</div>';
                        commentsHtml += '</div>';
                        commentsHtml += '</section>';
                        commentsHtml += '<section class="commentItem_content" id="content_section">';
                        commentsHtml += '<pre class="commentItem_content_pre" id="comment_pre_item">'+ comment.content + '</pre>';
                        commentsHtml += '<div class="comment_modifyWrapper">';
                        commentsHtml += '</div>';
                        commentsHtml += '</section>';
                        commentsHtml += '</li>'
                    });
                    $(".commentList_list").html(commentsHtml);
                },
                error : function (xhr, status, error){
                    console.log("ajax 댓글 불러오기 실패 ㅠㅠ")
                    let response = xhr.responseJSON;
                    alert(response.message);
                }
            });
        }

        // 댓글 수 불러오기
        function getCommentListCount(){

            $.ajax({
                url : '${root}api/getCommentsCount/study/${result.studyIdx}',
                type : 'GET',
                success : function (result){
                    console.log("댓글 수 불러오기 성공");
                    console.log("result = " + result)
                    $(".commentCount").text(result);
                },
                error : function (err){
                    console.log("댓글 수 불러오기 실패")
                }
            });
        }

        /*  댓글 끝*/

        /* 좋아요 버튼 클릭 이벤트*/
        const toggleBtn = $(".fa-heart");

        toggleBtn.click(function(){
            toggleBtn.toggleClass('fas');
            let data = {'boardId' : ${result.studyIdx}};

            if(toggleBtn.hasClass('fas')){
                console.log('active');
                $.ajax({
                    url : '${root}api/addLike/study',
                    method : 'POST',
                    data : JSON.stringify(data),
                    contentType : 'application/json',
                    success : function(response){
                        console.log("좋아요 추가 성공");
                        getLikeCount();
                    },
                    error : function(xhr){
                        console.log('좋아요 추가 실패');
                    }

                });
            }else{
                console.log('Inactive');
                $.ajax({
                    url : '${root}api/deleteLike/study',
                    method : 'POST',
                    data : JSON.stringify(data),
                    contentType :'application/json',
                    success : function (response) { 
                        console.log("좋아요 취소 성공");
                        getLikeCount();
                    },
                    error : function (xhr) {
                        console.log("좋아요 취소 실패")
                    }
                });
            }

        });

        // 좋아요 수
        function getLikeCount(){
            $.ajax({
                url : '${root}api/addLike/study/${result.studyIdx}',
                method : 'GET',
                success : function(response){
                    console.log("좋아요 수 불러오기 성공");
                    console.log(response.objectData);
                    $('#boardLikeCount').text(response.objectData);
                },
                error : function(err){
                    console.log(err);
                    
                }
            });
        }

        /* 좋아요 끝 */

        
    
    });



</script>


</html>
