package com.lzf.ez4webcast.bbs.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 9:55
 * 楼层下的回复
 */
@Data
public class Reply {

    public static final RowMapper<Reply> ROW_MAPPER = (rs, rowNum) -> {
        Reply reply = new Reply();
        reply.setId(rs.getInt("reply_id"));
        reply.setContent(rs.getString("reply_content"));
        reply.setReplyObjectId(rs.getInt("reply_object_id"));
        reply.setFloorId(rs.getInt("floor_id"));
        reply.setCreateUID(rs.getInt("create_uid"));
        reply.setCreateTime(rs.getTimestamp("create_time"));
        reply.setStatus(rs.getInt("status"));
        return reply;
    };

    private Integer id;

    private String content;

    private Integer replyObjectId;

    private Integer floorId;

    private Integer createUID;

    private Timestamp createTime;

    private Integer status;

}
