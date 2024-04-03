package cn.topcheer.halberd.app.api.framework.entity.result.client;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BladeClientTreeResult {


    @ApiModelProperty(value = "编码")
    private String code;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "应用")
    private String client;


    @ApiModelProperty(value = "子集合")
    private List<BladeClientTreeResult> children;

    public BladeClientTreeResult(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
