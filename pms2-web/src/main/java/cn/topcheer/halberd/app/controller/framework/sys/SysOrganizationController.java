package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.SysOrganizationDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsArea;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysOrganization;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysOrganizationVO;
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
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/organization")
@Api(value = "组织机构", tags = "用户体系-组织机构")
public class SysOrganizationController extends BladeController {


    private final SysOrganizationServiceImpl sysOrganizationService;


    /**
     * 树形列表
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "树形列表", notes = "树形列表")
    public R<List<SysOrganizationVO>> tree() {

        // 查询全部列表
        HqlBuilder<SysOrganization> hq = new HqlBuilder<>();
        hq.eq(SysOrganization::getIsDeleted, 0);
        hq.addOrder(SysOrganization::getSeq, "asc");
        List<SysOrganization> list = sysOrganizationService.find(hq);

        return R.data(recursionTreeData(list, null));
    }


    /**
     * 下级树形列表（传parentId）
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/nextTree")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "下级树形列表（传parentId）", notes = "下级树形列表（传parentId）")
    public R<List<SysOrganizationVO>> nextTree(@Valid IdStrDTO dto) {

        // 查询全部列表
        HqlBuilder<SysOrganization> hq = new HqlBuilder<>();
        hq.eq(SysOrganization::getIsDeleted, 0);
        hq.addOrder(SysOrganization::getSeq, "asc");
        List<SysOrganization> list = sysOrganizationService.find(hq);

        return R.data(recursionTreeData(list, dto.getId()));
    }


    /**
     * 递归获取树形数据
     *
     * @param list     全部数据
     * @param parentId 上级id
     * @return List
     * @author szs
     * @date 2024-01-12
     */
    private List<SysOrganizationVO> recursionTreeData(List<SysOrganization> list, String parentId) {
        List<SysOrganizationVO> listVo = new ArrayList<>();
        for (SysOrganization data : list) {
            SysOrganizationVO vo = new SysOrganizationVO();
            if (StringUtils.isBlank(parentId) && StringUtils.isBlank(data.getParentId())) {
                BeanUtil.copyProperties(data, vo);
                // 递归获取下级
                vo.setChildren(recursionTreeData(list, data.getId()));
                listVo.add(vo);

            } else if (StringUtils.isNotBlank(data.getParentId()) && data.getParentId().equals(parentId)) {
                BeanUtil.copyProperties(data, vo);
                // 递归获取下级
                vo.setChildren(recursionTreeData(list, data.getId()));
                listVo.add(vo);

            }
        }

        return listVo;
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
    public R<String> submit(@Valid @RequestBody SysOrganizationDTO dto) {

        SysOrganization sysOrganization = new SysOrganization();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(createOrganizationId(dto.getParentId()));
            sysOrganization.setIsDeleted(0);
            sysOrganization.setCreateTime(new Date());
            sysOrganization.setCreateUser(AuthUtil.getUserId());

        } else {
            // 编辑时，查询原数据
            sysOrganization = sysOrganizationService.getById(dto.getId());
            if (sysOrganization == null) {
                throw new ServiceException("查无此数据");
            }

            sysOrganization.setUpdateTime(new Date());
            sysOrganization.setUpdateUser(AuthUtil.getUserId());
        }

        // 复制数据
        BeanUtil.copyProperties(dto, sysOrganization);

        // 保存
        sysOrganizationService.saveOrUpdate(sysOrganization);

        return R.success("保存成功");
    }


    /**
     * 生成组织id
     * 从52开始，表示贵阳省
     *
     * @param parentId 上级id
     * @return 新的id
     */
    private String createOrganizationId(String parentId) {
        Page page = Page.of(1, 1);

        HqlBuilder<SysOrganization> hq = new HqlBuilder<>();
        hq.isNull(StringUtils.isBlank(parentId), SysOrganization::getParentId);
        hq.eq(StringUtils.isNotBlank(parentId), SysOrganization::getParentId, parentId);
        hq.eq(SysOrganization::getIsDeleted, 0);
        hq.addOrder(SysOrganization::getSeq, "asc");

        // 分页查询一条记录
        PageResult<List<SysOrganization>> pages = sysOrganizationService.getPagination(page, hq);

        // 如果当前所在层级没有数据，那就上级id+01，如果上级空，那就默认52
        if (pages.getData().size() == 0) {
            return StringUtils.isBlank(parentId) ? "52" : parentId + "01";
        }

        // 取当前所在层级ID最大的一条数据，转数字加1
        return String.valueOf(Integer.parseInt(pages.getData().get(0).getId()) + 1);
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
        SysOrganization sysOrganization = sysOrganizationService.getById(dto.getId());
        if (sysOrganization == null) {
            throw new ServiceException("查无此数据");
        }

        // 查询下级数量
        long count = sysOrganizationService.findCount(new HqlBuilder<SysOrganization>().eq(SysOrganization::getParentId, sysOrganization.getId()).eq(SysOrganization::getIsDeleted, 0));
        if (count > 0) {
            throw new ServiceException("请先删除下级数据");
        }

        // 逻辑删除
        sysOrganization.setIsDeleted(1);

        // 保存
        sysOrganizationService.update(sysOrganization);

        return R.success("删除成功");
    }


}
