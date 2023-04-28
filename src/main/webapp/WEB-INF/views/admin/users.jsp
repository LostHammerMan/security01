<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="../layout/top_head.jsp"/>
<body>
<div class="container">
    <div class="container">
        <a href="${root}">메인</a>
        <h2>회원관리 페이지</h2>
        <P>간편하게 회원관리 하세요</P>
    </div>

    <table class="table table-striped" id="userList">
        <thead>
        <tr>
            <th>일괄삭제</th>
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
                <td><input type="checkbox" name="deleteCheck" value="${users.id}"></td>
                <td>${users.id}</td>
                    <%--                        <td><input type="hidden" name="userId" id="userId" value="${users.id}"></td>--%>
                <td>${users.username}</td>
                <td>${users.email}</td>
                <td>${users.role}</td>
                <td>${users.createDate}</td>
                <td>
                    <a href="${root}user/${users.id}" class="btn btn-primary">수정</a>
                        <%--                            <button type="button" name="btnDelete" class="btn btn-danger" onclick="deleteMember(this)">삭제</button>--%>
                    <button type="button" name="btnDelete" class="btn btn-danger" data-id= ${users.id}>삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="button" name="btnDeleteAll" class="btn btn-danger">선택삭제</button>
    <!-- userList -->

</div>
</body>
<script type="module">
    /*// 1. onclick() 사용
    function deleteMember(e){
        // this 와 가장 가까운 tr
        let thisTr = $(e).closest('tr');

        // 위 tr의 0 번째 td 값 가져오기, <form> 아니므로 .text() 사용
        let thisTd = thisTr.find('td:eq(0)').text();

        console.log(thisTr);
        console.log(thisTd);

        $.ajax({
            url : "root/delete/" + thisTd,
            method : "DELETE",
            dataType : "json",
            success : function (result){
                alert("회원삭제 완료");
                console.log(result);
                location.href = "root/users";
            },
            error : function (err){
                alert("회원삭제 실패");
                console.log(err);
            }

        })

    }*/

    // 2. Jquery
    /* $("button[name='btnDelete']").on("click", function (){
         let thisTr = $(this).closest('tr');
         let thisTd = thisTr.find("td:eq(0)").text();

         console.log(thisTd);

         // 이하 ajax
     })*/

    // 3. jquery data() 사용
    //$("button[name='btnDelete']").on("click", function() {
    // import Mustache from "/static/js/mustache.js";

    import Mustache from "/static/js/mustache.js";

    $(document).on("click", "button[name='btnDelete']", function () {
        console.log("btnDelete clicked : " + $(this).data("id"));

        $.ajax({
            url: "${root}user2/" + $(this).data("id"),
            method: "DELETE",
            dataType: "json",
            success: function (result) {
                console.log(result.message);
                alert("회원 삭제 완료");

                <%--location.href = "${root}users";--%>
                // table
                <%--$("#userList").load("${root}users #userList", function (data) {--%>
                <%--   // console.log(data);--%>
                <%--});--%>

                // 1. list 갖고오는 ajax 새로 호출
                $.ajax({
                    url: "${root}user/getUserList",
                    method: "GET",
                    dataType: "json",
                    success: function (result) { // result : key(userList), value(List<User>)
                        console.log(JSON.stringify(result, null, 2));
                        // let userAll = JSON.stringify(userList);

                        /*let data = { name: "Jonathan" };
                        let template2 = "Hello {{ name }}";*/

                        /*let text = Mustache.render(template2, data);
                        console.log(text);*/

                        let template = $("#template").html();
                        let rendered_template = Mustache.render(template, result);
                        console.log(rendered_template)

                        $("tbody").html(rendered_template);

                        // let html = "";
                        // $.each(userList, function(index, user) {
                        //     // html += "<p class='font-weight-bold'>" + user.username + "</p>";
                        //     // html += "<p>" + user.email + "</p>";
                        //     html += "<tr>";
                        //     if(user.username === "") { // == 값, === 값 + type, 1 == "1" true, 1 === "1" false
                        //         html += "<td>존재하지 않는 회원입니다</td>";
                        //     } else {
                        //         html += "<td>" + user.username + "</td>";
                        //     }
                        //     html += "<td>" + user.email + "</td>";
                        //     html += "</tr>";
                        //     $("tbody").html(html);
                        // })
                    }
                })


                // 2. 삭제한 다음에 보내는 데이터에 list를 넣어서 보내기
                // let html = "";
                // $.each(userList, function(index, user) {
                //     html += "<p class='font-weight-bold'>" + user.username + "</p>";
                //     html += "<p>" + user.email + "</p>";
                //     $("#testDiv").html(html);
                // })

            },
            error: function (err) {
                console.log(err);
                alert("회원삭제 실패");
            }
        });
    });

    // 체크박스 선택
    function checkList(){
        let chk_arr = [];
        $("input[name=deleteCheck]:checked").each(function (){
            let chk = $(this).val();
            console.log(chk);
        })
    }

    // $(document).on("click", "input[name=deleteCheck]:checked",function (){
    /*$(document).on("click", "input[name=deleteCheck]",function (){
        $("input[name=deleteCheck]:checked").each(function (e) {
            let chk = $(this).val();


            chk_arr.push(chk);
            console.log(chk_arr);
        });
    });*/
    // <button type="button" name="btnDeleteAll" class="btn btn-danger">선택삭제</button>
    /* $(document).on("click", "button[name='btnDelete']", function () {
         console.log("btnDelete clicked : " + $(this).data("id"));*/
    $(document).on("click", "button[name=btnDeleteAll]", function (){

        console.log("선택삭제 눌렀으....");
        $.ajax({
            url : "/user2/" + chk_arr,
            method : "DELETE",
            dataType : "JSON",
            success : function (result){
                console.log(result);
                alert("삭제 성공");
                location.href = "/users";

            },
            error : function (err){
                console.log(err);
                alert("삭제 실패");

            }
        });

    });



    // $(document).on("click", "button[name='btnDelete']", function (){

   /* $(document).on("change", "input:checkbox[name='deleteCheck']", function () {
        //deleteId
//        console.log("btnDelete clicked : " + $(this).data("id"));

        // let deleteData = $(this).data("deleteId");
        let deleteData = $(this).text();
        console.log("deleteData = " + deleteData);


    });*/


    /*function makeHtml() {
        $.ajax({
            url: "${root}user/test", // url REST API
            method: "GET",
            dataType: "json",
            success: function (data) {
                console.log("test data received");
                console.log(data); //json(data = testDto)
                let html = "<p class='font-weight-bold'>" + data.data1 + "</p>";
                html += "<p>" + data.data2 + "</p>";
                html += "<p>" + data.data3 + "</p>";
                $("#testDiv").html(html);
            }
        });
    }*/
    // $(document).ready(function () {
    //
    // });

    // DOM 다 구성되고 동작
    $(function () {
        console.log("dom 완료");
        //makeHtml();

    });
