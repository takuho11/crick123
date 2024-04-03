package cn.topcheer.halberd.app.biz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("datacenter.datasource")
public class DataSourceConfig {
    private boolean throwException = false;
    private boolean globalReadonly = false;

    private Integer maxActive;

    private Integer initialSize;

    private Long maxWait;

    private Integer minIdle;

    private Long timeBetweenEvictionRunsMillis;

    private Long minEvictableIdleTimeMillis;

    private Boolean testWhileIdle;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;


}
