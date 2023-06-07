package org.zerock.seoulive.mypage.service;

import org.zerock.seoulive.board.course.domain.CourseVO;
import org.zerock.seoulive.board.review.domain.ReviewBoardVO;
import org.zerock.seoulive.board.travel.domain.TravelBoardVO;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.mypage.domain.Criteria;
import org.zerock.seoulive.mypage.domain.UserLikeVO;
import org.zerock.seoulive.mypage.domain.tbl_followerVO;
import org.zerock.seoulive.mypage.domain.tbl_likeVO;

import java.util.List;

public interface MyPageBoardService {
   
   // 찜목록 확인하기   list 리뷰 getList // Criteria cri
   public abstract List<ReviewBoardVO> getMyReviewList(String email) throws ServiceException;
   // 찜목록 중 게시물 상세조회 read
   public abstract ReviewBoardVO getMyReview(Integer seq) throws ServiceException;
   // 자신의 리뷰 확인하기 list
   public abstract List<UserLikeVO> getMyLikeReview(String email) throws ServiceException;
   public abstract List<UserLikeVO> getMyLikeCourse(String email) throws ServiceException;
   public abstract List<UserLikeVO> getMyLikeTravel(String email) throws ServiceException;


   // 자신의 리뷰들 중 상세조회 read
   public abstract tbl_likeVO getMyLike(Integer seq) throws ServiceException;
   
   // 6. 총 게시물 개수 반환
   public abstract Integer getMyPageReview(String email) throws ServiceException;
      
   
   
   // 내가 작성한 여행지 코스들 확인하기 list
   public abstract List<TravelBoardVO> getMyTravelList(String email) throws ServiceException;
   
   // 내가 작성한 여행지 코스들 확인하기 보드 
      public abstract TravelBoardVO getMyTravelBoard(Integer seq) throws ServiceException;
   
      
   // OK 그러나, mapping 문제가 있어서, 클릭 게시물 조회는 아직 되지 않는다. 
   // 자신이 작성한 코스들 확인하기 list
   public abstract List<CourseVO> getMyCourseList(String email) throws ServiceException;
   // 자신이 작성한 코스들 중 상세 조회 read
   public abstract CourseVO getMyCourse(Integer seq) throws ServiceException;
   
   
   
   // 팔로워 확인하기 
   public abstract List<tbl_followerVO> getFollowerList(Criteria cri) throws ServiceException;
   
}   // end interface