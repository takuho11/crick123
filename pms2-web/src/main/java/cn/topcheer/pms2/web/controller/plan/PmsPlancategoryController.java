package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.pms2.api.plan.dto.PmsPlancategoryDTO;
import cn.topcheer.pms2.api.plan.entity.PmsPlancategory;
import cn.topcheer.pms2.api.plan.vo.PmsPlancategoryVO;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlancategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author whq
 * @date 2023-11-09
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("/plan/pmsPlanCategoryController")
@Api(value = "事项类别", tags = "批次-事项类别")
public class PmsPlancategoryController {

    private final PmsPlancategoryService pmsPlanCategoryService;

    /**
     * 查询所有
     */
    @GetMapping("/getAll")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public R<List<PmsPlancategoryVO>> getAll(@Valid PmsPlancategory plancategory) {
        return pmsPlanCategoryService.getAll(plancategory);
    }

    /**
     * 查询
     * @param plancategory
     */
    @GetMapping("/getDetail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询", notes = "查询")
    public R<PmsPlancategoryVO> getDetail(@Valid PmsPlancategory plancategory) {
        return pmsPlanCategoryService.getDetail(plancategory);
    }

    /**
     * 保存
     * @param
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "保存", notes = "保存")
    public R save(@Valid @RequestBody PmsPlancategoryDTO plancategoryDTO) {
        return pmsPlanCategoryService.savePmsPlancategory(plancategoryDTO);
    }


}
