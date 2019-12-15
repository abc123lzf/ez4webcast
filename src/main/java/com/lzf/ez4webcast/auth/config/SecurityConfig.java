package com.lzf.ez4webcast.auth.config;

import com.alibaba.fastjson.JSON;
import com.lzf.ez4webcast.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 21:56
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SUCCESS;
    private static final String FAILURE;
    static {
        ResponseMessage msg = ResponseMessage.message(0, "SUCCESS");
        SUCCESS = JSON.toJSONString(msg);
        msg = ResponseMessage.message(1, "FAILURE");
        FAILURE = JSON.toJSONString(msg);
    }

    @Autowired
    private UserDetailsService userAuthService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/*", "/api/image/load", "/api/damanku/ws/**", "/api/room/rmtp/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.setHeader("Authorization", req.getSession().getId());
                    resp.getWriter().write(SUCCESS);
                })
                .failureHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(FAILURE);
                })
                .and()
                .logout()
                .logoutUrl("/api/user/logout")
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(SUCCESS);
                })
                .and()
                .httpBasic()
                .and().cors().and().csrf().disable();
    }

}
