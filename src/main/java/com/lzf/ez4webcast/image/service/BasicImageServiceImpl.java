package com.lzf.ez4webcast.image.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.image.dao.ImageDao;
import com.lzf.ez4webcast.image.dao.ImageFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

import static com.lzf.ez4webcast.common.ServiceResponse.response;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:54
 */
@Service
class BasicImageServiceImpl implements BasicImageService {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageFileManager imageFileManager;

    @Override
    public ServiceResponse<Void> uploadImage(byte[] bytes, String contentType) {

        return null;
    }

    @Override
    public ServiceResponse<Void> writeImage(int id, OutputStream os) {
        return null;
    }
}