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
public class AuthInterceptor implements HandlerInterceptor {

    @Setter(onMethod_ = {@Autowired})
    private UserMapper mapper;


    // HTTP request가 Request Mapping대로, Controller의 Handler에 위임되기 직전에 가로채서
    // 이 요청을 보낸 웹브라우저가 인증을 완료 여부를 체크하고 인증이 안되어 있으면 로그인 창으로 밀어버리는 것이 인터셉터의 역할
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {  // 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
        log.trace("====================================================");
        log.trace("preHandle(req, res, {}) invoked.", handler);
        log.trace("====================================================");

        HttpSession session = req.getSession();
        // 없으면 null 반환, 있으면 반환
        // 요청을 보낸 웹브라우저에 대한 세션(HttpSession 객체)이 있으면 있는걸 반환하고 없으면 null 반환
//        HttpSession session = req.getSession(false);

        // 세션이 있으면 아래에서 인증정보 (=Cridential)가 있는지 확인
        String sessionId = session.getId(); // sessionId : 웹브라우저에게 지어준 이름 (금고상자의 키)
        log.info("\t+ 1. sessionId : {}", sessionId);

        UserVO vo = (UserVO)session.getAttribute("__AUTH__");

        if(vo != null) {    // 인증된 웹브라우저
            log.info("Authentication found.");

            return true;
        } else {            // 세션은 있으나 인증정보가 없는 인증이 안된 웹브라우저
            // 자동로그인(Remember-Me) 처리
            // 1. 웹브라우저의 세션객체 안에 이전 웹브라우저가 종료되기 전에 인증되었던 Credential(=UserVO)를 복구해줌
            // 2. 1.의 처리가 만족 되는지 확인 => 무사통과(if satisfied)

            // 1) 스프링이 제공하는 유틸리티 클래스(WebUtils)의 기능을 이용해서
            // 웹 브라우저가 보낸 HTTP request 메시지에 있는 쿠키값을 쉽게 얻어냄
            Cookie rememberMeCookie = WebUtils.getCookie(req, "__REMEMBER_ME__");
            if(rememberMeCookie != null) {  // 자동로그인 설정된 웹브라우저의 요청
                log.info("\t+ Remember - Auto credential recovery.");

                // 2) 1)에서 얻어낸 자동로그인 쿠키의 이름과 값을 지역변수에 보관
                String cookieName = rememberMeCookie.getName();
                String cookieValue = rememberMeCookie.getValue();   // 세션ID => 웹브라우저의 이름으로 쿠키값 설정
                log.info("\t+ cookieName : {}, cookieValue : {}", cookieName, cookieValue);

                // 3) 2)에서 얻어낸 자동로그인 쿠키값(=세선ID)으로 tbl_user 테이블 조회 =>
                // 정확히 1개의 User가 선택(SELECT) => 마치 userid/userpw로 1 사용자 조회한것과 동일
                // 사용자가 발견된다면 이 사용자에 대한 UserVO객체를 획득하고
                // 획득한 UserVO객체를 현재 웹브라우저에 대응되는 세션객체
                // "__AUTH__" 이름으로 UserVO객체를 Credential
                Objects.requireNonNull(this.mapper);

                // 자동로그인 요청한 웹브라우저의 Credential(인증객체) 획득
                UserVO userVO = this.mapper.selectUserByRememberMe(cookieValue);
                log.info("\t+ userVO : {}", userVO);

                // 4) Credential Recovery
                if(userVO != null) {    // 자동로그인 설정된 웹브라우저이면
                    // 인증정보 복구 수행 (정상적인 로그인 처리과정을 거친 것과 동일한 결과)
                    session.setAttribute("__AUTH__", userVO);
                    log.info("Credential Recovery Done Successfully.");

                    return true;
                } // inner if : 정상적인 자동로그인 요청에 대한 처리인 경우

            } // outer if : 자동로그인 처리 대상 요청인 경우

            // 자동로그인 설정이 안되어 있는 경우와 위의 조건을 만족하지 못하는 경우 리다이레션
            log.info("No credential found. Redirect to Login");

            res.sendRedirect("/member/login/main");
            return false;   // 원래 요청을 처리하지 못하도록 함
        } // if-else
    } // preHandle

} // end class