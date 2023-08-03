<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<%--main header--%>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<%--community header--%>
<c:import url="/WEB-INF/views/layout/communityHeader.jsp"/>


<!-- 개발자 라운지 - 자유주제, 커리어 고민-->

<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>
