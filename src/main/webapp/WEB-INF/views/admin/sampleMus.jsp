<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 2023-04-28
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!---
mustache.js - Logic-less {{mustache}} templates with JavaScript
https://github.com/janl/mustache.js/
--->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>JavaScript Mustache template</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <%--  <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>--%>
    <script src="/static/js/mustache.js" type="module"></script>

</head>

<body>

<div id="mypanel">
    <table border="1px solid black">
        <thead>
        <tr>이름</tr>
        <tr>나이</tr>
        </thead>
        <tbody id="studentsAll">

        </tbody>
    </table>
</div>

<button id="btn">Load</button>

<script type="module">

    import Mustache from "/static/js/mustache.js";

    $("#btn").on('click', function () {

        let data = {
            students: [
                {
                    name: "lee",
                    age: 10
                },
                {name: "park", age: 20},
                {name: "seo", age: 30}

            ]
        };

        // let template = "Hello {{#students}}<tr><td>{{name}}</td><td>{{age}}</td></tr>{{/students}}";
        // $("#studentsAll").innerHTML = Mustache.render(template, data);
        let template = $('#sample').html();
        let text = Mustache.render(template, data);

        $("#studentsAll").html(text);
    });
</script>
<script type="text/x-mustache" id="sample">
    <h1>제대로 작동하는가</h1>
    {{#students}}
    <tr>
        <td>{{name}}</td>
        <td>{{age}}</td>
    </tr>
    {{/students}}
</script>

</body>
</html>