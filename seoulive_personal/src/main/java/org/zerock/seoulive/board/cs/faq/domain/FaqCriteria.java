package org.zerock.seoulive.board.cs.faq.domain;

import lombok.Data;


//DTO : 페이징처리와 관련된 기준값(전송파라미터)을
//      수집하는 DTO 입니다.

@Data
public class FaqCriteria {
    //-- For Fetching records according to the currPage.
    private Integer currPage = 1;		// 현재 페이지 번호
    private Integer amount = 10;		// 한 페이지당 게시물 개수

    //-- For Pagination
    private Integer pagesPerPage = 10;	// 한 페이지당 보여줄 페이지목록의 길이

    private String category;
} // end class
