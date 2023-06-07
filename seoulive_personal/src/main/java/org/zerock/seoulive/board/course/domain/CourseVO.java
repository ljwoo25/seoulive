package org.zerock.seoulive.board.course.domain;

import lombok.Value;

import java.util.Date;

@Value
public class CourseVO {
    private Integer seq;
    private String WRITER;
    private String TITLE;
    private String REVIEW;
    private Integer TOTAL;
    private Date WRITE_DATE;
    private Date MODIFY_DATE;
    private Date DURATION_START;
    private Date DURATION_END;
    
    public CourseDTO toDTO() {
        CourseDTO dto = new CourseDTO();

        dto.setSeq(seq);
        dto.setWRITER(WRITER);
        dto.setTITLE(TITLE);
        dto.setREVIEW(REVIEW);
        dto.setDURATION_START(DURATION_START);
        dto.setDURATION_END(DURATION_END);

        return dto;
    }

} // end class
