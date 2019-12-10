package com.lzf.ez4webcast.room;

import com.lzf.ez4webcast.common.ComplexResponseMessage;
import com.lzf.ez4webcast.room.model.Room;
import com.lzf.ez4webcast.room.service.BasicRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ComplexResponseMessage<List<Room>> all() {
        return message(0, "SUCCESS", basicRoomService.allRoomInfo().data());
    }



}
