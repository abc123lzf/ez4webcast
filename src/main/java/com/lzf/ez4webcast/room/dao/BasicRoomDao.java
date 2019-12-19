package com.lzf.ez4webcast.room.dao;

import com.lzf.ez4webcast.room.model.Room;

import java.util.Collection;
import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:20
 */
public interface BasicRoomDao {

    Room fromRoomID(int roomId);

    Room fromUID(int uid);

    boolean add(int uid, String title, Integer titleImage);

    List<Room> all();

    List<Room> fromRoomID(Collection<Integer> ids);

    void updateLastLiveTime(int rid);

    boolean changeRoomTitleImage(int uid, int imageId);

    boolean changeRoomTitle(int uid, String title);
}
