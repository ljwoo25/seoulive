package org.zerock.seoulive.board.review.domain;

import lombok.Data;

@Data
public class Criteria { //페이지 처리와 관련된 DTO
    private Integer currPage = 1;
    private Integer amount = 9;
    private Integer pagePerPage = 10;

    private Integer skip;

    // 검색에 필요한 키워드 선언
    private String searchType = "title";
    private String keyword = "";         // 검색이름

    public Criteria() {
        this(1, 9);
        this.skip = 0;
    }

    public Criteria(int currPage, int amount) {
        this.currPage = currPage;
        this.amount = amount;
        this.skip = (currPage-1) * amount;
    }

    public String getKeyword(){
        return keyword;
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public String toString(){
        return "Criteria [currPage= " + currPage + ", amount= " + amount + ", skip= " + skip + ", keyword=" + keyword + "]";
    }

    public Integer getAmount() {
        return amount != null ? amount : 10; // Null 값일 경우 기본값 10을 반환하도록 처리
    }








}