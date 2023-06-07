package org.zerock.seoulive.board.cs.faq.domain;


import lombok.Data;

@Data
public class FaqDTO {
    private Integer seq;
    private String title;
    private String content;
    private String writer;
    private String category;

}
