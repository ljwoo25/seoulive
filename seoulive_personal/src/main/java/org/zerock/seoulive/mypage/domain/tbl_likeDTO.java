package org.zerock.seoulive.mypage.domain;

import lombok.Data;

@Data
public class tbl_likeDTO {
	private Integer seq;
	private String email;
	private Integer boardSeq;
	private String board;
}	// end class
