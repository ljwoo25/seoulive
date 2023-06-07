package org.zerock.seoulive.board.free.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.seoulive.board.free.domain.FreeCommentDTO;
import org.zerock.seoulive.board.free.domain.FreeDTO;

import java.util.List;

@Mapper
public interface FreeCommentDAO {
    List<FreeCommentDTO> freeCommentList(int seq);
    public void freeCommentInsert(FreeCommentDTO dto);
    public void freeCommentDelete(int seq);
    public void freeCommentPostDelete(int seq);
    public void freeCommentUpdate(FreeCommentDTO dto);

}
