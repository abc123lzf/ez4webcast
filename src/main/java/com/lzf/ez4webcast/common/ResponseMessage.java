package com.lzf.ez4webcast.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:58
 * 附加消息JSON消息体
 */
public class ResponseMessage implements Serializable {

    @JSONField
    private final int code;

    @JSONField
    private final String message;

    @JSONField
    private final long time = System.currentTimeMillis();

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseMessage message(int code, String message) {
        return new ResponseMessage(code, message);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public String toJSONString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\",\"time\": %d}", code, message, time);
    }
}
