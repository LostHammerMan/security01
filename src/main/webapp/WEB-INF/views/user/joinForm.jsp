<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<%--
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="${root}static/css/bootstrap.css">
    <script src="${root}static/js/jquery-3.6.4.min.js"></script>
    <script src="${root}static/js/bootstrap.js"></script>
--%>
    <style type="text/css">
        a {
            text-decoration: none;
        }

        .bg-light {
            height: 1053px;
            padding-top: 20px;
            padding-bottom: 75px;
        }

        /* 입력창*/
        .container.py-4 {
            margin: 0 auto;
            width: 503px;
        }

        /* 에러 */
        /* .invalid-feedback {
             color: red;
         }*/

        .card {
            border-radius: 1rem;

        }

        h1 {
            margin-bottom: -0.5rem;
            margin-top: .5rem;
            font-weight: 500;
            line-height: 1.2;
        }

        .formLabelTitle {
            color: #333;
            font-size: 14px;
            font-weight: 700;
            line-height: 20px;
            letter-spacing: -.28px;
        }

        .select2-selection__rendered {
            vertical-align: middle;
        }

        .select2-container--default .select2-selection--multiple {
            border: 1px solid #d1d3e2;
        }   
    </style>

<section class="bg-light">
    <div class="container py-4">
        <div class="card p-4">
            <form:form action="${root}user/joinProc" method="post" class="frm" modelAttribute="joinUserDto" cssClass="has-validation">
                <div class="form-group">
                    <form:label path="username" cssClass="form-label mt-4 formLabelTitle">아이디</form:label><br>
                    <form:input type="text" path="username" placeholder="username" cssClass="form-control"/><br>
                    <form:errors path="username" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <form:label path="password" cssClass="formLabelTitle">비밀번호</form:label><br>
                    <form:input type="password" path="password" placeholder="password" cssClass="form-control"/><br>
                    <form:errors path="password" cssStyle="color: red"/>

                </div>
                <div class="form-group">
                    <form:label path="skillTagIdx" cssClass="formLabelTitle">관심분야</form:label><br>
                    <form:select path="skillTagIdx" id="skillTag" cssClass="form-control" multiple="multiple">
                        <form:option value="1">Spring</form:option>
                        <form:option value="2">Python</form:option>
                        <form:option value="3">AWS</form:option>
                    </form:select>
                    <br>
                    <br>
                    <form:errors path="skillTagIdx" cssStyle="color: red"/><br>
                </div>

                <form:label path="email_addr" cssClass="formLabelTitle">이메일</form:label>
                <div class="form-row" style="justify-content: space-between; flex-wrap: nowrap; align-items: center;">
                    <div class="col-auto">
                        <form:input path="email_id" id="email_id" placeholder="email" cssClass="form-control" readonly="false"/>
                    </div>
                    <div class="col-auto">
                        <span style="vertical-align: middle">@</span>
                    </div>
                    <div class="col-auto">
                        <form:select path="email_domain" placeholder="email" cssClass="form-control" disabled="false">
                            <form:option value="@naver.com">네이버</form:option>
                            <form:option value="@google.com">구글</form:option>
                            <form:option value="etc" id="etc2">기타</form:option>
                        </form:select>
                    </div>

                    <div class="col-auto" id="authBtnDiv">
                        <button type="button" name="emailAuth" id="emailAuth" class="btn btn-dark">인증</button>
                    </div>

                </div>
                <div class="form-row">
                    <div class="col-auto w-100" style="margin-top: 20px" id="div_auth">
                        <form:input path="auth_code" class="form-control" placeholder="인증코드 입력하세요"/>
                    </div>
                </div>
                

                <div class="form-row">
                    <div class="col-auto w-100">
                        <form:hidden path="email_addr" id="email_addr"/>
                        <span id="email_check_warn"></span>
                    </div>
                </div>
                <br>
                <form:errors path="email_id" element="div" cssStyle="color: red"/><br>
                <form:errors path="email_addr" cssStyle="color: red"/>

                <div class="form-group mt-2">
                    <div style="justify-content: flex-start">
                        <form:button class="btn btn-primary" id="submitBtn">회원가입</form:button>
                        <button type="button" class="btn btn-success" onclick="location.href='${root}'">메인화면</button>
                    </div>
                </div>
                
            </form:form>
        </div>
    </div>
</section>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

