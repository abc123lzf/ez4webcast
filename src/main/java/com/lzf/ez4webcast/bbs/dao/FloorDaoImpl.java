package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Floor;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 12:38
 */
@Repository
class FloorDaoImpl extends AbstractJdbcDao implements FloorDao {

    @Override
    public List<Floor> fromPostId(int postId) {
        return jdbcTemplate.query("select * from floor_inf where post_id = ?", parameters(postId), Floor.ROW_MAPPER);
    }
}
