package org.zerock.seoulive.board.course.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
//@Value
@Data
public class CourseCommVO {
    private Integer seq;
    private String writer;
    private String content;
    private Date write_date;
    private Date modify_date;
    private String board_name;
    private Integer post_seq;


    public CourseCommDTO toDTO() {
        log.trace("toDTO() invoked");

        CourseCommDTO dto = new CourseCommDTO();
//        dto.setSEQ(SEQ);
//        dto.setWRITER(WRITER);
//        dto.setCONTENT(CONTENT);
//
//        this.setWRITE_DATE(new Date());
//        dto.setWRITE_DATE(WRITE_DATE);
//
//
//        dto.setMODIFY_DATE(MODIFY_DATE);
//        dto.setBOARD_NAME(BOARD_NAME);
//        dto.setPOST_SEQ(POST_SEQ);

        log.info("\t dto: {}", dto);
        return dto;
    } //toDTO


} //end class