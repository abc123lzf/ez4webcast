package com.lzf.ez4webcast.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import sun.misc.BASE64Encoder;

import java.util.Date;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:54
 */
public class JSONFieldTest {
    public static void main(String[] args) {
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(encoder.encode("582339c2-21a8-4670-a106-b7e2bd04325b".getBytes()));
    }
}
