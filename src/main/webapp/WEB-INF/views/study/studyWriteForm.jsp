<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>
    .StudyRegister_wrapper {
        display: flex;
        flex-direction: column;
        width: 100%;
        max-width: 1040px;
        padding: 60px 16px;
        margin: 0 auto;
        color: #333;
        gap: 50px;
        position: relative;
    }

    .studyRegister_infoWrapper {
        display: flex;
        align-items: center;
        margin-bottom: 36px;
        border-bottom: 3px solid #F2F2F2;
        gap: 5px;
    }

    .infoWrapper_text {
        font-size: 18px;
        font-weight: 900;
        font-family: sans-serif;
    }

    .studyInfo_inputList {
        display: flex;
        flex-direction: row;
        gap: 20px;
        margin-top: 20px;
        list-style: none;
        padding: 3px;
    }

    .studyInfo_inputList_item {
        width: 100%;
    }

    .selectBox_labelText {
        font-family: sans-serif;
        font-size: 20px;
        font-weight: 900;
    }

    .custom-select {
        font-family: sans-serif;
        font-weight: 700;
        height: 60px;
        font-size: 18px;
    }

    .studyTitle_wrapper {
        margin-bottom: 20px;
        width: 100%;
    }

    .studyInfo_title {
        width: 100%;
        height: 50px;
        border: 1px solid #e1e3e8;
        border-radius: 5px;
    }

    .studyContents_wrapper {
        font-size: 1.125rem;
        margin-bottom: 20px;
    }

    .studyWrite_btn_wrapper {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
    }

    .select-container {
        display: flex;
    }

    .select-placeHolder {
        height: 100%;
        display: flex;
        align-items: center;
    }

    .selectedList {
        display: flex;
        flex-direction: row;
        gap: 10px;
        flex-wrap: wrap;
        height: auto;
        position: relative;
    }

    .availableItem-list {
        display: none;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        width: 100%;
        box-sizing: border-box;
        font-weight: normal;
        font-family: sans-serif;
        font-size: 18px;
        padding: .375rem 1.75rem .375rem .75rem;
        white-space: nowrap;
        appearance: none;
        height: auto;
    }

    .item-container {
        display: flex;
        height: auto;
        gap: 10px;
    }


    .available-item {
        /*display: none;*/
        width: 30%;
        box-sizing: border-box;
        border: 1px solid #F2F2F2;
        border-radius: .25rem;
        background-color: #F2F2F2;
    }

    .available-item-recruit {
        width: 30%;
        box-sizing: border-box;
        border: 1px solid #F2F2F2;
        border-radius: .25rem;
        background-color: #F2F2F2;
    }


    /*.available-item:hover {*/
    /*    background-color: #2977ff;*/
    /*    color: white;*/
    /*    width: 100%;*/
    /*}*/

    .selectedItem {
        border: 1px solid #F2F2F2;
        border-radius: .25rem;
        background-color: #F2F2F2;
        width: auto;
        height: auto;
    }

    .contactDetails {
        display: inline-block;
        width: 100%;
        height: calc(1.5em + .75rem + 2px);
        padding: .375rem 1.75rem .375rem .75rem;
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #495057;
        vertical-align: middle;
        background: #fff;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
    }

    .ck-editor__editable[role="textbox"] {
        /* editing area */
        min-height: 500px;
        max-height: max-content;
        padding: 1rem;
    }
    .ck-content .image {
        /* block images */
        max-width: 100%;
        margin: 20px auto;
    }
