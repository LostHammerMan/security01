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
      border: 1px solid #ccc;
      padding: 10px;
      margin: 10px;
      width: 500px; /* 가로 정렬을 위한 넓이 설정 */
      display: flex; /* Flexbox를 사용하여 가로 정렬 */
      flex-wrap: wrap; /* 5개를 넘으면 줄바꿈 */
      gap: 5px; /* 항목 간격 */
      position: relative; /* availableList 위치 조정을 위한 설정 */
    }
    .available-items {
      border: 1px solid #ccc;
      padding: 10px;
      margin: 10px;
      width: 200px;
      position: absolute; /* selected-items 내에 위치하게 설정 */
      top: 100%; /* selected-items 바로 아래에 위치하게 설정 */
      left: 0;
      display: none; /* 초기에는 숨김 */
      background-color: white; /* 배경색 설정 */
      z-index: 1; /* 다른 요소보다 위에 표시되도록 설정 */
    }
    .item {
      cursor: pointer;
      padding: 5px;
      margin: 2px;
      border: 1px solid #ccc;
      border-radius: 4px;
      background-color: #f9f9f9;
    }
  </style>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="selected-items">
  <div id="selectedList"></div>
  <div class="available-items" id="availableList">
    <div class="item" id="item1">JavaScript</div>
    <div class="item" id="item2">React</div>
    <div class="item" id="item3">TypeScript</div>
    <div class="item" id="item4">Vue</div>
    <div class="item" id="item5">Nodejs</div>
    <div class="item" id="item6">Spring</div>
    <div class="item" id="item7">Java</div>
    <div class="item" id="item8">Nextjs</div>
    <div class="item" id="item9">Nestjs</div>
  </div>
</div>
<script>
  $(document).ready(function() {
    const originalOrder = $('#availableList').children().toArray().map(item => item.id);

    $('#availableList').on('click', '.item', function() {
      if ($('#selectedList .item').length < 5) {
        var selectedItem = $(this);

        // 선택된 항목을 위쪽 div로 이동
        $('#selectedList').append(selectedItem.clone());

        // 기존 목록에서 항목 제거
        selectedItem.remove();

        // availableList를 숨김
        $('#availableList').hide();
      } else {
        alert("최대 5개의 항목만 선택할 수 있습니다.");
      }
    });

    $('#selectedList').on('click', '.item', function() {
      var selectedItem = $(this);
      var itemId = selectedItem.attr('id');

      // 원래 목록에서 위치 찾기
      var originalIndex = originalOrder.indexOf(itemId);

      // 선택된 항목을 원래 목록으로 이동
      var target = $('#availableList .item').toArray().findIndex(item => originalOrder.indexOf(item.id) > originalIndex);
      if (target === -1) {
        $('#availableList').append(selectedItem.clone());
      } else {
        $(selectedItem.clone()).insertBefore($('#availableList .item').eq(target));
      }

      // 선택 목록에서 항목 제거
      selectedItem.remove();
    });

    $('.selected-items').on('click', function(e) {
      e.stopPropagation(); // 이벤트 버블링 방지
      $('#availableList').toggle();
    });

    $(document).on('click', function() {
      $('#availableList').hide();
    });

    $('#availableList').on('click', function(e) {
      e.stopPropagation(); // 이벤트 버블링 방지
    });
  });
</script>
</body>
</html>