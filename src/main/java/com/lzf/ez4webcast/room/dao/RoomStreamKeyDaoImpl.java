package com.lzf.ez4webcast.room.dao;

import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 21:18
 */
@Repository
class RoomStreamKeyDaoImpl extends AbstractJdbcDao implements RoomStreamKeyDao {

    @Override
    public String makeKey(int rid) {
        String key = UUID.randomUUID().toString().replace("/", "");
        if(jdbcTemplate.update("insert into room_key_inf(room_id,room_key,update_time) values(?, ?, now()) on duplicate key update room_key = values(?)",
                rid, key, key) > 0) {
            return key;
        }

        return null;
    }

    @Override
    public String getKey(int rid) {
        return getFirst(jdbcTemplate.queryForList("select room_key from room_key_inf where room_id = ?", String.class, rid));
    }

}
