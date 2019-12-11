package com.lzf.ez4webcast.bbs.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 9:55
 * 楼层
 */
@Data
public class Floor {

    public static final RowMapper<Floor> ROW_MAPPER = (rs, rowNum) -> {
        Floor floor = new Floor();
        floor.setId(rs.getInt("floor_id"));
        floor.setFloorNumber(rs.getInt("floor_number"));
        floor.setContent(rs.getString("floor_content"));
        floor.setPostId(rs.getInt("post_id"));
        floor.setCreateUID(rs.getInt("create_uid"));
        floor.setCreateTime(rs.getTimestamp("create_time"));
        floor.setStatus(rs.getInt("status"));
        return floor;
    };

    private Integer id;

    private Integer floorNumber;

    private String content;

    private Integer postId;

    private Integer createUID;

    private Timestamp createTime;

    private Integer status;
}
