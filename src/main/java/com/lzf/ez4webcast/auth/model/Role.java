package com.lzf.ez4webcast.auth.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 20:18
 * 角色
 */
@Data
public class Role implements GrantedAuthority {

    public static final RowMapper<Role> ROW_MAPPER = (rs, rowNum) -> {
        Role role = new Role();
        role.setId(rs.getInt("role_id"));
        role.setName(rs.getString("role_name"));
        return role;
    };

    private Integer id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
