package com.lzf.ez4webcast.auth.dao;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:56
 */
@Repository
class UserDaoImpl extends AbstractJdbcDao implements UserDao {

    private static final RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

    @Override
    public User fromUID(int uid) {
        return getFirst(jdbcTemplate.query("select uid,nickname,password,email from user_inf where uid = ?",
                parameters(uid), rowMapper));
    }

    @Override
    public User fromEmail(String email) {
        return getFirst(jdbcTemplate.query("select uid,nickname,password,email from user_inf where email = ?",
                parameters(email), rowMapper));
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("insert into user_inf(nickname, password, email, register_time) value (?,?,?,now())",
                user.getNickName(), user.getPassword(), user.getEmail());
    }

    @Override
    public boolean updateHeadImage(int uid, int imageId) {
        return jdbcTemplate.update("update user_inf set head_image_id = ? where uid = ?", parameters(imageId, uid)) > 0;
    }
}
