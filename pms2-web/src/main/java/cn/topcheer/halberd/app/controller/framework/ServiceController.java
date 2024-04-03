package cn.topcheer.halberd.app.controller.framework;

import cn.topcheer.halberd.app.api.framework.entity.api.AmService;
import cn.topcheer.halberd.app.api.framework.service.api.AmServiceService;
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
import java.util.List;


/**
 *  控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("//service")
@Api(value = "服务管理", tags = "API-服务管理")
public class ServiceController extends BladeController {

	private final AmServiceService amServiceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入am_service")
	public R detail(AmService am_service) {
		AmService detail = amServiceService.getOne(Condition.getQueryWrapper(am_service));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入am_service")
	public R<IPage<AmService>> list(AmService am_service, Query query) {
		IPage<AmService> pages = amServiceService.page(Condition.getPage(query), Condition.getQueryWrapper(am_service));
		return R.data(pages);
	}


 


	/**
	 * 根据条件查询所有服务
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "根据条件查询所有服务", notes = "")
	public R<List<AmService>> select(AmService service) {
//		service.setStatus(0);
		return R.data(amServiceService.list(Condition.getQueryWrapper(service)));
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入am_service")
	public R save(@Valid @RequestBody AmService am_service) {
		return R.status(amServiceService.save(am_service));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入am_service")
	public R update(@Valid @RequestBody AmService am_service) {
		return R.status(amServiceService.updateById(am_service));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入am_service")
	public R submit(@Valid @RequestBody AmService am_service) {
		return R.status(amServiceService.saveOrUpdate(am_service));
	}


	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(amServiceService.removeBatchByIds(Func.toLongList(ids)));
	}

}
