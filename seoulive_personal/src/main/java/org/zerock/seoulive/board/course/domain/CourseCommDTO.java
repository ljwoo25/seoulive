package org.zerock.seoulive.board.course.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class CourseCommDTO {
    private Integer seq;
    private String WRITER;
    private String CONTENT;
    private Date WRITE_DATE;
    private Date MODIFY_DATE;
    private String BOARD_NAME;
    private Integer POST_SEQ;


    public Integer getSeq() {
        return seq;
    }

    public String getWriter() {
        return WRITER;
    }

    public String getContent() {
        return CONTENT;
    }

    public Date getWrite_date() {
        return WRITE_DATE;
    }

    public Date getModify_date() {
        return MODIFY_DATE;
    }

    public String getBoard_name() {
        return BOARD_NAME;
    }

    public Integer getPost_seq(Integer post_seq) {
        return POST_SEQ;
    }

    public void setSEQ(Integer SEQ) {
        this.seq = seq;
    }

    public void setWriter(String WRITER) {
        this.WRITER = WRITER;
    }

    public void setContent(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public void setWrite_date(Date WRITE_DATE) {
        this.WRITE_DATE = WRITE_DATE;
    }

    public void setModify_date(Date MODIFY_DATE) {
        this.MODIFY_DATE = MODIFY_DATE;
    }

    public void setBOARD_NAME(String BOARD_NAME) {
        this.BOARD_NAME = BOARD_NAME;
    }

    public void setPost_seq(Integer POST_SEQ) {
        this.POST_SEQ = POST_SEQ;
    }

} //end class