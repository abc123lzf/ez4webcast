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
import com.lzf.ez4webcast.bbs.param.PublishFloorParam;
import com.lzf.ez4webcast.bbs.param.PublishPostParam;
import com.lzf.ez4webcast.bbs.param.PublishReplyParam;
import com.lzf.ez4webcast.bbs.vo.FloorVo;
import com.lzf.ez4webcast.bbs.vo.PostVo;
import com.lzf.ez4webcast.bbs.vo.ReplyVo;
import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 16:16
 */
@Service
@Log4j2
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

        List<FloorVo> floorVos = new ArrayList<>(floors.size()); //楼层视图
        Map<Integer, FloorVo> floorMap = new HashMap<>(floors.size(), 1);
        List<Integer> queryUserIds = new ArrayList<>(); //查询用户ID
        Map<Integer, Object> queryUserMap = new HashMap<>();
        List<Integer> ids = new ArrayList<>(floors.size());
        floors.forEach(e -> {
            ids.add(e.getId());
            FloorVo vo = new FloorVo(e);
            vo.setReplies(new ArrayList<>());
            floorVos.add(vo);
            floorMap.put(e.getId(), vo);

            queryUserIds.add(e.getCreateUID());
            queryUserMap.put(e.getCreateUID(), e);
        });


        List<Reply> replies = replyDao.fromFloorId(ids);
        replies.forEach(e -> {
            ReplyVo vo = new ReplyVo(e);
            queryUserIds.add(e.getCreateUID());
            queryUserMap.put(e.getCreateUID(), e);
            floorMap.get(e.getFloorId()).getReplies().add(vo);
        });


        Map<Integer, UserVo> uidQueryRes = mapper(basicUserService.findUserByUID(queryUserIds).data());
        queryUserMap.forEach((k, v) -> {
            UserVo vo = uidQueryRes.get(k);
            if(vo != null) {
                if(v instanceof FloorVo) {
                    ((FloorVo)v).setCreateUser(vo);
                } else if(v instanceof ReplyVo) {
                    ((ReplyVo)v).setCreateUser(vo);
                }
            }
        });

        PostVo vo = new PostVo(post);
        vo.setFloors(floorVos);
        vo.setCreateUser(basicUserService.findUserByUID(post.getCreateUID()).data());
        return response(0, vo);
    }


    private static Map<Integer, UserVo> mapper(List<UserVo> list) {
        if(CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<Integer, UserVo> map = new HashMap<>(list.size() * 2);
        list.forEach(e -> map.put(e.getUid(), e));
        return map;
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

        int postId = postDao.insert(post);
        if(postId == -1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response(1);
        }

        Floor floor = new Floor();
        floor.setContent(param.getContent());
        floor.setCreateUID(user.getUid());
        floor.setFloorNumber(1);
        floor.setPostId(postId);

        floorDao.insertFloor(floor);
        return response(0);
    }

    @Override
    public ServiceResponse<Void> publishFloor(PublishFloorParam param) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        Floor floor = new Floor();
        floor.setContent(param.getContent());
        floor.setPostId(param.getPostId());
        floor.setCreateUID(user.getUid());

        return floorDao.insertFloor(floor) ? response(0) : response(1);
    }

    @Override
    public ServiceResponse<Void> publishFloorReply(PublishReplyParam param) {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return response(-1);
        }

        Reply reply = new Reply();
        reply.setCreateUID(user.getUid());
        reply.setContent(param.getContent());
        reply.setReplyObjectId(param.getReplyObjectId());
        reply.setFloorId(param.getFloorId());

        return replyDao.insert(reply) ? response(0) : response(1);
    }
}
