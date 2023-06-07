package org.zerock.seoulive.board.free.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zerock.seoulive.board.free.domain.Criteria;
import org.zerock.seoulive.board.free.domain.FreeDTO;

import java.util.List;

@Mapper
public interface FreeDAO {
    public List<FreeDTO> getList();
    public List<FreeDTO> getListWithPaging(Criteria cri);
    public void freeInsert(FreeDTO dto);
    public FreeDTO read(int seq);
    public void delete(int seq);
    public void update(FreeDTO dto);
    public int total_count(int seq);
    public int getTotalCount(Criteria cri);


//    @Select("SELECT count(seq) FROM tbl_free WHERE seq>0") //pk값으로 받아야해
//    public abstract int getTotalAmount();

}



// 첫번째
//    @Select("""
//	         SELECT
//	         /*+ index_desc(tbl_free) */
//	         *
//	         FROM tbl_free
//	         OFFSET (#{currPage}-1)* #{amount} ROWS
//	         FETCH NEXT #{amount} ROWS ONLY
//
//	         """)
//    public List<FreeDTO> getList(Criteria cri);

//두번째
//
//    @Select("""
//        SELECT *
//                FROM (
//                SELECT /*+ index_desc(tbl_free) */ t.*, ROWNUM AS rn
//                FROM (
//                SELECT *
//                FROM tbl_free
//                ) t
//                WHERE ROWNUM <= (#{currPage} * #{amount})
//                )
//                WHERE rn > ((#{currPage}-1) * #{amount})
//
//	         """)
//    public List<FreeDTO> getList(Criteria cri);



//@Select("""
//        SELECT *
//                FROM (
//                SELECT /*+ index_desc(tbl_free) */ t.*, ROWNUM AS rn
//                FROM (
//                SELECT *
//                FROM tbl_free
//                ) t
//                WHERE ROWNUM <= (#{currPage} * #{amount})
//                )
//                WHERE rn > ((#{currPage}-1) * #{amount})
//
//	         """)

