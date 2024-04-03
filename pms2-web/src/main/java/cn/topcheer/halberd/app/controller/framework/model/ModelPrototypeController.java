package cn.topcheer.halberd.app.controller.framework.model;

import cn.topcheer.halberd.app.api.minidev.entity.Model;
import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.service.ModelPrototypeService;
import cn.topcheer.halberd.app.api.minidev.service.ModelService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/model-prototype")
@Api(value = "代码建模原型表", tags = "模型-代码建模原型表接口")
public class ModelPrototypeController extends BladeController {

    private final ModelPrototypeService modelPrototypeService;

    private final ModelService modelService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入modelPrototype")
    public R<ModelPrototype> detail(ModelPrototype modelPrototype) {
        modelPrototype.setComment(StringUtils.isBlank(modelPrototype.getComment()) ? modelPrototype.getPropertyName() : modelPrototype.getComment());
        ModelPrototype detail = modelPrototypeService.getOne(Condition.getQueryWrapper(modelPrototype));
        return R.data(detail);
    }

    /**
     * 分页 代码建模原型表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入modelPrototype")
    public R<IPage<ModelPrototype>> list(ModelPrototype modelPrototype, Query query) {
        IPage<ModelPrototype> pages = modelPrototypeService.page(Condition.getPage(query), Condition.getQueryWrapper(modelPrototype));
        List<ModelPrototype> records = pages.getRecords();
        records.forEach(prototype -> {
            if (StringUtils.isBlank(prototype.getComment())) {
                prototype.setComment(prototype.getPropertyName());
            }
        });
        pages.setRecords(records);
        return R.data(pages);
    }

    /**
     * 新增 代码建模原型表
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入modelPrototype")
    public R<Boolean> save(@Valid @RequestBody ModelPrototype modelPrototype) {
        modelPrototype.setComment(StringUtils.isBlank(modelPrototype.getComment()) ? modelPrototype.getComment() : modelPrototype.getPropertyName());
        return R.status(modelPrototypeService.save(modelPrototype));
    }

    /**
     * 修改 代码建模原型表
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入modelPrototype")
    public R<Boolean> update(@Valid @RequestBody ModelPrototype modelPrototype) {
        modelPrototype.setComment(StringUtils.isBlank(modelPrototype.getComment()) ? modelPrototype.getPropertyName() : modelPrototype.getComment());
        return R.status(modelPrototypeService.updateById(modelPrototype));
    }

    /**
     * 新增或修改 代码建模原型表
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增或修改", notes = "传入modelPrototype")
    public R<Boolean> submit(@Valid @RequestBody ModelPrototype modelPrototype) {
        modelPrototype.setComment(StringUtils.isBlank(modelPrototype.getComment()) ? modelPrototype.getPropertyName() : modelPrototype.getComment());
        return R.status(modelPrototypeService.saveOrUpdate(modelPrototype));
    }

    /**
     * 批量新增或修改 代码建模原型表
     */
    @PostMapping("/submit-list")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "批量新增或修改", notes = "传入modelPrototype集合")
    public R<Boolean> submitList(@Valid @RequestBody List<ModelPrototype> modelPrototypes, String modelId) {

        // 批量保存
        boolean result = modelPrototypeService.submitList(modelPrototypes, modelId);
        if (!result) {
            return R.status(false);
        }

        // 增加api转换功能
        if (modelPrototypes.size() > 0) {
            Model model = modelService.getById(modelId);
            try {
                modelService.convertExchangApi(model);
                return R.status(true);
            } catch (Exception e) {
                log.error("属性保存成功，但是在生成API时发生错误", e);
                return R.status(false);
            }
        }

        return R.status(true);
    }


    /**
     * 删除 代码建模原型表
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(modelPrototypeService.removeByIds(Func.toStrList(ids)));
    }


}
