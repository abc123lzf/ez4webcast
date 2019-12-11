package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Reply;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 15:29
 */
public interface ReplyDao {

    List<Reply> fromFloorId(int floorId);

    List<Reply> fromFloorId(List<Integer> floorIds);

    boolean insert(Reply reply);

}
