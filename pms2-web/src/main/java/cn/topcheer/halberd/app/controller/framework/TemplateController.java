package cn.topcheer.halberd.app.controller.framework;

import cn.topcheer.halberd.app.api.minidev.entity.Template;
import cn.topcheer.halberd.app.api.minidev.service.TemplateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/template")
@Api(value = "代码模板表", tags = "低代码-代码模板表接口")
public class TemplateController extends BladeController {

	private final TemplateService templateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入template")
	public R<Template> detail(Template template) {
		Template detail = templateService.getById(template.getId());
		return R.data(detail);
	}

	/**
	 * 分页 代码模板表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入template")
	public R<IPage<Template>> list(Template template, Query query) {
		IPage<Template> pages = templateService.page(Condition.getPage(query), Condition.getQueryWrapper(template));
		return R.data(pages);
	}

	/**
	 * 新增 代码模板表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入template")
	public R save(@Valid @RequestBody Template template) {
		return R.status(templateService.save(template));
	}

	/**
	 * 修改 代码模板表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入template")
	public R update(@Valid @RequestBody Template template) {
		return R.status(templateService.updateById(template));
	}

	/**
	 * 新增或修改 代码模板表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入template")
	public R submit(@Valid @RequestBody Template template) {
		return R.status(templateService.saveOrUpdate(template));
	}


	/**
	 * 删除 代码模板表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(templateService.removeByIds((Func.toStrList(ids))));
	}

	/**
	 * 模板列表
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "模板列表", notes = "模板列表")
	public R<List<Template>> select() {
		List<Template> list = templateService.list();
		list.forEach(template -> template.setTemplateName(template.getTemplateName() + StringPool.LEFT_BRACKET + template.getTemplateCode() + StringPool.RIGHT_BRACKET));
		return R.data(list);
	}

	/**
	 * 复制 项目
	 */
	@PostMapping("/copy")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "复制模板",
			notes = "复制一份模板，包含模板下面的所有模板文件也同时复制一份，新模板名称为老模板名称后跟 copy 字符")
	public R copy(@RequestBody Template template) {
		templateService.copy(template.getId());
		return R.success("复制项目成功");
	}
}

