package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;

@Getter
@Setter
public class DbColumnDTO extends BaseUuidEntity implements Serializable {

    @ApiModelProperty(value = "列名")
    private String name;

    @ApiModelProperty(value = "中文名")
    private String chineseName;

    @ApiModelProperty(value = "类型")
    private String type;

    //目标字段长度
    private long destLength;
    //目标数据精度
    private int destNumericPrecision;
    //目标字端小数位数
    private int destDecimalPlaces;

    @ApiModelProperty(value = "是否为主键")
    private boolean primaryKey;

    @ApiModelProperty(value = "可否为空")
    private boolean nullable;

    @ApiModelProperty(value = "注释")
    private String comment;

    @ApiModelProperty(value = "其它")
    private String memo;

    @ApiModelProperty(value = "是否要B-Tree索引")
    private boolean keyBtree;
}
