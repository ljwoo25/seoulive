package org.zerock.seoulive.board.travel.mapper;

import org.apache.ibatis.annotations.*;
import org.zerock.seoulive.board.travel.domain.Criteria;
import org.zerock.seoulive.board.travel.domain.DTO;
import org.zerock.seoulive.board.travel.domain.TravelBoardLikeDTO;
import org.zerock.seoulive.board.travel.domain.VO;

import java.util.List;

public interface Mapper {
    @Select("""
            SELECT *
            FROM tbl_travel
            WHERE title LIKE '%' || #{keyword} || '%'
            AND category LIKE '%' || #{searchType} || '%'
            ORDER BY seq desc
            OFFSET (#{currPage} - 1) * #{amount} ROWS
            FETCH NEXT #{amount} ROWS ONLY
            """)
    public abstract List<VO> selectList(Criteria cri);
    @Insert("""
            INSERT INTO TBL_TRAVEL (TITLE, CATEGORY, CONTENT, ADDRESS, START_TIME, END_TIME, START_DATE, END_DATE)
            VALUES (#{title}, #{category}, #{content}, #{address}, #{start_time}, #{end_time}, #{start_date}, #{end_date})
            """)
    public abstract Integer insert(DTO dto);
    @Select("""
            SELECT * FROM TBL_TRAVEL WHERE seq = #{seq}
            """)
    public abstract VO select(@Param("seq") Integer seq);
    @Delete("""
            DELETE FROM TBL_TRAVEL WHERE seq = #{seq}
            """)
    public abstract Integer delete(Integer seq);

    @Update("""
            UPDATE TBL_TRAVEL SET title = #{title}, content = #{content}, address = #{address}, modify_date = current_date,
                                          start_time = #{start_time}, end_time = #{end_time}, start_date = #{start_date}, end_date = #{end_date}
                              WHERE seq = #{seq}
            """)
    public abstract Integer update(DTO dto);

    @Update("""
            UPDATE TBL_TRAVEL set total = total + 1 WHERE seq = #{seq}
            """)
    public abstract Integer total(Integer seq);

    @Select("""
   			SELECT count(seq) FROM tbl_travel
			WHERE title LIKE '%' || #{keyword} || '%'
			AND category LIKE '%' || #{searchType} || '%'
			""")
    public abstract Integer getTotalAmount(Criteria cri);

    List<VO> selectListByDate(String selectedDate);

    @Insert("""
         INSERT INTO tbl_like (EMAIL, BOARD_SEQ, BOARD)
         VALUES ('seoulive!!', #{board_seq}, #{board})
         """)
    public abstract void boardLike(TravelBoardLikeDTO dto);



} // end interface
