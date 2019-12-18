package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.vo.RoomBaseInfoVo;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/14 1:35
 */
public interface RoomManageService {

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


    /**
     * 更新直播间推流密钥
     * @return 密钥
     */
    ServiceResponse<String> updateAuthKey();


    /**
     * 创建直播间
     * @return 是否创建成功
     */
    ServiceResponse<Void> createRoom(String title, Integer titleImage);


    /**
     * 主播查看自己的直播间信息
     * @return 直播间信息
     */
    ServiceResponse<RoomBaseInfoVo> roomBaseInfo();
}
