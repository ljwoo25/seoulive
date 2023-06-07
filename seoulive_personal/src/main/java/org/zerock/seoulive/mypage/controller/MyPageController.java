package org.zerock.seoulive.mypage.controller;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.zerock.seoulive.board.course.domain.CourseVO;


import org.zerock.seoulive.board.review.domain.ReviewBoardVO;
import org.zerock.seoulive.exception.*;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.zerock.seoulive.mypage.domain.UserLikeVO;
import org.zerock.seoulive.mypage.mapper.MyPageBoardMapper;
import org.zerock.seoulive.mypage.service.MyPageBoardService;

@Log4j2
@NoArgsConstructor

@RequestMapping("/")
@Controller
public class MyPageController {

   @Setter(onMethod_=@Autowired)
   private MyPageBoardMapper mapper;
   //
   @Setter(onMethod_=@Autowired)
   private MyPageBoardService service;

   @GetMapping("/mypage/mypage")
   public void myPage(String email,Model model)throws ControllerException  {

      log.debug("\t myPage({},{}) invoked.",email);

      try {

         List<ReviewBoardVO> review = this.service.getMyReviewList(email);
         model.addAttribute("__REVIEW__",review);

         List<CourseVO> course = this.service.getMyCourseList(email);
         model.addAttribute("__COURSE__",course);

         List<UserLikeVO> userLikeReview = this.service.getMyLikeReview(email);
         model.addAttribute("__LIKE_REVIEW_LIST__",userLikeReview);
         List<UserLikeVO> userLikeCourse = this.service.getMyLikeCourse(email);
         model.addAttribute("__LIKE_COURSE_LIST__",userLikeCourse);
         List<UserLikeVO> userLikeTravel = this.service.getMyLikeTravel(email);
         model.addAttribute("__LIKE_TRAVEL_LIST__",userLikeTravel);

      }catch(Exception e) {
         throw new ControllerException(e);
      }   // end try-catch
//      return "/mypage/mypage";
   }   // end myPage

}   // end class
