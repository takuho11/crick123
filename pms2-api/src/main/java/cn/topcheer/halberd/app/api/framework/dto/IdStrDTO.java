package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class IdStrDTO {


    @Valid
    @NotBlank(message = "id必填")
    @ApiModelProperty(value = "id主键", required = true)
    private String id;


}
