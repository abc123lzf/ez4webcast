package com.lzf.ez4webcast.care.service;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.care.dao.CareDao;
import com.lzf.ez4webcast.care.model.Care;
import com.lzf.ez4webcast.care.vo.CareVo;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.room.service.BasicRoomService;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return response(0);
    }

    @Override
    public ServiceResponse<Void> addCare(int roomId) {
        return null;
    }

    @Override
    public ServiceResponse<Void> deleteCare(int roomId) {
        return null;
    }
}
