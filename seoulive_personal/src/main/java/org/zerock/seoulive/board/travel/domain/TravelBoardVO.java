package org.zerock.seoulive.board.travel.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2

public class TravelBoardVO {
    private Integer seq;
    private String writer;
    private String title;
    private String category;
    private String content;
    private String address;
    private String total;
    private Timestamp write_date;
    private Timestamp modify_date;
    private String start_time;
    private String end_time;
    private Date start_date;
    private Date end_date;
    public TravelBoardDTO toDTO(){
        log.trace("toDTO() invoked.");

        TravelBoardDTO dto = new TravelBoardDTO();
        dto.setSeq(seq);
        dto.setWriter(writer);
        dto.setTitle(title);
        dto.setCategory(category);
        dto.setContent(content);
        dto.setAddress(address);
        dto.setTotal(total);
        dto.setWrite_date(write_date);
        dto.setModify_date(modify_date);
        dto.setStart_time(start_time);
        dto.setEnd_time(end_time);
        dto.setStart_date(start_date);
        dto.setEnd_date(end_date);



        log.info("/t+ dto:{}", dto);

        return dto;

    } // TravelDTO

} // end class 
