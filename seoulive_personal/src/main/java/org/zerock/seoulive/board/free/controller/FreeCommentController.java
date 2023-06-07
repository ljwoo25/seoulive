package org.zerock.seoulive.board.free.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zerock.seoulive.board.free.domain.FreeCommentDTO;
import org.zerock.seoulive.board.free.service.FreeCommentService;

import java.util.List;

@RestController
@RequestMapping("/board/free/comment/*")
public class FreeCommentController {


    FreeCommentService freeCommentService;

    @Autowired
    public FreeCommentController(FreeCommentService freeCommentService) {
        this.freeCommentService = freeCommentService;
    }

    @GetMapping("/{post_seq}")
    public List<FreeCommentDTO> freeCommentList(@PathVariable("post_seq") int post_seq) {
        List<FreeCommentDTO> commentList = freeCommentService.freeCommentList(post_seq);
        return commentList;
    }

    @PostMapping("/write")
    public void freeCommentInsert(FreeCommentDTO dto) {
        freeCommentService.freeCommentInsert(dto);
    }

    @DeleteMapping("/{seq}")
    public void freeCommentDelete(@PathVariable("seq") int seq) {
        freeCommentService.freeCommentDelete(seq);
    }
    @DeleteMapping("/post/{seq}")
    public void freeCommentPostDelete(@PathVariable("seq") int seq) {
        freeCommentService.freeCommentPostDelete(seq);
    }



    @PutMapping("/commentModify")
    public void commentModify(@RequestBody FreeCommentDTO dto) {
        freeCommentService.freeCommentModify(dto);
    }



}
