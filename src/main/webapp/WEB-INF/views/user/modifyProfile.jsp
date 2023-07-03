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
            <form:form enctype="multipart/form-data">
                <div class="container " style="width: 100%; height: 100%;">
                    <div class="row profile">
                        <div class="col-lg-3 border d-flex m-0 left-col"
                             style="justify-content: center; align-items: center">
                            <p class="m-0">프로필 사진</p>
                        </div>
                        <div class="col-lg-9 border ">
                            <div class="m-3">
                                <img class="card-img-top" src=
                                        "https://media.geeksforgeeks.org/wp-content/uploads/20190506125816/avt.png"
                                     style="height: 140px; width: 140px">
                            </div>
                            <div class="flex-column m-3">
                                <label class="btn btn-outline-dark" for="profileImgFile" style="margin-top: 9px;">사진변경</label>
                                <input id="profileImgFile" name="profileImgFile" type="file" style="display: none" onchange="uploadImg()" />
                                <button class="btn btn-outline-secondary" type="button">삭제</button>
                            </div>
                        </div>
                    </div>
                    <div class="row nickName">
                        <div class="col-lg-3 border d-flex m-0 left-col"
                             style="justify-content: center; align-items: center; height: 50px">
                            <p class="m-0">별명</p>
                        </div>
                        <div class="col-lg-9 border d-flex" style="align-items: center">
                            <input class="ml-3" type="text" style="width: 50%; height: 60%">
                        </div>
                    </div>
                    <div class="row mt-lg-1 mb-lg-1 btn_wrap" style="justify-content: center">
                        <button class="btn btn-outline-primary m-1" type="button">적용</button>
                        <button class="btn btn-outline-danger m-1" type="button">취소</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript">function uploadImg() {

    let formData = new FormData();
    let inputFile = $("input[name='profileImgFile']");
    let inputFiles = inputFile[0].files;

    console.log("inputFile = {}", inputFile);
    formData.append("profileImgFile", inputFiles);

    // let profileVal = $("profileImgFile").val();

    $.ajax({
        url : '${root}api/profileImg',
        type : 'POST',
        data : JSON.stringify(formData),
        processData : false,
        contentType : false,
        success(res){
            console.log(res);
        },
        error(err){
            console.log(err);
        }
    })
}

$(function (){

        function uploadImg (){
            let profileVal = $("profileImgFile").val();

            $.ajax({
                url : '${root}api/profileImg',
                type : 'POST',
                data : {
                    "profileImgFile" : profileVal
                },
                success(res){
                    console.log(res);
                },
                error(err){
                    console.log(err);
                }
            })
        }
        /*$("#profileImgFile").onchange(function (){


    })*/

    });
</script>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>

