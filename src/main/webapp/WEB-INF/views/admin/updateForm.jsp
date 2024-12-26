<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<%--시큐리티가 갖고 있는 세션 정보에 접근--%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<c:import url="../layout/header.jsp"/>
<body>
<h2>관리자 - 회원 수정</h2>
<div class="container">
    <form action="${root}admin/user/${findUser.id}" method="post">
        <input type="hidden" name="_method" value="PUT" />
        <div class="form-group">
            <label for="user_id">유저 id</label>
            <input class="form-control" id="user_id" value="${findUser.id}" readonly>
        </div>
        <div class="form-group">
            <label for="username">username</label>
            <input class="form-control" id="username" value="${findUser.username}" readonly>
        </div>
        <div class="form-group">
            <label for="email">email</label>
            <input class="form-control" id="email" value="${findUser.email}" readonly>
        </div>
        <div class="form-group">
            <label for="user_role">현재권한</label>
            <input class="form-control" id="user_role" value="${findUser.role}">
        </div>
        <div class="form-group">
            <label for="updateRole">변경권한</label><br>
<%--            <input class="form-control" id="update_role" value="${findUser.role}">--%>
            <select name="updateRole" id="updateRole">
                <option value="USER">일반유저</option>
                <option value="MANAGER">매니저</option>
                <option value="ADMIN">관리자</option>
            </select>
        </div>
        <div>
            <input type="hidden" value="${page}" name="page">
        </div>

        <div>
        <button type="submit" class="btn btn-primary">수정완료</button>
        <button type="button" class="btn btn-danger" name="btnDelete" id="btnDelete" onclick="deleteUser()">삭제</button>
        </div>
    </form>
</div>


</body>
<script type="text/javascript">
    function deleteUser(){
        let userID = $("#user_id").val();

        $.ajax({
            type : 'DELETE',
            url : '/delete/'+ userID,
            dataType : 'json',
            success : function (result){
                console.log(result);
                alert("데이터 삭제 성공");
                location.href = '/users'
            },
            error : function (error){
                console.log(error);
                alert("삭제 실패")
            }
        })
    }


</script>
</html>