</style>
<body>
<div class="StudyRegister_wrapper">
    <section>
        <div class="studyRegister_infoWrapper">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 512 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M362.7 19.3L314.3 67.7 444.3 197.7l48.4-48.4c25-25 25-65.5 0-90.5L453.3 19.3c-25-25-65.5-25-90.5 0zm-71 71L58.6 323.5c-10.4 10.4-18 23.3-22.2 37.4L1 481.2C-1.5 489.7 .8 498.8 7 505s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L421.7 220.3 291.7 90.3z"/></svg>
            <h2 class="infoWrapper_text">기본 정보 입력해주세요</h2>
        </div>
        <ul class="studyInfo_inputList">
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">모집 구분</label>
                <div class="input-group mb-3">
                    <select class="custom-select" id="category">
                        <option selected>스터디/프로젝트</option>
                        <option value="5">스터디</option>
                        <option value="6">프로젝트</option>
                    </select>
                </div>
            </li>
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">모집 인원</label>
                <div class="input-group mb-3">
                    <select class="custom-select" id="recruitedNumber">
                        <option selected>인원미정 ~ 6명이상</option>
                        <option value="0">인원미정</option>
                        <option value="1">1명</option>
                        <option value="2">2명</option>
                        <option value="3">3명</option>
                        <option value="4">4명</option>
                        <option value="5">5명</option>
                        <option value="6">6명</option>
                    </select>

                </div>
            </li>
        </ul>
        <ul class="studyInfo_inputList">
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">진행 방식</label>
                <div class="input-group mb-3">
                    <select class="custom-select" id="progressMethod">
                        <option selected>전체</option>
                        <option value="온라인">온라인</option>
                        <option value="오프라인">오프라인</option>
                        <option value="오프라인">온라인/오프라인</option>
                    </select>

                </div>
            </li>
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">진행 기간</label>
                <div class="input-group mb-3">
                    <select class="custom-select" id="progressPeriod">
                        <option selected>기간 미정 ~ 5개월 이상</option>
                        <option value="기간미정">기간미정</option>
                        <option value="1개월">1개월</option>
                        <option value="2개월">2개월</option>
                        <option value="3개월">3개월</option>
                        <option value="4개월">4개월</option>
                        <option value="5개월">5개월</option>
                        <option value="장기">장기</option>
                    </select>
                </div>
            </li>
        </ul>
        <ul class="studyInfo_inputList">
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">모집 스킬</label>
                <div class="input-group mb-3 select-container">
                    <div class="custom-select selectedItems" id="selectedItems-skill" style="width: 100%">
                        <div class="select-placeHolder">프로젝트 사용스택</div>
                        <div class="selectedList" id="selectedList-skill"></div>
                    </div>
                    <div class="availableItem-list" id="availableItems-skill">
                        <div class="item-container" id="item-container-skill">
                            <div class="available-item" id="item1" data-value="1">Spring</div>
                            <div class="available-item" id="item2" data-value="2">Python</div>
                            <div class="available-item" id="item3" data-value="3">AWS</div>
                        </div>
                    </div>
                </div>
            </li>

            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">모집 분야</label>
                <div class="input-group mb-3 select-container">
                    <div class="custom-select selectedItems" id="selectedItems-recruit" style="width: 100%; height: auto; min-height: 60px">
                        <div class="select-placeHolder" id="placeHolder-recruit">백엔드, 프론트엔드...</div>
                        <div class="selectedList recruit" id="selectedList-recruit"></div>
                    </div>
                    <div class="availableItem-list" id="availableItems-recruit">
                        <div class="item-container" id="item-container-recruit">
                            <div class="available-item-recruit" id="recruit1" data-value="1">백엔드</div>
                            <div class="available-item-recruit" id="recruit2" data-value="2">프론트엔드</div>
                            <div class="available-item-recruit" id="recruit3" data-value="3">Manager</div>
                            <div class="available-item-recruit" id="recruit4" data-value="4">웹디자이너</div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>

        <ul class="studyInfo_inputList">
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">모집 마감일</label>
                <div class="input-group mb-3">
                    <input type="text" id="endDate" class="custom-select datepicker" placeholder="클릭 후 날짜 지정">
                </div>
            </li>
            <li class="studyInfo_inputList_item">
                <label class="selectBox_labelText">연락 방법</label>
                <div class="input-group mb-3">
                    <select class="custom-select" id="contactMethod">
                        <option value="kakao">카카오톡</option>
                        <option value="email">이메일</option>
                        <option value="googleForm">구글폼</option>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <input class="contactDetails" id="contactDetails" placeholder="오픈톡링크"/>
                </div>

            </li>
        </ul>
    </section>
    <section>
        <div class="studyRegister_infoWrapper">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 512 512"><!--!Font Awesome Free 6.6.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M362.7 19.3L314.3 67.7 444.3 197.7l48.4-48.4c25-25 25-65.5 0-90.5L453.3 19.3c-25-25-65.5-25-90.5 0zm-71 71L58.6 323.5c-10.4 10.4-18 23.3-22.2 37.4L1 481.2C-1.5 489.7 .8 498.8 7 505s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L421.7 220.3 291.7 90.3z"/></svg>
            <h2 class="infoWrapper_text">상세 정보 입력해주세요</h2>
        </div>
        <div class="studyTitle_wrapper">
            <label class="selectBox_labelText">제목</label><br>
            <input class="studyInfo_title" id="studyInfo_title" placeholder="제목 입력해주세요">
        </div>
        <div class="studyContents_wrapper">
            <label for="editor"></label><textarea id="editor"></textarea>
        </div>

        <div class="studyWrite_btn_wrapper">
            <button class="btn btn-outline-danger">취소</button>
            <button class="btn btn-primary" id="studySubmitBtn">등록</button>
        </div>
    </section>

