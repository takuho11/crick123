package cn.topcheer.halberd.app.api.framework.entity.api;

import cn.topcheer.halberd.app.api.framework.enumerate.FieldType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author templateTool
 */
@Data
@TableName("am_api_response")
@EqualsAndHashCode()
@ApiModel(value = "AmApiResponse对象", description = "")
public class AmApiResponse extends BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称
     */
    @ApiModelProperty(value = "字段名称")
    @TableField(value = "name")
    private String name;

    /**
     * 字段类型
     */
    @ApiModelProperty(value = "字段类型")
    @TableField(value = "type")
    private FieldType type;

    /**
     * 示例值
     */
    @ApiModelProperty(value = "示例值")
    @TableField(value = "sample")
    private String sample;

    /**
     * 绑定字段
     */
    @ApiModelProperty(value = "绑定字段")
    @TableField(value = "bind_field")
    private String bindField;

    /**
     * 字段描述
     */
    @ApiModelProperty(value = "字段描述")
    @TableField(value = "memo")
    private String memo;

    /**
     * apiID
     */
    @ApiModelProperty(value = "apiID")
    @TableField(value = "api_id")
    private String apiId;


    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @TableField(value = "sort_number")
    private Integer sortNumber;


}
