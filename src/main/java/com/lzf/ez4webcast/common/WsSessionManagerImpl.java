package com.lzf.ez4webcast.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 18:24
 */
@Component
@Scope("prototype")
@Log4j2
class WsSessionManagerImpl implements WsSessionManager {

    private final Map<String, WebSocketSession> map = new ConcurrentHashMap<>(128);

    @Override
    public WebSocketSession get(String key) {
        return map.get(key);
    }

    @Override
    public void add(String key, WebSocketSession session) {
        map.put(key, session);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public void removeAndClose(String key) {
        WebSocketSession session = map.remove(key);
        if(session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.warn("Close WsSession occur a exception", e);
            }
        }
    }

    @Override
    public boolean contanins(String key) {
        return map.containsKey(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void forEach(Consumer<WebSocketSession> consumer) {
        map.values().forEach(consumer);
    }

    @Override
    protected void finalize() throws Throwable {
        for (WebSocketSession session : map.values()) {
            session.close();
        }
        map.clear();
    }
}
