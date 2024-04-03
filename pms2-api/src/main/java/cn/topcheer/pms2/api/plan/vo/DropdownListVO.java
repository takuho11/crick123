package cn.topcheer.pms2.api.plan.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 大小批次查看权限限制传参
 */
@Data
public class DropdownListVO {

    @ApiModelProperty("批次类型")
    private String type;

    @ApiModelProperty("业务类型")
    private String gridType;

}
