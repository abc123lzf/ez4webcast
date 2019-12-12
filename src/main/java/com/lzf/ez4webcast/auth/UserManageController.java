package com.lzf.ez4webcast.auth;

import com.lzf.ez4webcast.auth.service.UserManageService;
import com.lzf.ez4webcast.common.ResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.lzf.ez4webcast.common.ResponseMessage.message;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 13:35
 */
@RestController
@RequestMapping("/api/user/manage")
public class UserManageController {

    @Autowired
    private UserManageService manageService;

    @RequestMapping("headimage")
    public ResponseMessage setHeadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int imgId;
        try {
            imgId = Integer.parseInt(request.getParameter("image"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = manageService.updateHeadImage(imgId);
        if(resp.success()) {
            return message(0, "SUCCESS");
        }

        return message(resp.code(), "FAILURE");
    }

}
