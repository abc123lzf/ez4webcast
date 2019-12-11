package com.lzf.ez4webcast.bbs.param;

import lombok.Data;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 17:05
 * 发布帖子请求的请求参数封装
 */
@Data
public class PublishPostParam {

    private Integer roomId;

    private String title;

    private String content;

}
