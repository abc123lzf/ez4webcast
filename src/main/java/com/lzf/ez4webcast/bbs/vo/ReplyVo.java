package com.lzf.ez4webcast.bbs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.auth.vo.UserVO;
import com.lzf.ez4webcast.bbs.model.Reply;
import lombok.Data;

import java.util.Date;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:16
 */
@Data
public class ReplyVo {

    @JSONField
    private Integer id;

    @JSONField
    private Integer replyNumber;

    @JSONField
    private String content;

    @JSONField
    private Integer replyObjectId;

    @JSONField
    private Integer floorId;

    @JSONField
    private UserVO createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField
    private Integer status;

    public ReplyVo() { }

    public ReplyVo(Reply reply) {
        this.id = reply.getId();
        this.replyNumber = reply.getReplyNumber();
        this.content = reply.getContent();
        this.replyObjectId = reply.getReplyObjectId();
        this.floorId = reply.getFloorId();
        this.createTime = reply.getCreateTime();
        this.status = reply.getStatus();
    }
}
