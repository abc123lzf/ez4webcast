package com.lzf.ez4webcast.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 16:06
 */
@Configuration
public class TaskSchedulerConfig {

    private final int threadNumber;

    public TaskSchedulerConfig() {
        int cpus = Runtime.getRuntime().availableProcessors();
        if(cpus <= 4) {
            threadNumber = 4;
        } else {
            threadNumber = Math.min(cpus, 10);
        }
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(threadNumber);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
