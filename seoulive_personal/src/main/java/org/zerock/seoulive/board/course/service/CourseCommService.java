package org.zerock.seoulive.board.course.service;

import org.zerock.seoulive.board.course.domain.CourseCommDTO;
import org.zerock.seoulive.board.course.domain.CourseCommVO;

import java.util.List;


public interface CourseCommService {
    public List<CourseCommVO> list(Integer seq) throws Exception;

    public void write(CourseCommDTO dto) throws Exception; //등록

    public Integer modify(CourseCommDTO dto) throws Exception; //수정

    public Integer delete(Integer seq) throws Exception; //삭제

//    List<commVO> getComList(String writer); 관리자용 회원 댓글 불러오기
}
