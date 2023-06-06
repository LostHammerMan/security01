package com.demo.security01.model.dto.paging;

import lombok.ToString;

// 게시판 하단 페이징
@ToString
public class Paging {

    private int totalCount; // 게시판 전체 데이터 개수
    private int displayPageNum = 10; // 게시판 화면에서 한번에 보여질 페이지 번호의 개수
    private int startPage; // 화면의 시작 번호
    private int endPage; // 화면의 끝 번호
    private boolean prev; // 이전 버튼 활성화 여부
    private boolean next; // 다음 버튼 활성화 여부

    private Criteria cri;

    public int getTotalCount(){
        return totalCount;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;

        pagingData();
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public Criteria getCri() {
        return cri;
    }

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    private void pagingData(){
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        // endPage = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
        startPage = (endPage - displayPageNum) + 1;
        // startPage = (끝페이지 번호 - 현재 보여질 페이지 번호 개수) + 1

        // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 개수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage){
            endPage = tempEndPage;
        }

//        prev = startPage != 1;
        prev = (startPage == 1 ? false : true);
//        next = (endPage * cri.getPerPageNum() < totalCount);
        next = (endPage * cri.getPerPageNum() >= totalCount ? false : true);

    }
}
