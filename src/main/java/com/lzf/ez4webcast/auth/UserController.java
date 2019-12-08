package com.lzf.ez4webcast.auth;


import com.lzf.ez4webcast.auth.service.BasicUserService;
import com.lzf.ez4webcast.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:46
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BasicUserService basicUserService;


    @PostMapping("login")
    public ResponseMessage login(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        return null;
    }

}
