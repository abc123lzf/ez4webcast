package com.lzf.ez4webcast.danmaku;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 16:39
 * 弹幕控制器
 */
@ServerEndpoint(value = "/api/danmaku/ws/message/{room}")
@Component
@Log4j2
public class DanmakuController {

    private final ConcurrentMap<String, ConcurrentSkipListSet<Session>> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("room") String room) throws IOException {
        int rid;
        try {
            rid = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            log.info("Room ID: {} is illegal", room);
            session.close();
            return;
        }

        /*ServiceResponse<Boolean> res = basicRoomService.containsRoom(rid);
        if(res.success() && !res.data()) {
            session.close();
            return;
        } else if(!res.success()) {
            log.error("Can not found Room id");
            session.close();
            return;
        }*/

        String uri = session.getRequestURI().getPath();
        if(!clients.containsKey(uri)) {
            clients.putIfAbsent(uri, new ConcurrentSkipListSet<>(Comparator.comparing(Session::getId)));
        }

        ConcurrentSkipListSet<Session> set = clients.get(uri);
        set.add(session);

    }

    @OnMessage
    public void onMessage(Session session, String text) {
        if(text == null) {
            return;
        }
        String uri = session.getRequestURI().getPath();
        ConcurrentSkipListSet<Session> set = clients.get(uri);
        set.forEach(v -> {
            if(v == session) {
                return;
            }
            v.getAsyncRemote().sendText(text);
        });
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        log.error("DanmankuController error", throwable);
        session.close();
        onClose(session);
    }

    @OnClose
    public void onClose(Session session) {
        session.getRequestURI();
        ConcurrentSkipListSet<Session> roomSet = clients.get(session.getRequestURI().getPath());
        if(roomSet != null) {
            roomSet.remove(session);
        }
    }
}
