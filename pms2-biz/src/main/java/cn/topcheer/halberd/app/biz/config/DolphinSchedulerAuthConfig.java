package cn.topcheer.halberd.app.biz.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dolphinscheduler")
@Getter
@Setter
//@RefreshScope
public class DolphinSchedulerAuthConfig {

    private Boolean enable=true;

    private String adminToken;

    private String backendUrl;

    private String loginByKeyUrl;

    private String location;

}
