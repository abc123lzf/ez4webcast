package com.lzf.ez4webcast.room.dao;

import com.lzf.ez4webcast.common.AbstractJdbcDao;
import com.lzf.ez4webcast.room.model.Room;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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
    public List<Room> fromRoomID(Collection<Integer> ids) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("ids", ids);
        return namedJdbcTemplate.query("select room_id, room_uid, room_title, room_image_id, room_create_time from " +
                "room_inf where room_inf in (:ids)", param, Room.ROW_MAPPER);
    }

    @Override
    public boolean add(int uid, String title, Integer image) {
        if(fromUID(uid) != null) {
            return false;
        }

        return image == null ?
                jdbcTemplate.update("insert into room_inf(room_uid, room_title, room_create_time) values(?,?,now())",
                        uid, title) > 0 :
                jdbcTemplate.update("insert into room_inf (room_uid, room_title, room_image_id, room_create_time) values " +
                        "(?, ?, ?, now())", uid, title, image) > 0;
    }

    @Override
    public List<Room> all() {
        return jdbcTemplate.query("select room_id, room_uid, room_title, room_image_id, room_create_time from room_inf",
                Room.ROW_MAPPER);
    }

    @Override
    public void updateLastLiveTime(int rid) {
        jdbcTemplate.update("update room_inf set room_last_live_time = now() where room_id = ?", rid);
    }
}
