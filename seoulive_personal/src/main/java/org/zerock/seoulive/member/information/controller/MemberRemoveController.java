package org.zerock.seoulive.member.information.controller;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.member.information.domain.tbl_memberVO;
import org.zerock.seoulive.member.information.service.MemberRemoveService;

import javax.servlet.http.HttpSession;

@RequestMapping(value = "/member")
@Controller
@Log4j2
@AllArgsConstructor
public class MemberRemoveController {

    @Setter(onMethod_ = @Autowired)
    private MemberRemoveService service;

    @GetMapping("/remove")
    public void getWithdrawal() throws Exception {
        log.info("getWithdrawal() invoked");
        }

    @PostMapping("/remove")
    public String postWithdrawal(HttpSession session, tbl_memberVO vo, RedirectAttributes rttrs) throws Exception {
        tbl_memberVO member = (tbl_memberVO)session.getAttribute("member");

        String storedPassword  = member.getPassword();
        String enteredPassword  = vo.getPassword();

        if(!(enteredPassword.equals(storedPassword))) {

            rttrs.addFlashAttribute("msg", false);
            return "redirect:/member/remove";
        }

        service.withdrawal(vo);

        session.invalidate();
        return "redirect:/";
    }
}


