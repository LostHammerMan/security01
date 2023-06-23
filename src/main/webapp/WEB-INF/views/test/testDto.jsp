<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

    <c:import url="../layout/header.jsp"></c:import>
    <h3>Validator Test</h3>
    <label for="testEmailAddr">testEmailAddr</label>
    <input type="text" id="testEmailAddr" name="testEmailAddr"><hr>

    <label for="addr">addr</label>
    <input type="text" id="addr" name="addr"><hr>

    <label for="userName">userName</label>
    <input type="text" id="userName" name="userName"><hr>

    <span id="examSpan"></span><hr>
    <button type="button" name="submitBtn" id="submitBtn">전송</button>
</body>
<script>
    $(function (){
        $("#submitBtn").on("click", function (e){
            e.preventDefault();
            let data = {
                "testEmailAddr" : $("#testEmailAddr").val()
            }
            $.ajax({
                url : "${root}test/testDto",
                type : "POST",
                data : JSON.stringify(data),
                contentType : "application/json; charset=utf-8",
                success : function (res){
                    console.log(res);
                    $("#examSpan").text(res);
                },
                error : function (err){
                    alert("실패");
                    $("#examSpan").text(err.responseText);
                }


            })
        })
    })
</script>
</html>
