package com.lzf.ez4webcast.bbs.dao;

import com.lzf.ez4webcast.bbs.model.Post;

import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:59
 */
public interface PostDao {

    List<Post> listFromRoomId(int roomId);

    Post fromPostId(int postId);

    int insert(Post post);
}
