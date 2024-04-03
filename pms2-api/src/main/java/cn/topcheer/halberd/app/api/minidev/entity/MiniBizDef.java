package cn.topcheer.halberd.app.api.minidev.entity;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 业务定义表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@TableName("mini_biz_def")
@ApiModel(value = "MiniBizDef对象", description = "业务定义表")
public class MiniBizDef extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "默认版本id")
    @TableField(value = "default_version_id", updateStrategy = FieldStrategy.IGNORED)
    private Long defaultVersionId;


    @ApiModelProperty(value = "默认版本名称")
    @TableField(value = "default_version_name", updateStrategy = FieldStrategy.IGNORED)
    private String defaultVersionName;


    @ApiModelProperty(value = "备注")
    @TableField(value = "memo")
    private String memo;


    @ApiModelProperty(value = "标记组件类型")
    @TableField(value = "edit_component_type")
    private String editComponentType;


    @ApiModelProperty(value = "查看 组件类型")
    @TableField(value = "view_component_type")
    private String viewComponentType;


    @ApiModelProperty(value = "业务分类id")
    @TableField(value = "biz_type_id")
    private Long bizTypeId;


    @ApiModelProperty(value = "单位ID(PMS_ENTERPRISE)")
    @TableField(value = "enterprise_id")
    private String enterpriseId;


}
