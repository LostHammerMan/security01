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

<%--    수정--%>
    <!-- CSS Files -->
    <link rel="stylesheet" href="${root}static/css/bootstrap.css"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="${root}static/css/toastr.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="${root}static/css/bootstrap-datepicker.min.css"/>
    <link rel="stylesheet" href="${root}static/css/bootstrap-multiselect.css"/>
    <link rel="stylesheet" href="${root}static/css/select2.css"/>
    <!-- <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"> -->
    <link rel="icon" type="image/ico" href="${root}static/img/favicon.ico"/>

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
    <!-- <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script> -->
    <title>Title</title>
    <style>
        footer {
            width: 100%;
            clear: both;
        }
    </style>
</head>
<body>
<div class="index_header">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${root}">메인</a>
            </li>

            <c:forEach var="categoryLists" items="${categoryDtos}">
                <li class="nav-item">
                    <a class="nav-link" href="${root}${categoryLists.categoryPath}">${categoryLists.categoryName}</a>
                </li>
<%--                <a href="#">${categoryLists.categoryName}</a>--%>
            </c:forEach>
            <%--<c:forEach var="cateDto" items="${cateDto}">
                <li class="nav-item">
                    <a class="nav-link" href="${root}study/main">${cateDto.getCategoryName}</a>
                </li>
            </c:forEach>--%>
           <%-- <li class="nav-item">
                <a class="nav-link" href="${root}study/main">스터디</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${root}community/main">커뮤니티</a>
            </li>--%>
        </ul>
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${empty principal}">
                    <li class="nav-item">
                        <a class="nav-link" href="${root}user/joinForm">회원가입</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}user/loginForm">로그인</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="${root}admin/main">관리자</a>
                        </li>
                    </sec:authorize>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}user/modifyForm">회원수정</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}logout">로그아웃</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>

