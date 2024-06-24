<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<c:import url="/WEB-INF/views/layout/communityHeader.jsp"/>
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

    .modal-title{
        font-size: 17px;
        text-align:left;
        font-weight: bold;
    }
    .modal_table{
        width:100%;
    }
    #modal_userImg{
        width:50px;
        height:50px;
        border-radius: 75%;
    }
    #modal_userId{
        width:200px;
    }
    #modal_userFollow{
        margin:10px;
        text-align: right;
    }

</style>
<div class="wrapper d-flex mt-lg-5">

   <form:form action="${root}community/lounge/modifyProc" method="post" id="frmContact" enctype="multipart/form-data"
   modelAttribute="loungeModifyRequest">
       <form:hidden path="loungeId" value="${findLounge.idx}"/>
       <div class="row">
           <div class="form-group col-md-4">
               <label>카테고리</label>
               <div class="input-group">
                   <div class="dropdown">
                       <form:select class="form-control dropdown-toggle" path="cateCode">
                           <option class="dropdown-menu">카테고리 선택</option>
                           <option class="dropdown-item" value="5">자유주제</option>
                           <option class="dropdown-item" value="6">커리어 고민</option>
                       </form:select>
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
                          class="form-control" placeholder="글 제목을 입력해주세요" value="${findLounge.title}" /><br>
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
                       <form:errors path="contents" cssStyle="color: red"/>
                   </div>
               </div>

           </div>
       </div>
       <div class="row">
           <div class="col">
               <button type="button" class="btn btn-outline-danger" id="cancelModal" <%--data-toggle="modal" data-target="#exampleModal"--%>>취소</button>
               <form:button type="submit" class="btn btn-primary ">등록</form:button>
           </div>
       </div>
   </form:form>

</div>

<div class="container">
    <h1>Bootstrap Modal Example</h1>
    <!-- Button to Open the Modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        Open Modal
    </button>

    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Modal Heading</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">
                    Modal body content goes here.
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>

</div>

<script>

    let content = "${findLounge.content}";
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            // ckEditor 인스턴스 참조
            window.editor = editor;

            // ckEditor 값 설정
            editor.setData(content);
        })

            .catch(error => {
            console.error(error);
        });
</script>
<script>
    $(document).ready(function (){
       $("#cancelModal").click(function (){
           console.log("cancelModalBtnClick.....");
           $("#exampleModal").modal();
       })
    });
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>