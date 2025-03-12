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
            height: 85px !important;
            align-items: center;
            padding: 0 !important;
            max-width: 1281px;
            margin: 0 auto;
            background-color: white !important;
            /* color: #454545; */
            font-weight: bolder;
        }

        .navbar-nav {
            display: flex;
            -ms-flex-direction: row;
            flex-direction: row;
            padding-left: 0;
            margin-bottom: 0;
            list-style: none;
            gap: 20px;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            align-content: center;
        }
    </style>
</head>
<body>

