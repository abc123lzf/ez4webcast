package com.lzf.ez4webcast.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/11 10:54
 */
public class JSONFieldTest {
    public static void main(String[] args) {
        Person p = new Person();
        p.setDate(new Date());
        System.out.println(JSON.toJSONString(p));
    }
}

@Data
class Person {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

}
