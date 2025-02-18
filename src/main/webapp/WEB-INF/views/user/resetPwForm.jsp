<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<style>
    body {
        background: #F2F2F2;
        /* background: -webkit-linear-gradient(to top, #EF629F, #EECDA3);
         background: linear-gradient(to top, #EF629F, #EECDA3);*/
    }

    .container {
        max-width: 550px;
    }

    span {
        font-size: 11px;
        vertical-align: middle;
        line-height: 20px;
    }

</style>

<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3 class="text-left" style="">비밀번호 변경</h3>
            <span class="">안전한 비밀번호로 내 정보를 보호하세요</span><br>
            <span style="color: red">- 다른 아이디/사이트에서 사용한 적 없는 비밀번호</span><br>
            <span style="color: red">- 이전에 사용한 적 없는 비밀번호</span><span>가 안전합니다</span>
        </div>

        <div class="card-body">
            <form>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <input type="password" data-minlength="4" class="form-control" id="newPw" name="newPw"
                               data-error="Have atleast 4 characters" placeholder="새 비밀번호"/>
                        <!-- Error -->
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <input type="password" class="form-control" id="confPw" name="confPw"
                               placeholder="새 비밀번호 확인"/>
                        <!-- Error -->
                    </div>
                </div>

                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submitBtn">확인</form:button>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-danger btn-block" id="cancelBtn">취소</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){

    // URLSearchParams : url 뒤 파라미터 값 가져오기 가능
    const urlParams = new URLSearchParams(window.location.search);
    const tempToken = urlParams.get('tempToken');
    console.log('tempToken =' + tempToken);

    $('#submitBtn').on('click', function(e){
        e.preventDefault();
        let newPassword = $('#newPw').val();
        let confirmPassword = $('#confPw').val();

        console.log('newPassword = ' + newPassword);
        console.log('confirmPassword = ' + confirmPassword);

        if(newPassword !== confirmPassword){
            alert('비밀번호가 일치하지 않습니다.'); 
            // 제이 쿼리 이벤트 핸들러에서는 return false; 가 더 안전
            return false;
        }
        $.ajax({
            url: '${root}api/updatePw',
            type: 'POST',
            contentType : 'application/json',
            data: JSON.stringify({
                tempToken: tempToken,
                newPw : newPassword,
                confPw : confirmPassword
            }),
            success: function (result) {
                console.log('비밀번호 변경 성공');
            },
            error: function (xhr) {
                console.log('비밀번호 변경 실패');
                console.log(xhr);
            }
        });
    });
    
});
   
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

