<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>

    .container {
        margin-top: 50px;
    }

    #left-header {
        background-color: #EBF5ff;
    }

    .profile-basic {
        display: flex;
        margin-left: 10px;
    }

    .profile-addr {
        display: flex;
        margin-bottom: 10px;
        margin-left: 10px;
    }

    input.form-control {
        /* width: 700px; */
        width: auto;
        min-width: 538px;
        /*background-color: #1c7430;*/
    }

    /*form-control btn btn-primary*/
    button.btn.btn-primary {
        width: 70px;
        margin-left: 20px;
        /*background-color: #6e707e;*/
    }

    #addrFindBtn {
        margin-left: 20px;
        /*background-color: #1c7430;*/
        width: 70px;
    }

    .avatar {
        margin-top: 10px;
    }

    .profile_email {
        display: flex;
        margin-left: 10px;
    }

    .item-text {
        margin-left: 2px;
    }


    #EmailModal {
        width: 700px;
        height: 400px;
    }

    #emailClose {
        width: 59px;
    }

    #emailModBtn {
        width: 59px;
        margin-left: 10px;
    }

    #emailAuthBtn {
        margin-left: 5px;
        width: 90px;
        float: right;
    }

    #email_icon {
        width: 50px;
        height: 50px;
    }

    #email_check_warn {
        margin-left: 10px;
        color: red;
    }

    .btn_duo_popup {
        margin-top: 10px;
    }

    /*emailClose, emailMod, emailAuthBtn*/
    /*.modal {
        position: absolute;
        background-color: #6e707e;
        width: 70%;
        height: 80%;
    }*/

    .formLabelTitle {
            color: #333;
            font-size: 14px;
            font-weight: 700;
            line-height: 20px;
            letter-spacing: -.28px;
        }


</style>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-3" id="left-header"><!--left col-->
            <div class="text-center">
                <c:choose>
                    <c:when test="${loginUser.userProfile.fileUrl == null}">
                        <a href="${root}user/modifyProfile" ><img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail"
                                                                  alt="avatar"></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${root}user/modifyProfile" ><img src="${root}api/profileImages/${loginUser.userProfile.fileName}" class="avatar img-circle img-thumbnail"
                                                                  alt="avatar"></a>
                    </c:otherwise>
                </c:choose>


                <h6 class="mt-2">${loginUser.username}</h6>
<!-- <%--                <input type="file" class="text-center center-block file-upload">--%> -->
            </div>
            <hr>


            <div class="panel panel-default">
                <div class="panel-heading"><a href="${root}user/modifyForm">내프로필</a></div>
                <div class="panel-body"><a href="${root}user/modifyPwdForm">보안변경</a></div>
            </div>

        </div><!--/col-3-->
        <div class="col-sm-9">
            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <hr>
                        <div class="form-group">
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="username" class="formLabelTitle">아이디</label>
                                    <div class="profile-basic">
                                        <input type="text" class="form-control" name="username" id="username"
                                               value="${loginUser.username}"/>
                                        <button type="button" class="form-control btn btn-primary"
                                                id="usernameModifyBtn" name="usernameModifyBtn">수정
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label for="email" class="formLabelTitle">이메일</label>
                                    <div class="profile-basic">
                                        <input type="email" class="form-control" name="email" id="email"
                                               value="${loginUser.email}">
                                        <button type="button" class="form-control btn btn-primary" id="emailModifyBtn"
                                                name="emailModifyBtn">수정
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="skillTagIdx" Class="formLabelTitle">관심분야</label><br>
                                <select class="form-control skillTagIdx" id="skillTag" multiple="multiple">
                                    <option value="1">Spring</option>
                                    <option value="2">Python</option>
                                    <option value="3">AWS</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label for="location" class="formLabelTitle">주소</label>
                                    <div class="profile-addr">
                                        <input type="text" class="form-control" id="sample2_postcode"
                                               name="sample2_postcode" placeholder="우편번호"
                                               value="${loginUser.userAddr.zipCode}">
                                        <input type="button" class="btn btn-secondary" id="addrFindBtn"
                                               name="addrFindBtn" onclick="sample2_execDaumPostcode()" value="검색"><br>
                                    </div>

                                    <div class="form-group">
                                        <div class="profile-addr">
                                            <input type="text" class="form-control" id="sample2_address"
                                                   name="sample2_address" placeholder="주소"
                                                   value="${loginUser.userAddr.postAddr1}"><br>
                                        </div>
                                        <div class="profile-addr">
                                            <input type="text" class="form-control" id="sample2_detailAddress"
                                                   name="sample2_detailAddress" placeholder="상세주소"
                                                   value="${loginUser.userAddr.postAddr2}">
                                            <button type="button" class="form-control btn btn-primary"
                                                    id="addrModifyBtn" name="addrModifyBtn">저장
                                            </button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
                        <div id="layer"
                             style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
                            <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer"
                                 style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1"
                                 onclick="closeDaumPostcode()" alt="닫기 버튼">
                        </div>
                </div>

            </div><!--/tab-pane-->
        </div><!--/tab-content-->

    </div><!--/col-9-->
</div>
<!--/row-->

 <!-- modal -->
