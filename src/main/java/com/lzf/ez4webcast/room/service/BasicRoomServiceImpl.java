package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.model.Room;
import org.springframework.stereotype.Service;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:25
 */
@Service
class BasicRoomServiceImpl implements BasicRoomService {

    @Override
    public ServiceResponse<Void> createRoom(int uid) {
        return null;
    }

    @Override
    public ServiceResponse<Room> roomInfo(int roomId) {
        return null;
    }

}
