package org.zerock.seoulive.board.course.domain;

import lombok.Data;


@Data
public class CoursePageTO {
	
	private Integer currPage = 1;		// 현재 페이지 번호
	private Integer amount = 9;		// 한 페이지당 게시물 개수

	private Integer pagesPerPage = 10;	// 한 페이지당 보여줄 페이지목록의 길이
	
	private String searchType = "TITLE";
	private String keyword = "";
} // end class
