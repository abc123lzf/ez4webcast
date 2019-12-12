package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Reply;
import com.lzf.ez4webcast.common.AbstractJdbcDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 15:31
 */
@Repository
class ReplyDaoImpl extends AbstractJdbcDao implements ReplyDao {

    @Override
    public List<Reply> fromFloorId(int floorId) {
        return jdbcTemplate.query("select reply_id,reply_number, reply_content,reply_object_id,floor_id,create_uid,create_time,status " +
                "from bbs_reply_inf where floor_id = ?", parameters(floorId), Reply.ROW_MAPPER);
    }

    @Override
    public List<Reply> fromFloorId(List<Integer> floorIds) {
        if(CollectionUtils.isEmpty(floorIds)) {
            return Collections.emptyList();
        }

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("ids", floorIds);
        return namedJdbcTemplate.query("select reply_id,reply_number, reply_content,reply_object_id,floor_id,create_uid,create_time,status " +
                "from bbs_reply_inf where floor_id in (:ids)", param, Reply.ROW_MAPPER);
    }

    @Override
    @Transactional
    public boolean insert(Reply reply) {
        jdbcTemplate.execute("select * from bbs_reply_inf where floor_id = " + reply.getFloorId() + " for update ");
        Integer max = jdbcTemplate.queryForObject("select max(reply_number) from bbs_reply_inf where floor_id = "
                + reply.getFloorId(), Integer.class);
        if(max == null) {
            max = 0;
        }

        if(reply.getReplyObjectId() == null) {
            return jdbcTemplate.update("insert into bbs_reply_inf(reply_number,reply_content,floor_id,create_uid,create_time,status) " +
                    "values (?,?,?,?,now(),0)", parameters(max + 1, reply.getContent(), reply.getFloorId(), reply.getCreateUID())) > 0;
        } else {
            return jdbcTemplate.update("insert into bbs_reply_inf(reply_number,reply_object_id, reply_content,floor_id,create_uid,create_time,status) " +
                    "values (?,?,?,?,?,now(),0)", parameters(max + 1, reply.getReplyObjectId(), reply.getContent(), reply.getFloorId(), reply.getCreateUID())) > 0;
        }
    }

    @Override
    public boolean changeStatus(int replyId, int status) {
        return jdbcTemplate.update("update bbs_reply_inf set status = ? where reply_id = ?", parameters(status, replyId)) > 0;
    }
}
