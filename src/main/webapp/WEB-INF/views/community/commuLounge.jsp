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
    .loungeContainer {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        position: relative;
        width: 100%;
        height: 100%;
        gap: 4rem;
        margin-left: auto;
        margin-right: auto;
    }

    .loungeContainerHeader {
        width: 100%;
        display: flex;
        justify-content: space-between;
        position: static;
        align-items: center;
        gap: 1rem;
    }

    .loungeContainerMain {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        justify-content: initial;
        width: 70%;
        gap: 1.5rem;
    }

    .loungeContainerAside {
        display: flex;
        width: 25%;
        flex-direction: column;
        align-items: initial;
        justify-content: flex-start;
        flex-shrink: 0;
        gap: 5rem;
    }

    .adImg {
        width: 100%;
        max-height: 400px;
        border-radius: 10px;

    }

</style>

<!-- 개발자 라운지 - 자유주제, 커리어 고민-->
<section class="pt-5 pb-5">
    <div class="container loungeContainer">
       <%-- <header class="loungeContainerHeader">
            <h1 style="font-size: 16px; font-weight: bolder;">슽디 커뮤니티에서 당신의 이야기를 나눠보세요!</h1>
            <a class="btn btn-primary ml-auto">작성하기</a>
        </header>--%>
        <main class="loungeContainerMain">
            <header class="loungeContainerHeader">
                <h1 style="font-size: 16px; font-weight: bolder;">슽디 커뮤니티에서 당신의 이야기를 나눠보세요!</h1>
                <a class="btn btn-primary ml-auto" href="${root}community/lounge/write">작성하기</a>
            </header>
            <div class="row d-flex ">

                <c:forEach items="${allLounge}" var="allLounge">
                    <div class="col-12 col-md-4 mb-4 mt-2">
                        <div class="card  h-100 border-light  bg-light shadow">
                            <a href="${root}community/lounge/${allLounge.idx}">
                                <div class="card-body d-flex-row">
                                    <p class="card-text mb-5">${allLounge.cateCode.categoryName}</p>
                                    <blockquote class="blockquote mb-4 pb-2">
                                        <p class="mb-0 font-weight-bold">${allLounge.title}</p>
                                    </blockquote>
<%--                                    <p class="card-text mb-5">${allLounge.content}</p>--%>
                                    <div class="w-100 pb-1"></div>
                                    <div class="d-flex align-items-center align-self-end">
                                        <div class="meta-author">
                                            <c:choose>
                                                <c:when test="${allLounge.user.userProfile.fileName == null}">
                                                    <img class="d-block img-fluid rounded-circle"
                                                         src="https://via.placeholder.com/40x40/5fa9f8/ffffff " alt="author avatar">

                                                </c:when>
                                                <c:otherwise>
                                                    <img class="d-block img-fluid rounded-circle"
                                                         src="${root}api/profileImages/${allLounge.user.userProfile.fileName}" height="40" width="40">
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                        <div class="m-2">
                                            <a href="#">${allLounge.user.username}</a>
                                        </div>
                                        <hr/>

                                    </div>
                                    <hr>
                                    <ul>
