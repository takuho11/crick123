package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 返回类
 *
 * @author szs
 * @date 2023-11-01
 */
@Data
public class PmsEnterpriseVO extends PmsEnterprise {


    @ApiModelProperty(value = "上级单位id")
    private String parentId;


    @ApiModelProperty(value = "上级单位名称")
    private String parentName;


    @ApiModelProperty(value = "所属区域id")
    private String areaId;


    @ApiModelProperty(value = "所属区域名称")
    private String areaName;


    @ApiModelProperty(value = "是否仅仅下级（0否，1是）")
    private Integer isNext;


}
