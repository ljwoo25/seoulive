package org.zerock.seoulive.board.travel.domain;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Value
public class VO {

    private Integer seq;
    private String writer;
    private String title;
    private String category;
    private String content;
    private String address;
    private Integer total;
    private Timestamp write_Date;
    private Timestamp modify_Date;
    private String start_time;
    private String end_time;
    @DateTimeFormat(pattern = "yymmdd")
    private Date start_date;
    @DateTimeFormat(pattern = "yymmdd")
    private Date end_date;

    public DTO toDTO() {
        DTO dto = new DTO();

        dto.setSeq(seq);
        dto.setWriter(writer);
        dto.setTitle(title);
        dto.setCategory(category);
        dto.setContent(content);
        dto.setAddress(address);
        dto.setTotal(total);
        dto.setStart_time(start_time);
        dto.setEnd_time(end_time);
        dto.setStart_date(start_date);
        dto.setEnd_date(end_date);


        return dto;
    }

} // end class
