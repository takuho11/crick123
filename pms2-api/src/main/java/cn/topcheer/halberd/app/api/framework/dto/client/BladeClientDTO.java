package cn.topcheer.halberd.app.api.framework.dto.client;

import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BladeClientDTO extends BladeClient {


    @ApiModelProperty(value = "是否仅仅查询自己创建（0否，1是）")
    private Integer isMyCreate;


}
