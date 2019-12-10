package com.lzf.ez4webcast.auth.model;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:40
 */
@Data
@Builder
public class User implements UserDetails, JSONAware {

    public static final RowMapper<User> ROW_MAPPER = (rs, num) -> {
        User user = new User();
        user.setUid(rs.getInt("uid"));
        user.setNickName(rs.getString("nickname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    private Integer uid;

    private String nickName;

    private String password;

    private String email;

    private Integer headImage;

    private List<Role> roles = Collections.emptyList();

    @Tolerate
    public User() { }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject(8);
        obj.put("uid", uid);
        obj.put("nickname", nickName);
        obj.put("email", email);
        obj.put("headImage", headImage);

        return obj.toJSONString();
    }
}
