package org.zerock.seoulive.board.travel.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DTO {

    private Integer seq;
    private String writer;
    private String title;
    private String category;
    private String content;
    private String address;
    private Integer total;
    private String start_time;
    private String end_time;
    private Date start_date;
    private Date end_date;





} // end class
