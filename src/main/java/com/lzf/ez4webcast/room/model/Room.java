package com.lzf.ez4webcast.room.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:21
 */
@Data
public class Room {

    private Integer id;

    private Integer uid;

    private String title;

    private Integer titleImage;

    private Timestamp lastLiveTime;

    private Timestamp createTime;

}
