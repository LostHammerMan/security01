<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>기술 스택 선택</title>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>
    .selected-items {
      border: 5px solid #6e707e;
      width: 300px;
      height: auto;
      min-height: 50px;
      display: flex;
      position: relative;
    }

    #selectedList {
      /*margin: 5px;*/
      /*width: 100%;*/
      /*height: 100%;*/
      display: flex;
      flex-wrap: wrap;
      position: relative;
      gap: 5px;
      height: auto;
    }

    .available-items {
      border: 1px solid #6e707e;
      display: none;
      width: 100px;
      margin-top: 5px;
      /*top: 100%;*/
      position: relative;
      z-index: 1;
      background-color: #F2F2F2;
    }

    .skill {
      cursor: pointer;
      border: 3px solid #0f6674;
      margin-top: 5px;
      margin-bottom: 5px;
      margin-left: 5px;
      padding: 3px;
    }


  </style>
</head>
<body>
<form>
<div class="select_container">
  <div class="selected-items" id="selected-items">
    <div class="select_placeholder">프로젝트 사용 스택</div>
    <div class="selectedList" id="selectedList"></div>

  </div>
  <div class="available-items" id="available-items">
    <div class="skill" id="item1">JavaScript</div>
    <div class="skill" id="item2">Python</div>
    <div class="skill" id="item3">Spring</div>
    <div class="skill" id="item4">Vue</div>

  </div>
</div>
</form>

</body>
<script>
  $(document).ready(function (){
    console.log("기술 선택 시작");

    let selectedSkill = $(this);
    let skillId = selectedSkill.attr("id");

    // available 자식의 id 를 리스트로 변환
    const originalIdxOrder = $("#available-items").children().toArray().map(item => item.id);


    // 기술 스택칸 클릭시
    $('.selected-items').on("click", function (e){
      console.log("선택된 기술 스택 div 클릭");
      e.stopPropagation();

      // e.target : 클릭한 요소
      // selectedList 에 skill 을 선택한 경우가 아닐때만 toggle 작동
      if (!$(e.target).closest('.skill').length){
        $("#available-items").toggle();
      }
      // if ($("#selectedList .skill").length < 1){
      //   $('.select_placeholder').show();
      // }else {
      //   $('.select_placeholder').hide();
      // }
    });


    // 선택 목록 추가
    $('#available-items').on('click', '.skill', function (){
      console.log("아이템 클릭");

      let selectedSkill = $(this);

      // 위 div 로 이동
      $('#selectedList').append(selectedSkill.clone());
      $('#available-items').hide();
      select_placeHolder();
      // 기존 항목에서 제거
      selectedSkill.remove()
    });

    // 선택된 요소를 다시 클릭시 제거 후 원래 자리로 이동
      $('#selectedList').on('click', '.skill', function (){
        console.log("등록된 요소 클릭");
        let selectedSkill = $(this);

        // 선택한 요소의 id 값
        let skillIdx = selectedSkill.attr("id");

        // 원래 순서에서 선택한 skill 의 인덱스
        let originalIdx = originalIdxOrder.indexOf(skillIdx);

        // 선택된 skill 클릭시 다시 원래 자리로 이동
        let target = $('#available-items .skill').toArray()
                .findIndex(item => originalIdxOrder.indexOf(item.id) > originalIdx);

        if (target === -1){
          // 선택한 요소가 가장 마지막인 경우
          console.log("제일마지막인 경우");
          $("#available-items").append(selectedSkill.clone());
        }else {
          console.log("마지막이 아닌 경우");
          $(selectedSkill.clone()).insertBefore($('#available-items .skill').eq(target));
        }
        // $("#selectedList").append(selectedSkill.clone());
        // $("#availableList").hide();

        // 선택한 요소 제거
        selectedSkill.remove();
        // $('#availableList').hide();
        select_placeHolder();
      });

      $(document).on('click', function (){
        $('#availableList').hide();
      });

      // $('#availableList').on('click', function (e){
      //   e.stopPropagation();
      // });
  });

  function select_placeHolder(){
    if ($('.selectedList .skill').length === 0){
      $('.select_placeholder').show();
    }else {
      $('.select_placeholder').hide();
    }
  }
</script>
</html>