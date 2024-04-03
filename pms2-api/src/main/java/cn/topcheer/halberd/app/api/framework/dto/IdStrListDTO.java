package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class IdStrListDTO {


    @Valid
    @NotNull(message = "ids必填")
    @ApiModelProperty(value = "ids主键", required = true)
    private List<String> ids;


}
