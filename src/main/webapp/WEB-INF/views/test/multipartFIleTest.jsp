<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<div class="card">
  <div class="card-header">
    <p>파일 업로드</p>
  </div>
  <div class="card-body">
    <form action="${root}test/items/new" type="post" enctype="multipart/form-data">
      <ul>
        <li>상품명 <input type="text" name="itemName"></li>
        <li>첨부파일 <input type="file" name="attachFile"></li>
        <li>이미지 파일들 <input type="file" name="imageFiles" multiple></li>
      </ul>
      <input type="submit" />
    </form>
  </div>
</div>
<script type="text/javascript">
  $(function (){

    function uploadImgFile(){

    }
  })
</script>


</body>
</html>
