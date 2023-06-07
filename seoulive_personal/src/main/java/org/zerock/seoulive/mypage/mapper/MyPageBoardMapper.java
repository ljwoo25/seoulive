package org.zerock.seoulive.mypage.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.seoulive.board.course.domain.CourseVO;
import org.zerock.seoulive.board.review.domain.ReviewBoardVO;
import org.zerock.seoulive.board.travel.domain.TravelBoardVO;
import org.zerock.seoulive.mypage.domain.UserLikeVO;
import org.zerock.seoulive.mypage.domain.tbl_likeVO;

import java.util.List;

public interface MyPageBoardMapper {
   

      @Select("""
            select 
                         r.seq as review_seq,
                         m.nickname as writer,
                       r.title as review_title,
                       r.content as review_content,
                       r.total as review_total,
                       r.write_date as review_write_date,
                       r.modify_date as review_modify_date,
                       r.place as review_place
                   from
                       tbl_member m, tbl_review r
                   where
                       m.nickname = r.writer
                            AND
                            m.email = #{email}
            """)
      public abstract List<ReviewBoardVO> selectReviewList(String email);


      @Select("""
               SELECT c.* FROM tbl_member m, tbl_course c
               WHERE m.nickname = c.writer AND m.email = #{email}
               """)
      public abstract List<CourseVO> getMyCourseList(String email);

      @Select("Select * FROM tbl_course where seq = #{seq}")
      public abstract CourseVO getMyCourse(Integer seq);

      
      public abstract ReviewBoardVO selectReview(Integer seq);
      public abstract List<tbl_likeVO> selectLikeList(String email);

      @Select("""
            SELECT seq , title
            FROM tbl_review
            WHERE seq in 
               (
               SELECT l.board_seq
               FROM tbl_like l
               WHERE l.email = #{email} 
                 AND l.board = 'review'
               )
            """)
      public abstract List<UserLikeVO> selectLikeReview(String email);
      @Select("""
            SELECT seq , title
            FROM tbl_course
            WHERE seq in 
               (
               SELECT l.board_seq
               FROM tbl_like l
               WHERE l.email = #{email} 
                 AND l.board = 'course'
               )
            """)
      public abstract List<UserLikeVO> selectLikeCourse(String email);
      @Select("""
            SELECT seq , title
            FROM tbl_travel
            WHERE seq in 
               (
               SELECT l.board_seq
               FROM tbl_like l
               WHERE l.email = #{email} 
                 AND l.board = 'travel'
               )
            """)
      public abstract List<UserLikeVO> selectLikeTravel(String email);
      

      
      
      @Select("""
            select t.* FROM tbl_member m , tbl_travel t
            WHERE m.nickname = t.writer AND m.email = #{email}
            """)
      public abstract List<TravelBoardVO> getMyTravelList(String email);
      
      @Select("Select * FROM tbl_travel where seq = #{seq}")
      public abstract TravelBoardVO getMyTravelBoard(Integer seq);

      
}   // end interface