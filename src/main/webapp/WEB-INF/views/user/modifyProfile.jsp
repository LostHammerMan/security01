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
        display: grid;
    }

    .left-col {
        background-color: #F2F2F2;
    }

    .nav-link {
        color: black;
    }

    #profileImgFile {
        border-radius: 80%;
    }
</style>
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

<div class="container-sm d-flex" style="border: #1c7430; flex-direction: column; align-items: center">

    <div class="container-header" style="margin-right: 374px">
        <p style="color:black;text-align:start;font-size: 20px; font-weight: bold">프로필 수정</p>
        <p style="color: gray; text-align: start;font-size: 13px;">대표 프로필과 별명을 수정 하실 수 있어요</p>
    </div>
    <div class="container-body justify-content-center" style="width: 624px;">
        <div class="justify-content-center">
            <form:form action="${root}user/modifyProfileProc" enctype="multipart/form-data"
                       modelAttribute="modifyUserProfileDto">
                <div class="container " style="width: 100%; height: 100%;">
                    <div class="row profile">
                        <div class="col-lg-3 border d-flex m-0 left-col"
                             style="justify-content: center; align-items: center">
                            <p class="m-0">프로필 사진</p>
                        </div>
                        <div class="col-lg-9 border ">
                            <div class="m-3">
                                <c:choose>
                                    <c:when test="${loginUser.userProfile.fileName == null}">
                                        <img class="card-img-top" src=
                                                "https://media.geeksforgeeks.org/wp-content/uploads/20190506125816/avt.png"
                                             style="height: 140px; width: 140px" id="profileImgFile" name="">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="card-img-top"
                                             src="${root}api/profileImages/${loginUser.userProfile.fileName}"
                                             style="height: 140px; width: 140px" id="profileImgFile" name="">
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="flex-column m-3">
                                <label class="btn btn-outline-dark" for="profileImg"
                                       style="margin-top: 9px;">사진변경</label>
                                <form:input path="profileImg" id="profileImg" name="profileImg" type="file"
                                            style="display: none" onchange="uploadImg()"/>
                                <form:button class="btn btn-outline-secondary" id="profileImgDel"
                                             name="profileImgDel" type="button"
                                             onclick="profileImaDel()">삭제</form:button>
                            </div>
                        </div>
                    </div>
                    <div class="row nickName">
                        <div class="col-lg-3 border d-flex m-0 left-col"
                             style="justify-content: center; align-items: center; height: 50px">
                            <p class="m-0">별명</p>
                        </div>
                        <div class="col-lg-9 border d-flex" style="align-items: center">
                            <div class="form-row">
                                <form:input path="nickName" class="ml-3" type="text" id="nickName" name="nickName"
                                            value="${loginUser.nickName}" style="width: 50%; height: 60%"/>
                                <form:errors path="nickName" cssClass="d-none" id="nickNameError" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-lg-1 mb-lg-1 btn_wrap" style="justify-content: center">
                        <button id="profileSubmitBtn" name="submitBtn" class="btn btn-outline-primary m-1" type="submit">적용</button>
                        <button class="btn btn-outline-danger m-1" type="button" id="cancelBtn">취소</button>
                    </div>
                </div>
                <form:input path="profileImgName" type="hidden" id="profileImgName" name="profileImgName"/>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        if ("${profileErrorMsg}" != null && "${profileErrorMsg}" !== ""){
            alert("${profileErrorMsg}");
        }

        // 전송 버튼 클릭이벤트
        $("#profileSubmitBtn").on("click",function (e) {
            e.preventDefault();
            if (confirm("프로필 변경 사항을 적용하시겠습니까?")) {
                // 확인 클릭
                $("form").submit();
            }
            // $("form").submit();

            // eq : '=', ne : '!=', empty : null 체크, not empty : not null
        });

        $("#cancelBtn").on("click", function () {
            location.href = "${root}user/modifyForm";
        });
    });

    // 프로필 이미지 파일 업로드
    function uploadImg() {

        let formData = new FormData();
        let inputFile = $("#profileImg").get(0).files[0];

        formData.append("profileImg", inputFile);

        // let profileVal = $("profileImgFile").val();

        $.ajax({
            url: '${root}api/updateProfileImg',
            type: 'POST',
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success(res) {

                console.log("success");
                alert(res.message);
                console.log(JSON.stringify(res, null, 2));
                console.log(res.objectData.storeFileName);
                let fileName = res.objectData.storeFileName;
                $("#profileImgFile").attr("src", '${root}api/profileImages/' + fileName);
                $("#profileImgName").attr("value", fileName);
            },
            error(jqXHR, textStatus, errorThrown) {
                let result = JSON.parse(jqXHR.responseText)

                alert(result.message);
                // jqXHR : 서버에서 ResponseEntity 에 담아서 보낸 응답 메시지,
                //        - 데이터 내부에서 응답 텍스트와 응답 코드 확인 가능
                // responseText : ResponseEntity 응답 메시지로 보낸 텍스트

                // JSON.parse : 텍스트를 객체 형태로 변환
                // 위 result 의 값이 변환되어 객체타입으로 저장됨 -> 프로퍼티를 조회하거나, 값 가져올 수 있음

                // 문자열로 변환하는 이유
                // - 인코딩이 필요하거나 저장되는 공간이 문자열만 가능한 경우이거나
                // - 최대한 단순한 구조로 저장하기 위함
                console.log("result = " + result.message);
                console.log("error!!!!!");
            }
        })
    }

    // 업로드 이미지 삭제
    function profileImaDel() {
        let target = $("#profileImgFile");
        // let fileName = target.data("name");
        let src = $('#profileImgFile').attr("src");

        let pos = src.lastIndexOf("/");

        // originalFileName.substring(pos + 1);
        let fileName = src.substring(pos + 1);
        console.log("============================")
        console.log("========== profileImaDel ==============")
        console.log("fileName = " + fileName)
        $.ajax({
            url: '${root}api/deleteProfileImg/' + fileName,
            type: 'GET',
            success(res) {
                console.log("delete success......");
                target.attr("src", "https://media.geeksforgeeks.org/wp-content/uploads/20190506125816/avt.png");
                $("#profileImgName").attr("value", "");
            },
            error(err) {
                console.log("delete fail....");
            }
        })

    }
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

