package cn.topcheer.halberd.app.api.minidev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MiniConnectorTreeDTO {


    @Valid
    @NotBlank(message = "编码为空")
    @ApiModelProperty(value = "编码")
    private String code;


}
