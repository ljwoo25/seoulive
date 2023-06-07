package org.zerock.seoulive.board.review.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewCommentDTO {



    private Integer seq;            //댓글 번호
    private String writer="joo";    //댓글 작성자
    private String content; //댓글 내용
//
//        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
//        private Date write_date;//작성일
//        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
//        private Date modify_date;//수정일


    private String board_name;   //게시판 이름
    private Integer post_seq;    //게시물 번호
}

