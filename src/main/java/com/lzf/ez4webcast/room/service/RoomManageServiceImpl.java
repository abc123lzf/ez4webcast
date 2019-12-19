package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.dao.BasicRoomDao;
import com.lzf.ez4webcast.room.dao.RoomStreamKeyDao;
import com.lzf.ez4webcast.room.model.Room;
import com.lzf.ez4webcast.room.vo.RoomBaseInfoVo;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/14 1:35
 */
@Service
class RoomManageServiceImpl implements RoomManageService {

    @Autowired
    private BasicRoomDao basicRoomDao;

    @Autowired
    private RoomStreamKeyDao roomStreamKeyDao;


    @Override
    public ServiceResponse<String> rmtpAuthKey(int rid) {
        String key = roomStreamKeyDao.getKey(rid);
        if(key == null) {
            return response(1);
        }

        return response(0, key);
    }


    @Override
    public ServiceResponse<String> updateAuthKey() {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        Room room = basicRoomDao.fromUID(user.getUid());
        if(room == null) {
            return response(1);
        }

        String key = roomStreamKeyDao.makeKey(room.getId());
        if(key == null) {
            return response(2);
        }

        return response(0, key);
    }


    @Override
    public ServiceResponse<Void> updateLastLiveTime(int roomId) {
        basicRoomDao.updateLastLiveTime(roomId);
        return response(0);
    }


    @Override
    public ServiceResponse<Void> createRoom(String title, Integer image) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        boolean succ = basicRoomDao.add(user.getUid(), title, image);
        return succ ? response(0) : response(1);
    }


    @Override
    public ServiceResponse<RoomBaseInfoVo> roomBaseInfo() {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        RoomBaseInfoVo result = new RoomBaseInfoVo();
        Room room = basicRoomDao.fromUID(user.getUid());
        result.setInfo(room);

        ServiceResponse<String> resp = rmtpAuthKey(room.getId());
        if(!resp.success() || resp.data() == null) {
            String key = roomStreamKeyDao.makeKey(room.getId());
            if(key == null) {
                return response(2);
            }
            result.setUpstreamKey(Base64Utils.encodeToString(key.getBytes()));
        } else {
            result.setUpstreamKey(Base64Utils.encodeToString(resp.data().getBytes()));
        }

        result.setUpstreamURI("/ez4webcast");
        return response(0, result);
    }


    @Override
    public ServiceResponse<Void> changeTitleImage(int imageId) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        int uid = user.getUid();
        return basicRoomDao.changeRoomTitleImage(uid, imageId) ? response(0) : response(1);
    }


    @Override
    public ServiceResponse<Void> changeTitle(String title) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        int uid = user.getUid();
        return basicRoomDao.changeRoomTitle(uid, title) ? response(0) : response(1);
    }
}
