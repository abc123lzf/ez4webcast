package com.lzf.ez4webcast.bbs;

import com.lzf.ez4webcast.bbs.service.BasicBBSService;
import com.lzf.ez4webcast.bbs.vo.PostVo;
import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.lzf.ez4webcast.common.ComplexResponseMessage.message;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 9:20
 * 贴吧控制器
 */
@RestController
@RequestMapping("/api/bbs/view")
public class BBSViewController {

    @Autowired
    private BasicBBSService basicBBSService;

    @RequestMapping("list")
    public ComplexResponseMessage<List<PostVo>> roomPostList(HttpServletRequest request,
                                                             HttpServletResponse response) throws IOException {
        int roomId;
        try {
            roomId = Integer.parseInt(request.getParameter("room"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }


        ServiceResponse<List<PostVo>> resp = basicBBSService.simplePostList(roomId);
        if(resp.success()) {
            return message(0, "SUCCESS", resp.data());
        }

        return message(resp.code(), "FAILURE", null);
    }

    @RequestMapping("post")
    public ComplexResponseMessage<PostVo> postDetail(HttpServletRequest request,
                                                     HttpServletResponse response) throws IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("post"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<PostVo> resp = basicBBSService.postDetail(id);
        if(resp.success()) {
            return message(0, "SUCCESS", resp.data());
        }

        return message(resp.code(), "FAILURE", null);
    }



}
