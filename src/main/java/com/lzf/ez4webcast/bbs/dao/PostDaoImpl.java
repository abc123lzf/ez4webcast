package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Post;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public int insert(Post post) {
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = (Connection con) -> {
            return con.prepareStatement("insert into bbs_post_inf(post_title, room_id, create_uid, " +
                        "create_time, update_time, status) values(?,?,?,now(),now(),0)", Statement.RETURN_GENERATED_KEYS);

        };

        jdbcTemplate.update(creator, holder);

        return (int)holder.getKey().longValue();
    }
}
