<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<style>
  .studyCategory {
    cursor: pointer;
  }

  .selected {
    font-weight: bolder;
  }

</style>


<div class="navbar navbar-light bg-light d-flex justify-content-start" id="commuNavVar">

  <a class="navbar-brand mr-lg-5 navbar-1" href="${root}community">
    <img src="${root}static/img/community/artist_love_you.png" width="30" height="30" class="d-inline-block align-top" alt="">
    슽디 <span style="color: #ff681a">study</span>
  </a>
  <span class="navbar-brand mr-lg-5 pt-2 navbar-1 studyCategory selected" 
          style="font-size: 16px; font-family: D2Coding,serif; vertical-align: center" data-index="${subCategory.categoryIdx}">
          전체
  </span>
  <c:forEach var="category" items="${categoryDtos}">
    <c:if test="${category.categoryIdx == 2}">
      <c:forEach var="subCategory" items="${category.subCategory}">
        <span class="navbar-brand mr-lg-5 pt-2 navbar-1 studyCategory" id="menu${subCategory.categoryIdx}"
          style="font-size: 16px; font-family: D2Coding,serif; vertical-align: center" data-index="${subCategory.categoryIdx}">
          ${subCategory.categoryName}
        </span>
      </c:forEach>
    </c:if>
  </c:forEach>
  
</div>
<script>
    // 카테고리 클릭시 이벤트
    $(document).on('click', '.studyCategory',function(e){
        e.preventDefault();
        $('.studyCategory').removeClass('selected');
        $(this).addClass('selected');
        selectedCategoryIdx = $(this).data('index');
        console.log('selectedCategoryIdx = ' + selectedCategoryIdx);
        console.log('카테고리 클릭');
        console.log("keywords = " + keywords);

        // getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess, keywords)
        getStudyList(0, 12, selectedCategoryIdx, selectedCategoryIdx, selectedSkillArr, selectedRecruitArr, selectedProcess, keywords, studyListUrl);
        
      });


  // $(function (){
  //   $(".navbar-brand").on("click", function (){
  //     // 이전에 선택된 메뉴의 클래스 제거
  //     console.log("navbar clicked........")
  //     $(".navbar-brand.selected").removeClass('selected');

  //     // 선택된 메뉴에 클래스 추가
  //     $(this).addClass('selected');
  //   })
  // });

</script>

<%--<script type="text/javascript">

  


  

  /*function selectMenu(menuId){
    // 이전에 선택된 메뉴의 클래스 제거
    console.log("menuId = " + menuId);
    let prevSelected = $(".navbar-brand.selected");
    if (prevSelected){
      prevSelected.classList.remove("selected");
    }

    // 선택된 메뉴에 클래스 추가
    let selectedMenu = $("#menuId");
    console.log("selectedMenu = " + selectedMenu);
    selectedMenu.classList.add("selected");
  }*/

</script>--%>
