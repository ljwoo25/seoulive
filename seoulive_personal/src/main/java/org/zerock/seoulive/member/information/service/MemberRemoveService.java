package org.zerock.seoulive.member.information.service;

import org.zerock.seoulive.member.information.domain.tbl_memberVO;

public interface MemberRemoveService {

    public void withdrawal(tbl_memberVO vo) throws Exception;
}
