package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.ServiceResponse;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:43
 */
public interface BasicUserService {

    /**
     * 登录
     * @param username 电子邮箱/UID
     * @param password 密码
     * @return 登录情况 0:成功 1:
     */
    ServiceResponse<User> login(String username, String password);

    /**
     * 注册
     * @param email 电子邮箱
     * @param nickName 昵称
     * @param password 密码
     * @return 注册情况
     */
    ServiceResponse<Void> register(String email, String nickName, String password);
}
