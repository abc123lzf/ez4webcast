package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.vo.UserVO;
import com.lzf.ez4webcast.common.ServiceResponse;

import java.util.Collection;
import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:43
 */
public interface BasicUserService {

    /**
     * 登录
     * @param uid 用户ID
     * @return 登录情况 0:成功 1:
     */
    ServiceResponse<UserVO> findUserByUID(int uid);

    /**
     * 登录
     * @param uidList 用户ID
     * @return 登录情况 0:成功 1:
     */
    ServiceResponse<List<UserVO>> findUserByUID(Collection<Integer> uidList);

    /**
     * 注册
     * @param email 电子邮箱
     * @param nickName 昵称
     * @param password 密码
     * @return 注册情况
     */
    ServiceResponse<Void> register(String email, String nickName, String password);
}
