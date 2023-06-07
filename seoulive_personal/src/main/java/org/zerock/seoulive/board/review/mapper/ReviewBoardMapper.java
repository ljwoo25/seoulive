package org.zerock.seoulive.board.review.mapper;


import org.apache.ibatis.annotations.*;
import org.zerock.seoulive.board.review.domain.*;

import java.util.List;



@Mapper
public interface ReviewBoardMapper {   //CRUD 를 해볼까 ?

    public abstract List<ReviewBoardVO> getLists(Criteria cri);

    //Create를 해보자
//    @Select("""
//            SELECT /*+ index_desc(TBL_REVIEW) */ *
//            FROM TBL_REVIEW
//            OFFSET (#{currPage}-1)* #{amount} ROWS
//            FETCH NEXT #{amount} ROWS ONLY
//            """)
    @Select("""
			SELECT *
			FROM tbl_review
			WHERE ${searchType} LIKE '%' || #{keyword} || '%'
			ORDER BY seq DESC
			OFFSET ( #{currPage} - 1) * #{amount} ROW
			FETCH NEXT #{amount} ROWS ONLY
			""")
    public abstract  List<ReviewBoardVO> selectList(Criteria cri);

    //새로운개시물 등록
    @Insert("INSERT INTO TBL_REVIEW (seq,title,content,writer,place) VALUES (seq_tbl_review.nextval,#{title},#{content},#{writer},#{place}) ")
    public abstract Integer insert( ReviewBoardDTO dto);

    //특정개시물 상세조회
    @Select("SELECT * FROM TBL_REVIEW WHERE SEQ = #{seq} ORDER BY SEQ desc")
    public abstract ReviewBoardVO select(Integer seq);

    @Insert("INSERT INTO TBL_UPLOAD (file_name, extension, file_size) VALUES (#{file_name}, #{extension}, #{file_size})")
    public abstract Integer fileSave( ReviewFileDTO dto);

    //특정게시물 삭제
    @Delete("DELETE FROM TBL_REVIEW WHERE seq = #{seq}")
    public abstract Integer delete(Integer seq);

    // 5. 특정 게시물 업데이트(갱신)
    @Update("UPDATE TBL_REVIEW SET title = #{title},content = #{content} WHERE seq = #{seq}")
    public abstract Integer update(ReviewBoardDTO dto);

    //6.요청시점에 총 게시물 개수를 반환
    @Select("SELECT count(seq) FROM TBL_REVIEW WHERE title LIKE '%' || #{keyword} || '%'") //pk값으로 받아야해
    public abstract Integer getTotalAmount(Criteria cri);

    @Insert("""
            INSERT INTO TBL_COMMENT (writer, content,board_name, post_seq) 
            VALUES (#{writer}, #{content},#{board_name},#{post_seq}) 
            """)
    public abstract Integer commentWrite( ReviewCommentDTO dto );



    @Select("""
                SELECT * 
			    FROM TBL_COMMENT
			    WHERE POST_SEQ = ${seq}
			    AND BOARD_NAME = 'review'
            """)
    public abstract List<ReviewCommentDTO> commentList(Integer seq);


    @Insert("""
            INSERT INTO TBL_LIKE (EMAIL,BOARD_SEQ, BOARD) 
            VALUES ('jofjdj@naver.com',#{board_seq},#{board}) 
            """)
    public abstract void likeList (ReviewLikeDTO dto);



//    // 4. 검색 결과 리스트
//    @Select("""
//         SELECT *
//         FROM tbl_review
//         WHERE ${searchType} LIKE '%' || #{keyword} || '%'
//         OFFSET ( #{currPage} - 1) * #{amount} ROW
//         FETCH NEXT #{amount} ROWS ONLY
//         """)
//    public abstract List<BoardVO> search(Criteria cri);
//
//    // 5. 검색 후 리스트 개수
//    @Select("""
//         SELECT count(seq)
//         FROM tbl_review
//         WHERE ${searchType} LIKE '%' || #{keyword} || '%'
//         """)
//    public abstract int getTotalSearch(String searchType, String keyword);
//
//



}
