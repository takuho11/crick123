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
 * 业务批注表
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
@Setter
@Getter
@TableName("mini_biz_audit_remark")
@ApiModel(value = "MiniBizAuditRemark对象", description = "业务批注表")
public class MiniBizAuditRemark extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "主表id")
    @TableField(value = "main_id")
    private String mainId;


    @ApiModelProperty(value = "子表id")
    @TableField(value = "table_id")
    private String tableId;


    @ApiModelProperty(value = "字段Code")
    @TableField(value = "field_code")
    private String fieldCode;


    @ApiModelProperty(value = "字段内容")
    @TableField(value = "field_content")
    private String fieldContent;


    @ApiModelProperty(value = "批注内容")
    @TableField(value = "audit_remark")
    private String auditRemark;


}
