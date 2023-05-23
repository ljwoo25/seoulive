package org.zerock.seoulive.board.course.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CourseWriteDTO {
    private String WRITER;
    private String TITLE;
    private String REVIEW;
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date DURATION_START;
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date DURATION_END;
    
//    private List<CourseWriteVO> listVO;
} // end class


