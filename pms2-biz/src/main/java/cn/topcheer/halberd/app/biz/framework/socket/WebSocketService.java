package cn.topcheer.halberd.app.biz.framework.socket;

import cn.topcheer.halberd.core.socket.HalberdSocketService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zuowentao
 */
@Service
public class WebSocketService implements InitializingBean {

    @Resource
    HalberdSocketService socketService;

    @Resource
    SocketListener socketListener;


    @Override
    public void afterPropertiesSet() throws Exception {
        socketService.addListerner(socketListener);
    }
}
