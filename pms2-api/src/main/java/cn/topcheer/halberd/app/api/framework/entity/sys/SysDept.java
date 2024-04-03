package cn.topcheer.halberd.app.api.framework.entity.sys;

import cn.topcheer.halberd.biz.modules.base.entity.HalberdDept;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;


@Data
@TableName("sf_dept")
@ApiModel(value = "Dept", description = "Dept对象")
public class SysDept extends BaseEntity implements HalberdDept {

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    /**
     * 父主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "父主键")
    private String parentId;

    /**
     * 机构全称
     */
    @ApiModelProperty(value = "机构全称")
    private String fullName;
    /**
     * 机构编码
     */
    @ApiModelProperty(value = "机构编码")
    private String deptCode;
    /**
     * 机构名
     */
    @ApiModelProperty(value = "机构名")
    private String deptName;

    /**
     * 祖级机构主键
     */
    @ApiModelProperty(value = "祖级机构主键")
    private String ancestors;

    /**
     * 机构类型
     * 10、管理部门（政府）
     * 20、企业
     */
    @ApiModelProperty(value = "机构类型")
    private Integer deptCategory;


    /**
     * 机构类型
     */
    @ApiModelProperty(value = "机构类型子类")
    private String deptType;

    /**
     * 级别 0、国家 1、省 2、市 3、县区 4、乡镇街道、5、村、社区 6、组、小区
     */
    @ApiModelProperty(value = "机构级别")
    private Integer deptLevel;


    /**
     * 区域编码
     */
    @ApiModelProperty(value = "区域编码")
    private String regionCode;


    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String regionName;

    /**
     * 区域编码 /code1/code2/格式
     */
    @ApiModelProperty(value = "区域编码 /code1/code2/格式")
    private String fullPath;


    /**
     * 归口部门ID
     */
    @ApiModelProperty(value = "归口部门ID")
    private String adminId;

    /**
     * 归口部门名称
     */
    @ApiModelProperty(value = "归口部门名称")
    private String adminName;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String contractPhone;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contractName;

    /**
     * 扩展JSON
     */
    @ApiModelProperty(value = "扩展JSON")
    private String extJson;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否已删除
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;

    /**
     * 创建部门
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建部门")
    private String createDept;

}
