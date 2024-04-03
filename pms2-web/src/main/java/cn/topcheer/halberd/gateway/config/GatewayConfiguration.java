package cn.topcheer.halberd.gateway.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"cn.topcheer.halberd.gateway.**"})
@MapperScan({"cn.topcheer.halberd.gateway.**.mapper.**"})
public class GatewayConfiguration {
    
}
