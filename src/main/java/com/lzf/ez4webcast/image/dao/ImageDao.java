package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.image.model.Image;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 8:17
 */
public interface ImageDao {

    int add(Image image);

    Image fromId(int id);

    boolean contains(int id);
}
