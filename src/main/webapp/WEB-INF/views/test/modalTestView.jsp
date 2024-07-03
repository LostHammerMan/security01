<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<%--<c:import url="/WEB-INF/views/layout/header.jsp"/><!-- 기존 쓰고 있는 헤더-->--%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Bootstrap Modal Example</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- jQuery -->
<%--  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>--%>
<%--  <script src="${root}static/js/commonToast.js"></script>--%>
  <script
          src="https://code.jquery.com/jquery-3.7.1.min.js"
          integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
          crossorigin="anonymous"></script>

  <script src="${root}static/test_js/this_test.js"></script>
  <!-- Bootstrap JS -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<%--  <h1>Bootstrap Modal Example</h1>--%>
<%--  <!-- Button to Open the Modal -->--%>
<%--  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">--%>
<%--    Open Modal--%>
<%--  </button>--%>

  <!-- The Modal -->
<%--  <div class="modal" id="myModal">--%>
<%--    <div class="modal-dialog">--%>
<%--      <div class="modal-content">--%>

<%--        <!-- Modal Header -->--%>
<%--        <div class="modal-header">--%>
<%--          <h4 class="modal-title">Modal Heading</h4>--%>
<%--          <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--        </div>--%>

<%--        <!-- Modal Body -->--%>
<%--        <div class="modal-body">--%>
<%--          Modal body content goes here.--%>
<%--        </div>--%>

<%--        <!-- Modal Footer -->--%>
<%--        <div class="modal-footer">--%>
<%--          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>--%>
<%--        </div>--%>

<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>

  <button id="button">this 는 누굴까?</button>
  <a href="${root}"><button>메인페이지</button></a>

</div>

<!-- Custom JavaScript -->
<script type="text/javascript">
  $(document).ready(function (){
    /*const car = {
      name : 'KIA',
      getName:function (){
        console.log("car getName", this);
      }
    };

    // car.getName(); // Car 객체 호출됨

    const globalCar = car.getName;
    globalCar(); // window 객체 호출됨(Window 객체 : 자바스크립트에서 사용하는 내장 객체, 화면 관련)

    // 차이점
    /!* car.getName() : car 객체 내부에 있는 함수 호출한 것 -> this 는 car 객체 그 자체
        globalCar() : 밖에서 호출됨, 여기서 this 는 최상단의 객체가 호출된 것
    *
    * *!/

    const car2 = {
      name : 'hyundai',
      getName: car.getName
    }

    car2.getName() // car2 객체를 가리킬 것

    const btn = document.getElementById('button');
    btn.addEventListener('click', car.getName); // button 태그 자체가 나온다, button 이 호출했기 때문

    // 위와 같이 호출될 때 마다 달라지는 this 값을 고정하기 위한 방법 : .bind
    // .bind(고정시킬 객체 이름) : 고정한 객체 호출됨
    const bindGetName = car2.getName().bind(car); // 위 car 객체로 고정
    bindGetName();*/

    const testCar = {
      name : 'benz',
      getName : function (){
        console.log("getName", this.name)
        const innerFunc = function (){
          console.log("innerFunc", this.name)
        };
        innerFunc();
      },
    };

    testCar.getName()


  })



</script>
</body>
</html>