package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItemField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MiniBizDefTabItemResult extends MiniBizDefTabItem {


    @ApiModelProperty(value = "字段集合")
    private List<MiniBizDefTabItemField> children = new ArrayList<>();


    @ApiModelProperty(value = "嵌套集合")
    private List<MiniBizDefTabItemResult> childrenSlot = new ArrayList<>();


}
