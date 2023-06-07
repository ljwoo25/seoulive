package org.zerock.seoulive.member.information.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2


@Value
public class tbl_memberVO {
	private String email;
	private String password;
	private String nickname;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date birth_date;
	private String gender;
	private String introduction;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date join_date;
	private String profile_img;
	
	public tbl_memberDTO toDTO() {
		
		log.trace("\t toDTO() invoked.");
		
		tbl_memberDTO dto = new tbl_memberDTO();
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setNickname(nickname);
		dto.setBirth_date(birth_date);
		dto.setJoin_date(join_date);
		dto.setGender(gender);
		dto.setIntroduction(introduction);
		dto.setProfile_img(profile_img);
		
		log.info("\t toDTO : {}",dto);
		return dto;
	}	// end toDTO
	
}	// end class
