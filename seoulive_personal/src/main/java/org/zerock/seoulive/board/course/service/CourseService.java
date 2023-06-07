package org.zerock.seoulive.board.course.service;

import java.util.List;

import org.zerock.seoulive.board.course.domain.CourseCommVO;
import org.zerock.seoulive.board.course.domain.CourseDTO;
import org.zerock.seoulive.board.course.domain.CourseLikeDTO;
import org.zerock.seoulive.board.course.domain.CoursePageTO;
import org.zerock.seoulive.board.course.domain.CourseTravelVO;
import org.zerock.seoulive.board.course.domain.CourseVO;
import org.zerock.seoulive.board.course.domain.CourseWriteDTO;
import org.zerock.seoulive.board.course.domain.CourseWriteVO;
import org.zerock.seoulive.board.course.exception.ServiceException;
import org.zerock.seoulive.board.travel.domain.TravelDTO;

public interface CourseService {
	
//	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
//	public abstract List<CourseDTO> getList(CoursePageTO page) throws ServiceException;
//	
//	// 2. 총 게시물 개수 반환
//	public abstract Integer getTotal() throws ServiceException;
	
	// 3. 코스게시물이 가지고 있는 여행지 목록 반환
	public abstract List<CourseTravelVO> getTravelList(CourseDTO dto) throws ServiceException;
	public abstract List<CourseTravelVO> getTravelList(Integer seq) throws ServiceException;
	
	// 4. 게시판 검색 목록
	public abstract List<CourseDTO> searchCourse(CoursePageTO page);
	
	// 5. 검색 목록 개수 반환
	public abstract Integer getTotalSearch(CoursePageTO page) throws ServiceException;
	
	// 6. 새로운 게시물 등록 기능 수행 (CREATE)
	public abstract void register(CourseWriteDTO dto) throws ServiceException;
	public abstract Integer getCourseSeq() throws ServiceException;
	public abstract void registerTravel(CourseWriteVO vo) throws ServiceException;
	
	// 7. Write에서 여행지 검색
	public abstract List<TravelDTO> getTravelData(String keyword) throws ServiceException;
	
	// 8. 찜 기능
	public abstract void courseLike(CourseLikeDTO dto) throws ServiceException;
	public abstract void courseUnlike(CourseLikeDTO dto) throws ServiceException;
	public abstract List<CourseLikeDTO> courseLikeList(CourseLikeDTO dto) throws ServiceException;
	
	// 9. 특정 게시물 상세조회 (READ)
	public abstract CourseVO get(Integer seq) throws ServiceException;
	public abstract void total(Integer seq) throws ServiceException;
	 
//	// n. 특정 게시물 업데이트 (UPDATE)
//		public abstract boolean modify(courseDTO course) throws ServiceException;

//	// n. 특정 게시물 삭제 (DELETE)
//	public abstract boolean remove(Integer seq) throws ServiceException;
	


} // end interface