</script>


<%--<script type="text/javascript">
    function deleteUser(e) {
        console.log(e);
        let thisRow = $(e).closest('tr');
        let id = thisRow.find('td:eq(1)').find('input').val();

        // console.log(thisRow);
        console.log("id = " + id);
        // let userId = thisRow.find('td:eq(1)').

        // console.log("userId =" + userId)
    }

    $(function() {
        /*$("button[name='btnDelete']").on("click", function () {
            let $tr = $(this).closest("tr");
            let id = $tr.find("td:eq(1)").find("input").val();
            console.log("id = " + id);
        });
*/
        $("button[name='btnDelete']").on("click", function () {
            // let $tr = $(this).closest("tr");
            // let id = $tr.find("td:eq(0)").data("id");
            $.ajax({
                url : "root/delete/" + $(this).data("id"), //localhost:888/admin/delete/33
                method: "DELETE",
                dataType: "json",
                success: function (result) {
                    console.log(result);
                },
                error: function (e) {
                    console.log(e);
                }
            })
        });


    })
</script>--%>
<script id="template" type="text/x-mustache">
                {{#userList}}
                <tr>
                    <td><input type="checkbox" data-deleteId="{{id}}" name="deleteCheck"></td>
                    <td>{{id}}</td>
                    <td>{{username}}</td>
                    <td>{{email}}</td>
                    <td>{{role}}</td>
                    <td>{{createDate}}</td>
                    <td>
                            <a href="${root}user/{{id}}" class="btn btn-primary">수정</a>
<%--                            <button type="button" name="btnDelete" class="btn btn-danger" onclick="deleteMember(this)">삭제</button>--%>
                            <button type="button" name="btnDelete" class="btn btn-danger" data-id = {{id}}>삭제</button>
                    </td>
                </tr>
                {{/userList}}


</script>
</html>