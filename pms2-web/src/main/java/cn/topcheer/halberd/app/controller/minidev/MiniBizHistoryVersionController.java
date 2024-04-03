package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniInitAllTableDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniVersionCompareDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizHistoryVersion;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizHistoryVersionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 业务历史版本
 *
 * @author szs
 * @date 2023-11-16
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizHistoryVersion")
@Api(value = "业务历史版本", tags = "低代码-业务历史版本")
public class MiniBizHistoryVersionController extends BladeController {


    private final MiniBizHistoryVersionService miniBizHistoryVersionService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-11-16
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部（主表id）")
    public R<List<MiniBizHistoryVersion>> all(@Valid IdStrDTO dto) {
        QueryWrapper<MiniBizHistoryVersion> qw = new QueryWrapper<>();
        qw.eq("main_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniBizHistoryVersionService.list(qw));
    }


    /**
     * 版本比对
     *
     * @author szs
     * @date 2023-11-17
     */
    @PostMapping("/versionCompare")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "版本比对", notes = "版本比对")
    public R<MiniInitAllTableDTO> versionCompare(@Valid @RequestBody MiniVersionCompareDTO dto) {

        return R.data(miniBizHistoryVersionService.versionCompare(dto));
    }


}
