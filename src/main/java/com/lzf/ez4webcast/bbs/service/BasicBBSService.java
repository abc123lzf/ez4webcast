package com.lzf.ez4webcast.bbs.service;

import com.lzf.ez4webcast.bbs.param.PublishPostParam;
import com.lzf.ez4webcast.bbs.vo.PostVo;
import com.lzf.ez4webcast.common.ServiceResponse;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 16:14
 */
public interface BasicBBSService {

    /**
     * 根据直播间ID查找帖子列表
     * @param roomId 直播间ID
     * @return 帖子列表
     */
    ServiceResponse<List<PostVo>> simplePostList(int roomId);

    /**
     * 根据帖子ID查找帖子信息
     * @param postId 帖子ID
     * @return 帖子
     */
    ServiceResponse<PostVo> postDetail(int postId);


    ServiceResponse<Void> publishPost(PublishPostParam param);
}
