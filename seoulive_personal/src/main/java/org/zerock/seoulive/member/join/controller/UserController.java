package org.zerock.seoulive.member.join.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.exception.ControllerException;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.member.join.domain.UserDTO;
import org.zerock.seoulive.member.join.service.MailSendService;
import org.zerock.seoulive.member.join.service.UserService;

import java.io.File;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@RequestMapping("/member")
@Controller
public class UserController {
    @Setter(onMethod_ = @Autowired)
    private UserService service;
    @Setter(onMethod_ = @Autowired)
    private MailSendService mailService;

    @GetMapping("/join/main")
    void joinMain() {   // 단순 등록화면 요청
        log.trace("joinMain() invoked.");

    } // joinMain

    @GetMapping("/join/email")
    void register() {   // 단순 등록화면 요청
        log.trace("register() invoked.");

    } // register

    // 회원가입 처리
    @PostMapping(
            value = "/join/email",
            params = {"email", "password", "birthDate", "gender", "nickname", "introduction"}
    )
    String register(UserDTO dto, @RequestParam("profileImg") MultipartFile profileImg, RedirectAttributes rttrs) throws ControllerException {
        log.trace("register({}, {}) invoked.", dto);

        try {
            Objects.requireNonNull(dto);

            // 프로필 사진
            if(profileImg != null) {
                File dir = new File(File.separator+"/opt/homebrew/Cellar/tomcat@9/9.0.73/libexec/bin/upload");
                if(dir.exists() == false)   {  dir.mkdirs();  }
                File savePath = new File(File.separator+"/opt/homebrew/Cellar/tomcat@9/9.0.73/libexec/bin/upload/" + profileImg.getOriginalFilename());
//                File savePath = new File("file:///opt/uploadfiles/" + profileImg.getOriginalFilename());
                profileImg.transferTo(savePath);

                dto.setProfileImgName("static/img/profile/" + profileImg.getOriginalFilename());
            }

            // 회원정보
            String hashedPw = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
            dto.setPassword(hashedPw);
            this.service.register(dto);
            rttrs.addFlashAttribute("msg", "REGISTERED");

            return "redirect:/member/login/email";
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // register

    //이메일 인증
    @GetMapping("/join/email/mailCheck")
    @ResponseBody
    public String mailCheck(String email) {
        log.trace("mailCheck({}) invoked.", email);

        return mailService.joinEmail(email);
    } // mailCheck

    // email 중복 체크 컨트롤러
    @PostMapping(value="/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email) throws ServiceException {
        log.trace("emailCheck({}) invoked.", email);

        try {
            return service.emailCheck(email);
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    } // emailCheck

    @PostMapping(value="/nicknameCheck")
    @ResponseBody
    public int nicknameCheck(@RequestParam("nickname") String nickname) throws ServiceException {
        log.trace("nicknameCheck({}) invoked.", nickname);

        try {
            return service.nicknameCheck(nickname);
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    } // nicknameCheck


} // end class
