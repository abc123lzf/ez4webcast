package com.lzf.ez4webcast.common;

import java.util.Objects;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:07
 * 服务响应体
 */
public class ServiceResponse<T> {

    private final int code;

    private final T data;

    public ServiceResponse(int code) {
        this.code = code;
        this.data = null;
    }

    public ServiceResponse(int code, T data) {
        this.code = code;
        this.data = Objects.requireNonNull(data);
    }

    public static <T> ServiceResponse<T> response(int code) {
        return new ServiceResponse<>(code);
    }

    public static <T> ServiceResponse<T> response(int code, T data) {
        return new ServiceResponse<>(code, data);
    }

    public boolean success() {
        return code == 0;
    }

    public int code() {
        return code;
    }

    public T data() {
        return data;
    }
}
