package com.lzf.ez4webcast.image.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.image.dao.ImageApiException;
import com.lzf.ez4webcast.image.dao.ImageDao;
import com.lzf.ez4webcast.image.dao.ImageFileManager;
import com.lzf.ez4webcast.image.model.Image;
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
    public ServiceResponse<Integer> uploadImage(byte[] bytes, String contentType) {
        Image image = Image.builder().contentType(contentType).build();
        try {
            boolean succ = imageFileManager.write(image, bytes);
            if(!succ) {
                return response(1);
            }
        } catch (ImageApiException e) {
            imageFileManager.delete(image);
            return response(2);
        }
        int id = imageDao.add(image);
        return id == -1 ? response(1) : response(0, id);
    }

    @Override
    public ServiceResponse<String> readImage(int id, OutputStream os) {
        Image image = imageDao.fromId(id);
        if(image == null) {
            return response(1);
        }

        try {
            if (!imageFileManager.read(image, os)) {
                return response(2);
            }
        } catch (ImageApiException e) {
            return response(3);
        }

        return response(0, image.getContentType());
    }

    @Override
    public ServiceResponse<Boolean> containsImage(int id) {
        boolean ans = imageDao.contains(id);
        return ans ? response(0, true) : response(0, false);
    }
}
