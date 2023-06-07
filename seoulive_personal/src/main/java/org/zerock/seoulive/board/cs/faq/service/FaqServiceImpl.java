package org.zerock.seoulive.board.cs.faq.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.board.cs.faq.domain.FaqCriteria;
import org.zerock.seoulive.board.cs.faq.domain.FaqDTO;
import org.zerock.seoulive.board.cs.faq.domain.FaqVO;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.board.cs.faq.mapper.FaqMapper;

import java.util.List;
import java.util.Objects;



@Log4j2
@NoArgsConstructor

@Service("faqService")
public class FaqServiceImpl implements FaqService, InitializingBean, DisposableBean {

    @Setter(onMethod_ = {@Autowired})
    private FaqMapper dao;

    private static final int PAGE_SIZE = 10;

    // 목록 얻기
    @Override
    public List<FaqVO> getList(FaqCriteria cri) throws ServiceException {
        log.trace("getList({}) invoked.", cri);

        try{
            return this.dao.selectList(cri);
        } catch(Exception e){
            throw new ServiceException(e);
        } // try-catch
    } // getList


    // 게시물 등록
    @Override
    public Boolean write(FaqDTO dto) throws ServiceException {
        log.trace("write({}) invoked.", dto);

        try{
            return(this.dao.insert(dto) == 1);
        } catch(Exception e){
            throw new ServiceException(e);
        } // try-catch
    } // register

    // 게시물 수정 상세
    @Override
    public FaqVO get(Integer seq) throws ServiceException {
        log.trace("get({}) invoked.", seq);
        try {
            return this.dao.select(seq);
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    }

    // 게시물 수정
    @Override
    public Boolean modify(FaqDTO dto) throws ServiceException {
        log.trace("modify({}) invoked.", dto);

        try{
            return(this.dao.update(dto) == 1);
        } catch(Exception e){
            throw new ServiceException(e);
        } // try-catch
    } // modify


    // 게시물 삭제
    @Override
    public Boolean remove(Integer seq) throws ServiceException {
        log.trace("remove({}) invoked.", seq);

        try{
            return (this.dao.delete(seq) == 1);
        } catch(Exception e){
            throw new ServiceException(e);
        } // try-catch
    } // remove

    @Override
    public Integer getTotal(FaqCriteria cri) throws ServiceException {
        log.trace("getTotal() invoked.");

        return this.dao.getTotalAmount(cri);
    }

    // 메인 노출용
    public List<FaqVO> getMainList(int count) {
        return this.dao.getRecentList(count);
    }

    //	======== InitializingBean, DisposableBean ========= //


    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.dao);
        log.info("\t+ this.dao : {}", dao);
    } // afterPropertiesSet

    @Override
    public void destroy() throws Exception {
        log.trace("destroy() invoked.");
    } // destroy

}
