package org.zerock.seoulive.board.review.domain;

import lombok.Data;

import java.sql.Timestamp;


// 결론 : VO패턴과 거의 차이가 없다.
// 		 단치 차이가 있다면,
//		 DTO패턴의 객체는, 수정가능하다(mutable)

@Data
public class ReviewBoardDTO {
    private Integer seq;
    private String writer;
    private String title;
    private String content;
    private String place;


}
