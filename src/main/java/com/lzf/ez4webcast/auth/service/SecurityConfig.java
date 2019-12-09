package com.lzf.ez4webcast.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 21:56
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthService userAuthService;

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
        http.authorizeRequests().antMatchers("/", "/user/**").permitAll()
            .anyRequest().authenticated().and();
    }
}
