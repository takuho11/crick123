package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysUserVO extends SysUser implements HalberdUser {
    private String roleName;
    private String enterPriseName;
    private String deptName;
    private String postName;
    private String userExt;
    private String roleId;
    private String enterPriseId;
    private String deptId;
    private List<String> roleIds;
    private List<String> enterPriseIds;
    private List<String> deptIds;


}
