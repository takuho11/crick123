package cn.topcheer.halberd.app.biz.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("optchange")
@Getter
@Setter
public class OptChangeKafkaConfig {


    private Boolean enable=true;

    private String kafkaTopicName;

    private String brokerUrl;

    private String groupId;

    private String keyDeserializer;

    private String valueDeserializer;


}
