package com.lzf.ez4webcast.auth.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.auth.model.User;
import lombok.Data;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:10
 */
@Data
public class UserVo implements JSONAware {

    @JSONField
    private Integer uid;

    @JSONField
    private String nickName;

    @JSONField
    private String email;

    @JSONField
    private Integer headImage;

    public UserVo(User user) {
        this.uid = user.getUid();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.headImage = user.getHeadImage();
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
