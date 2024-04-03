package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MiniSdBizDefTabItemResult extends MiniSdBizDefTabItem {


    @ApiModelProperty(value = "字段集合")
    private List<MiniSdBizDefTabItemField> children = new ArrayList<>();


    @ApiModelProperty(value = "嵌套集合")
    private List<MiniSdBizDefTabItemResult> childrenSlot = new ArrayList<>();


}
