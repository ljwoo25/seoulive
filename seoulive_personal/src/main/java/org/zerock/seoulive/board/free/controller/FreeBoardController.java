package org.zerock.seoulive.board.free.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.seoulive.board.free.domain.Criteria;
import org.zerock.seoulive.board.free.domain.FreeCommentDTO;
import org.zerock.seoulive.board.free.domain.FreeDTO;
import org.zerock.seoulive.board.free.domain.PageDTO;
import org.zerock.seoulive.board.free.persistence.FreeDAO;
import org.zerock.seoulive.board.free.service.FreeCommentService;
import org.zerock.seoulive.board.free.service.FreeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/free/*")
public class FreeBoardController {

    FreeService freeService;
    FreeCommentService freeCommentService;

    @Autowired
    public FreeBoardController(FreeService freeService, FreeCommentService freeCommentService) {
        this.freeService = freeService;
        this.freeCommentService = freeCommentService;
    }



    @GetMapping("/list")
    public String list(Criteria cri, Model model) {
        List<FreeDTO> list = freeService.getList(cri);
        model.addAttribute("list",list);

        int total = freeService.getTotal(cri);

        model.addAttribute("pageMaker",new PageDTO(cri,total));

        return "board/free/list";
    }
    @GetMapping("/write")
    public String write() {
        return "board/free/write";
    }

    @PostMapping("/write")
    public String write(FreeDTO dto) {
        freeService.freeInsert(dto);
        return "redirect:/board/free/list";
    }


    @GetMapping("/view")
    public String view(@RequestParam("seq") int seq, Model model) {

        freeService.total_count(seq);

        FreeDTO dto = freeService.view(seq);
        model.addAttribute("dto",dto);

//        //댓글
//        List<FreeCommentDTO> comment_list = freeCommentService.freeCommentList(seq);
//        model.addAttribute("comment_list",comment_list);



        return "board/free/view";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("seq") int seq, Model model) {
        FreeDTO dto = freeService.view(seq);
        model.addAttribute("dto",dto);
        return "board/free/modify";
    }

    @PostMapping("/modify")
    public String modify(FreeDTO dto) {
        freeService.modify(dto);
        return "redirect:/board/free/list";
    }



    @GetMapping("/remove")
    public String remove(@RequestParam("seq") int seq) {
        freeService.remove(seq);
        return "redirect:/board/free/list";
    }







}
