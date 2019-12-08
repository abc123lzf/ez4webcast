package com.lzf.ez4webcast.image.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:23
 */
@Data
public class Image {

    private Integer id;

    private String path;

    private Timestamp uploadTime;
}
