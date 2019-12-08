package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.model.Room;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:25
 */
public interface BasicRoomService {

    /**
     * 创建直播间
     * @param uid 用户ID
     * @return 是否创建成功
     */
    ServiceResponse<Void> createRoom(int uid, String title);

    /**
     * 获取直播间基本信息
     * @param roomId 直播间ID
     * @return 直播间基本信息
     */
    ServiceResponse<Room> roomInfo(int roomId);

}
