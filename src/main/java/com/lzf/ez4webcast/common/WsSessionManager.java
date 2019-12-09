package com.lzf.ez4webcast.common;

import org.springframework.web.socket.WebSocketSession;

import java.util.function.Consumer;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 18:21
 * WebSocket会话管理器
 */
public interface WsSessionManager {

    WebSocketSession get(String key);

    void add(String key, WebSocketSession session);

    void remove(String key);

    boolean contanins(String key);

    void removeAndClose(String key);

    int size();

    void forEach(Consumer<WebSocketSession> consumer);

}
