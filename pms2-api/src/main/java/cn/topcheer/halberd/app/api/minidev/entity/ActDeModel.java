package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 流程设计模型
 * </p>
 *
 * @author szs
 * @since 2024-03-23
 */
@Setter
@Getter
@TableName("act_de_model")
@ApiModel(value = "ActDeModel对象", description = "流程设计模型表")
public class ActDeModel implements Serializable {


    /**
     * ID
     */
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_UUID
    )
    private String id;


    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @TableField("NAME")
    private String name;

    /**
     * 模型标识
     */
    @ApiModelProperty("模型标识")
    @TableField("MODEL_KEY")
    private String modelKey;

    /**
     * 表单标识
     */
    @ApiModelProperty("表单标识")
    @TableField("FORM_KEY")
    private String formKey;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    @TableField("CATEGORY_ID")
    private String categoryId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 评论
     */
    @ApiModelProperty("评论")
    @TableField("MODEL_COMMENT")
    private String modelComment;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("CREATED")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date created;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 最后更新时间
     */
    @ApiModelProperty("最后更新时间")
    @TableField("LAST_UPDATED")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date lastUpdated;

    /**
     * 最后更新人
     */
    @ApiModelProperty("最后更新人")
    @TableField("LAST_UPDATED_BY")
    private String lastUpdatedBy;

    /**
     * 版本
     */
    @ApiModelProperty("版本")
    @TableField("VERSION")
    private Integer version;

    /**
     * xml
     */
    @ApiModelProperty("xml")
    @TableField("XML_TEXT")
    private String xmlText;

    /**
     * flowable-ui编辑器json
     */
    @ApiModelProperty("flowable-ui编辑器json")
    @TableField("MODEL_EDITOR_JSON")
    private String modelEditorJson;

    /**
     * 缩略图
     */
    @ApiModelProperty("缩略图")
    @TableField("THUMBNAIL")
    private byte[] thumbnail;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    @TableField("MODEL_TYPE")
    private Integer modelType;

    /**
     * 租户id
     */
    @ApiModelProperty("租户id")
    @TableField("TENANT_ID")
    private String tenantId;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    @TableField("ICON")
    private String icon;

    /**
     * 业务表单id
     */
    @ApiModelProperty("业务表单id")
    @TableField("BUSINESS_FORM")
    private String businessForm;

    /**
     * 业务代码
     */
    @ApiModelProperty("业务代码")
    @TableField("BUSINESS_CODE")
    private String businessCode;

    /**
     * 业务表名
     */
    @ApiModelProperty("业务表名")
    @TableField("BUSINESS_TABLE")
    private String businessTable;

    /**
     * 业务表流程标记字段，流程节点变化时往该字段赋值
     */
    @ApiModelProperty("业务表流程标记字段，流程节点变化时往该字段赋值")
    @TableField("BUSINESS_FIELD")
    private String businessField;

    /**
     * 业务表流程标记字段默认值（流程最终未通过时，也赋这个值）
     */
    @ApiModelProperty("业务表流程标记字段默认值（流程最终未通过时，也赋这个值）")
    @TableField("BUSINESS_FIELD_VALUE")
    private String businessFieldValue;

    /**
     * 业务表流程标记字段默认值（流程最终未通过时，也赋这个值）
     */
    @ApiModelProperty("业务表流程标记字段默认值（流程最终未通过时，也赋这个值）")
    @TableField("BUSINESS_FIELD_END_VALUE")
    private String businessFieldEndValue;

    /**
     * 单位ID(PMS_ENTERPRISE)
     */
    @ApiModelProperty("单位ID(PMS_ENTERPRISE)")
    @TableField("ENTERPRISE_ID")
    private String enterpriseId;


}
