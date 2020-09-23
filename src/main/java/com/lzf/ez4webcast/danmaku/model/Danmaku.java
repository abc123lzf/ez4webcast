package com.lzf.ez4webcast.danmaku.model;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Objects;


/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 19:27
 * 弹幕实体
 */
@Data
public class Danmaku implements JSONAware {

    private String type;

    private String color;

    private String content;

    private long sendTime;

    public static Danmaku fromJSON(JSONObject object) {
        Objects.requireNonNull(object);
        Danmaku danmaku = new Danmaku();
        danmaku.setContent(object.getString("text"));
        danmaku.setType(object.getString("type"));
        danmaku.setColor(object.getString("color"));
        danmaku.setSendTime(System.currentTimeMillis());
        return danmaku;
    }

    @Override
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("content", content);
        obj.put("send_time", sendTime);
        obj.put("color", color);
        obj.put("type", type);
        return obj.toJSONString();
    }
}
