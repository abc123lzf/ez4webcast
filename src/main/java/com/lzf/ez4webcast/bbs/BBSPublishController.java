package com.lzf.ez4webcast.bbs;

import com.lzf.ez4webcast.bbs.param.PublishFloorParam;
import com.lzf.ez4webcast.bbs.param.PublishPostParam;
import com.lzf.ez4webcast.bbs.param.PublishReplyParam;
import com.lzf.ez4webcast.bbs.service.BasicBBSService;
import com.lzf.ez4webcast.common.ResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lzf.ez4webcast.common.ComplexResponseMessage.message;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 9:10
 */
@Log4j2
@RestController
@RequestMapping("/api/bbs/publish")
public class BBSPublishController {

    @Autowired
    private BasicBBSService basicBBSService;

    @RequestMapping("post")
    public ResponseMessage publishPost(@RequestBody PublishPostParam param,
                                       HttpServletResponse response) throws IOException {
        if(param.getTitle() == null || param.getContent() == null || param.getRoomId() == null) {
            response.sendError(400);
            return null;
        }
        ServiceResponse<Void> resp = basicBBSService.publishPost(param);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }


    @RequestMapping("floor")
    public ResponseMessage publishFloor(@RequestBody PublishFloorParam param,
                                        HttpServletResponse response) throws IOException {
        log.info("floor {}, {}", param.getPostId(), param.getContent());
        if(param.getPostId() == null || param.getContent() == null) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = basicBBSService.publishFloor(param);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }


    @RequestMapping("reply")
    public ResponseMessage publishReply(@RequestBody PublishReplyParam param,
                                        HttpServletResponse response) throws IOException {
        if(param.getContent() == null || param.getFloorId() == null) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<Void> resp = basicBBSService.publishFloorReply(param);
        return resp.success() ? message(0, "SUCCESS") : message(resp.code(), "FAILURE");
    }
}
