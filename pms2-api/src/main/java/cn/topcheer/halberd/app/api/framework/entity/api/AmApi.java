package cn.topcheer.halberd.app.api.framework.entity.api;


import cn.topcheer.halberd.app.api.framework.enumerate.DataSourceType;
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
@TableName("am_api")
@EqualsAndHashCode()
@ApiModel(value = "AmApi对象", description = "")
public class AmApi extends BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * api名称
     */
    @ApiModelProperty(value = "api名称")
    @TableField(value = "name")
    private String name;

    /**
     * api类型
     */
    @ApiModelProperty(value = "api类型")
    @TableField(value = "type")
//    private ApiType type;
    private String type;

    /**
     * api路径
     */
    @ApiModelProperty(value = "api路径")
    @TableField(value = "path")
    private String path;

    /**
     * 脚本
     */
    @ApiModelProperty(value = "脚本")
    @TableField(value = "script", typeHandler = org.apache.ibatis.type.ClobTypeHandler.class)
    private String script;

    /**
     * 开发环境发布情况
     */
//	@ApiModelProperty(value = "开发环境发布情况")
//	@TableField(value = "dev_state")
//    private ApiState devState;
//
//	/**
//	 * 生产环境发布情况
//	 */
//	@ApiModelProperty(value = "生产环境发布情况")
//	@TableField(value = "prod_state")
//    private ApiState prodState;

    /**
     * 数据源类型
     */
    @ApiModelProperty(value = "数据源类型")
    @TableField(value = "source_type")
    private DataSourceType sourceType;

    /**
     * 数据源ID
     */
    @ApiModelProperty(value = "数据源ID")
    @TableField(value = "datasource_id")
    private String datasourceId;

    /**
     * 方案用户
     */
    @ApiModelProperty(value = "方案用户")
    @TableField(value = "schema_name")
    private String schemaName;

    /**
     * 数据库表名称
     */
    @ApiModelProperty(value = "数据库表名称")
    @TableField(value = "table_name")
    private String tableName;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    @TableField(value = "service_id")
    private Long serviceId;

    /**
     * 分页标志
     */
    @ApiModelProperty(value = "分页标志")
    @TableField(value = "pagination_bit")
    private Boolean paginationBit;

    /**
     * 授权标志
     */
    @ApiModelProperty(value = "授权")
    @TableField(value = "authorize")
    private Boolean authorize;

    /**
     * 代理地址
     */
    @ApiModelProperty(value = "代理地址")
    @TableField(value = "target_url")
    private String targetUrl;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    @TableField(value = "method")
    private String method;


    /**
     * 父ApiID
     */
    @ApiModelProperty(value = "父ApiID")
    @TableField(value = "api_id")
    private String apiId;


    @ApiModelProperty(value = "审批状态（0待审批，1审批中，2审批通过，3审批驳回）")
    @TableField(value = "approve_status")
    private Integer approveStatus;


    @ApiModelProperty(value = "发布状态（0未发布，1已发布）")
    @TableField(value = "status")
    private Integer status;


    @ApiModelProperty(value = "数据资源id")
    @TableField(value = "data_resource_id")
    private String dataResourceId;


    @ApiModelProperty(value = "是否访问鉴权（0否，1是）")
    @TableField(value = "is_access_authorize")
    private Boolean isAccessAuthorize;


    @ApiModelProperty(value = "是否公开（0否，1是）")
    @TableField(value = "is_public")
    private Boolean isPublic;


}
