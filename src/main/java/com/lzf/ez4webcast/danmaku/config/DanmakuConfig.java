package com.lzf.ez4webcast.danmaku.config;

import com.lzf.ez4webcast.danmaku.DanmakuHandler;
import com.lzf.ez4webcast.danmaku.DanmakuInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.10 11:27
 */
@Configuration
@EnableWebSocket
public class DanmakuConfig implements WebSocketConfigurer {

    @Autowired
    private DanmakuHandler danmakuHandler;

    @Autowired
    private DanmakuInterceptor danmakuInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(danmakuHandler, "/api/danmaku/ws")
                .addInterceptors(danmakuInterceptor)
                .setAllowedOrigins("*");
    }
}
