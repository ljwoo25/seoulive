package org.zerock.seoulive.board.free.service.impl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.board.free.domain.Criteria;
import org.zerock.seoulive.board.free.domain.FreeDTO;
import org.zerock.seoulive.board.free.persistence.FreeDAO;
import org.zerock.seoulive.board.free.service.FreeService;

import java.util.List;

@Service
public class FreeServiceImpl implements FreeService {

    @Setter(onMethod_=@Autowired)
    FreeDAO freeDAO;

//    public FreeServiceImpl(FreeDAO freeDAO) {
//        this.freeDAO = freeDAO;
//    }

    @Override
    public List<FreeDTO> getList(Criteria cri) {

        return freeDAO.getListWithPaging(cri);
    }
    @Override
    public void freeInsert(FreeDTO dto) {
        freeDAO.freeInsert(dto);
    }

    @Override
    public FreeDTO view(int seq) {
        FreeDTO dto = freeDAO.read(seq);
        return dto;
    }

    @Override
    public void remove(int seq) {
        freeDAO.delete(seq);
    }

    @Override
    public void modify(FreeDTO dto) {
        freeDAO.update(dto);
    }

    @Override
    public void total_count(int seq) {
        freeDAO.total_count(seq);
    }

    @Override
    public int getTotal(Criteria cri) {
        return freeDAO.getTotalCount(cri);
    }
}
