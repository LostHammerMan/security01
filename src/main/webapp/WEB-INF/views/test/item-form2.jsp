<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>
    .profileImgTest {
        width: 500px;
        height: 300px;
        border-radius: 70%;
    }
</style>
    <form>
        <div class="card">
            <div class="card-header">
                <p>Ajax 업로드 연습</p>
            </div>
            <div class="card-body">
                <img class="profileImgTest" src="${root}static/img/ssalmuk.jpg"><br>

<%--label for="요소 id--%>
                <label for="profileImg" class="btn btn-secondary">이미지 업로드</label>
                <input id="profileImg" name="profileImg" type="file" >
            </div>
        </div>
    </form>
<script type="text/javascript">
    $(function (){
        // onchange() : 변경 감지 탐지
        // Jquery 에서는 onchange() 없음 -> $("#profileImg").on('change',function ()
        $("#profileImg").on('change',function (){
            let data=new FormData();
            // let profileImg = $("input[name='profileImg']").get(0).files[0];
            let profileImg2 = $("#profileImg").get(0).files[0];
            // let profileImg = $("input[name='profileImg']").files[0];
            data.append("profileImg",profileImg2);

            $.ajax({
                url : '${root}profileTest/profileAjaxUpload',
                processData : false,
                contentType : false,
                //data : JSON.stringify(data),
                data : data,
                enctype : 'multipart/form-data',
                type : 'POST',
                success : function (res){
                    alert("전송 성공");
                    console.log(res);
                },
                error : function (err){
                    alert("실패")
                    console.log(err);

                }
            })

        })
    })
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>
