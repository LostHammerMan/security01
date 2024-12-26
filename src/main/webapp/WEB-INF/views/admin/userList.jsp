<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<div class="container d-flex justify-content-end">
<%--    <div class="container">--%>
<%--        <h2>회원관리 페이지</h2>--%>
<%--        <P>간편하게 회원관리 하세요</P>--%>
<%--        <div class="select">--%>
<%--            <div class="selected">--%>
<%--                <div class="selected-value">none</div>--%>
<%--                <div class="arrow"></div>--%>
<%--            </div>--%>
<%--            <ul>--%>
<%--                <li class="option"><a class="dropdown-item" href="${root}admin/userList?perPageNum=10">10개</a></li>--%>
<%--                <li class="option"><a class="dropdown-item" href="${root}admin/userList?perPageNum=10">10개</a></li>--%>
<%--                <li class="option"><a class="dropdown-item" href="${root}admin/userList?perPageNum=10">10개</a></li>--%>
<%--            </ul>--%>
            <%--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">정렬</button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="${root}admin/userList?perPageNum=10">10개</a>
                <a class="dropdown-item" href="${root}admin/userList?perPageNum=20">20개</a>
                <a class="dropdown-item" href="${root}admin/userList?perPageNum=30">30개</a>
            </div>--%>
          <select name="perPageNum">
              <option value="10" ${paging.cri.perPageNum eq 10 ? "selected" : ""}>10개</option>
              <option value="20" ${paging.cri.perPageNum eq 20 ? "selected" : ""}>20개</option>
              <option value="30" ${paging.cri.perPageNum eq 30 ? "selected" : ""}>30개</option>
          </select>
<%--        </div>--%>
    </div>
<div class="userList-table">
    <table class="table table-striped" id="userList">
        <thead>
        <tr>
            <th><input type="checkbox" name="chk_All">&nbsp;일괄선택</th>
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
                <td><input type="checkbox" name="deleteCheck" value="${users.id}" onclick="checkList()"></td>
                <td>${users.id}</td>
                    <%--                        <td><input type="hidden" name="userId" id="userId" value="${users.id}"></td>--%>
                <td>${users.username}</td>
                <td>${users.email}</td>
                <td>${users.role}</td>
                <td><fmt:formatDate value="${users.createDate}" pattern="yyyy-MM-dd"/></td>
                <td><input type="hidden" value="${paging.cri.page}" name="pageNum"></td>
                <td>
                    <a href="${root}admin/user/${users.id}?page=${paging.cri.page}" class="btn btn-primary">수정</a>
<%--                    <a href="${root}admin/user/${users.id}" class="btn btn-primary">수정</a>--%>
                        <%--                            <button type="button" name="btnDelete" class="btn btn-danger" onclick="deleteMember(this)">삭제</button>--%>
                    <button type="button" name="btnDelete" class="btn btn-danger" data-id= ${users.id}>삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="button" name="btnDeleteAll" class="btn btn-danger">선택삭제</button>
    <div class="board-bottom">
        <div class="pagination justify-content-center">
            <ul class="pagination">
                <c:if test="${paging.prev}">
                    <li class="page-item"><a class="page-link"
                                             href='<c:url value="${root}admin/userList?page=${paging.startPage -1}&type=${paging.cri.type}&keyword=${paging.cri.keyword}&perPageNum=${paging.cri.perPageNum}"/>'>이전</a>
                    </li>
                </c:if>
                <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
                    <li class="page-item"><a class="page-link"
                                             href='<c:url value="${root}admin/userList?page=${num}&type=${paging.cri.type}&keyword=${paging.cri.keyword}&perPageNum=${paging.cri.perPageNum}"/>'>${num}</a></li>
                </c:forEach>
                <c:if test="${paging.next && paging.endPage > 0}">
                    <li class="page-item"><a class="page-link"
                                             href='<c:url value="${root}admin/userList?page=${paging.endPage +1}&type=${paging.cri.type}&keyword=${paging.cri.keyword}&perPageNum=${paging.cri.perPageNum}"/> '>다음</a></li>
                </c:if>
            </ul>
        </div>

        <div class="d-flex justify-content-center">
            <form action="${root}admin/userList" method="get">
                <select name="type">
                    <option value="">카테고리</option>
                    <option value="U" ${paging.cri.type eq 'U' ? 'selected' : ''}>유저이름</option>
                    <option value="E" ${paging.cri.type eq 'E' ? 'selected' : ''}>이메일</option>
                    <option value="R" ${paging.cri.type eq 'R' ? 'selected' : ''}>권한</option>
                </select>
                <input type="hidden" name="page" value="${paging.cri.page}" />
                <input type="text" name="keyword" value="${paging.cri.keyword}"/>
                <button class="btn-primary btn-primary">검색</button>
            </form>
        </div>
    </div>
    <!-- userList -->
