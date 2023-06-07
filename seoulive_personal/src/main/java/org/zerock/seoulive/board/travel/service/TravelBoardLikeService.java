package org.zerock.seoulive.board.travel.service;

import org.apache.ibatis.annotations.Insert;
import org.zerock.seoulive.board.travel.domain.TravelBoardLikeDTO;

public interface TravelBoardLikeService {

    public abstract void boardLike(TravelBoardLikeDTO dto);

}
