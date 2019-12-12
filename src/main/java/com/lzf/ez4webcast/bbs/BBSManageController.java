package com.lzf.ez4webcast.bbs;

import com.lzf.ez4webcast.bbs.service.BBSManageService;
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
 * @since 2019/12/12 9:55
 * 帖子管理
 */

@RestController
@RequestMapping("/api/bbs/manage")
public class BBSManageController {

    @Autowired
    private BBSManageService manageService;

    @RequestMapping("delete/post")
    public ResponseMessage deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int postId;
        try {
            postId = Integer.parseInt(request.getParameter("post"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = manageService.deletePost(postId);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }


    @RequestMapping("delete/floor")
    public ResponseMessage deleteFloor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int floorId;
        try {
            floorId = Integer.parseInt(request.getParameter("floor"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = manageService.deleteFloor(floorId);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }


    @RequestMapping("delete/reply")
    public ResponseMessage deleteReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int replyId;
        try {
            replyId = Integer.parseInt(request.getParameter("reply"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = manageService.deleteReply(replyId);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }
}
