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
 * 代码模型表实体类
 *
 * @author Chill
 */
@Data
@TableName("gen_model")
@EqualsAndHashCode()
@ApiModel(value = "Model对象", description = "代码模型表")
public class Model extends BaseUuidEntity implements Serializable {


    /**
     * 数据源主键
     */
    @ApiModelProperty(value = "数据源主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private String datasourceId;

    /**
     * 模式（数据库）
     */
    @ApiModelProperty(value = "模式（数据库）")
    @JsonSerialize(using = ToStringSerializer.class)
    private String schemaName;

    /**
     * 模型名称
     */
    @ApiModelProperty(value = "模型名称")
    private String modelName;
    /**
     * 模型编号
     */
    @ApiModelProperty(value = "模型编号")
    private String modelCode;
    /**
     * 物理表名
     */
    @ApiModelProperty(value = "物理表名")
    private String modelTable;
    /**
     * 模型类名
     */
    @ApiModelProperty(value = "模型类名")
    private String modelClass;
    /**
     * 模型备注
     */
    @ApiModelProperty(value = "模型备注")
    private String modelRemark;


    /**
     * api类型
     */
    @ApiModelProperty(value = "api类型：0-无，1-自动生成(存在则跳过)，2-自动生成（存在则覆盖），3-自定义。自定义就是只是录入各个api地址，但是不会去生成")
    @TableField(value = "api_type")
    private Integer apiType;

    /**
     * insertapi 路径
     */
    @ApiModelProperty(value = "insertApi 路径")
    @TableField(value = "insert_api")
    private String insertApi;

    /**
     * deleteApi 路径
     */
    @ApiModelProperty(value = "deleteApi 路径")
    @TableField(value = "delete_api")
    private String deleteApi;


    /**
     * updateApi 路径
     */
    @ApiModelProperty(value = "updateApi 路径")
    @TableField(value = "update_api")
    private String updateApi;


    /**
     * selectApi 路径
     */
    @ApiModelProperty(value = "selectApi 路径")
    @TableField(value = "select_api")
    private String selectApi;

    /**
     * 状态[1:正常]
     */
    @ApiModelProperty(value = "业务状态")
    private Integer status;


    /**
     * 模型类型id
     */
    @ApiModelProperty(value = "模型类型id")
    private Long modelTypeId;


    @ApiModelProperty(value = "单位ID(PMS_ENTERPRISE)")
    @TableField(value = "enterprise_id")
    private String enterpriseId;


}
