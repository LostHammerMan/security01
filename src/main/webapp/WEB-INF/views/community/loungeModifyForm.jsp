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

    body {
        width: 100%;
        height: 100vh;
    }

    .modal-header {
        border-bottom: none;
    }

    .modal-content {
        width: 28vw;
    }

    .modal-body {
        font-weight: 400;
        color: rgb(73, 80, 87);
    }

    .modal-footer {
        border-top: none;

    }

    .btn-light {
        border-radius: 0.5rem;
        border: 0.0625rem solid rgb(206, 212, 218);
        font-size: 1rem;
        font-weight: 600;
    }

    .btn-success {
        border-radius: 0.5rem;
        font-weight: 600;
        font-size: 1rem;
    }






</style>
<body>

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
               <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modifyCancelModal">취소</button>
               <form:button type="submit" class="btn btn-primary ">등록</form:button>
           </div>
       </div>
   </form:form>

</div>

<div class="container modalContainer">

    <!-- The Modal -->
    <div class="modal" id="modifyCancelModal" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" style="font-weight: bold">작성 취소</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">
                    작성 중인 글이 있어요. 취소하시겠어요?
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-dismiss="modal" style="width: 45%">취소</button>
                    <button type="button" class="btn btn-success" id="closeModifyBtn" style="width: 45%">나가기</button>
                </div>

            </div>
        </div>
    </div>

</div>


</body>


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

        $("#closeModifyBtn").click(function (e){
            e.preventDefault();
            location.href = '${root}community/lounge/${findLounge.idx}';
        })
    });
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>