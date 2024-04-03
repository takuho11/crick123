package cn.topcheer.halberd.app.controller.framework.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApplication;
import cn.topcheer.halberd.app.api.framework.service.api.ApplicationService;
import cn.topcheer.halberd.biz.modules.system.entity.AuthClient;
import cn.topcheer.halberd.biz.modules.system.service.IAuthClientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
@RequestMapping("/application")
@Api(value = "Api应用", tags = "API-Api应用")
public class ApplicationController extends BladeController {

	private final ApplicationService applicationService;

	private final IAuthClientService clientService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入am_application")
	public R detail(AmApplication am_application) {
		AmApplication detail = applicationService.getOne(Condition.getQueryWrapper(am_application));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入am_application")
	public R<IPage<AmApplication>> list(AmApplication am_application, Query query) {
		IPage<AmApplication> pages = applicationService.page(Condition.getPage(query), Condition.getQueryWrapper(am_application));
		return R.data(pages);
	}

	/**
	 * 分页
	 */
	@GetMapping("/apiAuths")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入am_application")
	public R<IPage<AmApplication>> apiAuths(AmApplication am_application, Query query) {
		IPage<AmApplication> pages = applicationService.page(Condition.getPage(query), Condition.getQueryWrapper(am_application));
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入am_application")
	public R save(@Valid @RequestBody AmApplication am_application) {
		return R.status(applicationService.save(am_application));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入am_application")
	public R update(@Valid @RequestBody AmApplication am_application) {
		return R.status(applicationService.updateById(am_application));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入am_application")
	public R submit(@Valid @RequestBody AmApplication am_application) {
		return R.status(applicationService.saveOrUpdate(am_application));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(applicationService.removeBatchByIds(Func.toLongList(ids)));
	}

	/**
	 * 删除
	 */
	@GetMapping("/listAccountByName")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "逻辑删除", notes = "传入查询条件账户名称")
	public R listAccountByName(@ApiParam(value = "访客ID", required = true) @RequestParam String clientId) {
//		if(clientId == null|| clientId.length()<2)

		LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<AuthClient>()
				.likeRight(AuthClient::getClientId,clientId);
//				.eq(AuthClient::getClientId,client.getClientId());
		return R.data(clientService.list(queryWrapper));
	}

}
