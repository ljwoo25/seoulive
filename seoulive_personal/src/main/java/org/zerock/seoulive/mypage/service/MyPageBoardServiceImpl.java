package org.zerock.seoulive.mypage.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.board.course.domain.CourseVO;
import org.zerock.seoulive.board.review.domain.ReviewBoardVO;
import org.zerock.seoulive.board.travel.domain.TravelBoardVO;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.mypage.domain.Criteria;
import org.zerock.seoulive.mypage.domain.UserLikeVO;
import org.zerock.seoulive.mypage.domain.tbl_followerVO;
import org.zerock.seoulive.mypage.domain.tbl_likeVO;
import org.zerock.seoulive.mypage.mapper.MyPageBoardMapper;

import java.util.List;

@Log4j2
@NoArgsConstructor

@Service("MyPageBoardService")
public class MyPageBoardServiceImpl 
   implements 
      MyPageBoardService,
      InitializingBean,
      DisposableBean{
   
   @Setter(onMethod_= {@Autowired})
   private MyPageBoardMapper mapper;

   @Override
   public List<ReviewBoardVO> getMyReviewList(String email) throws ServiceException {
      
      log.trace("getLikeReviewList({}) invoked.");
      
      try {
         return this.mapper.selectReviewList(email);
      }catch(Exception e){
         throw new ServiceException(e);
      }   // end try-catch
      
      
   }   // end getLikeReviewList

   @Override
   public ReviewBoardVO getMyReview(Integer seq) throws ServiceException {
      log.trace("getLikeReview({}) invoked.",seq);
      
      try {
         return this.mapper.selectReview(seq);
      }catch(Exception e) {
         throw new ServiceException(e);
      }   // end try-catch
      
   }   // end getLikeReview

   @Override
   public List<UserLikeVO> getMyLikeReview(String email) throws ServiceException {
      log.trace("\t getMyLikeList ({},{}) invoked.",email);

      try {
         List<UserLikeVO> list =  this.mapper.selectLikeReview(email);

         return   list;
      }catch(Exception e) {
         throw new ServiceException(e);
      }   // end try-catch
   }
   @Override
   public List<UserLikeVO> getMyLikeCourse(String email) throws ServiceException {
      log.trace("\t getMyLikeList ({},{}) invoked.",email);

      try {
         List<UserLikeVO> list =  this.mapper.selectLikeCourse(email);

         return   list;
      }catch(Exception e) {
         throw new ServiceException(e);
      }   // end try-catch
   }
   @Override
   public List<UserLikeVO> getMyLikeTravel(String email) throws ServiceException {
      log.trace("\t getMyLikeList ({},{}) invoked.",email);

      try {
         List<UserLikeVO> list =  this.mapper.selectLikeTravel(email);

         return   list;
      }catch(Exception e) {
         throw new ServiceException(e);
      }   // end try-catch
   }


   @Override
   public tbl_likeVO getMyLike(Integer seq) throws ServiceException {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public List<TravelBoardVO> getMyTravelList(String email) throws ServiceException {
         log.trace("getMyTravelList ({}) invoked.",email);
      
      try {
         List<TravelBoardVO> list = this.mapper.getMyTravelList(email);   
         return list;
      }catch(Exception e) {
         throw new ServiceException(e);
      }
   }   // getMyTravelList
   
   @Override
   public TravelBoardVO getMyTravelBoard(Integer seq) throws ServiceException{
         log.trace("getMyTravelList ({}) invoked.",seq);
      
      try {
         TravelBoardVO result = this.mapper.getMyTravelBoard(seq);   
         return result;
      }catch(Exception e) {
         throw new ServiceException(e);
      }
   }
   

   @Override
   public List<CourseVO> getMyCourseList(String email) throws ServiceException {
      log.trace("getMyCourseList ({}) invoked.",email);
      
      try {
         List<CourseVO> list = this.mapper.getMyCourseList(email);   
         return list;
      }catch(Exception e) {
         throw new ServiceException(e);
      }
      
      
   }

   @Override
   public CourseVO getMyCourse(Integer seq) throws ServiceException {
      log.trace("getMyCourse ({}) invoked.",seq);
      try {
         CourseVO result = this.mapper.getMyCourse(seq);   
         return result;
      }catch(Exception e) {
         throw new ServiceException(e);
      }
   }

   @Override
   public List<tbl_followerVO> getFollowerList(Criteria cri) throws ServiceException {
      // TODO Auto-generated method stub
      return null;
   }

//   @Override
//   public Integer getMyPageReviewTotal(String email) throws ServiceException {
//      
//      log.trace("getTotal() invoked.");
//      
//      return this.mapper.getMyPageReviewTotal(email);
//   }   
   

   @Override
   public void afterPropertiesSet() throws Exception {
      // TODO Auto-generated method stub
      
   }   // afterPropertiesSet
   
   @Override
   public void destroy() throws Exception {
      // TODO Auto-generated method stub
      
   }

   @Override
   public Integer getMyPageReview(String email) throws ServiceException {
      // TODO Auto-generated method stub
      return null;
   }

   
   

   

}   // end class