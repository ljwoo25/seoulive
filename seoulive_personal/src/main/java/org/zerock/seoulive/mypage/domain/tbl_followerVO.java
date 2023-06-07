package org.zerock.seoulive.mypage.domain;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class tbl_followerVO {
	private String email;
	private String follower;
	
	public tbl_followerDTO toDTO() {
		log.trace("\t toDTO() invoked.");
		
		tbl_followerDTO dto = new tbl_followerDTO();
		dto.setEmail(email);
		dto.setFollower(follower);
		
		log.info("\t toDTO : {}",dto);
		return dto;
	}	// toDTO
}
