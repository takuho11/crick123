package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.PmsEnterpriseDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.vo.sys.PmsEnterpriseVO;
import cn.topcheer.halberd.app.api.framework.wrapper.PmsEnterpriseWrapper;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsAreaServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
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
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


/**
 * 控制器
 *
 * @author szs
 * @date 2023-10-31
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/enterprise")
@Api(value = "单位管理", tags = "用户体系-单位管理")
public class PmsEnterpriseController extends BladeController {


    private final PmsEnterpriseServiceImpl pmsEnterpriseService;

    private final PmsAreaServiceImpl pmsAreaService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 全部列表
     *
     * @author szs
     * @date 2023-10-31
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<PmsEnterprise>> all() {

        return R.data(pmsEnterpriseService.findAll());
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
    public PageResult<List<PmsEnterpriseVO>> page(@ApiIgnore PmsEnterpriseVO pmsEnterprise, Query query) {

        // 分页
        Page page = Page.of(query.getCurrent(), query.getSize());

        // 查询器
        HqlBuilder<PmsEnterprise> builder = HqlBuilder.builder();
        builder.like(StringUtils.isNotBlank(pmsEnterprise.getName()), PmsEnterprise::getName, "%" + pmsEnterprise.getName() + "%");
        builder.eq(StringUtils.isNotBlank(pmsEnterprise.getAreaId()), "areaid", pmsEnterprise.getAreaId());
        builder.eq(pmsEnterprise.getIsNext() != null && pmsEnterprise.getIsNext() == 1, "PARENTID", sysUserService.getEnterPriseId());
        PageResult<List<PmsEnterprise>> pages = pmsEnterpriseService.getPagination(page, builder);

        return PageResult.data(PmsEnterpriseWrapper.build().listVO(pages.getData()), pages.getTotal());
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
    public R<String> submit(@Valid @RequestBody PmsEnterpriseDTO dto) {

        PmsEnterprise pmsEnterprise = new PmsEnterprise();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(Util.NewGuid());
        } else {
            // 编辑时，查询原数据
            pmsEnterprise = pmsEnterpriseService.getById(dto.getId());
            if (pmsEnterprise == null) {
                throw new ServiceException("查无此数据");
            }
        }

        // 复制数据
        BeanUtil.copyProperties(dto, pmsEnterprise);

        // 管理下级时，上级单位id取当前登录人的单位id
        if (dto.getIsNext() != null && dto.getIsNext() == 1) {
            // 上级单位id
            dto.setParentId(sysUserService.getEnterPriseId());
        }

        // 上级单位
        if (StringUtils.isNotBlank(dto.getParentId())) {
            pmsEnterprise.setPmsParentEnterprise(pmsEnterpriseService.getById(dto.getParentId()));
        } else {
            pmsEnterprise.setPmsParentEnterprise(null);
        }

        // 地域
        if (StringUtils.isNotBlank(dto.getAreaId())) {
            pmsEnterprise.setPmsArea(pmsAreaService.getById(dto.getAreaId()));
        } else {
            pmsEnterprise.setPmsArea(null);
        }

        // 保存
        pmsEnterpriseService.saveOrUpdate(pmsEnterprise);

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
        PmsEnterprise pmsEnterprise = pmsEnterpriseService.getById(dto.getId());
        if (pmsEnterprise == null) {
            throw new ServiceException("查无此数据");
        }

        // 保存
        boolean bo = pmsEnterpriseService.deleteById(dto.getId());
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 获取下级单位列表
     *
     * @author szs
     * @date 2024-01-23
     */
    @GetMapping("/getNextEnterpriseList")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "获取下级单位列表", notes = "获取下级单位列表")
    public R<List<PmsEnterprise>> getNextEnterpriseList() {
        // 查询下级
        HqlBuilder<PmsEnterprise> builder = HqlBuilder.builder();
        builder.eq("PARENTID", sysUserService.getEnterPriseId());

        return R.data(pmsEnterpriseService.find(builder));
    }


}
