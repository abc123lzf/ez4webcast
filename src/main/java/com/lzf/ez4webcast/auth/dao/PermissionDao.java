package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.Permission;
import com.lzf.ez4webcast.auth.model.Role;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 21:18
 */
public interface PermissionDao {

    List<Permission> fromRoleId(int roleId);

}