</div>

<c:import url="../layout/footer.jsp"/>

<script type="text/javascript">
    // 화면에 보여지는 데이터
    $("select[name=perPageNum]").change(function (){
        let perPageNum = $(this).val();

        console.log("change clicked.......")
        console.log("perPageNum = " + perPageNum);
        location.href = "${root}admin/userList?perPageNum=" + perPageNum;
    });


    // 일괄선택시 이벤트
    $("input[name=chk_All]").on("click", function () {

        let chk_arr = [];
        if ($("input[name=chk_All]").is(":checked")) {
            $("input[name=deleteCheck]").prop("checked", true);
            /* $("input[name=deleteCheck]:checked").each(function (){
                 let chk = $(this).val();

                 /!* if ($(this).is(":checked") === true){
                      chk_arr.push(chk);

                  }else if ($(this).is(":checked") === false){
                      chk_arr.pop(chk);
                  }*!/
                 chk_arr.push(chk);
                 console.log(chk);
                 console.log(chk_arr);
             });*/
        } else {
            $("input[name=deleteCheck]").prop("checked", false);
        }

        // let total = $("in")

    })

    function checkList() {
        /* let chk_arr = [];
         $("input[name=deleteCheck]:checked").each(function (e){
             let chk = $(e).val();
             console.log(chk);
         })
         return chk_arr;*/

        let chk_arr = [];

        $("input[name=deleteCheck]:checked").each(function () {
            let chk = $(this).val();

            /* if ($(this).is(":checked") === true){
                 chk_arr.push(chk);

             }else if ($(this).is(":checked") === false){
                 chk_arr.pop(chk);
             }*/
            chk_arr.push(chk);
            console.log(chk);
            console.log(chk_arr);
        });


    }
</script>
<script type="module">

    import Mustache from "/static/js/mustache.js";

    // 회원 삭제 버튼
    $(document).on("click", "button[name='btnDelete']", function () {
        console.log("btnDelete clicked : " + $(this).data("id"));
        let page = $("input[name=pageNum]").val();
        console.log("pageNum = " + page);

        $.ajax({
            url: "${root}admin/user2/" + $(this).data("id"),
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
                    <%--url: "${root}admin/getUserList?page=" + pageNum,--%>
                    url: "${root}admin/getUserList?page=" + page,
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
    // ================회원목록  ajax 끝================


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

    //========= 선택삭제 ==============
    $(document).on("click", "button[name=btnDeleteAll]", function () {
        let page = $("input[name=pageNum]").val();
        console.log("pageNum = " + page);
        function checkList() {
            /* let chk_arr = [];
             $("input[name=deleteCheck]:checked").each(function (e){
                 let chk = $(e).val();
                 console.log(chk);
             })
             return chk_arr;*/

            let chk_arr = [];

            $("input[name=deleteCheck]:checked").each(function () {
                let chk = $(this).val();
                console.log(chk);

                chk_arr.push(chk);
                console.log(chk_arr);
            });
            return chk_arr;
        }

        let chk_arr = checkList();
        console.log("선택삭제 눌렀으....");

        $.ajax({
            url: "/admin/user2/" + chk_arr,
            method: "DELETE",
            dataType: "JSON",
            success: function (result) {
                console.log(result);
                alert("삭제 성공");
                location.href = "/admin/userList?page=" + page;

            },
            error: function (err) {
                console.log(err.responseText);
                alert("삭제 실패");

            }
        });

    });
</script>


<%-- 회원삭제 template--%>
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