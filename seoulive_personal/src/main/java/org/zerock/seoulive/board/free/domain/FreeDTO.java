package org.zerock.seoulive.board.free.domain;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;


//<insert id="freeInsert" parameterType="org.zerock.seoulive.board.free.domain.FreeDTO">
//        INSERT INTO TBL_FREE(seq,writer,category,title,content,write_date)
//        VALUES (seq.nextval,#{writer},#{category},#{title},#{content},sysdate)
//</insert>


@Data
public class FreeDTO {
    private int seq; //번호
    private String writer; //작성자
    private String category; //카테고리
    private String title; //타이틀
    private String content; //콘텐츠
    private Date write_date; //작성일
    private Date modify_date; //수정일
    private int total_count; //조회수
}
