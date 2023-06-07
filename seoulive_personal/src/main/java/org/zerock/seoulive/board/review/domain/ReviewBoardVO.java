package org.zerock.seoulive.board.review.domain;

import lombok.Value;

import java.sql.Timestamp;


//==================================== //
//VO (Value Object) Pattern 규칙
//==================================== //
//1. VO 패턴에 따라  클래스 설계
//2. Target Table의 1개의 행을 읽기전용(Immutable)으로 저장가능한 필드 설계
//3. Lombok annotation 을 잘 활용할 수록 -> Clean Class 설계
//4. (*중요*) VO패턴은,
//  가. 테이블의 명세(스키마, DESC명령)에 나온 컬럼순서대로 필드 선언 (***)
//   나. 테이블의 각 컬럼의 타입 => 호환가능한 자바 타입으로 선언 (***)
//   다. 모든 필드(각 컬럼)를 매개변수로 가지는 생성자 선언 (***)
@Value
public class ReviewBoardVO {



    private Integer seq;
    private String writer;
    private String title;
    private String content;
    private Integer total;
    private Timestamp write_date;
    private Timestamp modify_date;
    private String place;

    public ReviewBoardDTO toDTO() {


        ReviewBoardDTO dto = new ReviewBoardDTO();
        dto.setSeq(seq);
        dto.setWriter(writer);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPlace(place);



        return dto;
    }	//	 toDTO



}
