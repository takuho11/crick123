package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 业务允许修改表
 *
 * @author szs
 * @date 2023-12-11
 */
@Data
@ApiModel(value = "业务允许修改表", description = "业务允许修改表")
public class MiniBizAllowUpdateDTO {


    @ApiModelProperty(value = "版本名称")
    private String name;


    @ApiModelProperty(value = "业务模板定义ID")
    private Long miniBizDefId;


    @ApiModelProperty(value = "业务模板版本ID")
    private Long miniBizVersionId;


}
