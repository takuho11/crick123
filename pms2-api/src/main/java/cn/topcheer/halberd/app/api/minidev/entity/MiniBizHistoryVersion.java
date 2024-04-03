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
* 业务历史版本表
* </p>
*
* @author szs
* @since 2023-11-16
*/
@Setter
@Getter
@TableName("mini_biz_history_version")
@ApiModel(value="MiniBizHistoryVersion对象", description="业务历史版本表")
    public class MiniBizHistoryVersion extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "主表id")
    @TableField(value = "main_id")
    private String mainId;


    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;


}
