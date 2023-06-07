package org.zerock.seoulive.board.travel.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.board.travel.domain.TravelBoardLikeDTO;
import org.zerock.seoulive.board.travel.mapper.Mapper;


@NoArgsConstructor
@Log4j2


@Service("TravelBoardLikeService")
public class TravelBoardLikeServiceImpl implements TravelBoardLikeService {

    @Setter(onMethod_ = {@Autowired})
    private Mapper dao;

    @Override
    public void boardLike(TravelBoardLikeDTO dto) {
        this.dao.boardLike(dto);

    }

}
