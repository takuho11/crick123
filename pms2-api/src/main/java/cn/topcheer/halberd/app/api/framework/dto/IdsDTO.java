package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class IdsDTO {


    @Valid
    @NotBlank(message = "Ids必填")
    @ApiModelProperty(value = "Ids，逗号隔开", required = true)
    private String ids;


}
