package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.pms2.api.plan.entity.PmsPlancategory;
import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import cn.topcheer.pms2.api.plan.vo.PmsPlancategoryVO;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlantypeService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/plan/PmsPlantypeController")
@Api(value = "业务类别", tags = "批次-业务类别")
public class PmsPlantypeController {
    private final PmsPlantypeService pmsPlantypeService;

    /**
     * 查询
     * @param PmsPlantype
     */
    @GetMapping("/getDetail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询", notes = "查询")
    public R<List<PmsPlantype>> queryAll(@Valid PmsPlantype PmsPlantype) {
        return R.data(pmsPlantypeService.queryAll());
    }
}
