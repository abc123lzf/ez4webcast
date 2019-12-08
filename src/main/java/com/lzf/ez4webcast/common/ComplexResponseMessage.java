package com.lzf.ez4webcast.common;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:00
 */
public class ComplexResponseMessage<T> extends ResponseMessage {

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
}
