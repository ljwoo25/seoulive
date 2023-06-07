package org.zerock.seoulive.board.cs.faq.domain;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class FaqVO {
    private Integer rownum;
    private Integer seq;
    private String title;
    private String content;
    private String writer;
    private String category;
    private Timestamp writeDate;
    private Timestamp modifyDate;


    public FaqDTO toDTO(){
        FaqDTO dto = new FaqDTO();
        dto.setSeq(seq);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setWriter(writer);
        dto.setCategory(category);

        return dto;
    } // toDTO
} // end class
