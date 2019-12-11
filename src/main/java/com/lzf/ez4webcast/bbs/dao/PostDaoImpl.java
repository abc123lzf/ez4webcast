package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Post;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 11:00
 */
@Repository
class PostDaoImpl extends AbstractJdbcDao implements PostDao {

    @Override
    public List<Post> listFromRoomId(int roomId) {
        return jdbcTemplate.query("select post_id,post_title,room_id,create_uid,create_time,update_time,status " +
                "from bbs_post_inf where room_id = ?", parameters(roomId), Post.ROW_MAPPER);
    }

    @Override
    public Post fromPostId(int postId) {
        return getFirst(jdbcTemplate.query("select post_id,post_title,room_id,create_uid,create_time,update_time,status " +
                "from bbs_post_inf where post_id = ?", parameters(postId), Post.ROW_MAPPER));
    }
}
