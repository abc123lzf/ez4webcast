package com.lzf.ez4webcast.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 21:03
 */
@Configuration
@PropertySource(value = "image.properties")
public class ImageConfiguration {

    @Value("${ez4webcast.image.path}")
    private String path;

    @Value("${ez4webcast.image.maxsize:2000000}")
    private Integer sizeLimit;

    public String getPath() {
        return path;
    }

    public Integer getSizeLimit() {
        return sizeLimit;
    }
}
