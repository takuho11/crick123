package cn.topcheer.halberd.app.controller.framework.sys;

import cn.topcheer.halberd.app.api.framework.service.sys.ISysDeptService;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysDeptVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysDeptWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 *  控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("//dept")
@Api(value = "系统部门", tags = "用户体系-系统部门")
public class DepartmentController {

    private final ISysDeptService deptService;

    @GetMapping({"/lazy-list"})
    @ApiImplicitParams({@ApiImplicitParam(
            name = "deptName",
            value = "部门名称",
            paramType = "query",
            dataType = "string"
    ), @ApiImplicitParam(
            name = "fullName",
            value = "部门全称",
            paramType = "query",
            dataType = "string"
    )})
    @ApiOperationSupport(
            order = 3
    )
    @ApiOperation(
            value = "懒加载列表",
            notes = "传入dept"
    )
    public R<List<SysDeptVO>> lazyList(@ApiIgnore @RequestParam Map<String, Object> dept, String parentId, BladeUser bladeUser) {
        List<SysDeptVO> list = this.deptService.lazyList(bladeUser.getTenantId(), parentId, dept);
        return R.data(SysDeptWrapper.build().listNodeLazyVO(list));
    }
}
