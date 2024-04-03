package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysOrganization;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 返回类
 *
 * @author szs
 * @date 2024-01-12
 */
@Data
public class SysOrganizationVO extends SysOrganization {


    @ApiModelProperty(value = "下级")
    private List<SysOrganizationVO> children;


}
