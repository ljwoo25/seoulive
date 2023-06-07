package org.zerock.seoulive.member.join.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.seoulive.member.join.domain.UserDTO;
import org.zerock.seoulive.member.join.domain.UserVO;
import org.zerock.seoulive.exception.ServiceException;
import org.zerock.seoulive.member.join.mapper.UserMapper;


@Log4j2
@NoArgsConstructor

@Service("userService")
public class UserServiceImpl implements UserService {

    @Setter(onMethod_ = {@Autowired})
    private UserMapper dao;
    @Setter(onMethod_ = @Autowired)
    private SqlSessionTemplate userSqlSession;

    @Override
    public Boolean register(UserDTO dto) throws ServiceException {
        log.trace("register({}) invoked.", dto);

        try {
            return (this.dao.insert(dto) == 1);
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // register

    @Override
    public UserVO authenticate(UserDTO dto) throws ServiceException {
        log.trace("authenticate({}) invoked.", dto);

        try {
            return this.dao.selectUser(dto);
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // authenticate

    @Override
    public int emailCheck(String email) throws ServiceException {
        log.trace("emailCheck({}) invoked.", email);

        try {
            this.dao = userSqlSession.getMapper(UserMapper.class);

            return dao.checkOverEmail(email);
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // emailCheck

    @Override
    public int nicknameCheck(String nickname) throws ServiceException {
        log.trace("nicknameCheck({}) invoked.", nickname);

        try {
            this.dao = userSqlSession.getMapper(UserMapper.class);

            int result = dao.checkOverNickname(nickname);
            log.info("\t+result:{}",result);
//            return dao.checkOverEmail(nickname);
            return result;
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // nicknameCheck


} // end class
