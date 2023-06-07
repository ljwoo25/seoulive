package org.zerock.seoulive.board.cs.faq.service;

import org.zerock.seoulive.board.cs.faq.domain.FaqCriteria;
import org.zerock.seoulive.board.cs.faq.domain.FaqDTO;
import org.zerock.seoulive.board.cs.faq.domain.FaqVO;
import org.zerock.seoulive.exception.ServiceException;

import java.util.List;

public interface FaqService {


    // 목록 반환
    public abstract List<FaqVO> getList(FaqCriteria cri) throws ServiceException;

    // 게시물 등록
    public abstract Boolean write(FaqDTO dto) throws ServiceException;

    // 게시물 수정
    public abstract FaqVO get(Integer seq) throws ServiceException;

    // 게시물 업데이트
    public abstract Boolean modify(FaqDTO dto) throws ServiceException;

    // 게시물 삭제
    public abstract Boolean remove(Integer seq) throws ServiceException;

    // 총 개시물 갯수 반환
    public abstract Integer getTotal(FaqCriteria cri) throws ServiceException;

    public abstract List<FaqVO> getMainList(int count) throws ServiceException;


} // end interface
