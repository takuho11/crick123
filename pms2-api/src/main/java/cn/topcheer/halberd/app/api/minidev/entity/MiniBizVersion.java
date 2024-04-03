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
 * 业务版本表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@TableName("mini_biz_version")
@ApiModel(value = "MiniBizVersion对象", description = "业务版本表")
public class MiniBizVersion extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "版本名称")
    @TableField(value = "version_name")
    private String versionName;


    @ApiModelProperty(value = "备注")
    @TableField(value = "memo")
    private String memo;


    @ApiModelProperty(value = "业务类型id")
    @TableField(value = "biz_type_id")
    private Long bizTypeId;


    @ApiModelProperty(value = "业务定义id")
    @TableField(value = "biz_def_id")
    private Long bizDefId;


    @JsonIgnore
    @ApiModelProperty(value = "配置JSON")
    @TableField(value = "config_json")
    private String configJson;


}
