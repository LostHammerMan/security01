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

    .modal-header {
        display: flex;
        align-items: baseline;
    }

    .modal-content {
        width: 25.5rem;
    }

    .modal-title {
        line-height: 1;
        padding: 0px;
        margin: 0px;
        width: 100%;
        font-size: 18px;
        font-weight: 700;
        color: rgb(33, 37, 41);
    }

    .modal-body {
        display: flex;
        flex: 1 1 0%;
        min-height: 0px;
        flex-direction: column;
        padding: 0px;
        overflow: auto;
        padding: 1rem 1rem;
    }

    .modal-body-text {
        color: rgb(73, 80, 87);
        font-size: 1rem;
        text-decoration: none;
        text-align: start;
        line-height: 1.5;
        text-underline-position: under; /* underline 의 위치 설정*/
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
                <div class="form-group">
                    <div class="form-group">
                        <input type="password" data-minlength="4" class="form-control" id="newPw" name="newPw"
                               data-error="Have atleast 4 characters" placeholder="새 비밀번호"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <input type="password" class="form-control" id="confPw" name="confPw"
                               placeholder="새 비밀번호 확인"/>
                    </div>
                </div>

                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submitBtn">확인</form:button>
                </div>
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
                const modalHtml = `
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">비밀번호 변경 완료</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            </div>
                            <div class="modal-body">
                                <p class="modal-body-text">
                                    비밀번호가 변경되었습니다.<br>
                                    변경된 비밀번호로 로그인 후, 이용해주세요.<br>
                                </p>
                            </div>
                            <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="toMainPage">메인 페이지로</button>
                            </div>
                        </div>
                        </div>
                    </div>
                `;

                    $('body').append(modalHtml);
                    $('#exampleModalCenter').modal('show');

                    $('#toMainPage').on('click', function(){
                        console.log('메인페이지 클릭');
                        location.href = '${root}';
                    });
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

