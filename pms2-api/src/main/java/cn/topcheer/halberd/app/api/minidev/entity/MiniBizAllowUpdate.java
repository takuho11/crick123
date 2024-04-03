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
 * 业务允许修改表
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
@Setter
@Getter
@TableName("mini_biz_allow_update")
@ApiModel(value = "MiniBizAllowUpdate对象", description = "业务允许修改表")
public class MiniBizAllowUpdate extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "版本名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "类型（1通用，2自定义）")
    @TableField(value = "type")
    private Integer type;


    @ApiModelProperty(value = "业务模板定义ID")
    @TableField(value = "mini_biz_def_id")
    private Long miniBizDefId;


    @ApiModelProperty(value = "业务模板版本ID")
    @TableField(value = "mini_biz_version_id")
    private Long miniBizVersionId;


    @ApiModelProperty(value = "主表id")
    @TableField(value = "main_id")
    private String mainId;


    @ApiModelProperty(value = "批次id")
    @TableField(value = "batch_id")
    private String batchId;


}
