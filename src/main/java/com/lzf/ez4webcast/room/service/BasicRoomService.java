package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.vo.RoomDetailVo;
import com.lzf.ez4webcast.room.vo.RoomVo;

import java.util.Collection;
import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:25
 */
public interface BasicRoomService {

    /**
     * 获取直播间基本信息
     * @param roomId 直播间ID
     * @return 直播间基本信息
     */
    ServiceResponse<RoomDetailVo> roomInfo(int roomId);


    /**
     * 判断直播间是否存在
     * @param roomId 直播间ID
     * @return 直播间基本信息
     */
    ServiceResponse<Boolean> containsRoom(int roomId);


    /**
     * 获取直播间基本信息
     * @return 直播间基本信息
     */
    ServiceResponse<List<RoomVo>> allRoomInfo();

    /**
     * 获取直播间基本信息
     * @param ids 直播间ID
     * @return 直播间基本信息
     */
    ServiceResponse<List<RoomVo>> roomInfo(Collection<Integer> ids);



}
