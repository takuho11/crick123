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
 * 业务允许修改字段表
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
@Setter
@Getter
@TableName("mini_biz_allow_update_field")
@ApiModel(value = "MiniBizAllowUpdateField对象", description = "业务允许修改字段表")
public class MiniBizAllowUpdateField extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "业务允许修改ID")
    @TableField(value = "mini_biz_allow_update_id")
    private Long miniBizAllowUpdateId;


    @ApiModelProperty(value = "子项编码")
    @TableField(value = "item_code")
    private String itemCode;


    @ApiModelProperty(value = "字段类型（1字段，2行，3子项）")
    @TableField(value = "field_type")
    private Integer fieldType;


    @ApiModelProperty(value = "字段Code")
    @TableField(value = "field_code")
    private String fieldCode;


    @ApiModelProperty(value = "数据下标，第几条数据")
    @TableField(value = "row_num")
    private Integer rowNum;


}
