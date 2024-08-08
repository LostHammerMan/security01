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
  <title>기술 스택 선택기</title>
  <style>
    .selected-items {
      align-items: center;
      display: flex;
      flex: 1 1 0;
      padding: 2px 8px;
      position: relative;
      overflow: hidden;
      box-sizing: border-box;
      flex-direction: row;
      flex-wrap: wrap;
      border: 5px solid #1c7430;
    }
    .available-items {
      border: 1px solid #ccc;
      padding: 10px;
      margin: 10px;
      width: 200px;
    }
    .skill {
      cursor: pointer;
      padding: 5px;
      margin: 2px;
      border: 1px solid #ccc;
      border-radius: 4px;
      background-color: #f9f9f9;
    }
    .select_placeholder {
      font-size: 16px;
      color: #aaa;
    }
  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="select_container">
  <div class="selected-items" id="selected-items">
    <div class="select_placeholder">프로젝트 사용 스택</div>
    <div class="selectedList" id="selectedList"></div>
  </div>
  <div class="available-items" id="availableList">
    <div class="skill" id="item1">JavaScript</div>
    <div class="skill" id="item2">Python</div>
    <div class="skill" id="item3">Spring</div>
    <div class="skill" id="item4">Vue</div>
  </div>
</div>

<script>
  $(document).ready(function() {
    const originalOrder = $('#availableList').children().toArray().map(item => item.id);

    function togglePlaceholder() {
      if ($('#selectedList .skill').length === 0) {
        $('.select_placeholder').show();
      } else {
        $('.select_placeholder').hide();
      }
    }

    // 초기 상태 확인
    togglePlaceholder();

    // availableList toggle on selected-items click
    $('.selected-items').click(function(e) {
      // if (!$(e.target).closest('.skill').length) {
      if (!$(e.target).closest('.skill').length) {
        $('#availableList').toggle();
      }
    });

    // Add item from availableList to selectedList
    $('#availableList').on('click', '.skill', function() {
      if ($('#selectedList .skill').length < 5) {
        $('#selectedList').append($(this));
        $('#availableList').hide();
        togglePlaceholder();
      } else {
        alert('최대 5개의 항목만 선택할 수 있습니다.');
      }
    });

    // Remove item from selectedList and move back to availableList
    $('#selectedList').on('click', '.skill', function() {
      var selectedItem = $(this);
      var itemId = selectedItem.attr('id');

      // 원래 목록에서 위치 찾기
      var originalIndex = originalOrder.indexOf(itemId);

      // 선택된 항목을 원래 목록으로 이동
      var target = $('#availableList .skill').toArray().findIndex(item => originalOrder.indexOf(item.id) > originalIndex);
      if (target === -1) {
        $('#availableList').append(selectedItem.clone());
      } else {
        $(selectedItem.clone()).insertBefore($('#availableList .skill').eq(target));
      }

      // 선택 목록에서 항목 제거
      selectedItem.remove();
      togglePlaceholder();
    });
  });
</script>
</body>
</html>