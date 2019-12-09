package com.lzf.ez4webcast.room.dao;

import com.lzf.ez4webcast.common.AbstractJdbcDao;
import com.lzf.ez4webcast.room.model.Room;
import org.springframework.stereotype.Repository;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:30
 */
@Repository
class BasicRoomDaoImpl extends AbstractJdbcDao implements BasicRoomDao {

    @Override
    public Room fromRoomID(int roomId) {
        return getFirst(jdbcTemplate.query("select room_id, room_uid, room_title, room_image_id, room_create_time " +
                "from room_inf where room_id = ?", parameters(roomId), Room.ROW_MAPPER));
    }

    @Override
    public Room fromUID(int uid) {
        return getFirst(jdbcTemplate.query("select room_id, room_uid, room_title, room_image_id, room_create_time " +
                "from room_inf where room_uid = ?", parameters(uid), Room.ROW_MAPPER));
    }

    @Override
    public boolean add(int uid, String title) {
        if(fromUID(uid) != null) {
            return false;
        }

        return jdbcTemplate.update("insert into room_inf(room_uid, room_title, room_create_time) values(?,?,now())",
                uid, title) == 1;
    }
}
