<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<style>
    .bannerWrapper {
        width: 100%;
        height: 500px;
        overflow: hidden;
        position: relative;
    }

    #bannerImg {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .carousel-inner {
        border-radius: 3rem;
        height: 450px;
        object-fit: cover; /* 이미지 크기 비율 유지 */
    }

</style>
<main>
<!-- carousel -->
    <div class="carousel_container" style="margin-bottom: 20px;">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="${root}static/ad_img/advertisement4.png" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="${root}static/ad_img/advertisement7.png" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="${root}static/ad_img/advertisement6.png" alt="Third slide">
            </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="scrap_container">
        <div class="scrap_header">
            <p class="scrap_site">
                <span class="site_ex" data-value="INFLEARN">인프런</span>
                <span class="site_ex" data-value="SOUP">스프</span>
            </p>
        </div>
        <div class="scrap_list">
            <div class="scrap_item"></div>
        </div>

    </div>
</main>
    
<script type="text/javascript">

    $(document).ready(function(){
        let $site_value = '';

        $('.site_ex').on('click', function(){
            console.log('사이트 클릭');
            $('.scrap_item').html("");
            $site_value = '';
            $site_value = $(this).data('value');

            $.ajax({
                url: '${root}api/getStudyScrap',
                method: 'GET',
                data: {
                    site : $site_value
                },
                success: function(result){
                    console.log('사이트 크롤링 결과 불러오기 성공!');

                    // 변수 전역으로 선언되어 있는 경우, 이전 요청 결과 누적됨 주의
                    let itemHtml = '';
                    let grade = 1;
                    for(const [key, items] of Object.entries(result)){
                        items.forEach(function(item){
                            console.log("item = " + item.title);
                            itemHtml += `
                            <div class="list-group">
                                <a class="" href=${'${item.link}'}>
                                    <div class="list-group-item list-group-item-action">
                                    <div class="d-flex w-100">
                                        <h5>${'${grade}'}</h5>
                                        <h5 class="mb-1">${'${item.title}'}</h5>
                                    </div>
                                    <p class="mb-1">${'${item.content}'}</p>
                                </div>

                                </a>
                                
                            </div>
                        `;
                        grade++;
                        });
                    }

                    // result.forEach(function(item){

                    //     console.log("item = " + item);
                        
                    // });

                    $('.scrap_item').append(itemHtml);
                },
                error: function(xhr){
                    console.log('사이트 크롤링 결과 불러오기 실패!');
                }
            });
        });
    });

</script>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

</html>