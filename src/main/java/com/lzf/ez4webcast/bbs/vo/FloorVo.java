package com.lzf.ez4webcast.bbs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.auth.vo.UserVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:16
 */
@Data
public class FloorVo {

    @JSONField
    private Integer id;

    @JSONField
    private Integer floorNumber;

    @JSONField
    private String content;

    @JSONField
    private List<ReplyVo> replies;

    @JSONField
    private String postId;

    @JSONField
    private UserVo createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField
    private Integer status;

}
