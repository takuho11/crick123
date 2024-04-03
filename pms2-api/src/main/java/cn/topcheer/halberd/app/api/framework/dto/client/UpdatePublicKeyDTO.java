package cn.topcheer.halberd.app.api.framework.dto.client;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePublicKeyDTO extends IdDTO {


    @Valid
    @NotBlank(message = "公钥必填")
    @ApiModelProperty(value = "公钥", required = true)
    private String publicKey;


}
