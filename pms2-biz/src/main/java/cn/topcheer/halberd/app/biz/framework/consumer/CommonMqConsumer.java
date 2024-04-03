package cn.topcheer.halberd.app.biz.framework.consumer;

import cn.topcheer.halberd.core.mq.CommonMqObject;
import cn.topcheer.halberd.core.mq.consumer.AbstractConsumer;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通用MQ消费
 */
@Slf4j
@Component
public class CommonMqConsumer extends AbstractConsumer<CommonMqObject> {
    @Override
    public Boolean process(CommonMqObject commonMqObject) {

        try {
            log.info("CommonMQConsumer:{} ThreadId:{}", JSON.toJSON(commonMqObject),Thread.currentThread().getId());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.error("CommonMQConsumer catch an InterruptedException:ThreadId:"+Thread.currentThread().getId(),e);
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}
