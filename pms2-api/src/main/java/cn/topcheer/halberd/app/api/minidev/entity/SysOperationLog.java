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
 * 系统操作日志表
 * </p>
 *
 * @author szs
 * @since 2024-03-26
 */
@Setter
@Getter
@TableName("sys_operation_log")
@ApiModel(value = "SysOperationLog对象", description = "系统操作日志表")
public class SysOperationLog implements Serializable {


    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_UUID
    )
    private String id;


    @ApiModelProperty("业务类型")
    @TableField("TYPE")
    private String type;


    @ApiModelProperty("主表ID")
    @TableField("MAINID")
    private String mainid;


    @ApiModelProperty("操作结果")
    @TableField("OPERATION_RESULT")
    private String operationResult;


    @ApiModelProperty("操作意见")
    @TableField("OPERATION_COMMENT")
    private String operationComment;


    @ApiModelProperty("操作节点")
    @TableField("OPERATION_NODE")
    private String operationNode;


    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date createTime;


    @ApiModelProperty("创建人")
    @TableField("CREATE_USER")
    private String createUser;


    @ApiModelProperty("修改时间")
    @TableField("UPDATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updateTime;


    @ApiModelProperty("修改人")
    @TableField("UPDATE_USER")
    private String updateUser;


    @ApiModelProperty("是否删除（0未删除，1已删除）")
    @TableField("IS_DELETED")
    private Integer isDeleted;


}
