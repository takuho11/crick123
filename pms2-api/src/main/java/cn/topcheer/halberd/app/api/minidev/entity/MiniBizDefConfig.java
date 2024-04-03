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
 * 业务定义配置表
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
@Setter
@Getter
@TableName("mini_biz_def_config")
@ApiModel(value = "MiniBizDefConfig对象", description = "业务定义配置表")
public class MiniBizDefConfig extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "业务定义id")
    @TableField(value = "biz_def_id")
    private Long bizDefId;


    @ApiModelProperty(value = "业务版本id")
    @TableField(value = "biz_version_id")
    private Long bizVersionId;


    @ApiModelProperty(value = "数据校验方法")
    @TableField(value = "data_validate_function", updateStrategy = FieldStrategy.IGNORED)
    private String dataValidateFunction;


    @ApiModelProperty(value = "自定义按钮")
    @TableField(value = "custom_button", updateStrategy = FieldStrategy.IGNORED)
    private String customButton;


    @ApiModelProperty(value = "额外参数")
    @TableField(value = "extra_param", updateStrategy = FieldStrategy.IGNORED)
    private String extraParam;


}
