package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTab;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MiniBizDefTabResult extends MiniBizDefTab {


    @ApiModelProperty(value = "子项集合")
    private List<MiniBizDefTabItemResult> children = new ArrayList<>();


}
