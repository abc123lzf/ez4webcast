package com.lzf.ez4webcast.care.service;

import com.lzf.ez4webcast.care.vo.CareVo;
import com.lzf.ez4webcast.common.ServiceResponse;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:44
 */
public interface BasicCareService {

    ServiceResponse<List<CareVo>> userCareList();

    ServiceResponse<Void> addCare(int roomId);

    ServiceResponse<Void> deleteCare(int roomId);
}
