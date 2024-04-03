package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysAgency;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 返回类
 *
 * @author szs
 * @date 2024-01-12
 */
@Data
public class SysAgencyVO extends SysAgency {


    @ApiModelProperty(value = "组织机构id")
    private String organizationId;


    @ApiModelProperty(value = "组织机构名称")
    private String organizationName;


}
