package org.zerock.seoulive.board.travel.domain;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data

public class TravelBoardDTO {

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


} // end class
