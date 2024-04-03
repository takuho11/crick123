package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.PmsAreaDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsArea;
import cn.topcheer.halberd.app.api.framework.vo.sys.PmsAreaVO;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsAreaServiceImpl;
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
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/area")
@Api(value = "区域信息", tags = "用户体系-区域信息")
public class PmsAreaController extends BladeController {


    private final PmsAreaServiceImpl pmsAreaService;


    /**
     * 树形列表
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "树形列表", notes = "树形列表")
    public R<List<PmsAreaVO>> tree() {

        // 查询全部列表
        HqlBuilder<PmsArea> hq = new HqlBuilder<>();
        hq.eq(PmsArea::getIsDeleted, 0);
        hq.addOrder(PmsArea::getSeq, "asc");
        List<PmsArea> list = pmsAreaService.find(hq);

        return R.data(recursionTreeData(list, null));
    }


    /**
     * 下级树形列表（传parentareaid）
     *
     * @author szs
     * @date 2024-01-12
     */
    @GetMapping("/nextTree")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "下级树形列表（传parentareaid）", notes = "下级树形列表（传parentareaid）")
    public R<List<PmsAreaVO>> nextTree(@Valid IdStrDTO dto) {

        // 查询全部列表
        HqlBuilder<PmsArea> hq = new HqlBuilder<>();
        hq.eq(PmsArea::getIsDeleted, 0);
        hq.addOrder(PmsArea::getSeq, "asc");
        List<PmsArea> list = pmsAreaService.find(hq);

        return R.data(recursionTreeData(list, dto.getId()));
    }


    /**
     * 递归获取树形数据
     *
     * @param list         全部数据
     * @param parentareaid 上级id
     * @return List
     * @author szs
     * @date 2024-01-12
     */
    private List<PmsAreaVO> recursionTreeData(List<PmsArea> list, String parentareaid) {
        List<PmsAreaVO> listVo = new ArrayList<>();
        for (PmsArea data : list) {
            PmsAreaVO vo = new PmsAreaVO();
            if (StringUtils.isBlank(parentareaid) && StringUtils.isBlank(data.getParentareaid())) {
                BeanUtil.copyProperties(data, vo);
                // 递归获取下级
                vo.setChildren(recursionTreeData(list, data.getId()));
                listVo.add(vo);

            } else if (StringUtils.isNotBlank(data.getParentareaid()) && data.getParentareaid().equals(parentareaid)) {
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
    public R<String> submit(@Valid @RequestBody PmsAreaDTO dto) {

        PmsArea pmsArea = new PmsArea();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(createAreaId(dto.getParentareaid()));
            pmsArea.setIsDeleted(0);

        } else {
            // 编辑时，查询原数据
            pmsArea = pmsAreaService.getById(dto.getId());
            if (pmsArea == null) {
                throw new ServiceException("查无此数据");
            }

        }

        // 复制数据
        BeanUtil.copyProperties(dto, pmsArea);

        // 保存
        pmsAreaService.saveOrUpdate(pmsArea);

        return R.success("保存成功");
    }


    /**
     * 生成组织id
     * 从52开始，表示贵阳省
     *
     * @param parentareaid 上级id
     * @return 新的id
     */
    private String createAreaId(String parentareaid) {
        Page page = Page.of(1, 1);

        HqlBuilder<PmsArea> hq = new HqlBuilder<>();
        hq.isNull(StringUtils.isBlank(parentareaid), PmsArea::getParentareaid);
        hq.eq(StringUtils.isNotBlank(parentareaid), PmsArea::getParentareaid, parentareaid);
        hq.eq(PmsArea::getIsDeleted, 0);
        hq.addOrder(PmsArea::getId, "desc");

        // 分页查询一条记录
        PageResult<List<PmsArea>> pages = pmsAreaService.getPagination(page, hq);

        // 如果当前所在层级没有数据，那就上级id+01，如果上级空，那就默认52
        if (pages.getData().size() == 0) {
            return StringUtils.isBlank(parentareaid) ? "52" : parentareaid + "01";
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
        PmsArea pmsArea = pmsAreaService.getById(dto.getId());
        if (pmsArea == null) {
            throw new ServiceException("查无此数据");
        }

        // 查询下级数量
        long count = pmsAreaService.findCount(new HqlBuilder<PmsArea>().eq(PmsArea::getParentareaid, pmsArea.getId()).eq(PmsArea::getIsDeleted, 0));
        if (count > 0) {
            throw new ServiceException("请先删除下级数据");
        }

        // 逻辑删除
        pmsArea.setIsDeleted(1);

        // 保存
        pmsAreaService.update(pmsArea);

        return R.success("删除成功");
    }


}
