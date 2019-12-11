package com.lzf.ez4webcast.bbs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.auth.vo.UserVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:09
 */
@Data
public class PostVo {

    @JSONField
    private Integer id;

    @JSONField
    private String title;

    @JSONField
    private Integer roomId;

    @JSONField
    private List<FloorVo> floors;

    @JSONField
    private UserVo createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JSONField
    private Integer status;

}
