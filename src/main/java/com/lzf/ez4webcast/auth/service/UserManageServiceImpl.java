package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.UserDao;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.image.service.BasicImageService;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 13:43
 */
@Service
class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BasicImageService imageService;

    @Override
    public ServiceResponse<Void> updateHeadImage(int imageId) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        ServiceResponse<Boolean> resp = imageService.containsImage(imageId);
        if(resp.success()) {
            if(!resp.data()) {
                return response(1);
            }
        } else {
            return response(2);
        }

        return userDao.updateHeadImage(user.getUid(), imageId) ? response(0) : response(3);
    }

}
