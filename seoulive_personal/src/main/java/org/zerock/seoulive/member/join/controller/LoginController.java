package org.zerock.seoulive.member.join.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.member.join.domain.UserDTO;
import org.zerock.seoulive.member.join.domain.UserVO;
import org.zerock.seoulive.member.join.service.UserService;


@Log4j2
@NoArgsConstructor

@SessionAttributes({"__AUTH__"})    // 인증객체를 세션에 넣어줌

@RequestMapping("/member")
@Controller
public class LoginController {
    @Setter(onMethod_ = @Autowired)
    private UserService service;
    @Setter(onMethod_ = @Autowired)
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/loginPost")
    public String loginPost(Model model, UserVO user, UserDTO dto, RedirectAttributes rttrs) throws Exception {
        log.trace("loginPost({}, model) invoked.", dto);

        UserVO userVO = service.authenticate(dto);

        if (userVO != null) {  // 일치하는 아이디 존재시
            String rawPw = user.getPassword();   // 사용자가 제출한 비밀번호
            String encodePw = userVO.getPassword();   // 데이터베이스에 저장한 인코딩 비밀번호

            if (passwordEncoder.matches(rawPw, encodePw)) {   //비밀번호 일치 여부 판단
                dto.setPassword("");    // 인코딩된 비밀번호 정보 지움
                model.addAttribute("__AUTH__", userVO);

                return "redirect:/";
            } else {
                rttrs.addAttribute("__RESULT__", "로그인 실패");

                return "redirect:/member/login/main";
            } // if-else
        } else {        // 일치하는 아이디가 존재하지 않을 시
            rttrs.addAttribute("__RESULT__", "로그인 실패");

            return "redirect:/member/login/main";
        } // if-else
    } // loginPost


} // end class