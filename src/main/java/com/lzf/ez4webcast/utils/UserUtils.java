package com.lzf.ez4webcast.utils;

import com.lzf.ez4webcast.auth.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 0:00
 */
public class UserUtils {

    @SuppressWarnings("unchecked")
    public static <T> T contextPrincipal() {
        return (T)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private UserUtils() { }

}
