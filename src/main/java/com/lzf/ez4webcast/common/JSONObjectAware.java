package com.lzf.ez4webcast.common;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:24
 */
public interface JSONObjectAware extends JSONAware {

    JSONObject toJSONObject();

    @Override
    default String toJSONString() {
        return toJSONObject().toJSONString();
    }
}
