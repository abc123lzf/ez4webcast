package com.lzf.ez4webcast.danmaku;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 18:36
 */
public class DanmakuInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest req = (ServletServerHttpRequest) request;
            HttpSession session = req.getServletRequest().getSession();
            Object user = session.getAttribute("auth.user");
            if(user == null) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return false;
            }

            attributes.put("auth.user", user);
            return true;
        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {

    }
}
