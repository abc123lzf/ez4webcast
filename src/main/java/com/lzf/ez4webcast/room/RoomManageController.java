package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/14 0:42
 * 直播间管理控制器
 */
@RestController
@RequestMapping("/api/room/manage")
public class RoomManageController {



    @PostMapping("create")
    public ResponseMessage createRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        int image;
        try {
            image = Integer.parseInt(request.getParameter("image"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }




        return null;
    }

}
