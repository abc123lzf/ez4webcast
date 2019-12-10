package com.lzf.ez4webcast.danmaku;

import com.alibaba.fastjson.JSON;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.danmaku.model.Danmaku;
import com.lzf.ez4webcast.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/10 16:39
 * 弹幕控制器
 */
@ServerEndpoint("/api/damanku/ws/message/{room}")
@Component
@Log4j2
public class DanmakuController {

    private final ConcurrentMap<Integer, ConcurrentMap<String, Session>> clients = new ConcurrentHashMap<>();

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

        if(!clients.containsKey(rid)) {
            clients.putIfAbsent(rid, new ConcurrentHashMap<>());
        }

        ConcurrentMap<String, Session> roomMap = clients.get(rid);
        Map<String, Object> properties = session.getUserProperties();
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            String id = "tourist-" + UUID.randomUUID().toString().replace("-", "").substring(16);
            properties.put("uid", id);
            properties.put("tourist", true);
            roomMap.put(id, session);
        } else {
            String id = user.getUid().toString();
            properties.put("uid", id);
            properties.put("tourist", false);
            properties.put("user", user);
            Session sess = roomMap.remove(id);
            if(sess != null) {
                sess.close();
            }

            roomMap.put(id, session);
        }
    }

    @OnMessage
    public void onMessage(Session session, String text, @PathParam("room") String room) {
        Map<String, Object> properties = session.getUserProperties();
        if((Boolean) properties.get("tourist")) {
            return;  //游客禁止发送弹幕           b
        }

        Danmaku dmk = Danmaku.fromJSON(JSON.parseObject(text));
        if(dmk == null) {
            log.warn("Danmaku format is not right");
            return;
        }

        User user = (User) properties.get("user");
        if(user != null) {
            dmk.setUser(user);
        }

        String send = dmk.toJSONString();
        ConcurrentMap<String, Session> map = clients.get(Integer.parseInt(room));
        map.forEach((k, v) -> {
            if(v == session) {
                return;
            }
            v.getAsyncRemote().sendText(send);
        });
    }

    @OnError
    public void onError(Throwable throwable) {
        log.error("DanmankuController error", throwable);
    }

    @OnClose
    public void onClose(Session session, @PathParam("room") String room) {
        Map<String, Object> properties = session.getUserProperties();
        String id = (String) properties.get("uid");
        ConcurrentMap<String, Session> roomMap = clients.get(Integer.parseInt(room));
        if(roomMap != null) {
            roomMap.remove(id);
        }
    }
}
