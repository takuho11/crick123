package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefHistoryDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefHistory;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefHistoryResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefHistoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 业务历史版本
 *
 * @author szs
 * @date 2024-03-27
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizDefHistory")
@Api(value = "业务历史版本", tags = "低代码-业务历史版本")
public class MiniBizDefHistoryController extends BladeController {


    private final MiniBizDefHistoryService miniBizDefHistoryService;


    /**
     * 分页
     *
     * @author szs
     * @date 2024-03-27
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<MiniBizDefHistoryResult>> list(MiniBizDefHistoryDTO dto, Query query) {

        return R.data(miniBizDefHistoryService.pageList(dto, query));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2024-03-27
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "详情", notes = "详情")
    public R<MiniBizDefHistory> detail(@Valid IdDTO dto) {
        MiniBizDefHistory detail = miniBizDefHistoryService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


}
