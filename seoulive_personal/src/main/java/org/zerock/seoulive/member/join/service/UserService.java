package org.zerock.seoulive.member.join.service;

import org.zerock.seoulive.member.join.domain.UserDTO;
import org.zerock.seoulive.member.join.domain.UserVO;
import org.zerock.seoulive.exception.ServiceException;

public interface UserService {

    // 등록 기능 수행 (CREATE)
    public abstract Boolean register(UserDTO dto) throws ServiceException;

    // 로그인 수행시 입력한 아이디/암호에 해당하는 사용자 정보 획득 (성공시 인증성공의 의미)
    public abstract UserVO authenticate(UserDTO dto) throws ServiceException;

    // 이메일, 닉네임 중복체크
    public abstract int emailCheck(String email) throws ServiceException;
    public abstract int nicknameCheck(String nickname) throws ServiceException;



} // end interface
