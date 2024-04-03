package cn.topcheer.halberd.app.api.framework.entity.api;

import cn.topcheer.halberd.app.api.framework.enumerate.CompareType;
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
@TableName("am_api_request")
@EqualsAndHashCode()
@ApiModel(value = "AmApiRequest对象", description = "")
public class AmApiRequest extends BaseUuidEntity implements Serializable {

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
     * 值来源
     */
    @ApiModelProperty(value = "值来源")
    @TableField(value = "source")
    private String source;

    /**
     * 比较形式
     */
    @ApiModelProperty(value = "比较形式")
    @TableField(value = "compared")
    private CompareType compared;

    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否必填")
    @TableField(value = "filled")
    private Boolean filled;
    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否判断")
    @TableField(value = "whereby")
    private Boolean whereby;

    /**
     * 字段描述
     */
    @ApiModelProperty(value = "字段描述")
    @TableField(value = "memo")
    private String memo;

    /**
     * 查询是否返回
     */
    @ApiModelProperty(value = "查询是否返回")
    @TableField(value = "returned")
    private Boolean returned;
    /**
     * 是否更新
     */
    @ApiModelProperty(value = "是否更新")
    @TableField(value = "updated")
    private Boolean updated;

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


    /**
     * 临时存放sql参数值
     */
    private transient Object paramValue;

    private transient Object primaryKey;
}
