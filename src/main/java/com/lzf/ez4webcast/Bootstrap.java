package com.lzf.ez4webcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 14:57
 */
@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableTransactionManagement
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
