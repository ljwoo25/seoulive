package org.zerock.seoulive.member.information.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.member.information.domain.tbl_memberVO;
import org.zerock.seoulive.member.information.mapper.MemberBoardMapper;

@Service
public class MemberRemoveServiceImpl implements MemberRemoveService {

    @Setter(onMethod_ = @Autowired)
    private MemberBoardMapper mapper;
    @Override
    public void withdrawal(tbl_memberVO vo) throws Exception {
        mapper.withdrawal(vo);
    }
}
