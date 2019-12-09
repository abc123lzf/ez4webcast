package com.lzf.ez4webcast.danmaku;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzf.ez4webcast.auth.model.User;
import com.lzf.ez4webcast.common.WsSessionManager;
import com.lzf.ez4webcast.danmaku.model.Danmaku;
import com.lzf.ez4webcast.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 18:09
 */
@Component
public class DanmakuHandler extends TextWebSocketHandler {

    private final WsSessionManager sessionManager;

    @Autowired
    public DanmakuHandler(WsSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return;
        }

        int uid = user.getUid();
        String uidStr = Integer.toString(uid);
        if(sessionManager.contanins(uidStr)) {
            sessionManager.removeAndClose(uidStr);
        }

        sessionManager.add(uidStr, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return;
        }

        JSONObject obj = JSON.parseObject(message.getPayload());
        Danmaku dmk = Danmaku.fromJSON(obj);
        if(dmk == null) {
            return;
        }


        dmk.setUser(user);

        String broad = dmk.toJSONString();

        sessionManager.forEach(s -> {  //广播弹幕
            if(s == session) {
                return;
            }

            try {
                session.sendMessage(new TextMessage(broad));
            } catch (IOException ignore) { }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = UserUtils.contextPrincipal();
        if(user == null) {
            return;
        }

        sessionManager.remove(Integer.toString(user.getUid()));
    }
}
