package org.zerock.seoulive.board.course.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.seoulive.board.course.domain.*;
import org.zerock.seoulive.board.course.exception.ControllerException;
import org.zerock.seoulive.board.course.mapper.CommentMapper;
import org.zerock.seoulive.board.course.service.CourseCommService;
import org.zerock.seoulive.board.course.service.CourseService;
import org.zerock.seoulive.board.travel.domain.TravelDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board/course")

@Controller
public class CourseController {

	@Autowired private CourseService service;
	@Autowired private CourseCommService commservice;


	// 1. 검색 후 게시판 목록 조회
	@GetMapping(path="/list")
	void search(CoursePageTO page, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", page, model);
		
		try {
			List<CourseDTO> list = this.service.searchCourse(page);
			
			// Request Scope 공유속성 생성
			
			
			for (int i = 0; i<list.size(); i++) {
				list.get(i).setListVO(this.service.getTravelList(list.get(i)));
			} // for
			model.addAttribute("__LIST__", list);
			
			CoursePageDTO pageDTO = new CoursePageDTO(page, this.service.getTotalSearch(page));
			model.addAttribute("pageMaker", pageDTO);

		} catch(Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		} // try-catch
	} // search
	

	// 2. 새로운 게시물 등록
	@PostMapping(path="/write")
	String register(CourseWriteDTO dto) throws ControllerException {
		log.trace("write({}) invoked.", dto);
		
		try {
			this.service.register(dto);
			
			return "redirect:/board/course/list";
		} catch(Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		} // try-catch
	} // register
	// 2-1. 여행지 seq값 반환
	@PostMapping(path="/getSeq")
	ResponseEntity<Map<String, Integer>> getSeq(Model model) throws ControllerException {
		log.trace("getSeq() invoked.");
		
		try {
			Integer seq = this.service.getCourseSeq();
	        Map<String, Integer> response = new HashMap<>();
	        response.put("seq", seq);
	        return ResponseEntity.ok(response);
		} catch(Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		} // try-catch
	} // getSeq
	// 2-2. 코스게시물 내 여행지 등록
	@PostMapping(path="/registerTravel")
	String registerTravel(CourseWriteVO vo, Model model) throws ControllerException {
		log.trace("registerTravel({}) invoked.", vo);
		
		try {
			this.service.registerTravel(vo);

			return null;
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	// 2-3. 단순 등록화면 요청
	@GetMapping(path="/write")
	String register() {
		log.trace("register() invoked.");

		return "/board/course/write";
	} // register
	// 2-4. 작성시 여행지 검색
	@GetMapping("/travel_search")
	public String searchTravelData(@RequestParam("keyword") String keyword, 
									@RequestParam("resultId") String resultId, 
									Model model) throws ControllerException {
		try {
		    List<TravelDTO> travelData = this.service.getTravelData(keyword);
		    model.addAttribute("travelData", travelData);
		    model.addAttribute("resultId", resultId);
		    return "/board/course/searchTravelData"; 
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	}

	// 3. 찜 기능
	@PostMapping("/course_like")
	ResponseEntity<String> courseLike(CourseLikeDTO dto) throws ControllerException {
		log.trace("register({}) invoked.", dto);

		try {

			this.service.courseLike(dto);
			return ResponseEntity.ok("");
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // courseLike
	@PostMapping("/course_unlike")
	ResponseEntity<String> courseUnlike(CourseLikeDTO dto) throws ControllerException {
		log.trace("register({}) invoked.", dto);

		try {

			this.service.courseUnlike(dto);

			return ResponseEntity.ok("");
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // courseUnlike
	@PostMapping("/get_like_list")
	ResponseEntity<List<CourseLikeDTO>> courseLikeList(CourseLikeDTO dto) throws ControllerException {
		log.trace("register({}) invoked.", dto);

		try {
			List<CourseLikeDTO> favoriteList = this.service.courseLikeList(dto);
			System.out.println(favoriteList);
			return ResponseEntity.ok(favoriteList);
		} catch(Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		} // try-catch
	} // courseUnlike
	
	// 4. 상세조회
	@GetMapping(path ={ "/view", "/modify"}, params = {"seq"})
	public void get(Integer seq, Model model) throws ControllerException {
		log.trace("get() invoked");

		try {
			CourseVO vo = this.service.get(seq);
			List<CourseTravelVO> travelList = this.service.getTravelList(seq);
			List<CourseCommVO> List = this.commservice.list(seq);

			model.addAttribute("__BOARD__", vo);
			model.addAttribute("__COURSETRAVELBOARD__", travelList);
			model.addAttribute("__COMMENT_LIST__", List);

			Date startDate = vo.getDURATION_START();
			Date endDate = vo.getDURATION_END();

			// 종료일자에서 시작일자를 뺀 결과를 계산
			long durationInMillis = endDate.getTime() - startDate.getTime();
			int durationInDays = (int) (durationInMillis / (1000 * 60 * 60 * 24));
			model.addAttribute("__DAY_COUNT__", durationInDays);

		} catch (Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		}
	} // get
	
    @PostMapping("/comm_write")
	public String create(@RequestParam("WRITER") String WRITER,
						 @RequestParam("CONTENT") String CONTENT,
						 @RequestParam("POST_SEQ") Integer POST_SEQ
	) throws ControllerException {
		log.trace("create({}) invoked");

		try {
			CourseCommDTO dto = new CourseCommDTO();
			dto.setWriter(WRITER);
			dto.setContent(CONTENT);
			dto.setPost_seq(POST_SEQ);
			commservice.write(dto);

			return "redirect:/board/course/get?seq=" + dto.getPOST_SEQ();
		} catch (Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		}
	}
	//댓글 삭제
	@PostMapping(path = "/comm_delete")
	public ResponseEntity<String> commDelete(Integer seq) throws ControllerException {
		try {
			commservice.delete(seq);

			return ResponseEntity.ok("");
		} catch (Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		}
	}
	//댓글 수정
	@PostMapping("/comm_update")
	public void modify(CourseCommDTO dto) throws ControllerException {
		try {
			commservice.modify(dto);
		} catch (Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		}
	}


	
  //수정하기
//    @PostMapping (path = "/modify", params = {"seq"})
//    public String modify(@RequestParam(value="seq") Integer seq,
//                       courseDTO dto,courseTravelDTO coursedto,
//                       Integer currPage,RedirectAttributes rttrs) throws ControllerException {
//        log.trace("modify({}) invoked", dto);
//
//
//        try {
//            Objects.requireNonNull(dto);
//            assert coursedto != null;
//
//            rttrs.addAttribute("currPage", currPage);
//
//            if (this.service.modify(dto)) {
//                rttrs.addAttribute("result", "success");
//                rttrs.addAttribute("seq", dto.getSeq());
//                }
//            return "redirect:/course/list";
//        } catch (Exception e) {
//            throw new ControllerException(e);
//        }
//    }
	
//	@GetMapping(path="/modify")
//	void modify(Integer seq, Model model)  throws ControllerException {	// 단순 수정화면 요청
//		log.trace("modify({}) invoked.", seq);
//		
//		try {
//			
//			
//		} catch(Exception e) {
//			e.getStackTrace();
//			throw new ControllerException(e);
//		} // try-catch
//	} // modify		// get메소드로 이동함
	
	
//	// 5. 특정 게시물 삭제(DELETE)
//    @PostMapping("/remove")
//    public String remove(@RequestParam("seq")
//                             Integer currPage, Integer seq, RedirectAttributes rttrs) throws ControllerException {
//        log.trace("remove({}) inovked", seq);
//
//        try{
//            if(this.service.remove(seq)) {
//                rttrs.addAttribute("currPage", currPage);
//                rttrs.addAttribute("result", "success");
//                rttrs.addAttribute("seq", seq);
//            }
//            return "redirect:/board/course/list";
//        } catch (Exception e) {
//            throw new ControllerException(e);
//        }
//    }
	
} // end class
