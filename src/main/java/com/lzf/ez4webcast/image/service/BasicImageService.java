package com.lzf.ez4webcast.image.service;

import com.lzf.ez4webcast.common.ServiceResponse;
import org.springframework.http.RequestEntity;

import java.io.OutputStream;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 20:54
 */
public interface BasicImageService {


    ServiceResponse<Integer> uploadImage(byte[] bytes, String contentType);


    ServiceResponse<String> readImage(int id, OutputStream os);


    ServiceResponse<Boolean> containsImage(int id);

}
