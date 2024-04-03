package cn.topcheer.halberd.app.biz.framework.socket;

import cn.topcheer.halberd.core.socket.ISocketListerner;
import cn.topcheer.halberd.core.socket.SocketConnection;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author zuowentao
 */
@Component
@Slf4j
public class SocketListener implements ISocketListerner {



    @Override
    public void onConnected(SocketConnection socketConnection, WebSocketSession webSocketSession) {

    }

    @SneakyThrows
    @Override
    public void onMessage(SocketConnection socketConnection, String s) {
       }

    @Override
    public void onClose(SocketConnection socketConnection) {
    }

    @Override
    public void onError(int i, Object o) {

    }
}
