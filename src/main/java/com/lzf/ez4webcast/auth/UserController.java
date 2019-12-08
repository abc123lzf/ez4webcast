package com.lzf.ez4webcast.auth;


import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.auth.service.BasicUserService;
import com.lzf.ez4webcast.common.ResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.lzf.ez4webcast.common.ResponseMessage.message;

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
    public ResponseMessage login(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        ServiceResponse<User> resp = basicUserService.login(user, pass);
        if(resp.success()) {
            return message(0, "SUCCESS");
        }

        return message(resp.code(), "FAILURE");
    }


    public ResponseMessage register(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

        return null;
    }

}