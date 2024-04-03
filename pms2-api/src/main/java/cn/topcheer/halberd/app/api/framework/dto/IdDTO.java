package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IdDTO {


    @Valid
    @NotNull(message = "id必填")
    @ApiModelProperty(value = "id主键", required = true)
    private Long id;


}
