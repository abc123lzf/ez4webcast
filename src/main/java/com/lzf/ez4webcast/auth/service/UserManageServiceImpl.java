package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.auth.dao.UserDao;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.image.service.BasicImageService;
import com.lzf.ez4webcast.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 13:43
 */
@Log4j2
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

        boolean ans = userDao.updateHeadImage(user.getUid(), imageId);
        if(ans) {
            user.setHeadImage(imageId);
            UserUtils.setContextPrincipal(user);
        }

        return ans ? response(0) : response(3);
    }

    @Override
    public ServiceResponse<Void> updatePassword(String oldPass, String newPass) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        String od = DigestUtils.md5DigestAsHex(oldPass.getBytes());
        if(user.getPassword().equals(od)) {
            return response(1);
        }

        String nw = DigestUtils.md5DigestAsHex(newPass.getBytes());
        if(userDao.updatePassword(user.getUid(), nw)) {
            user.setPassword(nw);
            UserUtils.setContextPrincipal(user);
            return response(0);
        }

        return response(2);
    }

    @Override
    public ServiceResponse<Void> updateNickName(String newName) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        boolean ans = userDao.updateNickname(user.getUid(), newName);
        if(ans) {
            user.setNickName(newName);
            UserUtils.setContextPrincipal(user);
        }

        return ans ? response(0) : response(1);
    }
}
