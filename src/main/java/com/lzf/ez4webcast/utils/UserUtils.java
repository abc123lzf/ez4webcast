package com.lzf.ez4webcast.utils;

import com.lzf.ez4webcast.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 0:00
 */
public class UserUtils {

    @SuppressWarnings("unchecked")
    public static <T> T contextPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            return null;
        }

        return (T)auth.getPrincipal();
    }

    private UserUtils() { }

}
