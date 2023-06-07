package org.zerock.seoulive.member.join.interceptor;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import org.zerock.seoulive.member.join.domain.UserVO;
import org.zerock.seoulive.member.join.mapper.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Component
public class LogoutInterceptor implements HandlerInterceptor {
    @Setter(onMethod_ = @Autowired)
    private UserMapper mapper;


    // 로그아웃 요청을 보낸 웹브라우저가 가지고 있는 세션객체(금고상자)를 파괴하는 작업을 전처리에서 수행
    // 이 요청은 뒤로 보내지 않음(컨트롤러의 핸들러로 보내지 않음)
    // 주의) 인증된 사용자가 로그아웃을 요구하지 않는 한 세션객체를 파괴시키면 안됨
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {  // 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
        log.trace("====================================================");
        log.trace("preHandle(req, res, {}) invoked.", handler);
        log.trace("====================================================");

        // 1) 로그아웃 요청을 보낸 웹브라우저에 대응하는 세션객체 획득
        HttpSession session = req.getSession(false);
        UserVO userVO = (UserVO) session.getAttribute("__AUTH__");

        if(session != null) {   // 로그아웃 처리 수행

            session.invalidate();   // 세션객체 삭제 (로그아웃 처리)
        } // if

        // 자동로그인과 관련된 로직 추가
        // 자동로그인 설정된 웹브라우저가 로그아웃을 요청했으니 자동로그인 해제

        // 2) 현재 로그아웃 요청한 웹브라우저가 자동로그인 설정 대상인지 확인
        Cookie rememberMeCookie = WebUtils.getCookie(req, "__REMEMBER_ME__");
        if(rememberMeCookie != null) {  // 자동로그인 설정 해제
            // 3) 웹브라우저가 보관하는 자동로그인 쿠키(RememberMeCookie) 파괴
            rememberMeCookie.setMaxAge(1);
            rememberMeCookie.setPath("/");

            res.addCookie(rememberMeCookie);    // 만료기간이 변경된 기존 쿠키를 다시 브라우저로 전송

            log.info("\t+ Remember-Me Cookie Destroyed.");

            // 4) tbl_user 테이블에 설정된 자동로그인 쿠키값과 만료일시 컬럼의 값을 null로 변경
            Objects.requireNonNull(this.mapper);
            Objects.requireNonNull(userVO);

            this.mapper.updateUserWithRememberMe(userVO.getEmail(), null, null);
            log.info("\t+ Remember-Me Released Successfully.");
        } // if

        res.sendRedirect("/member/login/main");    // 로그아웃처리 후 로그인 창으로 밀어버림

        return false;
    } // preHandle

} // end class
