package org.zerock.seoulive.member.information.domain;

import java.util.Date;

import lombok.Data;

@Data
public class tbl_memberDTO {
	private String email;
	private String password;
	private String nickname;
	private Date birth_date;
	private String gender;
	private String introduction;
	private Date join_date;
	private String profile_img;
	
	
}	// end class
