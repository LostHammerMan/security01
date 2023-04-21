<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="../layout/top_head.jsp"/>
    <body>
        <div class="container">
            <h2>회원관리 페이지</h2>
            <P>간편하게 회원관리 하세요</P>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>email</th>
                    <th>권한</th>
                    <th>가입날짜</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="users" items="${findAllUser}">
                    <tr>
                        <td>${users.id}</td>
                        <td>${users.username}</td>
                        <td>${users.email}</td>
                        <td>${users.role}</td>
                        <td>${users.createDate}</td>
                        <td>
                            <div>
                                <a href="${root}user/${users.id}" class="btn btn-primary">수정</a>
                                <a href="${root}" class="btn btn-danger">삭제</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>