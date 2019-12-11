package com.lzf.ez4webcast.bbs.param;

import lombok.Data;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 23:38
 */
@Data
public class PublishReplyParam {

    private Integer floorId;

    private String content;

    private Integer replyObjectId;

}
