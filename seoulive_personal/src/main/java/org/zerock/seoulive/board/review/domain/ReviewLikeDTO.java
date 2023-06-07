package org.zerock.seoulive.board.review.domain;


import lombok.Data;

@Data
public class ReviewLikeDTO {

    private Integer seq;
    private String email;
    private Integer board_seq;
    private String board;
}
