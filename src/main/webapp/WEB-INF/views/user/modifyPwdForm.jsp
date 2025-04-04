<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header2.jsp"/>

<style>
    body {
        background: #F2F2F2;
        /* background: -webkit-linear-gradient(to top, #EF629F, #EECDA3);
         background: linear-gradient(to top, #EF629F, #EECDA3);*/
    }

    .container {
        max-width: 550px;
    }

    .has-error label,
    .has-error input,
    .has-error textarea {
        color: red;
        border-color: red;
    }

    .list-unstyled li {
        font-size: 13px;
        padding: 4px 0 0;
        color: red;
    }

    span {
        font-size: 11px;
        vertical-align: middle;
        line-height: 20px;
    }

    .captcha {

    }
</style>

<body>
<nav class="navbar navbar-expand-sm navbar-light mb-3" style="background-color: #E2E2E2;">
    <div class="navbar-nav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${root}user/modifyForm">내프로필</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${root}user/modifyPwdForm">보안변경</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3 class="text-left" style="">비밀번호 변경</h3>
            <span class="">안전한 비밀번호로 내 정보를 보호하세요</span><br>
            <span style="color: red">- 다른 아이디/사이트에서 사용한 적 없는 비밀번호</span><br>
            <span style="color: red">- 이전에 사용한 적 없는 비밀번호</span><span>가 안전합니다</span>
        </div>

        <div class="card-body">
            <form:form action="${root}user/modifyPwdProc" method="post" modelAttribute="modifyUserPwdDto" >
                <div class="form-group">
                    <div class="form-group">
                        <form:input path="nowPw" type="password" data-minlength="4" class="form-control" id="nowPwd" name="nowPwd"
                               data-error="Have atleast 4 characters" placeholder="현재 비밀번호"/>
                        <form:errors path="nowPw" cssStyle="color: red" />
                        <!-- Error -->
<%--                        <div class="help-block with-errors"></div>--%>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <form:input path="newPw" type="password" data-minlength="4" class="form-control" id="newPw" name="newPw"
                               data-error="Have atleast 4 characters" placeholder="새 비밀번호"/>
                        <!-- Error -->
                        <form:errors path="newPw" cssStyle="color: red" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <form:input path="confPw" type="password" class="form-control" id="confPw" name="confPw"
                               placeholder="새 비밀번호 확인"/>
                        <!-- Error -->
                        <form:errors path="confPw" cssStyle="color: red" />
                    </div>
                </div>

                <div class="form-group">
                    <p>아래 이미지를 보이는 대로 입력해주세요</p>
                    <div class="captcha d-flex justify-content-between">
                        <div class="d-flex mr-2" >
                            <img title="캡차이미지" src="${root}api/getCaptchaImg" alt="캡차이미지" id="captchaImg"/>
                        </div>
                        <div class="d-flex flex-column justify-content-between" style="width: 58%">
                            <div class="d-flex justify-content-between">
                                <button id="reload" class="btn btn-outline-info" type="button" onclick="getImage()" style="width: 48%">새로고침</button>
                                <button id="soundOn" class="btn btn-outline-secondary" type="button" onclick="getAudio()" style="width: 48%">음성듣기</button>
                            </div>
                            <div class="mt-2">
                                <form:input path="captchaCode" id="answer" type="text" value="" class="form-control" placeholder="자동 입력 방지문자"/>
                                <form:errors path="captchaCode" cssStyle="color: red" />
                            </div>
                        </div>
                    </div>
                    <%--<div class="captcha d-flex justify-content-between">
                        <div>
                            <img title="캡차이미지" src="${root}api/getCaptchaImg" alt="캡차이미지" id="captchaImg"/>
                        </div>
                        <div class="d-flex flex-column justify-content-between" style="width: 55%">
                            <div class="d-flex justify-content-between">
                                <button id="reload" class="btn btn-outline-info" type="button" onclick="getImage()" style="width: 48%">새로고침</button>
                                <button id="soundOn" class="btn btn-outline-secondary" type="button" onclick="getAudio()" style="width: 48%">음성듣기</button>
                                &lt;%&ndash;                                <button id="soundOn" class="btn btn-outline-secondary" type="button" onclick="javaScript:audio()" style="width: 48%">음성듣기</button>&ndash;%&gt;
                            </div>
                            <div>
                                <input id="answer" type="text" value="" class="form-control" placeholder="자동 입력 방지문자"/>
                            </div>
                        </div>
                    </div>--%>
                </div>

                <div class="form-group">
                    <form:button type="submit" class="btn btn-primary btn-block">확인</form:button>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-danger btn-block">취소</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script type="text/javascript">


</script>


<script type="text/javascript">

    window.onload = function () {
        getImage();	// 이미지 가져오기

       /* document.querySelector('#check').addEventListener('click', function () {
            var params = {answer: document.querySelector('#answer').getAttribute('value')};


            AF.ajax('${root}api/chkCaptchaAnswer', params, function (returnData) {
                if (returnData == 200) {
                    alert('입력값이 일치합니다.');
                    // 성공 코드
                } else {
                    alert('입력값이 일치하지 않습니다.');
                    getImage();
                    document.querySelector('#answer').setAttribute('value', '');
                }
            }, 'json');
        });*/
    }

    /*매번 랜덤값을 파라미터로 전달하는 이유 : IE의 경우 매번 다른 임의 값을 전달하지 않으면 '새로고침' 클릭해도 정상 호출되지 않아 이미지가 변경되지 않는 문제가 발생된다*/
    function getAudio() {
        var rand = Math.random();
        var uAgent = navigator.userAgent;
        var soundUrl = '${root}api/getCaptchaAudio?rand=' + rand;

        if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MISE') > -1) {	/*IE 경우 */
            audioPlayer(soundUrl);
        } else if (!!document.createElement('audio').canPlayType) { /*Chrome 경우 */
            try {
                new Audio(soundUrl).play();
            } catch (e) {
                audioPlayer(soundUrl);
            }
        } else {
            window.open(soundUrl, '', 'width=1,height=1');
        }
    }

    /** captcha 이미지 가져오는 함수 */
    function getImage() {
        let rand = Math.random();
        let url = '${root}api/getCaptchaImg?rand=' + rand;
        console.log(url);
        $("#captchaImg").attr('src', url);

        // document.querySelector('img').setAttribute('src', url);
    }

    function audioPlayer(objUrl) {
        document.querySelector('#ccaudio').innerHTML = '<bgsoun src="' + objUrl + '">';
    }
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

