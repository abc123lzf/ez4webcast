package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.BasicRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 21:01
 */
@Controller
@RequestMapping("/api/room/rmtp")
public class RmtpController {

    @Autowired
    private BasicRoomService basicRoomService;

    @RequestMapping("auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String room = request.getParameter("room");
        String key = request.getParameter("key");

        int rid;
        try {
            rid = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }

        ServiceResponse<String> resp = basicRoomService.rmtpAuthKey(rid);
        if(resp.success()) {
            if(resp.data().equals(key)) {
                return;
            } else {
                response.sendError(403);
            }
        }

        response.sendError(403);
    }

}
