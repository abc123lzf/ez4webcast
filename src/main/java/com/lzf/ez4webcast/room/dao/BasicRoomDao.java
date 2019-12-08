package com.lzf.ez4webcast.room.dao;

import com.lzf.ez4webcast.room.model.Room;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:20
 */
public interface BasicRoomDao {

    Room fromRoomID(int roomId);

    Room fromUID(int uid);

    boolean add(int uid, String title);
}
