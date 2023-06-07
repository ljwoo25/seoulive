package org.zerock.seoulive.board.review.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.seoulive.board.review.domain.*;
import org.zerock.seoulive.board.review.mapper.ReviewBoardMapper;
import org.zerock.seoulive.exception.ServiceException;

import java.util.List;
import java.util.Objects;

@Log4j2
@NoArgsConstructor

@Service("BoardService")
public class ReviewBoardServiceImpl
        implements
        ReviewBoardService,
        InitializingBean,
        DisposableBean {

    @Setter(onMethod_ = {@Autowired})
    private ReviewBoardMapper dao;    // 영속성 계층의 DAO빈 주입받음

    // 1. 게시판 목록 얻어 반환해주는 기능 수행
    @Override
    public List<ReviewBoardVO> getList(Criteria cri) throws ServiceException {
        log.trace("\n\t getList() invoked.");

        try {
            return this.dao.selectList(cri);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }    //	getList

    @Override
    public Integer getTotal(Criteria cri) throws ServiceException {
        log.trace("getTotal() invoked");


        return this.dao.getTotalAmount(cri);
    } // getTotal



    // 2. 새로운 게시물 등록 기능 수행(CREATE)
    @Override
    public Boolean register(ReviewBoardDTO dto) throws ServiceException {
        log.trace("\n\t register({}) invoked.", dto);

        try {
            return (this.dao.insert(dto) == 1);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }    //	register
    @Override
    public void fileSave(MultipartFile file)throws ServiceException {
        log.trace("\n\t fileSave({}) invoked.", file);

        try {
//            byte[] arr = file.getBytes();
//            String path = "C:/Users/82104/Desktop/seoulliveproject/codeNineSeoulive/src/main/webapp/resources/imgUpload/"+file.getName();
//            file.transferTo(new File(path));

            ReviewFileDTO fileDto = new ReviewFileDTO();
            fileDto.setFile_name(file.getOriginalFilename());
            fileDto.setExtension(file.getContentType());
            fileDto.setFile_size(file.getSize());

            this.dao.fileSave(fileDto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }


    @Override
    public ReviewBoardVO get(Integer seq) throws ServiceException {
        log.trace("\n\t BoardVO({}) invoked.", seq);
        ReviewBoardVO boardVO = dao.select(seq);
        return boardVO;
    }    //	get


    // 4. 특정 게시물 업데이트(update)
    @Override
    public Boolean modify(ReviewBoardDTO dto) throws ServiceException {
        log.trace("\n\t modify({}) invoked.", dto);

        try {
            return (this.dao.update(dto) == 1);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }    //	modify

    // 5. 특정 게시물 삭제(DELETE)
    @Override
    public Boolean remove(Integer seq) throws ServiceException {
        log.trace("\n\t remove({}) invoked.", seq);

        try {
            return (this.dao.delete(seq) == 1);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }    //	remove
    @Override
    public void commentWrite(ReviewCommentDTO dto)throws ServiceException{
        try {
            this.dao.commentWrite(dto);


        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public  List<ReviewCommentDTO> commentList(Integer seq)throws ServiceException{
        try {

            return this.dao.commentList(seq);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void  likeList(ReviewLikeDTO dto)throws ServiceException{
        try {

            this.dao.likeList(dto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

//	===================InitializingBean,DisposableBean ==========//


    @Override
    public void afterPropertiesSet() throws Exception { // 초기화(=전처리)
        log.trace("\n\t afterPropertiesSet() invoked.");

//		assert this.dao != null;
        Objects.requireNonNull(this.dao);
        log.info("\n\t this.dao : {} ", dao);
    }    // afterPropertiesSet

    @Override
    public void destroy() throws Exception { // 자원해제(=후처리)
        log.trace("\n\t destroy() invoked.");

    }    // destroy










}
