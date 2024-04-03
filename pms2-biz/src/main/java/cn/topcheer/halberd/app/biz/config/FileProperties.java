package cn.topcheer.halberd.app.biz.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author zuowentao
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("file-change")
public class FileProperties {

    private int maxThreadPoolSize = 500;
    private int maxTagThreadSize = 10;
}
