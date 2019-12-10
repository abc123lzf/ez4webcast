package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.dao.BasicRoomDao;
import com.lzf.ez4webcast.room.dao.RoomStreamKeyDao;
import com.lzf.ez4webcast.room.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:25
 */
@Service
class BasicRoomServiceImpl implements BasicRoomService {

    @Autowired
    private BasicRoomDao basicRoomDao;

    @Autowired
    private RoomStreamKeyDao roomStreamKeyDao;

    @Override
    public ServiceResponse<Void> createRoom(int uid, String title) {
        boolean succ = basicRoomDao.add(uid, title);
        return succ ? response(0) : response(1);
    }

    @Override
    public ServiceResponse<Room> roomInfo(int roomId) {
        Room room = basicRoomDao.fromRoomID(roomId);
        return room != null ? response(0, room) : response(1);
    }

    @Override
    public ServiceResponse<List<Room>> allRoomInfo() {
        return response(0, basicRoomDao.all());
    }

    @Override
    public ServiceResponse<String> rmtpAuthKey(int rid) {
        String key = roomStreamKeyDao.getKey(rid);
        if(key == null) {
            return response(1);
        }

        return response(0, key);
    }
}
