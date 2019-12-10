package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.PermissionDao;
import com.lzf.ez4webcast.auth.dao.RoleDao;
import com.lzf.ez4webcast.auth.model.Permission;
import com.lzf.ez4webcast.auth.model.Role;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 20:59
 * 请求和权限的对应关系
 */
@Service
class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource, InitializingBean {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    private final Map<String, Collection<ConfigAttribute>> map = new HashMap<>();

    private final Map<String, AntPathRequestMatcher> matchers = new LinkedHashMap<>();

    @Override
    public void afterPropertiesSet() {
        for (Role role : roleDao.all()) {
            for (Permission p : permissionDao.fromRoleId(role.getId())) {
                map.compute(p.getUrl(), (url, ca) -> {
                    if(ca == null) {
                        List<ConfigAttribute> list = new ArrayList<>();
                        list.add(new SecurityConfig(role.getName()));
                        return list;
                    } else {
                        ca.add(new SecurityConfig(role.getName()));
                        return ca;
                    }
                });

                matchers.put(p.getUrl(), new AntPathRequestMatcher(p.getUrl()));
            }
        }
    }

    /**
     * @param object FilterInvocation
     * @return 请求资源所需角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<String, AntPathRequestMatcher> entry : matchers.entrySet()) {
            String url = entry.getKey();
            AntPathRequestMatcher matcher = entry.getValue();
            if(matcher.matches(request)) {
                return map.get(url);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
