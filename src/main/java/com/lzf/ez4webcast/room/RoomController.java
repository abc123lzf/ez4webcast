package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.room.model.Room;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lzf.ez4webcast.common.ResponseMessage.message;
/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 14:45
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    public ComplexResponseMessage<Room> roomInfo(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