</div>

</body>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script>
    $(document).ready(function () {

        const originalOrder = $('#item-container-skill').children().toArray().map(item => item.id);
        const originalOrder_recruitment = $('#item-container-recruit').children().toArray().map(item => item.id);

        // 사용가능 리스트 토글 이벤트
        toggleList('#selectedItems-skill', '.available-item', '#availableItems-skill');
        toggleList('#selectedItems-recruit', '.available-item-recruit', '#availableItems-recruit');

        // 선택한 목록 추가 이벤트
        addItem('#availableItems-skill', '.available-item', '#availableItems-skill', '#selectedList-skill');
        addItem('#availableItems-recruit', '.available-item-recruit', '#availableItems-recruit', '#selectedList-recruit');

        // 추가된 요소 다시 클릭하면 제거, 제거 후 원래 자리로 이동
        removeItem('#selectedList-skill', '.available-item', originalOrder, '#item-container-skill');
        removeItem('#selectedList-recruit', '.available-item-recruit', originalOrder_recruitment, '#item-container-recruit');

        // contactMethod 에 따라 placeholder 값 변경
        $('#contactMethod').change(function(){

            let selectedMethod = $(this).val();
            let placeHolderText = '';

            if(selectedMethod === 'kakao'){
                placeHolderText = '오픈톡링크';
            }else if(selectedMethod === 'email'){
                placeHolderText = '이메일 주소';
            }else if(selectedMethod ==='googleForm'){
                placeHolderText = '링크 입력';
            }

            console.log("placeHolderText = " + placeHolderText);

            $('#contactDetails').attr('placeholder', placeHolderText);


        });
    });

    // 등록 버튼 클릭시 이벤트
    $('#studySubmitBtn').on('click', function(e){

        const studyWriteForm = $('<form>', {
            action: '${root}study/writeProc',
            method: 'post'
        });


        e.preventDefault();
        let selectedSkillArr = [];
        let selectedRecruitArr = [];

        // 모집구분
        let cateCode = $('#category').val();
        if(cateCode === '스터디/프로젝트'){
            alert("모집 구분을 선택해주세요")
            return false;
        }
        console.log("category = " + category);

        // 모집인원
        let recruitedNumber = $('#recruitedNumber').val();

        if(recruitedNumber === '인원미정 ~ 6명이상'){
            alert("모집 인원 선택해주세요");
            return false;
        }
        console.log("recruitedNumber = " + recruitedNumber);

        // 진행방식
        let progressMethod = $('#progressMethod').val();
        if(progressMethod === '전체'){
            alert("진행 방식 선택해주새요");
            return false;
        }

        console.log("progressMethod = " + progressMethod);

        // 진행 기간
        let progressPeriod = $('#progressPeriod').val();
        if(progressPeriod === '기간 미정 ~ 5개월 이상'){
            alert("진행 기간 선택해주세요");
            return false;
        }
        console.log("progressPeriod = " + progressPeriod);
        

        // 모집 스킬
        $('#selectedList-skill .available-item').each(function(){
            selectedSkillArr.push($(this).data('value'));
        });

        // 모집 분야
        $('#selectedList-recruit .available-item-recruit').each(function () {
            selectedRecruitArr.push($(this).data('value'));
          });
        
        console.log("selectedSkillArr = " + selectedSkillArr);
        console.log("selectedRecruitArr = " + selectedRecruitArr);

        // 모집 마감일
        let endDate = $('#endDate').val();
        if(!endDate){ // 자바스크립트에서 공백체크시 === 보다, !String 사용
            alert("모집 마감일을 선택해주세요");
            return false;
        }
        console.log("endDate = " + endDate);

        // 연락 방법
        let contactMethod = $('#contactMethod').val();
        let contactDetails = $('#contactDetails').val();
        console.log("contactMethod = " + contactMethod);
        console.log("contactDetails = " + contactDetails);

        // 상세 정보
        let studyInfo_title = $('#studyInfo_title').val();
        const studyInfo_contents = editorInstance.getData();
        console.log("studyInfo_title = " + studyInfo_title);
        console.log("studyInfo_contents = " + studyInfo_contents);

        // ajax 가 아닌 가상 form
        // - 스터디 작성 이후 스터디 리스트로 이동(리다이렉트 필요) 
        // - 페이지 변환이 있는 경우 전통적 폼 제출 방식이 더 적합

        studyWriteForm.append($('<input>', {type: 'hidden', name: 'cateCode', value: cateCode}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'title', value: studyInfo_title}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'contents', value: studyInfo_contents}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'recruitedNumber', value: recruitedNumber}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'progressPeriod', value: progressPeriod}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'progressMethod', value: progressMethod}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'recruitDeadline', value: endDate}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'recruitPositions', value: selectedRecruitArr}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'contactMethod', value: contactMethod}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'contactAddress', value: contactDetails}));
        studyWriteForm.append($('<input>', {type: 'hidden', name: 'skillTagIdx', value: selectedSkillArr}));

        // 폼 제출
        studyWriteForm.appendTo('body').submit();




    });

    // placeHolder 토글
    function togglePlaceHolder(classSelector, itemSelector, holderSelector) {
        if ($(classSelector + ' ' + itemSelector).length === 0) {
            $(holderSelector).show();
        } else {
            $(holderSelector).hide();
        }
    }

    // 사용가능 리스트 토글
    function toggleList(idSelector, availableItemSelector, targetSelector) {
        $(idSelector).on('click', function (e) {
            if (!$(e.target).closest(availableItemSelector).length) {
                $(targetSelector).toggle();
            }
        })
    }

    // 아이템 클릭시 추가 이벤트
    function addItem(idSelector, listClassSelector, listIdSelector, selectedListIdSelector){
        $(idSelector).on('click', listClassSelector, function (){
           let selectedItem = $(this);
           $(selectedListIdSelector).append(selectedItem.clone());
           togglePlaceHolder('#selectedList-skill', '.available-item', '.select-placeHolder');
           togglePlaceHolder('#selectedList-recruit', '.available-item-recruit', '#placeHolder-recruit');
           $(listIdSelector).hide();
           selectedItem.remove();
        });
    }

    // 다시 선택시 제거 이벤트
    function removeItem(idSelector, itemClassSelector, originalOrderSelector, containerSelector){
        $(idSelector).on('click', itemClassSelector, function (e){
            e.stopPropagation();
            let selectedItem = $(this);

            // 선택 요소의 id 값
            let itemIdx = selectedItem.attr('id');

            // 원래 순서에서 id
            let originalIdx = originalOrderSelector.indexOf(itemIdx);

            // 선택된 item 클릭시 다시 원래 자리로 이동
            let target = $(containerSelector + ' ' + itemClassSelector).toArray()
                .findIndex(item => originalOrderSelector.indexOf(item.id) > originalIdx);

            if (target === -1){
                console.log("제일 마지막인 경우")
                $(containerSelector).append(selectedItem.clone());
            }else {
                $(selectedItem.clone()).insertBefore($(containerSelector + ' ' + itemClassSelector).eq(target));
            }
            selectedItem.remove();
            togglePlaceHolder('#selectedList-skill', '.available-item', '.select-placeHolder');
            togglePlaceHolder('#selectedList-recruit', '.available-item-recruit', '#placeHolder-recruit');
        });
    }


</script>


<script>
    // datePicker
    $('.datepicker').datepicker({
       startDate: new Date(Date.now()),
       calendarWeeks: false,
       todayHighlight: true,
       autoclose: true,
       format: "yyyy-mm-dd",
       language: "kr"
    });
</script>
<script>
    // classicEditor
    let editorInstance;
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            editorInstance = editor;
        })
        .catch(error => {
            console.error(error);
        });
</script>
</html>
