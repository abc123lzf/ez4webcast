package com.lzf.ez4webcast.auth.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 21:43
 * 鉴定用户是否有访问对应资源的权限
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if(CollectionUtils.isEmpty(attributes)) {
            return;
        }

        for (ConfigAttribute ca : attributes) {
            String role = ca.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if(role.trim().equals(ga.getAuthority().trim())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
