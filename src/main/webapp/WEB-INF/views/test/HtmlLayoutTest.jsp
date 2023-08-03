<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>구조요소</title>
    <style>
        body {
            margin: 0; /*모든 콘텐츠가 인터넷 창에 딱 붙게*/
        }

        h2 {
            margin: 0;
        }

        header {
            width: 100%;
            height: 100px;
            background-color: aquamarine
        }

        header h1 {
            width: 20%;
            height: 100px;
            text-align: center;
            line-height: 100px;
            /*줄간격값과 높이값을 동일하게 주면 세로 가운데 정렬된다(콘텐츠가 한줄일때만 가능*/
            float: left; /*이미지 배치 효육적으로 하기 위한 속성*/
            margin: 0; /*붕뜨게 하는 margin을 없애야함*/
            background-color: antiquewhite
        }

        header h1 a {
            font-size: 30px;
            color: hotpink
            text-decoration: none; /*a태그 밑줄 삭제*/
        }

        header nav {
            float: left;
            width: 80%;
            height: 100px;
            background-color: cadetblue
        }

        header nav ul {
            margin: 40px auto 0;
            padding: 0;
            list-style: none; /* 리스트 목록 (네모) 없게*/
            width: 80%;
            height: 25px;
            background-color: forestgreen
        }

        header nav ul li {
            width: 20%;
            line-height: 25px;
            float: left; /*세로형인 리스트 영역을 가로로 바꿔줌*/
            text-align: center; /*li영역 가운데 정렬*/
        }

        header nav ul li a {
            text-decoration: none; /*밑줄삭제*/
            font-size: 14px;
            color: #ff0;
        }

        /* ---------------------------------------------------------------------------------------------------------------*/
        section {
            width: 100%;
            height: 500px;
            background-color: beige
        }

        section .part1, section .part2 {
            width: 40%;
            float: left; /*가로정렬*/
            line-height: 500px; /*높이값을 동일하게 줘서 세로 가운데 정렬*/
            text-align: center;
        }

        section .part1 {
            background-color: lightgreen;
        }

        section .part2 {
            background-color: lightcyan;
        }

        section .part3, aside {
            width: 20%;
            height: 50%;
            float: left;
            text-align: center;
            line-height: 250px
        }

        section .part3 {
            background-color: cornsilk;
        }

        aside {
            background-color: lightsalmon
        }

        /* ---------------------------------------------------------------------------------------------------------------*/
        footer {
            clear: both; /*float초기화 : float의 영향을 받지 않겠다*/
            height: 100px;
            background-color: #999;
            padding-top: 10px;
            font-size: 12px;
        }

        footer div {
            width: 70%;
            height: 30px;
            background-color: lightyellow;
            margin: auto;
            margin-bottom: 10px;
            padding-left: 30px;
            line-height: 30px;
        }
    </style>
</head>
<body>
<!-- div#wrap>(header>h1+nav>ul>(li>a[#])*5)-->
<header>
    <h1><a href="#">반응형 웹</a></h1>
    <nav>
        <ul>
            <li><a href="#">MENU01</a></li>
            <li><a href="#">MENU02</a></li>
            <li><a href="#">MENU03</a></li>
            <li><a href="#">MENU04</a></li>
            <li><a href="#">MENU05</a></li>
        </ul>
    </nav>
</header>
<!-- section>article.part$*4-->
<section>
    <article class="part1">
        <h2>콘텐츠그룹01</h2>
    </article>
    <article class="part2">
        <h2>콘텐츠그룹02</h2>
    </article>
    <article class="part3">
        <h3>주요기사</h3>
    </article>
    <aside>광고</aside>
</section>
<!-- footer>div.fo$*2-->
<footer>
    <div class="fo1">
        경기도 부천시 오정구 삼정동 032)674-5685/
        icoxpub@naver.com
    </div>
    <div class="fo2">
        Copyright &amp;copy; All rights reserved.
    </div>
</footer>
</body>
</html>