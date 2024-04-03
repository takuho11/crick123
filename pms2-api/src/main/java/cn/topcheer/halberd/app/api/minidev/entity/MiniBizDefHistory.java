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
 * 业务定义历史表
 * </p>
 *
 * @author szs
 * @since 2024-02-19
 */
@Setter
@Getter
@TableName("mini_biz_def_history")
@ApiModel(value = "MiniBizDefHistory对象", description = "业务定义历史表")
public class MiniBizDefHistory extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "业务定义id")
    @TableField(value = "biz_def_id")
    private Long bizDefId;


    @ApiModelProperty(value = "业务版本id")
    @TableField(value = "biz_version_id")
    private Long bizVersionId;


    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;


}
