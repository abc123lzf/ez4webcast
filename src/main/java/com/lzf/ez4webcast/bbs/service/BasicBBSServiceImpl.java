package com.lzf.ez4webcast.bbs.service;

import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.auth.service.BasicUserService;
import com.lzf.ez4webcast.auth.vo.UserVo;
import com.lzf.ez4webcast.bbs.dao.FloorDao;
import com.lzf.ez4webcast.bbs.dao.PostDao;
import com.lzf.ez4webcast.bbs.dao.ReplyDao;
import com.lzf.ez4webcast.bbs.model.Floor;
import com.lzf.ez4webcast.bbs.model.Post;
import com.lzf.ez4webcast.bbs.model.Reply;
import com.lzf.ez4webcast.bbs.param.PublishPostParam;
import com.lzf.ez4webcast.bbs.vo.FloorVo;
import com.lzf.ez4webcast.bbs.vo.PostVo;
import com.lzf.ez4webcast.bbs.vo.ReplyVo;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 16:16
 */
@Service
class BasicBBSServiceImpl implements BasicBBSService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private FloorDao floorDao;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private BasicUserService basicUserService;

    @Override
    public ServiceResponse<List<PostVo>> simplePostList(int roomId) {
        List<Post> list = postDao.listFromRoomId(roomId);
        List<PostVo> ans = new ArrayList<>(list.size());
        list.forEach(e -> {
            PostVo vo = new PostVo(e);
            ServiceResponse<UserVo> resp = basicUserService.findUserByUID(e.getCreateUID());
            if(resp.success()) {
                vo.setCreateUser(resp.data());
            }
            ans.add(vo);
        });

        return response(0, ans);
    }

    @Override
    public ServiceResponse<PostVo> postDetail(int postId) {
        Post post = postDao.fromPostId(postId);
        if(post == null) {
            return response(1);
        }

        List<Floor> floors = floorDao.fromPostId(post.getId());

        List<FloorVo> floorVos = new ArrayList<>(floors.size());
        Map<Integer, FloorVo> floorMap = new HashMap<>(floors.size(), 1);
        List<Integer> ids = new ArrayList<>(floors.size());
        floors.forEach(e -> {
            ids.add(e.getId());
            FloorVo vo = new FloorVo(e);
            vo.setReplies(new ArrayList<>());
            floorVos.add(vo);
            floorMap.put(e.getId(), vo);
        });

        List<Reply> replies = replyDao.fromFloorId(ids);
        replies.forEach(e -> floorMap.get(e.getFloorId()).getReplies().add(new ReplyVo(e)));

        PostVo vo = new PostVo(post);
        vo.setFloors(floorVos);
        return response(0, vo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceResponse<Void> publishPost(PublishPostParam param) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        Post post = new Post();
        post.setRoomId(param.getRoomId());
        post.setTitle(param.getTitle());
        post.setCreateUID(user.getUid());

        postDao.insert(post);
        /*if(!postDao.insert(post)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response(1);
        }*/


        Floor floor = new Floor();
        floor.setContent(param.getContent());
        floor.setCreateUID(user.getUid());
        floor.setFloorNumber(1);


        return null;
    }
}
