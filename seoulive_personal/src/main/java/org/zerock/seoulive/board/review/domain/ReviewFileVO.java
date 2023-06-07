package org.zerock.seoulive.board.review.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewFileVO {
    private Integer seq;
    private String email;
    private String file_name;
    private String extension;
    private Timestamp upload_date;
    private Integer file_size;
    private String board_name;
    private Integer post_seq;



}
