package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDef;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MiniSdBizDefConfigResult extends MiniSdBizDef {


    @ApiModelProperty(value = "分页集合")
    private List<cn.topcheer.halberd.app.api.minidev.result.MiniSdBizDefTabResult> children = new ArrayList<>();


    @ApiModelProperty(value = "连接器集合")
    private List<MiniConnector> connectorList = new ArrayList<>();


    @ApiModelProperty(value = "配置")
    private MiniSdBizDefConfig config;


}
