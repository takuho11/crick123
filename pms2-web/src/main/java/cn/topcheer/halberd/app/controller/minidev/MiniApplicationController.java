package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniApplication;
import cn.topcheer.halberd.app.api.minidev.service.MiniApplicationService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 *  控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniApplication")
@Api(value = "MiniApplicationController", tags = "低代码-应用管理")
public class MiniApplicationController extends BladeController {

	private final MiniApplicationService miniApplicationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入miniApplication")
	public R detail(MiniApplication miniApplication) {
		MiniApplication detail = miniApplicationService.getOne(Condition.getQueryWrapper(miniApplication));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入miniApplication")
	public R<IPage<MiniApplication>> list(MiniApplication miniApplication, Query query) {
		IPage<MiniApplication> pages = miniApplicationService.page(Condition.getPage(query), Condition.getQueryWrapper(miniApplication));
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "保存", notes = "传入miniApplication")
	public R save(@Valid @RequestBody MiniApplication miniApplication) {
		return R.status(miniApplicationService.save(miniApplication));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入miniApplication")
	public R update(@Valid @RequestBody MiniApplication miniApplication) {
		return R.status(miniApplicationService.updateById(miniApplication));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入miniApplication")
	public R submit(@Valid @RequestBody MiniApplication miniApplication) {
		return R.status(miniApplicationService.saveOrUpdate(miniApplication));
	}


	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(miniApplicationService.removeBatchByIds(Func.toLongList(ids)));
	}

}
