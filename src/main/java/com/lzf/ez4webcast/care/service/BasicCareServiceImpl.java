package com.lzf.ez4webcast.care.service;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.care.dao.CareDao;
import com.lzf.ez4webcast.care.model.Care;
import com.lzf.ez4webcast.care.vo.CareVo;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.BasicRoomService;
import com.lzf.ez4webcast.room.vo.RoomVo;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:44
 */
@Service
class BasicCareServiceImpl implements BasicCareService {

    @Autowired
    private CareDao careDao;

    @Autowired
    private BasicRoomService basicRoomService;

    @Override
    public ServiceResponse<List<CareVo>> userCareList() {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        List<Care> list = careDao.fromUID(user.getUid());
        Map<Integer, Care> map = new HashMap<>(list.size() * 2);
        list.forEach(e -> map.put(e.getRoomId(), e));
        ServiceResponse<List<RoomVo>> resp = basicRoomService.roomInfo(map.keySet());

        if(!resp.success()) {
            return response(1);
        }

        List<CareVo> ans = new ArrayList<>(list.size());
        resp.data().forEach(e -> {
            Care c = map.get(e.getId());
            CareVo vo = new CareVo(c);
            vo.setRoomInfo(e);
            ans.add(vo);
        });

        return response(0, ans);
    }

    @Override
    public ServiceResponse<Void> addCare(int roomId) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        Care c = new Care();
        c.setRoomId(roomId);
        c.setUid(user.getUid());

        return careDao.add(c) ? response(0) : response(1);
    }

    @Override
    public ServiceResponse<Void> deleteCare(int roomId) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        return careDao.delete(user.getUid(), roomId) ? response(0) : response(1);
    }
}
