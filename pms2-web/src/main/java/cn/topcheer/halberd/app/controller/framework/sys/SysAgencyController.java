package cn.topcheer.halberd.app.controller.framework.sys;


import cn.hutool.core.bean.BeanUtil;
import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.SysAgencyDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysAgency;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysAgencyVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysAgencyWrapper;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysAgencyServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysOrganizationServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


/**
 * 控制器
 *
 * @author szs
 * @date 2024-01-12
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/agency")
@Api(value = "政府部门", tags = "用户体系-政府部门")
public class SysAgencyController extends BladeController {


    private final SysAgencyServiceImpl sysAgencyService;

    private final SysOrganizationServiceImpl sysOrganizationService;


    /**
     * 列表（传组织机构id）
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "列表（传组织机构id）", notes = "列表（传组织机构id）")
    public R<List<SysAgency>> list(@Valid IdStrDTO dto) {
        HqlBuilder<SysAgency> hq = HqlBuilder.builder();
        hq.eq("organization_id", dto.getId());
        hq.eq("is_deleted", 0);
        hq.addOrder("seq", "asc");

        return R.data(sysAgencyService.find(hq));
    }


    /**
     * 分页列表
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页列表", notes = "分页列表")
    public PageResult<List<SysAgencyVO>> page(@ApiIgnore SysAgencyVO sysAgency, Query query) {
        Page page = Page.of(query.getCurrent(), query.getSize());

        HqlBuilder<SysAgency> hq = HqlBuilder.builder();
        hq.like(StringUtils.isNotBlank(sysAgency.getCode()), "code", "%" + sysAgency.getCode() + "%");
        hq.like(StringUtils.isNotBlank(sysAgency.getName()), "name", "%" + sysAgency.getName() + "%");
        hq.eq(StringUtils.isNotBlank(sysAgency.getOrganizationId()), "organization_id", sysAgency.getOrganizationId());
        hq.eq("is_deleted", 0);
        hq.addOrder("seq", "asc");
        PageResult<List<SysAgency>> pages = sysAgencyService.getPagination(page, hq);

        return PageResult.data(SysAgencyWrapper.build().listVO(pages.getData()), pages.getTotal());
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2024-01-12
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody SysAgencyDTO dto) {

        SysAgency sysAgency = new SysAgency();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(Util.NewGuid());
            sysAgency.setIsDeleted(0);
            sysAgency.setCreateTime(new Date());
            sysAgency.setCreateUser(AuthUtil.getUserId());

        } else {
            // 编辑时，查询原数据
            sysAgency = sysAgencyService.getById(dto.getId());
            if (sysAgency == null) {
                throw new ServiceException("查无此数据");
            }
            sysAgency.setUpdateTime(new Date());
            sysAgency.setUpdateUser(AuthUtil.getUserId());

        }

        // 复制数据
        BeanUtil.copyProperties(dto, sysAgency);

        // 组织机构
        if (StringUtils.isNotBlank(dto.getOrganizationId())) {
            sysAgency.setSysOrganization(sysOrganizationService.getById(dto.getOrganizationId()));
        } else {
            sysAgency.setSysOrganization(null);
        }

        // 保存
        sysAgencyService.saveOrUpdate(sysAgency);

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2024-01-12
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {

        // 查询
        SysAgency sysAgency = sysAgencyService.getById(dto.getId());
        if (sysAgency == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        sysAgency.setIsDeleted(1);

        // 保存
        sysAgencyService.update(sysAgency);

        return R.success("删除成功");
    }


}
