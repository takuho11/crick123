package cn.topcheer.halberd.app.api.framework.constant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 错误日志配置
 *
 * @author szs
 * @date 2023-08-31
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties("logerror")
public class LogErrorConfig {


    @ApiModelProperty(value = "开关")
    private boolean enable = false;


    @ApiModelProperty(value = "错误日志URL")
    private String logErrorUrl;


    @ApiModelProperty(value = "钉钉消息URL")
    private String dingTalkUrl;


}
