package com.lzf.ez4webcast.auth.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:40
 */
@Data
@Builder
public class User {

    public static final RowMapper<User> ROW_MAPPER = (rs, num) -> {
        User user = new User();
        user.setUid(rs.getInt("uid"));
        user.setNickName(rs.getString("nickname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    private int uid;

    private String nickName;

    private String password;

    private String email;

    public User() { }
}
