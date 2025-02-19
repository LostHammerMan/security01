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

    .modal-body-emailContainer {
        display: flex;
        position: relative;
        overflow: hidden;
        padding: 0.75rem 0.75rem 0.75rem 1rem;
        border-radius: 0.5rem;
        border: 0.0625rem solid transparent;
        background-color: rgb(248, 249, 250);
        margin-bottom: 1rem;
    }

    .fa-envelope {
        display: flex;
        width: 1.25rem;
        height: 1.25rem;
        align-items: center;
        justify-content: flex-start;
        margin-right: 1rem;
        margin-top: 0px;
    }

    .modal-body-text {
        color: rgb(73, 80, 87);
        font-size: 1rem;
        text-decoration: none;
        text-align: start;
        line-height: 1.5;
        text-underline-position: under; /* underline 의 위치 설정*/
        padding-left: 1rem;
    }
</style>

<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 style="font-weight: 700; font-size: 1.625rem;">아이디 찾기</h3>
            <span>가입한 이메일을 입력해 주세요.</span>
            <span>이메일을 통해 아이디를 알려드립니다.</span>
        </div>

        <div class="card-body">

            <div class="email-submit">
                <div class="input-group">
                    <input class="form-control" type="text" id="email" name="email" placeholder="example@google.com">
                </div>
                <button class="btn btn-success submitBtn" type="button" data-toggle="modal" data-target="#exampleModalCenter">
                    <span>아이디 찾기</span>
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

        // const 는 값 재할당이 불가능하다는 점에 유의하여 변수 선언 위치 고려할것!
        $.ajax({
            url : '${root}api/sendIdEmail', 
            type : 'GET',
            data : {
                email: $('#email').val()
            },
            beforeSend: function(){ // success, error 실행전 동작
                $('#exampleModalCenter').remove();
            },
            success : function(result){
                console.log(result);
                const modalHtml = `
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">비밀번호 재설정 메일 발송</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            </div>
                            <div class="modal-body">
                                <div class="modal-body-emailContainer">
                                    <i class="fa-solid fa-envelope"></i>    
                                    <span id="emailInput"></span>
                                </div>
                                <p class="modal-body-text">
                                    위 메일로 아이디가 발송되었습니다<br>
                                    메일이 확인되지 않을 경우, 스팸함을 확인해주세요<br>
                                </p>
                            </div>
                            <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">창 닫기</button>
                            <button type="button" class="btn btn-primary" id="toMainPage">메인 페이지로</button>
                            </div>
                        </div>
                        </div>
                    </div>
                `;

                    $('body').append(modalHtml);
                    $('#emailInput').text($('#email').val());
                    $('#exampleModalCenter').modal('show');

                    $('#toMainPage').on('click', function(){
                        console.log('메인페이지 클릭');
                        location.href = '${root}';
                    });
            },
            error : function(xhr){
                console.log('이메일 전송 실패');
                console.log(xhr);
                const modalHtml = `
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">아이디 찾기</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            </div>
                            <div class="modal-body">
                                <div class="modal-body-emailContainer">
                                    <i class="fa-solid fa-envelope"></i>
                                    <span id="emailInput"></span>
                                </div>
                                <p class="modal-body-text">
                                    가입하셨던 메일을 입력하셔야 합니다.<br>
                                    메일이 생각나지 않으신다면, 로그인 화면에서<br> 
                                    아이디 찾기를 해주세요.<br>
                                </p>
                                
                            </div>
                            <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">창 닫기</button>
                            </div>
                        </div>
                        </div>
                    </div>
                `;

                $('body').append(modalHtml);
                $('#emailInput').text(xhr.responseJSON.message);
                $('#exampleModalCenter').modal('show');

                
                // alert(xhr.responseJSON.message);
            }
        });
    });

    

});

</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

