package com.demo.security01.model.dto.paging;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 페이지 자체를 관리하는 객체, 페이지당 보여줄 개수 담당
@ToString
@Slf4j
public class Criteria {

    private int page; // 현재 페이지 번호
    private Integer perPageNum; // 페이지당 보여줄 게시글의 개수
    private String type;
    private String keyword;

    public int getPageStart(){
        // 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
        // 0 ~ 10, 10 ~ 20
        return (this.page -1) * perPageNum;
    }

    public Criteria(){
        log.info("## Criteria 기본 생성자");
        // 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
        this.page = 1;
        this.perPageNum = 10;
    }

    /*public Criteria(int page, int perPageNum, String type, String keyword){
        this.page = page;
        this.perPageNum = perPageNum;
        this.type = type;
        this.keyword = keyword;
    }*/
    // 현재 페이지 번호 page : getter, setter
    public int getPage(){
        return page;
    }

    public void setPage(int page){
        if (page <= 0){
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    // 페이지당 보여줄 게시글의 개수 perPageNum : getter, setter
    public int getPerPageNum(){
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        log.info("## setPerPageNum, perPageNum = {}", perPageNum);
        /*if (perPageNumList.contains(perPageNum)){
            this.perPageNum = perPageNum;
        }else {
            this.perPageNum = 10;
        }*/
        if (this.perPageNum != perPageNum){
            this.perPageNum = perPageNum;
        }
    }

    public String getType(){
        return type;
    }

    public String getKeyword(){
        return keyword;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }


}
