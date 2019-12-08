package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.UserDao;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lzf.ez4webcast.utils.StringTypeUtils.isEmail;
import static com.lzf.ez4webcast.utils.StringTypeUtils.isNumber;
import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:52
 * 登录注册服务
 */
@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ServiceResponse<User> login(String username, String password) {
        User user;
        if(isNumber(username)) {
            user = userDao.fromUID(Integer.parseInt(username));
        } else if(isEmail(username)) {
            user = userDao.fromEmail(username);
        } else {
            return response(2);
        }

        if(user == null) {
            return response(1);
        }

        String cur = MD5Utils.encode(password);
        if(!cur.equals(user.getPassword())) {
            return response(1);
        }

        return response(0, user);
    }

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
            User user = User.builder().nickName(nickName).password(MD5Utils.encode(password)).email(email).build();
            userDao.add(user);
        } catch (RuntimeException e) {
            return response(1);
        }

        return response(0);
    }
}
