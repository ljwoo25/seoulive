package org.zerock.seoulive.board.travel.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@Data
public class Criteria {

    private Integer currPage = 1;   // 현재 페이지 번호
    private Integer amount = 10;    // 한 페이지당 게시물 개수
//    private Integer pageNum;

    private Integer pagesPerPage = 5; // 한 페이지당 보여줄 페이지목록의 길이

    // 스킵 할 게시물 수
    private Integer skip;

    // 검색에 필요한 키워드 선언
    private String searchType = "";
    private String keyword = "";         // 검색이름

    private Date start_date;
    private Date end_date;
    private String end_time;

    private String selectedDate;

    public Criteria() {
        this(1, 10, null, null, null);
        this.skip = 0;
    }

    public Criteria(int currPage, int amount, Date start_date, Date end_date, String end_time) {
        this.currPage = currPage;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.end_time = end_time;
//        this.skip = (currPage-1) * amount;
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

    public Date getStartDate(){
        return start_date;
    }
    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEndDate(){
        return end_date;
    }
    public void setEndDate(Date end_date){
        this.end_date = end_date;
    }

    public String getEndTime(){
        return end_time;
    }

    public void setEndTime(String end_time){
        this.end_time = end_time;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

} // end class
