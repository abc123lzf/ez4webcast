package com.lzf.ez4webcast.bbs.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 9:55
 * 帖子
 */
@Data
public class Post {

    public static final RowMapper<Post> ROW_MAPPER = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getInt("post_id"));
        post.setTitle(rs.getString("post_title"));
        post.setRoomId(rs.getInt("room_id"));
        post.setCreateUID(rs.getInt("create_uid"));
        post.setCreateTime(rs.getTimestamp("create_time"));
        post.setUpdateTime(rs.getTimestamp("update_time"));
        post.setStatus(rs.getInt("status"));
        return post;
    };

    private Integer id;

    private String title;

    private Integer roomId;

    private Integer createUID;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer status;

}
