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
 * 业务数据跟允许修改中间表
 * </p>
 *
 * @author szs
 * @since 2023-12-07
 */
@Setter
@Getter
@TableName("mini_biz_data_in_allow_update")
@ApiModel(value = "MiniBizDataInAllowUpdate对象", description = "业务数据跟允许修改中间表")
public class MiniBizDataInAllowUpdate extends BasicEntity implements Serializable {


    @ApiModelProperty(value = "业务允许修改ID")
    @TableField(value = "mini_biz_allow_update_id")
    private Long miniBizAllowUpdateId;


    @ApiModelProperty(value = "主表id")
    @TableField(value = "main_id")
    private String mainId;


}
