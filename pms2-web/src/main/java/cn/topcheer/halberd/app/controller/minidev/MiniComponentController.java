package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniComponent;
import cn.topcheer.halberd.app.api.minidev.service.MiniComponentService;
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
@RequestMapping("/minidev/miniComponent")
@Api(value = "组件管理", tags = "低代码-组件管理")
public class MiniComponentController extends BladeController {

	private final MiniComponentService miniComponentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入miniComponent")
	public R detail(MiniComponent miniComponent) {
		MiniComponent detail = miniComponentService.getOne(Condition.getQueryWrapper(miniComponent));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入miniComponent")
	public R<IPage<MiniComponent>> list(MiniComponent miniComponent, Query query) {
		IPage<MiniComponent> pages = miniComponentService.page(Condition.getPage(query), Condition.getQueryWrapper(miniComponent));
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入miniComponent")
	public R save(@Valid @RequestBody MiniComponent miniComponent) {
		return R.status(miniComponentService.save(miniComponent));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入miniComponent")
	public R update(@Valid @RequestBody MiniComponent miniComponent) {
		return R.status(miniComponentService.updateById(miniComponent));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入miniComponent")
	public R submit(@Valid @RequestBody MiniComponent miniComponent) {
		return R.status(miniComponentService.saveOrUpdate(miniComponent));
	}


	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(miniComponentService.removeBatchByIds(Func.toLongList(ids)));
	}

}
