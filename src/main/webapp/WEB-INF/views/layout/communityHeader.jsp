<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />


<div class="navbar navbar-light bg-light d-flex justify-content-start" id="commuNavVar">

  <a class="navbar-brand mr-lg-5 navbar-1" href="${root}community">
    <img src="${root}static/img/community/artist_love_you.png" width="30" height="30" class="d-inline-block align-top" alt="">
    슽디 <span style="color: #ff681a">community</span>
  </a>
  <c:forEach var="category" items="${categoryDtos}">
    <c:if test="${category.categoryIdx == 1}">
      <c:forEach var="subCategory" items="${category.subCategory}">
        <a class="navbar-brand mr-lg-5 pt-2 navbar-1" id="menu1"
           href="${root}${subCategory.categoryPath}" style="font-size: 16px; font-family: D2Coding,serif; vertical-align: center">
          ${subCategory.categoryName}
        </a>
      </c:forEach>
    </c:if>
  </c:forEach>
  <%--<a class="navbar-brand mr-lg-5 navbar-1" href="${root}community/main">
    <img src="${root}static/img/community/artist_love_you.png" width="30" height="30" class="d-inline-block align-top" alt="">
    슽디 <span style="color: #ff681a">community</span>
  </a>
  <a class="navbar-brand mr-lg-5 pt-2 navbar-1" id="menu1"
     href="${root}community/lounge" style="font-size: 16px; font-family: D2Coding,serif; vertical-align: center">
    개발자 라운지
  </a>
  <a class="navbar-brand pt-2 navbar-1" href="${root}community/QnA" id="menu2"
     style="font-size: 16px; font-family: D2Coding,serif">
    Q&A
  </a>--%>
</div>

<%--<script type="text/javascript">

  $(function (){
    $(".navbar-brand").on("click", function (){
      // 이전에 선택된 메뉴의 클래스 제거
      console.log("navbar clicked........")
      $(".navbar-brand.selected").removeClass('selected');

      // 선택된 메뉴에 클래스 추가
      $(this).addClass('selected');
    })
  })

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
