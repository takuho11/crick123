package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;

/**
 * 代码建模原型表实体类
 *
 * @author Chill
 */
@Data
@TableName("gen_model_prototype")
@EqualsAndHashCode()
@ApiModel(value = "ModelPrototype对象", description = "代码建模原型表")
public class ModelPrototype extends BaseUuidEntity implements Serializable {

    /**
     * 模型主键
     */
    @ApiModelProperty(value = "模型主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private String modelId;
    /**
     * 物理列名
     */
    @ApiModelProperty(value = "物理列名")
    private String jdbcName;
    /**
     * 物理类型
     */
    @ApiModelProperty(value = "物理类型")
    private String jdbcType;
    /**
     * 注释说明
     * 达梦数据库，关键字需要加双引号
     */
    @ApiModelProperty(value = "注释说明")
    @TableField("\"comment\"")
    private String comment;
    /**
     * 实体类型
     */
    @ApiModelProperty(value = "实体类型")
    private String propertyType;
    /**
     * 实体类型引用
     */
    @ApiModelProperty(value = "实体类型引用")
    private String propertyEntity;
    /**
     * 实体列名
     */
    @ApiModelProperty(value = "实体列名")
    private String propertyName;
    /**
     * 表单显示
     */
    @ApiModelProperty(value = "表单显示")
    @TableField(value = "is_form")
    private Boolean isForm;
    /**
     * 独占一行
     */
    @ApiModelProperty(value = "独占一行")
    @TableField(value = "is_row")
    private Boolean isRow;
    /**
     * 组件类型
     */
    @ApiModelProperty(value = "组件类型")
    private String componentType;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;
    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否必填")
    @TableField(value = "is_required")
    private Boolean isRequired;


    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否只读")
    @TableField(value = "is_readonly")
    private Boolean isReadonly;


    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否主键")
    @TableField(value = "is_primarykey")
    private Boolean isPrimarykey;


    /**
     * 验证类型
     */
    @ApiModelProperty(value = "验证类型")
    private String validateType;


    /**
     * 验证规则
     */
    @ApiModelProperty(value = "验证规则")
    @TableField(value = "validate_rule")
    private String validateRule;


    /**
     * 列表显示
     */
    @ApiModelProperty(value = "列表显示")
    @TableField(value = "is_list")
    private Boolean isList;
    /**
     * 查询配置
     */
    @ApiModelProperty(value = "查询配置")
    @TableField(value = "is_query")
    private Boolean isQuery;
    /**
     * 查询类型
     */
    @ApiModelProperty(value = "查询类型")
    private String queryType;


    /**
     * 字段在表单中的排序
     */
    @ApiModelProperty(value = "字段在表单中的排序")
    @TableField(value = "form_seq")
    private Integer formSeq;


    /**
     * 字段在列表中的排序
     */
    @ApiModelProperty(value = "字段在列表中的排序")
    @TableField(value = "list_seq")
    private Integer listSeq;


    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @TableField(value = "sort_number")
    private Integer sortNumber;


    /**
     * 字符长度
     */
    @ApiModelProperty(value = "字符长度")
    @TableField(value = "char_length")
    private Long charLength;


}
