package cn.topcheer.halberd.app.api.framework.entity.result.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
@Setter
public class BladeLogErrorResult {


    @ApiModelProperty(value = "日志数量")
    private Long count;


    @ApiModelProperty(value = "最后的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;


}
