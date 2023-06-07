package org.zerock.seoulive.board.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.seoulive.board.course.domain.CourseCommVO;
import org.zerock.seoulive.board.course.domain.CourseDTO;
import org.zerock.seoulive.board.course.domain.CourseLikeDTO;
import org.zerock.seoulive.board.course.domain.CoursePageTO;
import org.zerock.seoulive.board.course.domain.CourseTravelVO;
import org.zerock.seoulive.board.course.domain.CourseVO;
import org.zerock.seoulive.board.course.domain.CourseWriteDTO;
import org.zerock.seoulive.board.course.domain.CourseWriteVO;
import org.zerock.seoulive.board.course.domain.SeqDTO;
import org.zerock.seoulive.board.travel.domain.TravelDTO;

public interface CourseMapper {
	

	// 3. 게시물 당 가지고 있는 여행지 목록
	@Select("""
			SELECT ct.*, tt.CATEGORY, tt.TITLE, tt.CONTENT, tt.ADDRESS
			FROM TBL_COURSE_TRAVEL ct JOIN TBL_TRAVEL tt 
				ON ct.TRAVEL_SEQ = tt.seq
			WHERE BOARD_SEQ = ${seq}
			ORDER BY travel_id
			""")
	public abstract List<CourseTravelVO> selectTravelList(CourseDTO dto);
	@Select("""
			SELECT ct.*, tt.CATEGORY, tt.TITLE, tt.CONTENT, tt.ADDRESS
			FROM TBL_COURSE_TRAVEL ct JOIN TBL_TRAVEL tt 
				ON ct.TRAVEL_SEQ = tt.seq
			WHERE BOARD_SEQ = ${seq}
			""")
	public abstract List<CourseTravelVO> selectTravelList2(Integer seq);
	
	// 4. 검색 결과 리스트
	@Select("""
			SELECT *
			FROM tbl_course 
			WHERE ${searchType} LIKE '%' || #{keyword} || '%'
			ORDER BY seq DESC
			OFFSET ( #{currPage} - 1) * #{amount} ROW
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<CourseDTO> searchCourse(CoursePageTO page);
	
	// 5. 검색 후 리스트 개수
	@Select("""
			SELECT count(seq)
			FROM tbl_course 
			WHERE ${searchType} LIKE '%' || #{keyword} || '%'
			""")
	public abstract int getTotalSearch(@Param("searchType") String searchType, @Param("keyword")String keyword);
	
	// 6. 새로운 게시물 등록
	@Insert("""
			INSERT INTO tbl_course (WRITER, TITLE, REVIEW, DURATION_START, DURATION_END)
			VALUES (#{WRITER}, #{TITLE}, #{REVIEW}, #{DURATION_START}, #{DURATION_END})
			""")
	public abstract void insertCourse(CourseWriteDTO dto);
	@Select("""
			SELECT max(seq)+1 as seq
			FROM tbl_course
			""")
	public abstract SeqDTO getCourseSeq();
	@Insert("""
			INSERT INTO tbl_course_travel (BOARD_SEQ, TRAVEL_SEQ, USER_REVIEW, TRAVEL_ID)
			VALUES (#{BOARD_SEQ}, #{TRAVEL_SEQ}, #{USER_REVIEW}, #{TRAVEL_ID})
			""")
	public abstract void insertCourseTravel(CourseWriteVO vo);
	
	// 7. 게시물 등록 중 여행지 검색
	@Select("""
			SELECT SEQ, CATEGORY, TITLE, ADDRESS
			FROM TBL_TRAVEL
			WHERE TITLE LIKE '%' || #{keyword} || '%'
			""")
	public abstract List<TravelDTO> getTravelData(String keyword);
	
	// 8. 게시물 찜
	@Insert("""
			INSERT INTO tbl_like (email, board_seq, board)
			VALUES (#{user}, #{board_seq}, #{board})
			""")
	public abstract void courseLike(CourseLikeDTO dto);
	@Delete("""
			DELETE FROM tbl_like 
			WHERE board = #{board}
			  AND board_seq = #{board_seq}
			  AND email = #{user}
			""")
	public abstract void courseUnlike(CourseLikeDTO dto);
	@Select("""
			SELECT board_seq
			FROM tbl_like
			WHERE email = #{user}
			  AND board = 'course'
			""")
	public abstract List<CourseLikeDTO> courseLikeList(CourseLikeDTO dto);
		
	// 9. 특정 게시물 상세조회
	public CourseVO read(Integer seq);
	@Insert("""
			UPDATE TBL_COURSE
			SET TOTAL = TOTAL + 1
			WHERE seq = #{seq}
			""")
	public void total(Integer seq);
	
	// 10. 댓글 가져오기
	@Select("""
			SELECT writer, content, write_date
	        FROM tbl_comment
	        WHERE post_seq = #{seq}
	          AND board_name = 'course'
			""")
	public abstract List<CourseCommVO> commList(Integer seq);
	
	@Insert("""
			INSERT INTO tbl_comment (CONTENT, BOARD_NAME, POST_SEQ, WRITER)
			VALUES (#{content}, 'course', #{seq}, 'NICKNAME')
			""")
	public abstract void commRegister(String content, Integer seq);
	
	
	
//	// n. 특정 게시물 삭제
//	public Integer delete(Integer seq);
//	
//	// n. 특정 게시물 업데이트(갱신)
//	public Integer update(courseDTO course);

	
} // end interface
