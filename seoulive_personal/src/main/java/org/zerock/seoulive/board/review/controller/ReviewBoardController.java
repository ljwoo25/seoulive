package org.zerock.seoulive.board.review.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.fileupload.UploadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.board.review.domain.*;
import org.zerock.seoulive.board.review.service.ReviewBoardService;
import org.zerock.seoulive.exception.ControllerException;

import java.io.File;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Log4j2
@Controller
@RequestMapping("/board/review")
public class ReviewBoardController {
    @Setter(onMethod_={@Autowired})
    private ReviewBoardService service;
    //게시물 목록 조회


    // 1. 게시판 목록 조회
//    @GetMapping("/list")
//    void list(Model model) throws ControllerException {
//        log.trace("list() invoked.", model);
//
//        try {
//            List<BoardVO> list =this.service.getList();
//
//            // Request Scope 공유속성 생성
//            model.addAttribute("__LIST__", list);
//        } catch (Exception e) {
//            throw new ControllerException(e);
//        } // try-catch
//    } // list

    // 1. 게시판 목록 조회
    @GetMapping("/list")
    void list(Criteria cri,Model model) throws ControllerException {
        log.trace("list({},{}) invoked.",cri, model);

        try {
            List<ReviewBoardVO> list = this.service.getList(cri);
            // Request Scope 공유속성 생성
            model.addAttribute("__LIST__", list);

            PageDTO pageDTO = new PageDTO(cri,this.service.getTotal(cri));
            model.addAttribute("pageMaker", pageDTO);

        } catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // list

    @PostMapping("/likeList")
    void likeList(ReviewLikeDTO dto) throws ControllerException {
        try {

            this.service.likeList(dto);

        } catch(Exception e) {
            e.getStackTrace();
            throw new ControllerException(e);

        } //try-catch
    }

// 2. 새로운 게시물 등록
    @PostMapping(path={"/write"}, params= {"title", "content", "writer","place"})
    String writer(ReviewBoardDTO dto,  MultipartFile file) throws ControllerException {

        try {

            this.service.register(dto);
            this.service.fileSave(file);

            String path = "C:/Users/82104/Desktop/seoulliveproject/codeNineSeoulive/src/main/webapp/resources/imgUpload/"+file.getName();
            file.transferTo(new File(path));

            return "redirect:/board/review/list";
        } catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // writer

    @GetMapping("/write")
    void writer() {	// 단순 등록화면 요청
        log.trace("write() invoked.");

    } // register



    // 3. 특정 게시물 상세조회
    @GetMapping(path={"/view","/modify"}, params= "seq")
    void view(@RequestParam("seq") Integer seq, Model model)
            throws ControllerException {
        log.trace("view() invoked.");

        try {
            ReviewBoardVO vo = this.service.get(seq);
            List<ReviewCommentDTO> list = this.service.commentList(seq);
            model.addAttribute("__CLIST__", list);

            model.addAttribute("__BOARD__", vo);
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // view
    // 4. 특정 게시물 업데이트(UPDATE)
    @PostMapping("/modify")
    String modify(ReviewBoardDTO dto, RedirectAttributes rttrs ) throws ControllerException {
        log.trace("modify({}) invoked.",dto);

        try { //title ,content , writer 3개의 전송파라미터가 들어와야함
            Objects.requireNonNull(dto);
//            rttrs.addAttribute("currPage",currPage);

            if(this.service.modify(dto)) {
                rttrs.addAttribute("result","true");
                rttrs.addAttribute("seq",dto.getSeq());
            } // if
            return "redirect:/board/review/list";
        } catch(Exception e) {
            throw new ControllerException(e);
        } //try-catch
    } // modify

    @PostMapping("/commentWrite")
    String commentWrite(ReviewCommentDTO dto) throws ControllerException {
        try {

            this.service.commentWrite(dto);

            return "redirect:/board/review/view?seq="+dto.getPost_seq();

        } catch(Exception e) {
            throw new ControllerException(e);
        } //try-catch
    }
    // 5. 특정 게시물 삭제(DELETE)
    @PostMapping("/remove")
    String remove(Integer seq, RedirectAttributes rttrs)
            throws ControllerException {
//        log.trace("remove({},{}) invoked.",currPage,seq);

        try {
            if(this.service.remove(seq)) {
//                rttrs.addAttribute("currPage", currPage);
                rttrs.addAttribute("result","true");
                rttrs.addAttribute("seq", seq);
            } // if

            return "redirect:/board/review/list";
        } catch(Exception e) {
            throw new ControllerException(e);
        } //try-catch
    } // remove

    private static final Logger logger=LoggerFactory.getLogger(UploadContext.class);

    @RequestMapping(value = "/uploadform" ,method = RequestMethod.POST)
    public void uploadForm(MultipartFile file, Model model)throws Exception{



    }


}//end class






