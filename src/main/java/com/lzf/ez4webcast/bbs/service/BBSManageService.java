package com.lzf.ez4webcast.bbs.service;

import com.lzf.ez4webcast.common.ServiceResponse;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 10:04
 */
public interface BBSManageService {

    ServiceResponse<Void> deletePost(int postId);

    ServiceResponse<Void> deleteFloor(int floorId);

    ServiceResponse<Void> deleteReply(int replyId);
}
