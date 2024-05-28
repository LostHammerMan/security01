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

    .card_bottom {

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
                                    <ul class="card_bottom d-flex row" style="gap: 1.5rem">
<%--                                        <li style="list-style-type: none">${allLounge.count}</li>--%>
                                        <li style="list-style-type: none">
                                            <img src="${root}static/img/eye.svg" alt="eye" style="width: 20px; height: auto"  />
                                            <span class="post_count" style="color: #6e707e; font-size: 14px; font-weight: 500">${allLounge.viewCount}</span>
                                        </li>
                                        <li style="list-style-type: none">댓글 수</li>
                                        <li style="list-style-type: none">
                                            <i class="far fa-heart fas" style="width: 20px; height: auto; color: red"></i>
                                            <span class="post_heart" style="color: #6e707e; font-size: 14px; font-weight: 500">${allLounge.likeCount}</span>
                                        </li>
                                    </ul>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>



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



<!-- Q&A - 이용관련, 자유질문-->

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>
