package cn.topcheer.halberd.app.controller.framework;

import cn.topcheer.halberd.app.api.minidev.entity.TemplateFile;
import cn.topcheer.halberd.app.api.minidev.service.TemplateFileService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping( "/template-file")
@Api(value = "代码模板文件表", tags = "低代码-代码模板文件表接口")
public class TemplateFileController extends BladeController {

	private final TemplateFileService templateFileService;

	private final Logger log= LoggerFactory.getLogger(this.getClass());

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入templateFile")
	public R<TemplateFile> detail(TemplateFile templateFile) {
		TemplateFile detail = templateFileService.getById(templateFile.getId());
 
		return R.data(detail);
	}

	/**
	 * 分页 代码模板文件表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入templateFile")
	public R<IPage<TemplateFile>> list(TemplateFile templateFile, Query query) {
		IPage<TemplateFile> pages = templateFileService.page(Condition.getPage(query), Condition.getQueryWrapper(templateFile));

 

		return R.data(pages);
	}

	/**
	 * 新增 代码模板文件表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入templateFile")
	public R save(@Valid @RequestBody TemplateFile templateFile) {

 
		return R.status(templateFileService.save(templateFile));
	}

	/**
	 * 修改 代码模板文件表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入templateFile")
	public R update(@Valid @RequestBody TemplateFile templateFile) {

		return R.status(templateFileService.updateById(templateFile));
	}

	/**
	 * 新增或修改 代码模板文件表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入templateFile")
	public R submit(@Valid @RequestBody TemplateFile templateFile) {

 

		return R.status(templateFileService.saveOrUpdate(templateFile));
	}


	/**
	 * 删除 代码模板文件表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {


		return R.status(templateFileService.removeByIds(Func.toStrList(ids)));
	}


}