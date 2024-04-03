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
 * 模型类型表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@TableName("gen_model_type")
@ApiModel(value = "GenModelType对象", description = "模型类型表")
public class GenModelType extends BasicEntity implements Serializable {



    @ApiModelProperty(value = "模型类型编码")
    @TableField(value = "model_type_code")
    private String modelTypeCode;


    @ApiModelProperty(value = "模型类型名称")
    @TableField(value = "model_type_name")
    private String modelTypeName;


    @ApiModelProperty(value = "模型类型用途（1普通，2业务模型）")
    @TableField(value = "model_type_use")
    private Integer modelTypeUse;


    @ApiModelProperty(value = "备注")
    @TableField(value = "memo")
    private String memo;


    @ApiModelProperty(value = "单位ID(PMS_ENTERPRISE)")
    @TableField(value = "enterprise_id")
    private String enterpriseId;


}
