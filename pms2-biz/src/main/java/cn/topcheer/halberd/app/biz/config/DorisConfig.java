package cn.topcheer.halberd.app.biz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("doris")
@Getter
@Setter
public class DorisConfig {

    private String replicationNum;
}
