package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.image.model.Image;

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
    boolean write(Image image, byte[] bytes);

    /**
     *
     * @param image
     * @return
     */
    boolean read(Image image);

    /**
     *
     * @param image
     * @return
     */
    boolean delete(Image image);
}
