package cn.topcheer.halberd.app.api.minidev.entity;


import cn.topcheer.halberd.app.api.framework.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 业务分页表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@TableName("mini_biz_def_tab")
@ApiModel(value = "MiniBizDefTab对象", description = "业务分页表")
public class MiniBizDefTab extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "顺序")
    @TableField(value = "seq")
    private Integer seq;


    @ApiModelProperty(value = "分页名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "分页Prop")
    @TableField(value = "data_member")
    private String dataMember;


    @ApiModelProperty(value = "提示")
    @TableField(value = "tip_text")
    private String tipText;


    @ApiModelProperty(value = "是否含有说明（0否，1是）")
    @TableField(value = "if_nodes")
    private Boolean ifNodes;


    @ApiModelProperty(value = "说明")
    @TableField(value = "notes")
    private String notes;


    @ApiModelProperty(value = "是否显示（0否，1是）")
    @TableField(value = "is_show")
    private Boolean isShow;


    @ApiModelProperty(value = "切换事件")
    @TableField(value = "event_function")
    private String eventFunction;


    @ApiModelProperty(value = "业务版本id")
    @TableField(value = "biz_version_id")
    private Long bizVersionId;


    @ApiModelProperty(value = "业务定义id")
    @TableField(value = "biz_def_id")
    private Long bizDefId;


    @ApiModelProperty(value = "标准模板分页id")
    @TableField(value = "sd_biz_def_tab_id")
    private Long sdBizDefTabId;


    @ApiModelProperty(value = "是否显示动态方法")
    @TableField(value = "is_show_function", updateStrategy = FieldStrategy.IGNORED)
    private String isShowFunction;


    @ApiModelProperty(value = "分页事件，各种事件JOSN")
    @TableField(value = "events", updateStrategy = FieldStrategy.IGNORED)
    private String events;


    @ApiModelProperty(value = "复制业务定义分页id")
    @TableField(exist = false)
    private Long copyBizDefTabId;


}
