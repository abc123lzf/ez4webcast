package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.Role;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 20:47
 */
@Repository
class RoleDaoImpl extends AbstractJdbcDao implements RoleDao {

    @Override
    public List<Role> byUserId(int uid) {
        return jdbcTemplate.query("select role_id, role_name from role_inf where role_id in " +
                "(select role_id from user_role_inf where user_id = ?)", parameters(uid), Role.ROW_MAPPER);
    }

    @Override
    public List<Role> all() {
        return jdbcTemplate.query("select role_id, role_name from role_inf", Role.ROW_MAPPER);
    }
}
