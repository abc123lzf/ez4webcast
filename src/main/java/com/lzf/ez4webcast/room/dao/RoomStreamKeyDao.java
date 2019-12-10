package com.lzf.ez4webcast.room.dao;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 21:16
 */
public interface RoomStreamKeyDao {

    void makeKey(int rid);

    String getKey(int rid);
}
