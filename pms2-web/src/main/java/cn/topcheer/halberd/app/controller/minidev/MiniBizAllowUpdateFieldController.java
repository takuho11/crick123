package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdateField;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAllowUpdateFieldService;
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
 * 业务允许修改字段
 *
 * @author szs
 * @date 2023-12-05
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizAllowUpdateField")
@Api(value = "业务允许修改字段", tags = "低代码-业务允许修改字段")
public class MiniBizAllowUpdateFieldController extends BladeController {


    private final MiniBizAllowUpdateFieldService miniBizAllowUpdateFieldService;


    /**
     * 全部（业务允许修改ID）
     *
     * @author szs
     * @date 2023-12-05
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "MiniBizAllowUpdateFieldController.全部（业务允许修改ID）", notes = "全部（业务允许修改ID）")
    public R<List<MiniBizAllowUpdateField>> all(@Valid IdDTO dto) {

        return R.data(miniBizAllowUpdateFieldService.getAllowUpdateFieldList(dto.getId()));
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-12-05
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody MiniBizAllowUpdateField miniBizAllowUpdateField) {

        // 保存
        miniBizAllowUpdateFieldService.submit(miniBizAllowUpdateField);

        return R.success("保存成功");
    }


}