<script type="text/javascript">
    $(function () {

        // 관심분야
        $('#skillTag').select2({
            placeholder: "하나 이상의 관심 스킬을 선택하세요."
        });

        // 기타 선택시 form:input 태그 추가
        $("#email_domain").change(function () {
            if ($("#email_domain").val() === "etc") {
                $("#div_auth").prepend(
                    "<input name='email_etc' id='email_etc' class='form-control' style='margin-bottom: 20px; justify-content: flex-end' " +
                    "placeholder='@ 빼고 입력하세요'/>"
                    // "<p>" + "새로추가" + "</p>"
                )
            } else {
                $("input[name=email_etc]").remove();
            }
        });


        // 인증 메일 전송 이벤트
        $("#emailAuth").on("click", function (text, reviver) {

            let email_domain = $("#email_domain").val();
            let email_id = $("#email_id").val();

            if (email_domain === "etc"){
                email_domain = "@" + $("#email_etc").val();
            }

            /*if ($("#email_domain").val() === "etc"){
                $email_domain = "@" + $("#email_etc").val();
            } else {
                $email_domain = $("#email_domain").val();
            }*/

            // ajax 에 사용할 data 정의시 아래와 같이 정의해서 사용
            let data = { // JavsScript Object
                email_id: email_id,
                email_domain: email_domain
            };
            console.log(JSON.stringify(data));

            let data2 = { // JavsScript Map
                "email_id": email_id,
                "email_domain": email_domain
            };
            console.log(JSON.stringify(data2));

           /* let data3 = {
                email_id, email_domain
            }
            console.log(JSON.stringify(data3));*/

            $.ajax({
                url: "${root}api/emailAuth",
                type: "post",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    alert("인증번호 전송 성공");
                    console.log(data);
                    $("#email_id").attr("disabled", "true");
                    $("#email_domain").attr("disabled", "true");
                    $("#email_etc").attr("disabled", "true");
                },
                error: function (jqXHR) {
                    alert("인증번호 전송 실패");
                    let err = JSON.parse(jqXHR, null, 2);
                    console.log(err.responseText);
                }
            })

        });

        // 인증 코드 일치 확인
        $("#auth_code").blur(function(){ // blur : 이벤트가 fucus 를 잃었을 때 요소 전달
            // let auth_code = $("input[name=auth_code]").text();
            <%--let authNum = '<%= session.getAttribute("code")%>'; // 저장되어 있는 session 값 가져오기--%>
            let $email_warn =  $("#email_check_warn");
            let inputAuthNum = $("#auth_code").val();
            console.log("inputAuthNum = " + inputAuthNum);

            // 파라미터 하나만 전송하는 경우 -> get 방식으로 처리
            $.ajax({
                url : "${root}api/authNumCheck" + inputAuthNum,
                type : "GET",
                data : {
                    "inputAuthNum" : inputAuthNum
                    // auth_code
                },
                success : function (result) { // response = result
                    console.log(JSON.stringify(result, null, 2)); // java object -> json
                    $email_warn.text(result.message);
                    $email_warn.css('color', 'green');
                },
                error : function (jqXHR) { // a=jqXHR, b=textStatus(error), c=errorThrown
                    // JSON: jqXHR.responseText, JS Object: jqXHR.responseJSON
                    let response = JSON.parse(jqXHR.responseText); // json -> java object
                    console.log(JSON.stringify(response, null, 2)); // 에러 메시지 json 형태로 출력
                    console.log(jqXHR.responseText); // 에러 메시지 json 형태로 출력
                    console.log(response);

                    // json.stringfy(value, replacer, space)
                    // value : json 문자열로 변환할 값
                    // replacer : json 문자열에 포함될 값 객체의 속성을 선택하기 위한 String, Number 객체들의 배열
                    //  - replacer 값이 null이거나, 제공되지 않으면 객체의 모든 속성들이 json 문자열 결과에 포함됨
                    // space : 가독성을 목적으로 json 문자열 출력에 공백을 삽입하는데 사용되는 String, Number 객체
                    $email_warn.text(response.message); // message
                    $email_warn.css('color', 'red');


                }
            });

           /* setTimeout(function (){
            }, 100)*/

            /*if (inputNum === authNum){
               /!* $("#email_check_warn").after(
                    "<span name='authenticated' id='authenticated' class='form-control' style='color: red; margin-bottom: 20px; justify-content: flex-end'>"
                    + "인증코드가 일치함." +"</span>"
                )*!/
                $email_warn.html("인증코드가 일치합니다. 계속 진행하세요");
                $email_warn.css('color', 'red');


            }else {
                $email_warn.html("인증코드가 일치하지 않습니다.");
                $email_warn.css('color', 'red');
                $("#authNum").focus();

            }*/

        });

        $("#submitBtn").on("click", function (e){
            e.preventDefault();
            let email_etc = $("#email_etc");
            let email_domain = $("#email_domain");
            let email_addr = $("#email_addr");
            // let email_id = $("#email_id");
            console.log("email_etc = " + email_etc.val());
            console.log("email_domain = " + email_domain.val());

            if(email_domain.val() === "etc"){
                $("#etc2").attr("value", "@" + email_etc.val())
            }
            // email_addr.attr("value", email_id.val() + email_domain.val());

            console.log($("form").serialize());

            $("#email_id").removeAttr("disabled");
            email_etc.removeAttr("disabled");
            email_domain.removeAttr("disabled");
           /* $("#email_domain").removeAttr("disabled");
            $("#email_etc").removeAttr("disabled");*/

            $("form").submit();
        });


    });
</script>
</html>