<%--                                        <li style="list-style-type: none">${allLounge.count}</li>--%>
                                        <li style="list-style-type: none">조회 수 </li>
                                        <li style="list-style-type: none">댓글 수</li>
                                        <li style="list-style-type: none">좋아요 수</li>
                                    </ul>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>


                <div class="col-12 col-md-4 mb-4 mt-2">
                    <div class="card  h-100 border-light  bg-light shadow">
                        <div class="card-body d-flex-row">
                            <blockquote class="blockquote mb-4 pb-2">
                                <p class="mb-0 font-weight-bold">Lorem ipsum cursus ectetur gravida.</p>
                                <footer class="blockquote-footer">Author Name</footer>
                            </blockquote>
                            <p class="card-text mb-5">Cras in fringilla egestas condimentum morbi ut urna nec nunc.</p>
                            <div class="w-100 pb-1"></div>
                            <div class="d-flex align-items-center align-self-end">
                                <div class="meta-author">
                                    <img class="d-block img-fluid rounded-circle"
                                         src="https://via.placeholder.com/40x40/5fa9f8/ffffff " alt="author avatar">
                                </div>
                                <div class="m-2">
                                    <a href="#">Author Name</a>
                                </div>
                                <div class="meta-item ml-auto">
                                    <a href="#">
                                        <i class="fas fa-link m-1"></i>Read more</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-4 mb-4 mt-2">
                    <div class="card  h-100 border-light  bg-light shadow">
                        <div class="card-body d-flex-row">
                            <blockquote class="blockquote mb-4 pb-2">
                                <p class="mb-0 font-weight-bold">Lorem ipsum cursus ectetur gravida.</p>
                                <footer class="blockquote-footer">Author Name</footer>
                            </blockquote>
                            <p class="card-text mb-5">Cras in fringilla egestas condimentum morbi ut urna nec nunc.</p>
                            <div class="w-100 pb-1"></div>
                            <div class="d-flex align-items-center align-self-end">
                                <div class="meta-author">
                                    <img class="d-block img-fluid rounded-circle"
                                         src="https://via.placeholder.com/40x40/5fa9f8/ffffff " alt="author avatar">
                                </div>
                                <div class="m-2">
                                    <a href="#">Author Name</a>
                                </div>
                                <div class="meta-item ml-auto">
                                    <a href="#">
                                        <i class="fas fa-link m-1"></i>Read more</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-4 mb-4 mt-2">
                    <div class="card  h-100 border-light  bg-light shadow">
                        <div class="card-body ">
                            <blockquote class="blockquote mb-4 pb-2">
                                <p class="mb-0 font-weight-bold">Lorem ipsum cursus ectetur gravida.</p>
                                <footer class="blockquote-footer">Author Name</footer>
                            </blockquote>
                            <p class="card-text mb-5">Cras in fringilla egestas condimentum morbi ut urna nec nunc.</p>
                            <div class="w-100 pb-1"></div>
                            <div class="d-flex align-items-center">
                                <div class="meta-author">
                                    <img class="d-block img-fluid rounded-circle"
                                         src="https://via.placeholder.com/40x40/5fa9f8/ffffff " alt="author avatar">
                                </div>
                                <div class="m-2">
                                    <a href="#">Author Name</a>
                                </div>
                                <div class="meta-item ml-auto">
                                    <a href="#">
                                        <i class="fas fa-link m-1"></i>Read more</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--<aside class="loungeContainerAside">
                <div>
                    <img class="adImg" src="${root}static/img/community/01.png" alt="광고1" width="70%" height="70%"/>
                </div>
                <div class="mt-lg-5">
                    <img class="adImg" src="${root}static/img/community/42.png" alt="광고1" width="70%" height="70%"/>
                </div>
            </aside>--%>
        </main>
           <aside class="loungeContainerAside">
               <div>
                   <img class="adImg" src="${root}static/img/community/01.png" alt="광고1"/>
               </div>
               <div class="mt-lg-5">
                   <img class="adImg" src="${root}static/img/community/42.png" alt="광고1"/>
               </div>
           </aside>

    </div>
</section>
<%--<section class="d-flex justify-content-between">
    <div class="left_part">
        <div class="left_part1 header d-flex justify-content-between" style="margin: 40px 0 40px 20px; align-items: center">
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
            </ul>
        </div>
    </div>

    &lt;%&ndash;<aside>
    </aside>&ndash;%&gt;
    <aside class="right_part">
        <div>
            <img class="adImg" src="${root}static/img/community/01.png" alt="광고1" width="70%" height="70%"/>
        </div>
        <div class="mt-lg-5">
            <img class="adImg" src="${root}static/img/community/42.png" alt="광고1" width="70%" height="70%"/>
        </div>

    </aside>
</section>--%>


<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>
