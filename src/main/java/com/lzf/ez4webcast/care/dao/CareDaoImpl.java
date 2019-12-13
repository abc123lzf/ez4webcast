package com.lzf.ez4webcast.care.dao;

import com.lzf.ez4webcast.care.model.Care;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:27
 */
@Repository
class CareDaoImpl extends AbstractJdbcDao implements CareDao {

    @Override
    public void add(Care care) {
        jdbcTemplate.update("insert into care_inf(care_room_id, belong_uid, care_time) value (?, ?, now())",
                parameters(care.getUid(), care.getRoomId()));
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from care_inf where care_id = ?", parameters(id)) > 0;
    }

    @Override
    public Care fromId(int id) {
        return getFirst(jdbcTemplate.query("select care_id, care_room_id, belong_uid, care_time from care_inf " +
                "where care_id = ?", parameters(id), Care.ROW_MAPPER));
    }

    @Override
    public List<Care> fromUID(int uid) {
        return jdbcTemplate.query("select care_id, care_room_id, belong_uid, care_time from care_inf " +
                "where belong_uid = ?", parameters(uid), Care.ROW_MAPPER);
    }
}
