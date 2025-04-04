<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>

    .wrapper {
        gap: 2.5rem;
        width: 100%;
        margin-left: 5%;
    }

    #container {
        width: 1500px;
    }
    .ck-editor__editable[role="textbox"] {
        /* editing area */
        min-height: 300px;
    }
    .ck-content .image {
        /* block images */
        max-width: 100%;
        margin: 20px auto;
    }


</style>
<div class="wrapper d-flex mt-lg-5">
   <%-- <div class="row py-4">
        <div class="col">
            &lt;%&ndash;      <h2>Bootstrap Contact Form</h2>&ndash;%&gt;
        </div>
    </div>--%>
<%--<form:form action="${root}user/joinProc" method="post" class="frm" modelAttribute="joinUserDto" cssClass="has-validation">--%>

<%--<form name="frmContact" id="frmContact" method="post" action=""--%>
<%--          enctype="multipart/form-data" novalidate>--%>
   <form:form action="${root}community/lounge/writeProc" method="post" id="frmContact" enctype="multipart/form-data"
   modelAttribute="loungeWriteRequest">
       <div class="row">
           <div class="form-group col-md-4">
               <label>카테고리</label>
               <div class="input-group">
                   <div class="dropdown">
                       <form:select class="form-control dropdown-toggle" path="cateCode">
                           <option class="dropdown-menu">카테고리 선택</option>
                           <option class="dropdown-item" value="3">자유주제</option>
                           <option class="dropdown-item" value="4">커리어 고민</option>
                       </form:select>
<%--                       <spring:hasBindErrors name="loungeWriteRequest">--%>
<%--                           <c:if test="${errors.hasFieldErrors('cateCode')}">--%>
<%--                               <c:forEach var="fieldError" items="${errors.getFieldErrors('cateCode')}">--%>
<%--                                   <span>${fieldError.code}</span>--%>
<%--                                    <c:if test="${fieldError.code eq 'invalidCateCode'}">--%>
<%--                                        &lt;%&ndash;<span>invalidCateCode</span>&ndash;%&gt;--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${fieldError.code eq 'typeMismatch'}">--%>
<%--                                        <spring:message code="${fieldError.code}" var="errorMessage"/>--%>
<%--                                        <span>${errorMessage}</span>--%>
<%--                                    </c:if>--%>
<%--                               </c:forEach>--%>
<%--                           </c:if>--%>

<%--                       </spring:hasBindErrors>--%>
                       <form:errors path="cateCode" cssStyle="color: red"/>

                   <%--<button class="form-control dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                             카테고리 선택
                           </button>
                           <div class="dropdown-menu">
                             <button type="button" class="dropdown-item" value="free">자유주제</button>
                             <button type="button" class="dropdown-item" value="career">커리어 고민</button>
                           </div>--%>
                   </div>
               </div>
           </div>

       </div>
       <div class="row">
           <div class="form-group col-md-4">
               <form:label path="title">제목</form:label>
               <div class="form-group" style="display: flex">
                   <form:input type="text" path="title"
                          class="form-control" placeholder="글 제목을 입력해주세요" /><br>
               </div>
               <form:errors path="title" cssStyle="color: red"  />
           </div>
       </div>
       <div class="row">
           <div class="form-group col-md-8">
               <form:label path="contents">내용</form:label>
               <div class="input-group">
                       <%--          <textarea class="form-control" rows="5" name="message"--%>
                       <%--                    id="message" required></textarea>--%>
                   <div id="container">
                       <form:textarea path="contents" id="editor"/>
<%--                       <div id="editor">--%>
<%--                       </div>--%>
                   </div>
               </div>

           </div>
       </div>
       <div class="row">
           <div class="col">
               <form:button type="button" class="btn btn-outline-danger">취소</form:button>
               <form:button type="submit" class="btn btn-primary">등록</form:button>

           </div>
       </div>
   </form:form>

</div>
<script>
    ClassicEditor
        .create(document.querySelector('#editor'))
        .catch(error => {
            console.error(error);
        });
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>