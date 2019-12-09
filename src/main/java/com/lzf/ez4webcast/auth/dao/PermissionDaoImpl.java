package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.Permission;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 21:19
 */
@Repository
class PermissionDaoImpl extends AbstractJdbcDao implements PermissionDao {

    @Override
    public List<Permission> fromRoleId(int roleId) {
        return jdbcTemplate.query("select permission_id, permission_url, permission_name, permission_desc from permission_inf " +
                        "where permission_id in (select permission_id from role_permission_inf where role_id = ?)", parameters(roleId),
                Permission.ROW_MAPPER);
    }

}
