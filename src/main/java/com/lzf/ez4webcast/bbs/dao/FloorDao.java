package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Floor;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 12:38
 */
public interface FloorDao {

    List<Floor> fromPostId(int postId);

    boolean insertFloor(Floor floor);
}
