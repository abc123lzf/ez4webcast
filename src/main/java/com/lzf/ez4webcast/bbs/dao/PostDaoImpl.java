package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Post;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public int postFloorCount(int postId) {
        Integer v = getFirst(jdbcTemplate.queryForList("select count(*) from bbs_floor_inf where post_id = " + postId, Integer.class));
        return v != null ? v : 0;
    }

    @Override
    public int insert(Post post) {
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = (Connection con) -> {
            PreparedStatement ps = con.prepareStatement("insert into bbs_post_inf(post_title, room_id, create_uid, " +
                        "create_time, update_time, status) values(?,?,?,now(),now(),0)", Statement.RETURN_GENERATED_KEYS);
            statementParams(ps, post.getTitle(), post.getRoomId(), post.getCreateUID());
            return ps;
        };

        jdbcTemplate.update(creator, holder);

        Number key = holder.getKey();
        return key == null ? -1 : key.intValue();
    }

    @Override
    public boolean changeStatus(int postId, int status) {
        return jdbcTemplate.update("update bbs_post_inf set status = ? where post_id = ?", parameters(postId, status)) > 0;
    }

    @Override
    public boolean resetPostUpdateTime(int postId) {
        return jdbcTemplate.update("update bbs_post_inf set update_time = now() where post_id = ?", parameters(postId)) > 0;
    }
}
