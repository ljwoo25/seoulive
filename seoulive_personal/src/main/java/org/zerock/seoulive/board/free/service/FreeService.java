package org.zerock.seoulive.board.free.service;

import org.zerock.seoulive.board.free.domain.Criteria;
import org.zerock.seoulive.board.free.domain.FreeDTO;

import java.util.List;

public interface FreeService {
    public void freeInsert(FreeDTO dto);
    public FreeDTO view(int seq);
    public void remove(int seq);
    public void modify(FreeDTO dto);
    public void total_count(int seq);

    public List<FreeDTO> getList(Criteria cri);
    public int getTotal(Criteria cri);



}
