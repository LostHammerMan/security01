<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<%--main header--%>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<%--community header--%>
<c:import url="/WEB-INF/views/layout/communityHeader.jsp"/>
<style>
    section {
        width: 100%;
        height: 100%;
        margin-left: 100px;
    }

    section > header {
        width: 70%;
    }

    .adImg {
        border-radius: 10px;
        background-color: #F2F2F2;
    }

    .left_part {
        width: 60%;
        float: left;
    }

    .right_part {
        width: 30%;
        height: 50%;
        float: right;
        /* margin 12 부터 시계 방향 */
        margin: 40px 200px 0 50px;
        /*margin: auto;*/
    }

    /*.loungeWrapper {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }*/

    .loungeBoard {
        border: 1px solid #FFFBE3;
        background-color: #FFFBE3;
        border-radius: 10px;
        width: 200px;
        height: 100%;
        list-style-type: none;
        margin: 10px 30px 10px 10px;
    }
</style>

<!-- 개발자 라운지 - 자유주제, 커리어 고민-->
<section class="d-flex justify-content-between">
    <div class="left_part">
        <div class="left_part1 d-flex justify-content-between" style="margin: 40px 0 40px 20px; align-items: center">
            <h1 style="font-size: 16px; font-weight: bolder;">슽디 커뮤니티에서 당신의 이야기를 나눠보세요!</h1>
            <a class="btn btn-primary ml-auto">작성하기</a>
        </div>
        <div class="left_part2" style="margin: 0 0 0 0">
            <ul class="loungeWrapper d-flex flex-sm-wrap justify-content-between">
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
                <li class="loungeBoard">
                    <a href="#">
                        <h4>자유주제</h4>
                        <h2>글제목</h2>
                        <h3>글 내용</h3>
                        <div>
                            <img src="">
                            <p>
                                <span>닉네임</span>
                                <span>글생성일</span>
                            </p>
                        </div>
                        <ul>
                            <li>조회 수</li>
                            <li>댓글 수</li>
                            <li>좋아요 수</li>
                        </ul>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <%--<aside>
    </aside>--%>
    <aside class="right_part">
        <div>
            <img class="adImg" src="${root}static/img/community/01.png" alt="광고1" width="70%" height="70%"/>
        </div>
        <div class="mt-lg-5">
            <img class="adImg" src="${root}static/img/community/42.png" alt="광고1" width="70%" height="70%"/>
        </div>

    </aside>
</section>


<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>
