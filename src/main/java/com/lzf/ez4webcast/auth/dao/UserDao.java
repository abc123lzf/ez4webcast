package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.User;

import java.util.Collection;
import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:56
 */
public interface UserDao {

    User fromUID(int uid);

    List<User> fromUIDList(Collection<Integer> ids);

    User fromEmail(String email);

    void add(User user);

    boolean updateHeadImage(int uid, int imageId);

    boolean updatePassword(int uid, String newPass);

    boolean updateNickname(int uid, String newName);
}
