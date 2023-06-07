package org.zerock.seoulive.member.join.mapper;


import org.apache.ibatis.annotations.Param;
import org.zerock.seoulive.member.join.domain.UserDTO;
import org.zerock.seoulive.member.join.domain.UserVO;

import java.util.Date;

public interface UserMapper {


    // 회원가입
    public abstract Integer insert(UserDTO dto);

    // 이메일, 닉네임 중복체크
    public abstract int checkOverEmail(String email);
    public abstract int checkOverNickname(String nickname);

    // 로그인
    public abstract UserVO selectUser(UserDTO dto);

    // 자동 로그인
    public abstract Integer updateUserWithRememberMe(
            @Param("email") String email,
            @Param("rememberme") String rememberMeCookie,
            @Param("remembermeage") Date rememberMeCookieMaxAge
    );

    public abstract UserVO selectUserByRememberMe(String rememberMe);


} // end interface
