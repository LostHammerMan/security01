<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<style>
    .card {
        margin-right: auto;
        margin-left: auto;
        padding-right: 1rem;
        padding-left: 1rem;
        padding-top: 1.5rem;
        padding-bottom: 1.5rem;
        width: 27.5rem;
        display: flex;
        align-items: center;
        flex-direction: column;
    }

    .card-header {
        display: flex;
        flex-direction: column;
        align-items: center;
        border-bottom: none;
    }

    .email-submit {
        display: flex;
        gap: 20px;
        flex-direction: column;
    }
</style>

<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 style="font-weight: 700; font-size: 1.625rem;">비밀번호 찾기</h3>
            <span>가입한 이메일을 입력해 주세요.</span>
            <span>이메일을 통해 비밀번호 변경 링크가 전송됩니다.</span>
        </div>

        <div class="card-body">

            <div class="email-submit">
                <div class="input-group">
                    <input class="form-control" type="text" id="email" name="email" placeholder="example@google.com">
                </div>
                <button class="btn btn-success submitBtn" type="button">
                    <span>변경 링크 전송하기</span>
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $('.submitBtn').on('click', function(){
        console.log("제출 버튼 클릭");
        console.log( $('#email').val());

        $.ajax({
            url : '${root}api/sendPwEmail', 
            type : 'GET',
            data : {
                email: $('#email').val()
            },
            success : function(result){
                console.log(result);
                alert(result);
            },
            error : function(xhr){
                console.log('이메일 전송 실패');
                console.log(xhr);
                
                
                alert(xhr.responseJSON.message);
            }
        });
    });

});

</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

