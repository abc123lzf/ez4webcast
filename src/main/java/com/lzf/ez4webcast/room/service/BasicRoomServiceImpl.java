package com.lzf.ez4webcast.room.service;

import com.lzf.ez4webcast.auth.service.BasicUserService;
import com.lzf.ez4webcast.auth.vo.UserVO;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.dao.BasicRoomDao;
import com.lzf.ez4webcast.room.dao.RoomStreamKeyDao;
import com.lzf.ez4webcast.room.model.Room;
import com.lzf.ez4webcast.room.vo.RoomDetailVo;
import com.lzf.ez4webcast.room.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:25
 */
@Service
@PropertySource("live.properties")
class BasicRoomServiceImpl implements BasicRoomService {

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private BasicRoomDao basicRoomDao;

    @Autowired
    private RoomStreamKeyDao roomStreamKeyDao;

    @Value("${ez4webcast.live.flv.url.prefix}")
    private String flvUri;

    @Value("${ez4webcast.live.flv.url.argument.app}")
    private String flvUriAppArg;

    @Value("${ez4webcast.live.flv.url.argument.stream}")
    private String flvUriStreamArg;

    @Override
    public ServiceResponse<RoomDetailVo> roomInfo(int roomId) {
        Room room = basicRoomDao.fromRoomID(roomId);
        if(room == null) {
            return response(1);
        }

        ServiceResponse<UserVO> resp = basicUserService.findUserByUID(room.getUid());
        if(!resp.success()) {
            return response(2);
        }

        RoomDetailVo vo = new RoomDetailVo(room);
        vo.setAnchor(resp.data());
        vo.getAnchor().setEmail(null);  //防止泄露Email

        URI flv = UriComponentsBuilder.fromUriString(flvUri)
                    .queryParam(flvUriAppArg, "ez4webcast")
                    .queryParam(flvUriStreamArg, room.getId())
                    .build()
                    .toUri();

        vo.setFlvUri(flv.toString());
        vo.setDanmakuUri("/api/damanku/ws/message/" + room.getId());
        return response(0, vo);
    }

    @Override
    public ServiceResponse<Boolean> containsRoom(int roomId) {
        Room r = basicRoomDao.fromRoomID(roomId);
        return r != null ? response(0, true) : response(0, false);
    }

    @Override
    public ServiceResponse<List<RoomVo>> allRoomInfo() {
        List<Room> list = basicRoomDao.all();
        if(list == null) {
            return response(1);
        }
        List<RoomVo> vos = new ArrayList<>(list.size());
        list.forEach(e -> vos.add(new RoomVo(e)));
        return response(0, vos);
    }


    @Override
    public ServiceResponse<List<RoomVo>> roomInfo(Collection<Integer> ids) {
        List<Room> list = basicRoomDao.fromRoomID(ids);
        if(list == null) {
            return response(1);
        }

        List<RoomVo> ans = new ArrayList<>(list.size());
        list.forEach(e -> ans.add(new RoomVo(e)));

        return response(0, ans);
    }


}
