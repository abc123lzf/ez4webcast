package com.lzf.ez4webcast.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:00
 */
public class ComplexResponseMessage<T> extends ResponseMessage {

    @JSONField
    private final T data;

    public ComplexResponseMessage(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> ComplexResponseMessage<T> message(int code, String message, T data) {
        return new ComplexResponseMessage<>(code, message, data);
    }

    public T data() {
        return data;
    }

    public T getData() {
        return data;
    }

    public String toJSONString() {
        if(data == null) {
            return super.toJSONString();
        }

        JSONObject object = new JSONObject(4);
        object.put("code", code());
        object.put("msg", message());
        object.put("data", data);
        return object.toJSONString();
    }
}
