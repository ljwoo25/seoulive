package org.zerock.seoulive.board.review.service;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.seoulive.board.review.domain.*;
import org.zerock.seoulive.exception.ServiceException;

import java.util.List;

public interface ReviewBoardService {

    // 1. 게시판 목록 얻어 반환해주는 기능 수행
    public abstract List<ReviewBoardVO> getList(Criteria cri) throws ServiceException;

    // 2. 새로운 게시물 등록 기능 수행(CREATE)
    public abstract Boolean register(ReviewBoardDTO dto)throws ServiceException;

    public abstract void fileSave(MultipartFile file)throws ServiceException;

    // 3. 특정 게시물 상세조회(read)
    public abstract ReviewBoardVO get(Integer seq) throws ServiceException ;

    // 4. 특정 게시물 업데이트(update)
    public abstract Boolean modify(ReviewBoardDTO dto) throws ServiceException;

    // 5. 특정 게시물 삭제(DELETE)
    public abstract Boolean remove(Integer seq)throws ServiceException;

    // 6. 총 게시물 개수 반환
    public abstract Integer getTotal(Criteria cri) throws ServiceException;

    public abstract void commentWrite(ReviewCommentDTO dto)throws ServiceException;

    public abstract  List<ReviewCommentDTO> commentList(Integer seq)throws ServiceException;

    public abstract void  likeList(ReviewLikeDTO dto)throws ServiceException;




}
