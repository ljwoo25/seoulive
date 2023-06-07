package org.zerock.seoulive.board.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.seoulive.board.course.domain.CourseCommDTO;
import org.zerock.seoulive.board.course.domain.CourseCommVO;

import java.util.List;

@Mapper
public interface CommentMapper {

    public Integer commRegit(CourseCommDTO dto);
    public List<CourseCommVO> commList(Integer seq); //목록
    public Integer commTotal(Integer seq); //개수

    public Integer commDelete(Integer seq);

    public Integer commUpdate(CourseCommDTO dto);

    public CourseCommDTO commUpdateOne(Integer seq); //댓글 한 개 정보 수정


}
