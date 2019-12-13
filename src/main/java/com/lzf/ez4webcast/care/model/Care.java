package com.lzf.ez4webcast.care.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:24
 */
@Data
public class Care {

    public static final RowMapper<Care> ROW_MAPPER = (rs, rowNum) -> {
        Care c = new Care();
        c.setId(rs.getInt("care_id"));
        c.setRoomId(rs.getInt("care_room_id"));
        c.setUid(rs.getInt("belong_uid"));
        c.setCareTime(rs.getTimestamp("care_time"));
        return c;
    };

    private Integer id;

    private Integer roomId;

    private Integer uid;

    private Timestamp careTime;

}
