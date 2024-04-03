package cn.topcheer.pms2.api.plan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 事项类别
 * @author whq
 * @date 2023-11-09
 */
@Data
@Entity
@Table(name = "PMS_PLANCATEGORY")
public class PmsPlancategory {
    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类")
    private String category;

    @ApiModelProperty("")
    private String entrance;

    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("默认业务类型")
    private String defaultType;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除")
    private Integer isDelete;

    /**
     * 归口单位
     */
    @ApiModelProperty("归口单位")
    @TableField("ORGANIZE_ID")
    @Column(columnDefinition = "ORGANIZE_ID")
    private String organizeId;

    /**
     *  事项所属部门
     */
    @ApiModelProperty("事项所属部门")
    @TableField("ITEM_ORGAN_CODE")
    @Column(columnDefinition = "ITEM_ORGAN_CODE")
    private String itemOrganCode;

    /**
     * 访问权限
     * @return
     */
    @ApiModelProperty("访问权限")
    @TableField("ROLE_IDS")
    @Column(columnDefinition = "ROLE_IDS")
    private String roleIds;

}
