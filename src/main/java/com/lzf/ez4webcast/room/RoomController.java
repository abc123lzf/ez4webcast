package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.BasicRoomService;
import com.lzf.ez4webcast.room.vo.RoomDetailVo;
import com.lzf.ez4webcast.room.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.lzf.ez4webcast.common.ComplexResponseMessage.message;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 14:45
 */
@RestController
@RequestMapping("/api/room/common")
public class RoomController {

    @Autowired
    private BasicRoomService basicRoomService;

    @GetMapping("all")
    public ComplexResponseMessage<List<RoomVo>> all() {
        ServiceResponse<List<RoomVo>> resp = basicRoomService.allRoomInfo();
        return resp.success() ?
                message(0, "SUCCESS", resp.data()) :
                message(1, "FAILURE", null);
    }

    @RequestMapping("detail")
    public ComplexResponseMessage<RoomDetailVo> detail(HttpServletRequest request,
                                                       HttpServletResponse response) throws IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("room"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return null;
        }

        ServiceResponse<RoomDetailVo> resp = basicRoomService.roomInfo(id);
        return resp.success() ? message(0, "SUCCESS", resp.data()) : message(1, "FAILURE", null);
    }

}
