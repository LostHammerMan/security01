<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<%-- 스프링 시큐리티가 갖고 있는 세션 정보(PrincipalDetail) 에 접근 --%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 수정 -->
    <!-- CSS Files -->
    <link rel="stylesheet" href="${root}static/css/bootstrap.css"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="${root}static/css/toastr.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="${root}static/css/bootstrap-datepicker.min.css"/>
    <link rel="stylesheet" href="${root}static/css/bootstrap-multiselect.css"/>
    <link rel="stylesheet" href="${root}static/css/select2.css"/>
    <link rel="icon" type="image/ico" href="${root}static/img/favicon.ico"/>
    <link href="/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="/static/css/sb-admin-2.min.css" rel="stylesheet">
    <!-- JS Files -->
    <script src="${root}static/js/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="${root}static/js/bootstrap-datepicker.min.js"></script>
    <script src="${root}static/js/bootstrap-datepicker.kr.min.js"></script>
    <script src="${root}static/js/bootstrap-multiselect.js"></script>
    <script src="${root}static/js/select2.full.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/39.0.0/classic/ckeditor.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="${root}static/js/commonToast.js"></script>
    <script src="${root}static/js/mustache.js" type="module"></script>
    <script src="https://kit.fontawesome.com/5d8a0a5e4b.js" crossorigin="anonymous"></script>
    <title>Title</title>
    <style>
        a {
            text-decoration: none;
        }

        footer {
            width: 100%;
            clear: both;
        }

        .index_header {
            margin-bottom: 30px;
        }

        .navbar {
            width: 80%;
            display: flex;
            justify-content: space-between;
            /* height: 46px !important; */
            min-height: 70px !important;
            max-height: 70px !important;
            align-content: center;
            align-items: center;
            margin: 0 auto;
            padding-top: 0 !important;
            padding-bottom: 0 !important;
            max-width: 74rem;
            background-color: white !important;
            /* color: #454545; */
            font-weight: bolder;
            position: static;
        }

        .navbar-nav {
            display: flex;
            flex-direction: row;
            padding-left: 0;
            margin-bottom: 0;
            margin-top: 0;
            list-style: none;
            gap: 20px;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            align-content: center;
        }

        .dropdown-menu {
            position: absolute !important;
        }

        .dropdown-toggle {
            background-color: #fff;
            border: none;
        }

        .dropdown-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 1rem;
        }

        .fastRecruit-btn {
            color: #fff;
            outline: none;
            border: none;
            background-color: #81e1cf;
            font-size: 16px;
            font-weight: 700;
            padding: 8px 22px;
            border-radius: 36px;
            height: 42px;
        }

    </style>
</head>
<body>
<!-- <div class="index_header"> -->
    <!-- <nav class="navbar navbar-expand-sm bg-dark navbar-dark"> -->
    <nav class="navbar navbar-light bg-light">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${root}">
                    <img src="${root}static/img/banner_img/stdyBanner.png" style="width: auto; height: 30px;">
                </a>
            </li>

            <c:forEach var="categoryLists" items="${categoryDtos}">
                <li class="nav-item">
                    <a class="nav-link categoryBtn active" href="${root}${categoryLists.categoryPath}">${categoryLists.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="${root}study/getStudyWriteForm"><button class="fastRecruit-btn">빠른 모집</button></a>
            </li>
            
            <c:choose>
                <c:when test="${empty principal}">
                    <!-- <li class="nav-item">
                        <a class="nav-link" href="${root}user/joinForm">회원가입</a>
                    </li> -->
                    <li class="nav-item">
                        <a class="nav-link active" href="${root}user/loginForm">로그인</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <!-- <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="${root}admin/main">관리자</a>
                        </li>
                    </sec:authorize> -->
                    <li class="dropdown">
                        <button class="dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                            <img src="${root}api/profileImages/${loginUser.userProfile.fileName}" width="30" height="30" class="d-inline-block align-top" alt="">
                        </button>
                        <div class="dropdown-menu">
                            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                                    <a class="dropdown-item" href="${root}admin/main"><i class="fa-solid fa-list-check"></i> 관리자</a>
                            </sec:authorize>
                          <a class="dropdown-item" href="${root}user/modifyForm"><i class="fa-solid fa-user"></i> 마이페이지</a>
                          <a class="dropdown-item" href="${root}logout"><i class="fa-solid fa-right-from-bracket"></i> 로그아웃</a>
                        </div>
                    </li>
                   <!--  <li class="nav-item">
                        <a class="nav-link" href="${root}user/modifyForm">회원수정</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}logout">로그아웃</a>
                    </li> -->
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
<!-- </div> -->
<script>
    $(document).ready(function(){

       
    });
</script>
