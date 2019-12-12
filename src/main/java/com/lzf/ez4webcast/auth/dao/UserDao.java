package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.User;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:56
 */
public interface UserDao {

    User fromUID(int uid);

    User fromEmail(String email);

    void add(User user);

    boolean updateHeadImage(int uid, int imageId);

}
