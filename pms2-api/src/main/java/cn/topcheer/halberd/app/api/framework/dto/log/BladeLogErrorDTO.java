package cn.topcheer.halberd.app.api.framework.dto.log;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
public class BladeLogErrorDTO {


    @Valid
    @NotNull(message = "开始时间为空")
    @ApiModelProperty(value = "开始时间（不包括）（yyyy-MM-dd HH:mm:ss）")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;


}
