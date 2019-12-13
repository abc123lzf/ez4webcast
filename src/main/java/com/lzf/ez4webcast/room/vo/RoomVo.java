package com.lzf.ez4webcast.room.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.room.model.Room;
import lombok.Data;
import java.util.Date;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 15:50
 */
@Data
public class RoomVo {

    @JSONField
    private Integer id;

    @JSONField
    private String title;

    @JSONField
    private Integer titleImage;

    @JSONField
    private Date lastLiveTime;

    @JSONField
    private Date createTime;

    public RoomVo() { }

    public RoomVo(Room room) {
        this.id = room.getId();
        this.title = room.getTitle();
        this.titleImage = room.getTitleImage();
        this.lastLiveTime = room.getLastLiveTime();
        this.createTime = room.getCreateTime();
    }

}
