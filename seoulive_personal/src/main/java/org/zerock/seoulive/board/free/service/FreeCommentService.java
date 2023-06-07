package org.zerock.seoulive.board.free.service;

import org.zerock.seoulive.board.free.domain.FreeCommentDTO;

import java.util.List;

public interface FreeCommentService {
    List<FreeCommentDTO> freeCommentList(int seq);
    public void freeCommentInsert(FreeCommentDTO dto);
    public void freeCommentDelete(int seq);
    public void freeCommentPostDelete(int seq);
    public void freeCommentModify(FreeCommentDTO dto);


}
