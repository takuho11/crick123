package cn.topcheer.halberd.app.api.minidev.entity;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* 业务分类表
* </p>
*
* @author szs
* @since 2023-08-09
*/
@Setter
@Getter
@TableName("mini_biz_type")
@ApiModel(value="MiniBizType对象", description="业务分类表")
    public class MiniBizType extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "业务分类编码")
    @TableField(value = "biz_type_code")
    private String bizTypeCode;


    @ApiModelProperty(value = "业务分类名称")
    @TableField(value = "biz_type_name")
    private String bizTypeName;


    @ApiModelProperty(value = "备注")
    @TableField(value = "memo")
    private String memo;


}
