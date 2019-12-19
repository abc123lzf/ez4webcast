package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.RoomManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 21:01
 */
@Controller
@Log4j2
@RequestMapping("/api/room/rmtp")
public class NginxRmtpController {

    @Autowired
    private RoomManageService manageService;

    @RequestMapping("publish")
    public void publishCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String room = request.getParameter("room");
        String key = request.getParameter("key");
        key = new String(Base64Utils.decodeFromString(key));
        int rid;
        try {
            rid = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }

        ServiceResponse<String> resp = manageService.rmtpAuthKey(rid);
        if(resp.success()) {
            if(resp.data().equals(key)) {
                if(!manageService.updateLastLiveTime(rid).success()) {
                    log.warn("Update room {} last live time failure", room);
                }
                response.setStatus(200);
                return;
            }

            response.sendError(403);
            return;
        }

        response.sendError(403);
    }

}
