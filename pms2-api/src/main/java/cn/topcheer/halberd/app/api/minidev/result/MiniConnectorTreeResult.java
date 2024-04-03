package cn.topcheer.halberd.app.api.minidev.result;


import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MiniConnectorTreeResult {


    @ApiModelProperty(value = "ID")
    private String id;


    @ApiModelProperty(value = "编码")
    private String code;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "子集合")
    private List<MiniConnector> children;


}
