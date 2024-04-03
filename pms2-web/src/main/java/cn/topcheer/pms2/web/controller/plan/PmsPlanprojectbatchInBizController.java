package cn.topcheer.pms2.web.controller.plan;


import cn.hutool.core.bean.BeanUtil;
import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.plan.dto.PmsPlanprojectbatchDTO;
import cn.topcheer.pms2.api.plan.dto.SavePmsPlanprojectbatchInBizDTO;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;
import cn.topcheer.pms2.api.plan.vo.PmsPlanprojectbatchInBizVO;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchInBizService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlantypeService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 控制器
 *
 * @author szs
 * @date 2023-11-09
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("/plan/pmsPlanprojectbatchInBiz")
@Api(value = "小批次绑定业务模板", tags = "批次-小批次绑定业务模板")
public class PmsPlanprojectbatchInBizController extends BladeController {


    private final PmsPlanprojectbatchInBizService pmsPlanprojectbatchInBizService;

    private final PmsPlantypeService pmsPlantypeService;


    /**
     * 查询
     *
     * @author szs
     * @date 2023-11-09
     */
    @GetMapping("/getDetail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询", notes = "查询")
    public R<PmsPlanprojectbatchInBizVO> getDetail(@Valid PmsPlanprojectbatchDTO dto) {
        PmsPlanprojectbatchInBizVO vo = new PmsPlanprojectbatchInBizVO();

        // 查询
        PmsPlanprojectbatchInBiz data = pmsPlanprojectbatchInBizService.getPmsPlanprojectbatchInBiz(dto.getPmsPlanprojectbatchId(), dto.getPlanprojectType());

        if (data != null) {
            BeanUtil.copyProperties(data, vo);
            vo.setPlanprojectType(data.getPlanprojectType());
            vo.setPlanprojectTypeName(data.getPlanprojectTypeName());
        } else {
            BeanUtil.copyProperties(dto, vo);
        }

        return R.data(vo);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-11-09
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody SavePmsPlanprojectbatchInBizDTO dto) {

        // 查询
        PmsPlanprojectbatchInBiz data = pmsPlanprojectbatchInBizService.getPmsPlanprojectbatchInBiz(dto.getPmsPlanprojectbatchId(), dto.getPlanprojectType());
        if (data == null) {
            // 新增
            data = new PmsPlanprojectbatchInBiz();
            data.setId(Util.NewGuid());
            data.setCreateTime(new Date());
            data.setCreateUser(AuthUtil.getUserId());
            data.setPmsPlantype(pmsPlantypeService.getPmsPlantype(dto.getPlanprojectType()));

        } else {
            // 修改
            data.setUpdateTime(new Date());
            data.setUpdateUser(AuthUtil.getUserId());
        }

        // 复制数据
        BeanUtil.copyProperties(dto, data);


        // 保存
        pmsPlanprojectbatchInBizService.saveOrUpdate(data);

        return R.success("保存成功");
    }


    /**
     * 全部列表(批次id)
     *
     * @author szs
     * @date 2023-11-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<PmsPlanprojectbatchInBizVO>> all(@Valid IdStrDTO dto) {
        List<PmsPlanprojectbatchInBizVO> voList = new ArrayList<>();

        // 查询
        HqlBuilder<PmsPlanprojectbatchInBiz> builder = new HqlBuilder<>();
        builder.eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId, dto.getId());
        builder.addOrder(PmsPlanprojectbatchInBiz::getSeq, "asc");
        List<PmsPlanprojectbatchInBiz> list = pmsPlanprojectbatchInBizService.find(builder);

        // 遍历
        for (PmsPlanprojectbatchInBiz data : list) {
            PmsPlanprojectbatchInBizVO vo = new PmsPlanprojectbatchInBizVO();
            BeanUtil.copyProperties(data, vo);
            vo.setPlanprojectType(data.getPlanprojectType());
            vo.setPlanprojectTypeName(data.getPlanprojectTypeName());
            voList.add(vo);
        }

        return R.data(voList);
    }


}
