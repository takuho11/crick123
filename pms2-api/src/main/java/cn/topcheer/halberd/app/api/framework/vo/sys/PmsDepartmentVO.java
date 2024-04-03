package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
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
public class PmsDepartmentVO extends PmsDepartment {


    @ApiModelProperty(value = "上级单位id")
    private String enterPriseId;


    @ApiModelProperty(value = "上级单位名称")
    private String enterPriseName;


}
