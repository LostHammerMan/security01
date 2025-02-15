<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<style>
    .error-msg {
        color: red;
        font-family: sans-serif;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .divider:after,
    .divider:before {
        content: "";
        flex: 1;
        height: 1px;
        background: #eee;
    }

    .h-custom {
        height: calc(100% - 73px);
    }

    @media (max-width: 450px) {
        .h-custom {
            height: 100%;
        }
    }
</style>

<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="${root}login" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="username">Username</label>
                        <input type="text" id="username" name="username" class="form-control form-control-lg"
                               placeholder="당신의 ID를 입력하세요"/>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control form-control-lg"
                               placeholder="비밀번호를 입력하세요"/>
                    </div>
                    <c:if test="${not empty errorMsg}">
                        <div class="form-outline mb-3">
                            <div class="error-msg">${errorMsg}</div>
                        </div>
                    </c:if>

                    <div class="d-flex justify-content-between align-items-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3"/>
                            <label class="form-check-label" for="form2Example3">
                                Remember me
                            </label>
                        </div>
                        <a href="${root}user/findPw" class="text-body">비밀번호 찾기</a>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="button" class="btn btn-primary btn-lg" id="loginBtn" name="loginBtn"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Login
                        </button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">회원이 아니신가요? <a href="${root}user/joinForm"
                                                                              class="link-danger">회원가입</a></p>

                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

<script type="text/javascript">
    $(function () {
        let $loginBtn = $("#loginBtn");
        // let $toast = $("#toast");
        // <%--let message = ${errorMsg};--%>
        // "" 사용 이유 :  자체가 getAttribute() 로 가져오는 것 ->
        let msg = "${errorMsg}";
        console.log(msg);

        $loginBtn.on('click', function (e) {
            // e.preventDefault();
            $("form").submit();
        });
    })
</script>
