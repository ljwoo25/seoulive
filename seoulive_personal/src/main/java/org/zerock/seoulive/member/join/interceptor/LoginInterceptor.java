package org.zerock.seoulive.member.join.interceptor;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.seoulive.member.join.domain.UserVO;
import org.zerock.seoulive.member.join.mapper.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@Log4j2
@NoArgsConstructor

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Setter(onMethod_ = @Autowired)
    UserMapper mapper;

    // 전처리는 이미 사용자가 로그인창에서 아이디/암호를 입력/전송했다는 것은 분명하기 때문에
    // 로그인 요청을 먼저 가로채서 전처리에서는 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체가 있다면 무조건 파괴
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {  // 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
        log.trace("====================================================");
        log.trace("preHandle(req, res, {}) invoked.", handler);
        log.trace("====================================================");

        // 1) 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체 획득
        HttpSession session = req.getSession(false);
        if(session != null) {
            log.info("\t+ sessionId : {}", session.getId());

            // 2) 기존 세션은 무조건 파괴시켜서 새로운 credential은 새로운 객체에 저장
            session.invalidate();
            log.info("\t+ Session({}) Destroyed.", session.getId());
        } // if

        return true;    // 인증로직은 기존대로 컨트롤러의 핸들러가 처리하도록 함
    } // preHandle

    // 후처리
    // 자동 로그인 처리를 위한 로직 구현
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView)
            throws Exception {  // 후처리
        HandlerInterceptor.super.postHandle(req, res, handler, modelAndView);
        log.trace("====================================================");
        log.trace("postHandle(req, res, {}, {}) invoked.", handler, modelAndView);
        log.trace("====================================================");

        // 1) 인증 성공여부에 따라 자동로그인 처리를 수행
        boolean authenticated = isAuthenticated(req);
        boolean rememberMe = checkRememberMEOption(req);
        log.info("\t+ Remember-Me : authencated({}), rememberMe({})", authenticated, rememberMe);

        if(authenticated & rememberMe) {    // 자동로그인 처리 조건을 만족한다면

            // 2) 자동로그인 설정을 요청한 웹브라우저의 이름인 세션ID 획득
            HttpSession session = req.getSession(false);
            String sessionId = session.getId();     // 쿠키값으로 사용

            // 3) 2)에서 얻은 세션ID를 값으로 하는 자동로그인 쿠키 생성
            Cookie rememberMeCookie = new Cookie("__REMEMBER_ME__", sessionId);
//            rememberMeCookie.setMaxAge(0);  // 0 : 만료기간 없음, 1 : 1초 (in seceonds)
            final int maxAge = 1*60*60*24*7;   // 유효기간 1주일
            rememberMeCookie.setMaxAge(maxAge);
            rememberMeCookie.setPath("/");

            // 4) 응답메시지의 Set-Cookie 헤더에 쿠키를 값으로 설정
            res.addCookie(rememberMeCookie);

            // 5) tbl_user 테이블에 현재 사용자의 추가정보로 쿠키값 기록
            long currDateTime = System.currentTimeMillis();
            long expireDateTime = currDateTime + (maxAge * 1000L);

            Timestamp rememeberMeCookieMaxAge = new Timestamp(expireDateTime);

            int affectedLines = this.mapper.updateUserWithRememberMe(
                    getEmail(req),
                    rememberMeCookie.getValue(),
                    rememeberMeCookieMaxAge
            );

            boolean isUpdated = (affectedLines == 1);
            log.info("\t+ isUpdated : {}", isUpdated);
        } // if

    } // postHandle

    // 인증정보로부터 사용자 아이디 반환
    private String getEmail(HttpServletRequest req) {
        log.trace("\t getUserId(req) invoked.");

        HttpSession session = req.getSession(false);

        UserVO userVO = (UserVO) session.getAttribute("__AUTH__");
        log.info("\t userVO: {}", userVO);

        return userVO.getEmail();
    } // getUserId

    // 인증 성공 여부 반환
    private boolean isAuthenticated(HttpServletRequest req) {
        log.trace("\t isAuthenticated(req) invoked.");

        HttpSession session = req.getSession(false);
        Object obj = session.getAttribute("__AUTH__");
        log.info("\t obj: {}",obj);

        return obj != null;
    } // isAuthenticated

    // 자동 로그인 옵션 on/off 반환
    private boolean checkRememberMEOption(HttpServletRequest req) {
        log.trace("checkRememberMEOption() invoked.");

        String rememberMe = req.getParameter("rememberMe"); // if checked, return "on"
        return rememberMe != null;
    } // checkRememberMEOption

} // end class