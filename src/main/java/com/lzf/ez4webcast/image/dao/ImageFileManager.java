package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.image.model.Image;

import java.io.OutputStream;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 8:29
 */
public interface ImageFileManager {

    /**
     * 写图像文件
     * @param image 图片
     * @param bytes 图像字节
     * @return 是否写入成功
     */
    boolean write(Image image, byte[] bytes) throws ImageApiException;

    /**
     * 读取图像文件
     * @param image 图片
     * @param os 目标输出流
     * @return 是否读取成功
     */
    boolean read(Image image, OutputStream os) throws ImageApiException;

    /**
     * 删除图像文件
     * @param image 图片
     * @return 是否删除成功
     */
    boolean delete(Image image);
}
