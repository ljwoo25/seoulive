package org.zerock.seoulive.board.travel.service;

import org.zerock.seoulive.board.travel.domain.Criteria;
import org.zerock.seoulive.board.travel.domain.DTO;
import org.zerock.seoulive.board.travel.domain.TravelBoardLikeDTO;
import org.zerock.seoulive.board.travel.domain.VO;
import org.zerock.seoulive.board.travel.exception.ServiceException;

import java.util.List;

public interface Service {

    public abstract List<VO> getList(Criteria cri) throws ServiceException;

    public abstract Boolean register(DTO dto) throws ServiceException;
    public abstract VO get(Integer seq) throws ServiceException;
    public abstract void total(Integer seq) throws ServiceException;
    public abstract Boolean modify(DTO dto) throws ServiceException;
    public abstract Boolean remove(Integer seq) throws ServiceException;
    public abstract Integer getTotal(Criteria cri) throws ServiceException;
    void boardLike(TravelBoardLikeDTO likeDTO);

} // end interface
