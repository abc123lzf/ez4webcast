package com.lzf.ez4webcast.image.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:54
 */
@Service
class BasicImageServiceImpl implements BasicImageService {

    @Override
    public ServiceResponse<Void> uploadImage(byte[] bytes, String contentType) {

        return null;
    }

    @Override
    public ServiceResponse<Void> writeImage(int id, OutputStream os) {
        return null;
    }
}
