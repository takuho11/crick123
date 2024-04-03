package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTab;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MiniSdBizDefTabResult extends MiniSdBizDefTab {


    @ApiModelProperty(value = "子项集合")
    private List<MiniSdBizDefTabItemResult> children = new ArrayList<>();


}
