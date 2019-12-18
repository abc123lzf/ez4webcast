package com.lzf.ez4webcast.danmaku.model;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.lzf.ez4webcast.auth.vo.UserVo;
import lombok.Data;


/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 19:27
 * 弹幕实体
 */
@Data
public class Danmaku implements JSONAware {

    private UserVo user;

    private String type;

    private String color;

    private String content;

    private long time;

    public static Danmaku fromJSON(JSONObject object) {
        if(!object.containsKey("content")) {
            return null;
        }

        Danmaku danmaku = new Danmaku();
        danmaku.setContent(object.getString("content"));
        danmaku.setTime(System.currentTimeMillis());
        return danmaku;
    }

    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("content", content);
        obj.put("time", time);
        obj.put("color", color);
        obj.put("type", type);

        JSONObject user = new JSONObject();
        user.put("uid", this.user.getUid());
        user.put("nickname", this.user.getNickName());

        obj.put("user", user);
        return obj.toJSONString();
    }
}
