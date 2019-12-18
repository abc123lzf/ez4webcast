package com.lzf.ez4webcast.room.vo;

import com.lzf.ez4webcast.room.model.Room;
import lombok.Data;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/18 13:10
 */
@Data
public class RoomBaseInfoVo {

    private Room info;

    private String upstreamURI;

    private String upstreamKey;

}
