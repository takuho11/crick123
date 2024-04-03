package cn.topcheer.pms2.web.controller.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.PmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author
 */
@RestController
@RequestMapping("/PmsRole")
@Api(value = "角色管理-子系统", tags = "用户体系-角色管理-子系统")
public class PmsRoleController extends GenericController<SysRole> {

    @Resource
    private PmsRoleService pmsRoleService;


    /**
     * 返回所有SysRole 对象
     */
    @RequestMapping({"/findSysRole.do"})
    public @ResponseBody
    ReturnToJs findSysRole() {
        List<Map> list = pmsRoleService.findSysRole();
        return ReturnToJs.success(list);
    }

    /**
     * 获取当前用户单位可分配的 SysRole
     */
    @PostMapping({"/findEnterpriseRole"})
    @ApiModelProperty(value = "获取当前用户单位可分配的 SysRole ",notes = "获取当前用户单位可分配的 SysRole ")
    public @ResponseBody
    ReturnToJs findEnterpriseRole() {
        List<Map> list = pmsRoleService.findEnterpriseRole();
        return ReturnToJs.success(list);
    }
}
