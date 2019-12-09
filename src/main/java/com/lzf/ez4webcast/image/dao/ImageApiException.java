package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.image.model.Image;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/9 16:03
 */
public class ImageApiException extends RuntimeException {

    ImageApiException(String message, Throwable cause) {
        super(message, cause, false, false);
    }

    ImageApiException(String message, Image image, Throwable cause) {
        super(message, cause, false, false);
    }

    ImageApiException(Image image, Throwable cause) {
        super("", cause, false, false);
    }
}
