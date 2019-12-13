package com.lzf.ez4webcast.care.dao;

import com.lzf.ez4webcast.care.model.Care;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:22
 */
public interface CareDao {

    void add(Care care);

    boolean delete(int id);

    Care fromId(int id);

    List<Care> fromUID(int uid);
}
