<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<%--main header--%>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<%--community header--%>
<c:import url="/WEB-INF/views/layout/studyHeader.jsp"/>
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
        width: 97%;
        gap: 1.5rem;
    }

    .loungeList_container {
        display: flex;
        grid-gap: 27px;
        gap: 27px;
        flex-wrap: wrap;
        padding: 0 20px;
    }

    .loungeList_loungeItem {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        padding: 20px 25px 0;
        width: 300px;
        /* max-width: 300px; */
        /* height: 340px; */
        height: 340px;
        background: #fff;
        border: 2px solid #d1d1d1;
        border-radius: 30px;
        position: relative;
        cursor: pointer;
        transition: .2s ease-in
    }

    .loungeItem_list {
        display: flex;
        flex-direction: column;
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
        gap: 8px;
        color: #999;
        font-weight: 500;
        margin-top: 20px;
    }

    .loungeItem_title {
        font-size: 17px;
        /* min-height: 50px; */
        /* line-height: 28px; */
        font-weight: 900;
        /*letter-spacing: -0.5em;*/
        /* margin: 7px 0 10px; */
        word-break: break-all;
        overflow: hidden;
    }

    .loungeItem_positions {
        display: flex;
        height: 22px;
        margin-bottom: 7px;
        /* grid-gap: 3px; */
        gap: 3px;
        padding: 0px;
    }

    .skillImg {
        width: 32px;
        height: 32px;
    }

    .loungeItem_boarder {
        border-top: 2px solid #F2F2F2;
        margin-top: 0.5rem;
    }

    .loungeItem_info {
        display: flex;
        font-size: 14px;
        font-weight: 500;
        justify-content: space-between;
        margin-top: 8px;
        align-items: center;
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

    .loungeItem_tag {
        padding: 3px 10px;
        grid-gap: 5px;
        gap: 5px;
        height: 22px;
        background: #f2f4f8;
        border-radius: 15px;
        font-weight: 700;
        font-size: 13px;
        line-height: 16px;
        color: #3e86f5;
    }

    .loungeItem_skill {
        display: flex;
        gap: 5px;
        padding: 0px;
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

    .study_paginationWrapper {
        display: flex;
        justify-content: center;
        margin: 32px 0;
        width: 97%;
    }

    .pagination {
        display: flex;
        gap: 5px;
    }

    .pagination {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
    }

    .pagination-btn:active {
        background-color: #4caf50;
        color: white;
    }

    .pagination-btn:hover:not(.active){
        background-color: #ddd;
    }

    .pagination-btn {
        border: 0;
        background-color: transparent;
    }

    .studyCategoryContainer {
        max-width: 1300px;
        width: 100%;
        display: flex;
        flex-direction: column;
        gap: 30px;
        position: relative;
        margin-right: auto;
        padding: 0px 20px;
    }

    .css-1 {
        display: flex;
        -webkit-box-pack: justify;
        justify-content: space-between;
        -webkit-box-align: center;
    }

    .category_containerr {
        display: flex;
        gap: 10px;
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

    .category_container {
        display: flex;
        gap: 10px;
        width: 50%;
    }

    .selectedItems {
    -webkit-box-align: center;
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

    .select-container {
    width: 65%;
    }

    .select2-container--default .select2-selection--multiple {
    background-color: white;
    border: 1px solid rgb(227, 227, 227);
    border-radius: 36px;
    cursor: pointer;
    letter-spacing: 0.03em;
    font-size: 16px;
    padding-left: 10px;
    }

    .availableItem-list {
    display: none;
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

</style>

<!-- 개발자 라운지 - 자유주제, 커리어 고민-->
<section class="pt-5 pb-5" style="width: 100%;">
    <div class="container_loungeContainer">

        <main class="loungeContainerMain">
            <header class="loungeContainerHeader">
                <h1 style="font-size: 16px; font-weight: bolder;">찾고 있는 스터디/프로젝트 있으신가요?</h1>
                <a class="btn btn-primary ml-auto" href="${root}study/getStudyWriteForm">함께 해요</a>
            </header>
            <div class="studyCategoryContainer">
                <div class="css-1">
                    <div class="category_container">
                        <div class="input-group mb-3 select-container">
                            <select class="js-example-placeholder-multiple js-states form-control selectedItems" id="selected-skill" multiple="multiple">
                                <!-- <option disabled hidden selected>기술 스택</option> -->
                                <option value="1">Spring</div>
                                <option value="2">Python</div>
                                <option value="3">AWS</div>
                            </select>
                        </div>
                        <div class="input-group mb-3 select-container">
                            <select class="js-example-placeholder-multiple js-states form-control selectedItems" id="selected-recruit" multiple="multiple">
                                <!-- <option disabled hidden selected>포지션</option> -->
                                <option value="1">백엔드</div>
                                <option value="2">프론트엔드</div>
                                <option value="3">Manager</div>
                                <option value="4">웹디자이너</div>
                            </select>
                        </div>
                        <div class="input-group mb-3 select-container">
                            <select class="selectedItems" id="process" aria-placeholder="진행 방법">
                                <option value="온라인">온라인</option>
                                <option value="오프라인">오프라인</option>
                                <option value="">온라인/오프라인</option>
                            </select>
                        </div>
                    </div>
                    <div class="study_searchContainer">
                        <i class="fa-solid fa-magnifying-glass" style="font-size: 12px;"></i>
                        <input class="searchInput" placeholder="제목, 내용으로 검색해보세요">
                        <i class="fa-solid fa-x seachInputCancelBtn" style="font-size: 10px; cursor: pointer;"></i>
                    </div>
                </div>
            </div>
            <ul class="loungeList_container">
            </ul>
            <div class="study_paginationWrapper">
                <div class="pagination">
                    
                </div>
        </div>
        </main>
        <!-- <aside class="loungeContainerAside">
            <div>
                <img class="adImg" src="${root}static/img/community/01.png" alt="광고1"/>
            </div>
            <div class="mt-lg-5">
                <img class="adImg" src="${root}static/img/community/42.png" alt="광고1"/>
            </div>
        </aside> -->
        

    </div>
</section>



<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script>

    let selectedCategoryIdx = null;
    let selectedProcess = null;
    let selectedSkillArr = [];
    let selectedRecruitArr = [];

    
    $(document).ready(function (){
		getStudyList();

        // 기술 스택 시작
        $("#selected-skill").select2({
            placeholder: "기술 스택"
        });

        $("#selected-skill").on("change", function(e){
            selectedSkillArr = $("#selected-skill").val();
            console.log(selectedSkillArr);
            getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess), e
        });
        
        // $("#selected-skill").on("select2:select", function(e){
        //     selectedSkillArr = $("#selected-skill").val();
        //     console.log(selectedSkillArr);

        //     getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr), e
        // });

        // $("#selected-skill").on("select2:unselecting", function(e){
        //     selectedSkillArr = [];
        //     console.log(selectedSkillArr);

        //     getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr), e
        // });
        // 기술 스택 끝

        // 모집 분야 시작
        $("#selected-recruit").select2({
            placeholder: "모집 분야",
        });

        $("#selected-recruit").on("change", function(e){

        // || 연산자: 왼쪽 값이 null, undefined, 0, false, '' (빈 문자열) 같은 "false" 값일 때, 오른쪽 값을 대신 반환

        selectedRecruitArr = $("#selected-recruit").val() || [];
        console.log(selectedRecruitArr);
        getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess), e
        });

        // 모집 분야 끝

        /* process 시작 */
        $("#process").on("change", function(e){
            selectedProcess = $("#process").val() || "";

            console.log(selectedProcess);
            getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess)
        });

        /* process 끝 */
        
        /* 검색 시작 */
        // 검색 취소 버튼
        $(document).on('click', '.seachInputCancelBtn', function(e){
            console.log('검색 취소 버튼');
            $('.searchInput').val("");
            getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess)
        });


        /* 검색 끝 */
    });

    function getStudyList(pageNum=0, pageSize=12, categoryNum = selectedCategoryIdx, process, selectedSkillArr, selectedRecruitArr, selectedProcess, keywords){  
            $.ajax({
                url: '${root}api/study/studyList',
                method: 'GET',
                data : {page : pageNum,
                        size : pageSize,
                        categoryIdx : selectedCategoryIdx,
                        skillIdx : selectedSkillArr,
                        positionIdx : selectedRecruitArr,
                        process : selectedProcess,
                        keyword : keywords
                },
                success: function(result){
                    $('.loungeList_container').empty();
                    $('.pagination').empty();
                    
                    // 본문
                    result.dtoList.forEach(function(item){
                    let itemHtml = '';
                    let pagingHtml = '';
                    itemHtml += `
                <a class="loungeList_loungeItem" href="${root}study/${'${item.studyIdx}'}">
                        <li class="loungeItem_list">
                            <div class="loungeItem_badgeWrapper">
                                <div class="badge_categoryWrapper">
                                    <div class="badge_category">
                                        ${'${item.categoryName}'}
                                    </div>
                                </div>
                            </div>
                            <div class="loungeItem_regDate">
                                <p class="loungeItem_regDateTitle">마감일 |</p>
                                <p>${'${item.recruitDeadline}'}</p>
                            </div>
                            <h1 class="loungeItem_title">${'${item.title}'}</h1>
                            <ul class="loungeItem_positions">

                            `;

                            item.recruitPositions.forEach(function (position) {
                                itemHtml += `<li class="loungeItem_tag">${'${position}'}</li>`;
                              });

                            
                            itemHtml += `
                            </ul>
                            <ul class="loungeItem_skill">
                            `;
                            
                            item.skillTags.forEach(function(imgName){
                                // itemHtml += `<li>${'${imgName}'}</li>`;
                                itemHtml += `<li><img class="skillImg" src="${root}static/img/study/${'${imgName}'}"></li>`;
                                // ${root}static/img/community/42.png
                              
                            });

                            itemHtml += `    
                            </ul> 
                            <div class="loungeItem_boarder"></div>
                            <section class="loungeItem_info">
                                <div class="loungeItem_userInfo">
                                    <div class="userInfo_avatar">
                                        <img class="avatar_userImg" width="30px" height="30px" src="${root}api/profileImages/${'${item.profileFilename}'}">
                                    </div>
                                
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

                    // 페이징 초기화
                    $('.pagination').empty();

                    // 본문 끝
                    // 페이징
                    // paging 
        let pagingHtml = '';
        pagingHtml += `
                    <button class="pagination-btn" data-page="0"><svg width="10px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M41.4 233.4c-12.5 12.5-12.5 32.8 0 45.3l160 160c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L109.3 256 246.6 118.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0l-160 160zm352-160l-160 160c-12.5 12.5-12.5 32.8 0 45.3l160 160c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L301.3 256 438.6 118.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0z"/></svg></button>
                    `;

        if(result.prev == true){
            pagingHtml += `            
                    <button class="pagination-btn" data-page="${'${result.prevPage}'}"><svg width="8px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M41.4 233.4c-12.5 12.5-12.5 32.8 0 45.3l160 160c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L109.3 256 246.6 118.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0l-160 160z"/></svg></button>
                    `;
        }
        

        result.pageNumList.forEach(function(pageNum){
            pagingHtml += `
            <button class="pagination-btn" data-page="${'${pageNum - 1}'}">${'${pageNum}'}</button> 
            `;
        });

        if(result.next == true){
            pagingHtml += `
            <button class="pagination-btn" data-page=${'${result.nextPage}'}><svg width="8px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg></button>
            `;
        }
        pagingHtml += `
            <button class="pagination-btn" data-page="${'${result.realEnd}'}"><svg width="10px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M470.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 256 265.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160zm-352 160l160-160c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L210.7 256 73.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0z"/></svg></button>
        `;

        $('.pagination').append(pagingHtml);

        // 페이지 버튼 클릭
        $('.pagination-btn').on('click', function(){
            let selectedPage = $(this).data('page');
            // $('.pagination').children.remove();
            // $('.loungeList_container').empty();
            // $('.pagination').empty();
            getStudyList(selectedPage, pageSize, selectedCategoryIdx);
        });
        
        // 페이징 끝
                },
                error: function(err){
                    console.log('스터디 목록 불러오기 실패');
                }
                
            });
        
    }
    
    // placaHolder 토글
    function togglePlaceHolder(classSelector, itemSelector, holderSelector) { 
        if($(classSelector + ' ' + itemSelector).length === 0){
            $(holderSelector).show();
        }else{
            $(holderSelector).hide();
        }
     }

     // 사용가능 리스트 토글
     function toggleList(idSelector, availableItemSelector, targetSelector) {
        $(idSelector).on('click', function(e){
            if(!$(e.target).closest(availableItemSelector).length){
                $(targetSelector).toggle();
            }
        });
       }


</script>
</html>
