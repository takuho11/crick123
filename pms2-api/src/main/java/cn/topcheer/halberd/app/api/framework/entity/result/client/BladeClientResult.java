package cn.topcheer.halberd.app.api.framework.entity.result.client;


import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BladeClientResult extends BladeClient {


    @ApiModelProperty(value = "部门id")
    private String departmentId;


}
