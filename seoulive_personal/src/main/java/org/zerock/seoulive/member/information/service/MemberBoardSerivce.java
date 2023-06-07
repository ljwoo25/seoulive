package org.zerock.seoulive.member.information.service;


import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.member.information.domain.tbl_memberVO;




public interface MemberBoardSerivce {
	
		
		
		// 이메일 찾기 
		public abstract tbl_memberVO get(String email) throws ServiceException;
		
		// 비밀번호 변경
		
		public abstract Boolean modifyPassword(String email, String password) throws ServiceException;
		
	
	
}	// end interface
