package com.lzf.ez4webcast.common;

import com.alibaba.fastjson.JSONAware;

import java.io.Serializable;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:58
 * 附加消息JSON消息体
 */
public class ResponseMessage implements JSONAware, Serializable {

    private final int code;

    private final String message;

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

    public String toJSONString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\"}", code, message);
    }
}
