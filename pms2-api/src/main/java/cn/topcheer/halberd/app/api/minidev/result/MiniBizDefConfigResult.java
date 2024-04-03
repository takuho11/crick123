package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDef;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefConfig;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MiniBizDefConfigResult extends MiniBizDef {


    @ApiModelProperty(value = "分页集合")
    private List<MiniBizDefTabResult> children = new ArrayList<>();


    @ApiModelProperty(value = "连接器集合")
    private List<MiniConnector> connectorList = new ArrayList<>();


    @ApiModelProperty(value = "配置")
    private MiniBizDefConfig config;


}
