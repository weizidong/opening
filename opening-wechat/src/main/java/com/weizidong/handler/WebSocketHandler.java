package com.weizidong.handler;

import org.restful.api.websocket.BaseWebSocket;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author 魏自东
 * @date 2018/4/6 0006 14:28
 */
@ServerEndpoint(value = "/websocket/{userId}", configurator = SpringConfigurator.class)
@Component
public class WebSocketHandler extends BaseWebSocket {
    @Override
    public void push(String userId, String socketMsg, Session session) {

    }
}
