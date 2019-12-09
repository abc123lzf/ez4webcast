package com.lzf.ez4webcast.auth.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 20:38
 * 访问权限
 */
@Data
public class Permission {

    public static final RowMapper<Permission> ROW_MAPPER = (rs, rowNum) -> {
        Permission p = new Permission();
        p.setId(rs.getInt("permission_id"));
        p.setUrl(rs.getString("permission_url"));
        p.setName(rs.getString("permission_name"));
        p.setDescription(rs.getString("permission_desc"));
        return p;
    };

    private Integer id;

    private String url;

    private String name;

    private String description;

}
