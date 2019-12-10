package com.lzf.ez4webcast.room.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 21:12
 */
@Data
public class RoomStreamKey {

    public static final RowMapper<RoomStreamKey> ROW_MAPPER = (rs, rowNum) -> {
        RoomStreamKey rk = new RoomStreamKey();
        rk.setRoomId(rs.getInt("room_id"));
        rk.setKey(rs.getString("room_key"));
        rk.setUpdateTime(rs.getTimestamp("update_time"));
        return rk;
    };

    private Integer roomId;

    private String key;

    private Timestamp updateTime;

}
