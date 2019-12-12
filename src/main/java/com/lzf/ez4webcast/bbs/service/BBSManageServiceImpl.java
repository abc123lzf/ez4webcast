package com.lzf.ez4webcast.bbs.service;

import com.lzf.ez4webcast.bbs.dao.FloorDao;
import com.lzf.ez4webcast.bbs.dao.PostDao;
import com.lzf.ez4webcast.bbs.dao.ReplyDao;
import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.lzf.ez4webcast.common.ServiceResponse.response;
/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/12 10:04
 * 帖子管理服务
 */
@Service
class BBSManageServiceImpl implements BBSManageService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private FloorDao floorDao;

    @Autowired
    private ReplyDao replyDao;

    @Override
    public ServiceResponse<Void> deletePost(int postId) {
        return postDao.changeStatus(postId, -1) ? response(0) : response(1);
    }

    @Override
    public ServiceResponse<Void> deleteFloor(int floorId) {
        return floorDao.changeStatus(floorId, -1) ? response(0) : response(1);
    }

    @Override
    public ServiceResponse<Void> deleteReply(int replyId) {
        return replyDao.changeStatus(replyId, -1) ? response(0) : response(1);
    }


}
