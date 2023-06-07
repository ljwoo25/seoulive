package org.zerock.seoulive.member.information.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.member.information.domain.tbl_memberDTO;
import org.zerock.seoulive.member.information.domain.tbl_memberVO;
import org.zerock.seoulive.member.information.mapper.MemberBoardMapper;

import java.util.Objects;

@Log4j2
@NoArgsConstructor

@Service("boardMemberSerivce")
public class MemberBoardServiceImpl 
	implements 
		MemberBoardSerivce,
		InitializingBean,
		DisposableBean{

	// 영속성 계층의 DAO 빈 주입 받음(Persistence)
	@Setter(onMethod_= {@Autowired})
	private MemberBoardMapper mapper;

	
	
	// 이메일 찾기 
	@Override
	public tbl_memberVO get(String email) throws ServiceException {
		log.trace("get({}) invoked.",email);
		
		try {
			return this.mapper.selectEmail(email);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}	// tbl_courseVO
	
	@Override
	public Boolean modifyPassword(String email,String password) throws ServiceException{
		log.trace("modify({},{}) invoked.",email,password);
		try {
			tbl_memberVO vo = this.mapper.selectEmail(email);
			tbl_memberDTO dto = vo.toDTO();
			dto.setPassword(password);
			dto.setEmail(vo.getEmail());
			log.info("\n\ndto :{} ",dto);
			
			return (this.mapper.updatePassword(dto)==1);
		}catch(Exception e) {
			throw new ServiceException(e);
		}	// end try-catch
		
		
	}	// modify
	@Override
	public void afterPropertiesSet() throws Exception {
			log.trace("afterPropertiesSet() invoked.");
		
			Objects.requireNonNull(this.mapper);
			log.info("\t+ this.mapper: {}", mapper);
	} // afterPropertiesSet
	
	@Override
	public void destroy() throws Exception {
		log.trace("destroy() invoked.");
		
	}	// end destroy



}	// end class
