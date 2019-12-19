package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.common.ResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.RoomManageService;
import com.lzf.ez4webcast.room.vo.RoomBaseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lzf.ez4webcast.common.ComplexResponseMessage.message;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/14 0:42
 * 直播间管理控制器
 */
@RestController
@RequestMapping("/api/room/manage")
public class RoomManageController {

    @Autowired
    private RoomManageService manageService;

    @PostMapping("create")
    public ResponseMessage createRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");

        Integer image = null;
        try {
            String s = request.getParameter("image");
            if(s != null) {
                image = Integer.parseInt(request.getParameter("image"));
            }
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = manageService.createRoom(title, image);
        return resp.success() ?
                message(0, "SUCCESS") :
                message(resp.code(), "FAILURE");
    }


    @PostMapping("resetkey")
    public ComplexResponseMessage<String> updateAuthKey() {
        ServiceResponse<String> resp = manageService.updateAuthKey();
        return resp.success() ?
                message(0, "SUCCESS", resp.data()) :
                message(resp.code(), "FAILURE", null);
    }


    @GetMapping("baseinfo")
    public ComplexResponseMessage<RoomBaseInfoVo> baseInfo() {
        ServiceResponse<RoomBaseInfoVo> resp = manageService.roomBaseInfo();
        return resp.success() ?
                message(0, "SUCCESS", resp.data()) :
                message(resp.code(), "FAILURE", null);
    }


    @PostMapping("titleimage")
    public ResponseMessage changeTitleImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int image;
        try {
            image = Integer.parseInt(request.getParameter("image"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> ans = manageService.changeTitleImage(image);
        if(!ans.success()) {
            return message(ans.code(), "FAILURE");
        }

        return message(0, "SUCCESS");
    }

    @PostMapping("title")
    public ResponseMessage changeTitle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        if(title == null) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> ans = manageService.changeTitle(title);
        if(!ans.success()) {
            return message(ans.code(), "FAILURE");
        }

        return message(0, "SUCCESS");
    }

}
