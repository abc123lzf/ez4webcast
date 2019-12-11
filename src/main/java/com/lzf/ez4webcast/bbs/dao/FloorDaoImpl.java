package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Floor;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 12:38
 */
@Repository
class FloorDaoImpl extends AbstractJdbcDao implements FloorDao {

    @Override
    public List<Floor> fromPostId(int postId) {
        return jdbcTemplate.query("select floor_id,floor_number,floor_content,post_id,create_uid,create_time,status " +
                "from bbs_floor_inf where post_id = ?", parameters(postId), Floor.ROW_MAPPER);
    }

    @Override
    @Transactional
    public boolean insertFloor(Floor floor) {
        jdbcTemplate.execute("select * from bbs_floor_inf where post_id = " + floor.getPostId() + " for update ");
        Integer max = jdbcTemplate.queryForObject("select max(floor_number) from bbs_floor_inf where post_id = " + floor.getPostId(), Integer.class);
        if(max == null) {
            return false;
        }

        return jdbcTemplate.update("insert into bbs_floor_inf(floor_number,floor_content,post_id,create_uid,create_time,status) " +
                "values(?,?,?,?,now(),0)", parameters(max + 1, floor.getContent(), floor.getPostId(), floor.getCreateUID())) > 0;
    }
}
