package org.zerock.seoulive.member.information.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.zerock.seoulive.member.information.domain.tbl_memberDTO;
import org.zerock.seoulive.member.information.domain.tbl_memberVO;

public interface MemberBoardMapper {
	
	// persistance layor
	

//	// 목록 확인 
//	@Select("""
//			SELECT /*+ index_besc(tbl_course) */
//			*
//			FROM tbl_member
//			""")
//	public abstract List<tbl_courseVO> selectList();
	
	@Select("Select current_date+5 from dual")
	public abstract Date GetDate();
	
	@Select("Select current_date from dual")
	public abstract Date getJoin_date();

	 

	
	// 계정생성 
	public abstract void createUser(
			String email,
			String password,
			String nickName,
			Date birth_date, 
			Date join_date, 
			String gender, 
			String introduction, 
			String profile_img
			);
	
	// 5. 특정 멤버 업데이트(갱신)
		public abstract Integer update(tbl_memberVO vo);
	
	// 비밀번호 선택 
	public abstract String selectPassword(String email);
	// 비밀번호 insert
	// 이친구는 필요없음 
//	public abstract String insertPassword(String email,String password);
	// 비밀번호 업데이트 
	public abstract Integer updatePassword(tbl_memberDTO dto);
	
	// 이메일 확인 select
	// 일단은 닉네임으로 해둠 
	public abstract tbl_memberVO selectEmail(String email);
	//			insert
	// 버림 
//	public abstract String insertEmail(tbl_memberDTO dto);
	//			delete
	public abstract Boolean deleteEmail(String email);
	
	// 생년월일 select
	public abstract Date  selectBirthDate(String email);
	//		  insert

//	public abstract Date insertBirthDate(String birth_date,String email);
	//		  update 
	public abstract void updateBirthDate(String birth_date,String email);
	
	// 가입일 select 
	public abstract Date selectJoinDate(String email);
	// 가입일 update
	
	public abstract void updateJoinDate(String join_date,String email);
	
	
	// 성별 select
	public abstract String selectGender(String email);
	//     insert
//	public abstract String insertGender(tbl_memberDTO dto);
	//	   update 
	public abstract void updateGender(String email,String gender);
	
	// 소개글  select
	public abstract String selectIntroduction(String email);
	//		insert
//	public abstract String insertIntroduction(tbl_memberDTO dto);
	//       update 수정 
	public abstract void updateIntroduction(String email,String Introduction);
	// 프로필 사진  select
	public abstract String selectProfileImg(String email);
	//			insert
//	public abstract String insertProfileImg(tbl_memberDTO dto);
	//			 update 수정 
	public abstract void updateProfileImg(String email,String profile_img);

	public abstract void withdrawal(tbl_memberVO vo);
	
}	// end interface
