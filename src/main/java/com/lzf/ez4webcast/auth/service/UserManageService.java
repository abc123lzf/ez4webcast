package com.lzf.ez4webcast.auth.service;

import com.lzf.ez4webcast.common.ServiceResponse;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 13:43
 * 用户管理服务
 */
public interface UserManageService {

    ServiceResponse<Void> updateHeadImage(int imageId);

}
