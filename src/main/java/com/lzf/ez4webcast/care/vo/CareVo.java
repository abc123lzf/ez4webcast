package com.lzf.ez4webcast.care.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lzf.ez4webcast.care.model.Care;
import com.lzf.ez4webcast.room.vo.RoomVo;
import lombok.Data;

import java.util.Date;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.13 19:41
 */
@Data
public class CareVo {

    @JSONField
    private Integer id;

    @JSONField
    private Integer roomId;

    @JSONField
    private RoomVo roomInfo;

    @JSONField
    private Date createTime;

    public CareVo() { }

    public CareVo(Care care) {
        this.id = care.getId();
        this.roomId = care.getRoomId();
        this.createTime = care.getCareTime();
    }
}
