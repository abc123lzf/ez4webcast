package com.lzf.ez4webcast.utils;

import com.lzf.ez4webcast.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 0:00
 */
public class UserUtils {

    public static User contextPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            return null;
        }
        return auth.getPrincipal() instanceof User ? (User) auth.getPrincipal() : null;
    }

    public static void setContextPrincipal(User userObject) {
        Authentication authentication = new PreAuthenticatedAuthenticationToken(userObject, userObject.getPassword(), userObject.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserUtils() { }

}
