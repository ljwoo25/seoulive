package org.zerock.seoulive.board.free.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.zerock.seoulive.board.free.domain.FreeCommentDTO;
import org.zerock.seoulive.board.free.persistence.FreeCommentDAO;
import org.zerock.seoulive.board.free.service.FreeCommentService;

import java.util.List;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {

    FreeCommentDAO freeCommentDAO;
    @Autowired
    public FreeCommentServiceImpl(FreeCommentDAO freeCommentDAO) {
        this.freeCommentDAO = freeCommentDAO;
    }

    @Override
    public List<FreeCommentDTO> freeCommentList(int seq) {
        List<FreeCommentDTO> freeCommentDTO = freeCommentDAO.freeCommentList(seq);
        return freeCommentDTO;
    }

    @Override
    public void freeCommentInsert(FreeCommentDTO dto) {
        freeCommentDAO.freeCommentInsert(dto);
    }

    @Override
    public void freeCommentDelete(int seq) {
        freeCommentDAO.freeCommentDelete(seq);
    }
    @Override
    public void freeCommentPostDelete(int seq) {
        freeCommentDAO.freeCommentPostDelete(seq);
    }

    @Override
    public void freeCommentModify(FreeCommentDTO dto) {
        freeCommentDAO.freeCommentUpdate(dto);
    }
}
