package org.zerock.seoulive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.seoulive.board.course.domain.CourseDTO;
import org.zerock.seoulive.board.course.domain.CoursePageTO;
import org.zerock.seoulive.board.course.service.CourseService;
import org.zerock.seoulive.board.free.service.FreeService;
import org.zerock.seoulive.board.review.domain.ReviewBoardVO;
import org.zerock.seoulive.board.review.service.ReviewBoardService;
import org.zerock.seoulive.board.travel.domain.VO;
import org.zerock.seoulive.board.travel.service.Service;
import org.zerock.seoulive.exception.ControllerException;

import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired CourseService courseService;
	@Autowired FreeService freeService;
	@Autowired ReviewBoardService reviewService;
	@Autowired Service travelService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws ControllerException {

		try {
			CoursePageTO page = new CoursePageTO();
			List<CourseDTO> courseList = this.courseService.searchCourse(page);
			model.addAttribute("__COURSE_LIST__", courseList);

			org.zerock.seoulive.board.review.domain.Criteria cri_review = new org.zerock.seoulive.board.review.domain.Criteria();
			List<ReviewBoardVO> reviewList = this.reviewService.getList(cri_review);
			model.addAttribute("__REVIEW_LIST__", reviewList);

			org.zerock.seoulive.board.travel.domain.Criteria cri_travel = new org.zerock.seoulive.board.travel.domain.Criteria();
			List<VO> travelList = this.travelService.getList(cri_travel);
			model.addAttribute("__TRAVEL_LIST__",travelList);

		} catch (Exception e) {
			e.getStackTrace();
			throw new ControllerException(e);
		}
		return "home";
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//		String formattedDate = dateFormat.format(date);
//
//		model.addAttribute("serverTime", formattedDate );
//
//		return "home";
//	}
}
