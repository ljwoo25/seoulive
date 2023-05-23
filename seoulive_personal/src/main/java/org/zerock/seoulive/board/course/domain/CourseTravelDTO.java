package org.zerock.seoulive.board.course.domain;


import lombok.Data;
import lombok.Value;

@Data
public class CourseTravelDTO {
    private Integer SEQ;
    private Integer BOARD_SEQ;
    private Integer TRAVEL_SEQ;
    private Integer UPLOAD_SEQ;
    private String LINK;
    private String USER_REVIEW;
    

} // end class
