package com.lzf.ez4webcast.room.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:21
 */
@Data
public class Room {

    public static final RowMapper<Room> ROW_MAPPER = (rs, rowNum) -> {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setUid(rs.getInt("room_uid"));
        room.setTitle(rs.getString("room_title"));
        room.setTitleImage(rs.getInt("room_image_id"));
        room.setCreateTime(rs.getTimestamp("room_create_time"));
        room.setLastLiveTime(rs.getTimestamp("room_last_live_time"));
        return room;
    };

    private Integer id;

    private Integer uid;

    private String title;

    private Integer titleImage;

    private Timestamp lastLiveTime;

    private Timestamp createTime;

}
