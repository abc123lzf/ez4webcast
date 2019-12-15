package com.lzf.ez4webcast.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.15 15:06
 */
@Configuration
public class SessionConfig extends HeaderHttpSessionIdResolver {

    public SessionConfig() {
        super("Platform-Token");
    }
}
