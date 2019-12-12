package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.UserDao;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.auth.vo.UserVo;
import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.lzf.ez4webcast.utils.StringTypeUtils.isEmail;
import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:52
 * 登录注册服务
 */
@Service
class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ServiceResponse<Void> register(String email, String nickName, String password) {
        if(!isEmail(email)) {
            return response(2);
        }

        User u = userDao.fromEmail(email);
        if(u != null) {
            return response(1);
        }

        try {
            User user = User.builder()
                    .nickName(nickName)
                    .password(DigestUtils.md5DigestAsHex(password.getBytes()))
                    .email(email)
                    .build();
            userDao.add(user);
        } catch (RuntimeException e) {
            return response(1);
        }

        return response(0);
    }

    @Override
    public ServiceResponse<UserVo> findUserByUID(int uid) {
        User user = userDao.fromUID(uid);
        if(user == null) {
            return response(1);
        }

        return response(0, new UserVo(user));
    }
}
