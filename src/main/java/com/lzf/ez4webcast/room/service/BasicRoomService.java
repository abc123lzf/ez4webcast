package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.model.Room;

import java.util.List;

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

    /**
     * 获取直播间基本信息
     * @return 直播间基本信息
     */
    ServiceResponse<List<Room>> allRoomInfo();


    /**
     * 更新直播间
     * @param roomId 房间ID
     * @return 结果
     */
    ServiceResponse<Void> updateLastLiveTime(int roomId);


    /**
     * 获取直播间推流密钥
     * @param rid 直播间ID
     * @return 密钥
     */
    ServiceResponse<String> rmtpAuthKey(int rid);
}
