package org.zerock.seoulive.board.course.domain;


import lombok.Value;

@Value
public class CourseTravelVO {
    private Integer SEQ;
    private Integer BOARD_SEQ;
    private Integer TRAVEL_SEQ;
    private Integer UPLOAD_SEQ;
    private String TRAVEL_ID;
    private String USER_REVIEW;
//    private String ID;
    
    private String CATEGORY;
    private String TITLE;
    private String CONTENT;
    private String ADDRESS;

} // end class
