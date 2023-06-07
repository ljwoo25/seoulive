package org.zerock.seoulive.board.cs.faq.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.board.cs.faq.domain.FaqCriteria;
import org.zerock.seoulive.board.cs.faq.domain.FaqDTO;
import org.zerock.seoulive.board.cs.faq.domain.FaqVO;
import org.zerock.seoulive.board.cs.faq.domain.FaqPageDTO;
import org.zerock.seoulive.exception.ControllerException;
import org.zerock.seoulive.board.cs.faq.service.FaqService;

import java.util.List;
import java.util.Objects;

@Log4j2
@NoArgsConstructor


@Controller
@RequestMapping("/board/cs/faq")
public class FaqController {

    @Setter(onMethod_ = @Autowired)
    private FaqService service;

    // 목록 조회
    @GetMapping(value = "/list")
    void list(FaqCriteria cri, Model model) throws ControllerException {
        log.trace("list({} ,{}) invoked", cri, model);
        try {
            List<FaqVO> list = this.service.getList(cri);
            model.addAttribute("__LIST__", list);

            FaqPageDTO pageDTO = new FaqPageDTO(cri, this.service.getTotal(cri));
            model.addAttribute("pageMaker", pageDTO);
        } catch (Exception e){
            throw new ControllerException(e);
        } // try-catch
    } // list

    // 게시물 등록
    @PostMapping(path="/write", params={"title", "content", "writer", "category"})
    String write(FaqDTO dto, RedirectAttributes rttrs) throws ControllerException{
        log.trace("write({}, {}) invoked.", dto, rttrs);

        try{
            Objects.requireNonNull(dto);

            if(this.service.write(dto)){
                rttrs.addAttribute("result", "true");
                rttrs.addAttribute("seq", dto.getSeq());
            } // if

            return "redirect:/board/cs/faq/list";

        } catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // write

    // 단순등록
    @GetMapping("/write")
    void wirte(){
        log.trace("write() invoked.");
    }

    // 특정 게시물 수정
    @GetMapping(path={ "/modify" }, params = "seq")
    void get(@RequestParam("seq") Integer seq, Model model)
            throws ControllerException {
        log.trace("get() invoked.");

        try {
            FaqVO vo = this.service.get(seq);

            model.addAttribute("__BOARD__", vo);
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // get

    // 특정 게시물 업데이트 (UPDATE)
    @PostMapping("/modify")
    String modify(FaqDTO dto, Integer currPage, RedirectAttributes rttrs)
            throws ControllerException {
        log.trace("modify({}, {}) invoked.", dto, currPage);

        try {   // 전송파라미터 필요 (title, content, writer, category)
            Objects.requireNonNull(dto);
            rttrs.addAttribute("currPage", currPage);

            if(this.service.modify(dto)) {  // if success
                rttrs.addAttribute("result", "true");
                rttrs.addAttribute("seq", dto.getSeq());
            } // if

            return "redirect:/board/cs/faq/list";
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // modify


    // 게시물 삭제 (DELETE)
    @ResponseBody
    @RequestMapping(value="/remove", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    void remove(Integer currPage, @RequestParam("seq") List<String> seq, RedirectAttributes rttrs)
            throws ControllerException {
        log.trace("remove({}, {}) invoked.", currPage, seq);

        try {

            rttrs.addAttribute("currPage", currPage);
            for(String seqNum : seq){
                Integer removeSeq = Integer.valueOf(seqNum);
                if(this.service.remove(removeSeq)) { // if success
                    rttrs.addAttribute("result", "true");
                    rttrs.addAttribute("seq", removeSeq);
                } // if
            }

        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // remove

    @ResponseBody
    @GetMapping(value="/main/list")
    ResponseEntity<List<FaqVO>> mainList() throws ControllerException {
        try {
            List<FaqVO> list = this.service.getMainList(5);

            return ResponseEntity.ok().body(list);
        } catch (Exception e){
            throw new ControllerException(e);
        } // try-catch
    }

}
