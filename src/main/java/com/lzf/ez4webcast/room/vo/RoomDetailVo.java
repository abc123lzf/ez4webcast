package com.lzf.ez4webcast.room.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.auth.vo.UserVo;
import com.lzf.ez4webcast.room.model.Room;
import lombok.Data;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 15:54
 */
@Data
public class RoomDetailVo extends RoomVo {

    @JSONField
    private UserVo anchor;

    @JSONField
    private String flvUri;

    @JSONField
    private String danmakuUri;

    public RoomDetailVo() {
        super();
    }

    public RoomDetailVo(Room room) {
        super(room);
    }

}