<div id="EmailModal" class="form-group modal">
    <div class="form-group contact_edit_title">
        <h4><strong>${loginUser.username}님</strong>의 이메일을 수정하기 위해 인증절차가 필요합니다.</h4>
    </div>
    <hr/>
    <div class="form-group item-text">
        <img src="${root}static/img/email_icon.jpg" id="email_icon">
        <span>${loginUser.email}</span>
    </div>
    <form name="email_form" action="${root}user/modifyEmail" method="post">
        <div class="form-group">
            <div class="col-xs-6">
                <div class="profile_email">
                    <input type="text" class="form-control" name="modifiedEmailAddr" id="modifiedEmailAddr"
                           placeholder="변경할 이메일을 입력하세요">
                    <button type="button" class="btn btn-secondary" id="emailAuthBtn" name="emailAuthBtn">인증</button>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-6">
                <div class="profile-basic">
                    <input type="text" class="form-control" name="authCode" id="authCode" placeholder="인증번호 입력하세요">
                </div>
            </div>
        </div>
        <div class="form-group">
            <span id="email_check_warn"></span>
        </div>
        <div class="form-group btn_duo_popup" style="margin-left: 10px">
            <a role="button" class="btn btn-danger" id="emailClose" rel="modal:close" href="#">취소</a>
            <button class="btn btn-primary" type="submit" id="emailModBtn" name="emailModBtn">변경</button>
        </div>
    </form>

</div>
<!-- modal end  -->


<c:import url="/WEB-INF/views/layout/footer.jsp"/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function sample2_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    // document.getElementById("sample2_extraAddress").value = extraAddr;

                } /*else {
                    document.getElementById("sample2_extraAddress").value = '';
                }*/

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample2_postcode').value = data.zonecode;
                document.getElementById("sample2_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample2_detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width: '100%',
            height: '100%',
            maxSuggestItems: 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition() {
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth) + 'px';
    }
</script>
<script>
    $(function () {

        /* select2 start*/
        // 초기화
        $('#skillTag').select2();

        // 초기값 설정
        let select2Val = [1,3];
        // let select2Val2 = ${loginUser.user_skillTag.skillTag};
        let select2Val2 = "<c:out value='${loginUser.user_skillTag.skillTag}' />";
        console.log(select2Val2);
        $('#skillTag').val(select2Val).trigger('change');

        /* select2 end */

        /*주소 수정 이벤트*/
        $("#addrModifyBtn").on("click", function () {
            let zipcodeVal = $("input[name=sample2_postcode]").val();
            let postAddr1Val = $("input[name=sample2_address]").val();
            let postAddr2Val = $("input[name=sample2_detailAddress]").val();

            let data1 = {
                "zipCode": zipcodeVal,
                "postAddr1": postAddr1Val,
                "postAddr2": postAddr2Val
            }

            $.ajax({
                url: "${root}api/modifyAddr",
                type: "POST",
                data: JSON.stringify(data1),
                contentType: 'application/json',
                success: function (res) {
                    console.log(res.toString());
                    alert("주소 수정 완료");
                    location.reload();
                },
                error: function (err) {
                    console.log(err);
                    alert("주소 수정 실패");
                }
            })
        });

        /* 이메일 수정버튼 이벤트 - modal*/
        $("#emailModifyBtn").on("click", function (e) {
            // modal 창 열 때 초기화
            document.forms['email_form'].reset();
            $("#email_check_warn").text("");

            $("#modifiedEmailAddr").attr("readonly", false);
            $("#EmailModal").modal();
        });
    });

    /* 이메일 인증 이벤트 - modal */
    $("#emailAuthBtn").on("click", function (e) {
        let modifiedEmailAddrVal = $("#modifiedEmailAddr").val();
        let data2 = {
            "modifiedEmailAddr": modifiedEmailAddrVal
        }
        $.ajax({
            url: "${root}api/modifyEmailAuth",
            type: "post",
            data: JSON.stringify(data2),
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                alert("인증번호 전송 성공");
                console.log("result = {}", res);
                $("#modifiedEmailAddr").attr("readonly", true);
            },
            error: function (err) {
                alert("인증번호 전송 실패");
                let error = JSON.parse(err, null, 2);
                console.log(error.responseText)
            }
        })
    });

    /* 인증번호 확인 이벤트 */
    $("#authCode").blur(function () {
        let inputAuthNum = $("#authCode").val();
        let $email_check_warn = $("#email_check_warn");

        $.ajax({
            url : "${root}api/authNumCheck" + inputAuthNum,
            type : "GET",
            data : {
                "inputAuthNum" : inputAuthNum,
            },
            success : function (result){
                console.log(JSON.stringify(result, null, 2));
                $email_check_warn.text(result.message);
                $email_check_warn.css("color", "green");
            },
            error : function (jqXHR){
                let response = JSON.parse(jqXHR.responseText);
                console.log(response);
                $email_check_warn.text(response.message);
                $email_check_warn.css("color", "red");
            }
        })
    });

    <!-- 이메일 변경 버튼 이벤트 - modal -->
    $("#emailModBtn").on("click", function (e){
        let $email_check_warn = $("#email_check_warn");
        e.preventDefault();
        let data3 = {
            "modifiedEmailAddr" : $("#modifiedEmailAddr").val(),
            "authCode" : $("#authCode").val()
        }

        console.log("%s", JSON.stringify(data3, null, "\t"));
        // $("form").submit();

        $.ajax({
            url : "${root}api/modifyEmail",
            type : "POST",
            /*data : {
                "modifiedEmailAddrVal" : modifiedEmailAddrVal,
            },*/
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(data3),
            success : function (res){
                console.log(res.toString())
                alert("이메일 변경 성공");
                location.href = "${root}user/modifyForm";
            },
            error : function (jqXHR, textStatus, errorThrown){
                alert("이메일 변경 실패");
                let error = JSON.parse(jqXHR.responseText);
                // let error = jQuery.parseJSON(jqXHR.responseText);
                let errorMsg = error.message;
                console.log("error = {}", error);
                console.log(error.message);
                $email_check_warn.text(errorMsg);
                // $email_check_warn.text(error.responseText.message);
            }
        })
    });

</script>
</html>