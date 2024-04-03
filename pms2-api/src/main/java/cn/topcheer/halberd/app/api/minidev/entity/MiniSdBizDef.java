package cn.topcheer.halberd.app.api.minidev.entity;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 标准业务模板表
 * </p>
 *
 * @author szs
 * @since 2023-08-21
 */
@Setter
@Getter
@TableName("mini_sd_biz_def")
@ApiModel(value = "MiniSdBizDef对象", description = "标准业务模板表")
public class MiniSdBizDef extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "编码")
    @TableField(value = "code")
    private String code;


    @ApiModelProperty(value = "业务分类id")
    @TableField(value = "biz_type_id")
    private Long bizTypeId;


    @ApiModelProperty(value = "状态（0禁用，1启用）")
    @TableField(value = "status")
    private Integer status;


    @JsonIgnore
    @ApiModelProperty(value = "配置JSON")
    @TableField(value = "config_json")
    private String configJson;


    @ApiModelProperty(value = "单位ID(PMS_ENTERPRISE)")
    @TableField(value = "enterprise_id")
    private String enterpriseId;


}
