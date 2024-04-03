package cn.topcheer.halberd.app.controller.framework.sys;


import cn.hutool.core.bean.BeanUtil;
import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.PmsDepartmentDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.vo.sys.PmsDepartmentVO;
import cn.topcheer.halberd.app.api.framework.wrapper.PmsDepartmentWrapper;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsDepartmentServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author szs
 * @date 2023-10-31
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/department")
@Api(value = "部门管理", tags = "用户体系-部门管理")
public class PmsDepartmentController extends BladeController {


    private final PmsDepartmentServiceImpl pmsDepartmentService;

    private final PmsEnterpriseServiceImpl pmsEnterpriseService;


    /**
     * 全部列表
     *
     * @author szs
     * @date 2023-10-31
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<PmsDepartment>> all() {
        HqlBuilder<PmsDepartment> builder = HqlBuilder.builder();
        builder.addOrder(PmsDepartment::getSeq, "asc");

        return R.data(pmsDepartmentService.find(builder));
    }


    /**
     * 分页列表
     *
     * @author szs
     * @date 2023-10-31
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页列表", notes = "分页列表")
    public PageResult<List<PmsDepartmentVO>> page(@ApiIgnore PmsDepartment pmsDepartment, Query query) {
        Page page = Page.of(query.getCurrent(), query.getSize());

        HqlBuilder<PmsDepartment> builder = HqlBuilder.builder();
        builder.like(StringUtils.isNotBlank(pmsDepartment.getName()), PmsDepartment::getName, "%" + pmsDepartment.getName() + "%");
        builder.like(StringUtils.isNotBlank(pmsDepartment.getDepartmentcode()), PmsDepartment::getDepartmentcode, "%" + pmsDepartment.getDepartmentcode() + "%");
        builder.addOrder(PmsDepartment::getSeq, "asc");
        PageResult<List<PmsDepartment>> pages = pmsDepartmentService.getPagination(page, builder);

        return PageResult.data(PmsDepartmentWrapper.build().listVO(pages.getData()), pages.getTotal());
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-10-31
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody PmsDepartmentDTO dto) {

        PmsDepartment pmsDepartment = new PmsDepartment();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(Util.NewGuid());
        } else {
            // 编辑时，查询原数据
            pmsDepartment = pmsDepartmentService.getById(dto.getId());
            if (pmsDepartment == null) {
                throw new ServiceException("查无此数据");
            }
        }

        // 复制数据
        BeanUtil.copyProperties(dto, pmsDepartment);

        // 上级单位
        if (StringUtils.isNotBlank(dto.getEnterPriseId())) {
            pmsDepartment.setPmsEnterprise(pmsEnterpriseService.getById(dto.getEnterPriseId()));
        } else {
            pmsDepartment.setPmsEnterprise(null);
        }

        // 保存
        pmsDepartmentService.saveOrUpdate(pmsDepartment);

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-10-31
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {

        // 查询
        PmsDepartment pmsDepartment = pmsDepartmentService.getById(dto.getId());
        if (pmsDepartment == null) {
            throw new ServiceException("查无此数据");
        }

        // 保存
        boolean bo = pmsDepartmentService.deleteById(dto.getId());
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }

    @RequestMapping({ "/findAllDepartment.do" })
    public @ResponseBody String findAllDepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        List<Map> revExperts = pmsDepartmentService.findAllDepartment();
        Map<String, String> map = new HashMap<String, String>();
        map.put(PmsDepartment.class.getName(), "[leader],[name],[id]");
        JsonConfig jsonConfig = JsonBuilder.getJsonConfig(map);
        JSONArray jsondata = JSONArray.fromObject(revExperts, jsonConfig);
        JSONObject result = new JSONObject();
        result.put("data", jsondata);
        return result.toString();
    }

}
