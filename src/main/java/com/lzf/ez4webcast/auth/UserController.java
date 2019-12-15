package com.lzf.ez4webcast.auth;


import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.auth.service.BasicUserService;
import com.lzf.ez4webcast.auth.vo.UserVo;
import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.common.ResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lzf.ez4webcast.common.ComplexResponseMessage.message;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:46
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private BasicUserService basicUserService;

    @PostMapping("register")
    public ResponseMessage register(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String nick = request.getParameter("nickname");
        String pass = request.getParameter("password");

        ServiceResponse<Void> resp = basicUserService.register(email, nick, pass);
        if(resp.success()) {
            return message(0, "SUCCESS");
        }

        return message(resp.code(), "FAILURE");
    }

    @PostMapping("context")
    public ComplexResponseMessage<UserVo> contextUser() {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return ComplexResponseMessage.message(1, "Not login", null);
        }

        return ComplexResponseMessage.message(0, "SUCCESS", new UserVo(user));
    }
}
