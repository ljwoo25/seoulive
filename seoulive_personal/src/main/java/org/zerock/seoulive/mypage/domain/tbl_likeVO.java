package org.zerock.seoulive.mypage.domain;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Value
public class tbl_likeVO {
	
	private Integer seq;
	private String email;
	private Integer boardSeq;
	private String board;
	
	public tbl_likeDTO toDTO() {
		log.trace("\t toDTO() invoked.");
		tbl_likeDTO dto = new tbl_likeDTO();
		dto.setSeq(boardSeq);
		dto.setEmail(email);
		dto.setBoardSeq(boardSeq);
		dto.setBoard(board);
		
		log.info("\t toDTO : {}",dto);
		return dto;
	}	// end toDTO()
}	// end class
