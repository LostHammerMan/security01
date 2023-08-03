<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 2023-08-03
  Time: 오후 5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        header {
            width: 100%;
            height: 100px;
            background-color: #9dff63;

        }

        section {
            width: 100%;
            height: 500px;
        }

        /* section - header */
        section .part1 {
            background-color: #1cc88a;
            float: left;
            line-height: 500px;
        }

        /* section - article */
        section .part2 {
            width: 40%;
            line-height: 500px;
            background-color: #6e707e;
            float: left;

        }

        section aside {
            width: 20%;
            height: 50%;
            float: left;
            line-height: 250px;
        }

        aside {
            background-color: #feff6c;
        }



        footer {
            width: 100%;
            height: 100px;
            clear: both;
            background-color: #0f6674;
        }
    </style>
</head>
<body>
  <header class="header">header</header>
  <section>section
      <header class="part1">section_header</header>
      <article class="part2">section_article</article>
      <aside>section_aside</aside>
  </section>
  <footer>footer</footer>
</body>
</html>
