package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.RoleDao;
import com.lzf.ez4webcast.auth.dao.UserDao;
import com.lzf.ez4webcast.auth.model.Role;
import com.lzf.ez4webcast.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 20:45
 */
@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.fromEmail(username);
        if(user != null) {
            List<Role> roles = roleDao.byUserId(user.getUid());
            user.setRoles(roles);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }
}
