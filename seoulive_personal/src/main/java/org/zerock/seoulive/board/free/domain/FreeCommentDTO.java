package org.zerock.seoulive.board.free.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


//CREATE TABLE tbl_comment (
//        seq	 NUMBER(8)		NOT NULL,
//        writer   	VARCHAR2(50)		NOT NULL,
//        content	 VARCHAR2(1000)		NOT NULL,
//        write_date	  DATE		NOT NULL,
//        modify_date	  DATE		NULL,
//        board_name	  VARCHAR2(10)		NOT NULL,
//        post_seq	NUMBER(8)	  	NOT NULL
//        );


@Data
public class FreeCommentDTO {

    private int seq;        //댓글 번호
    private String writer;  //댓글 작성자
    private String content; //댓글 내용

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date write_date;//작성일
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date modify_date;//수정일


    private String board_name;//게시판 이름
    private int post_seq;     //게시물 번호
}

