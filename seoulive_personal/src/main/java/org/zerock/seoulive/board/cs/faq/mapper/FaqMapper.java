package org.zerock.seoulive.board.cs.faq.mapper;

import org.zerock.seoulive.board.cs.faq.domain.FaqCriteria;
import org.zerock.seoulive.board.cs.faq.domain.FaqDTO;
import org.zerock.seoulive.board.cs.faq.domain.FaqVO;

import java.util.List;

public interface FaqMapper {

    // 1. 목록 조회
    public abstract List<FaqVO> selectList(FaqCriteria cri);

    // 2. 게시물 등록
    public abstract Integer insert(FaqDTO dto);

    // 3. 특정 게시물 삭제
    public abstract Integer delete(Integer seq);

    // 특정 게시물 상세조회
    public abstract FaqVO select(Integer seq);

    // 4. 특정 게시물 업데이트
    public abstract Integer update(FaqDTO dto);

    // 5. 총 게시물 갯수
    public abstract Integer getTotalAmount(FaqCriteria cri);

    // 6. 최신글 게시물 갯수
    public abstract List<FaqVO> getRecentList(int count);
}
