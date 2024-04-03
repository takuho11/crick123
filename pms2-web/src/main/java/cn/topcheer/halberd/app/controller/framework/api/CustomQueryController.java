package cn.topcheer.halberd.app.controller.framework.api;

import cn.topcheer.halberd.app.api.minidev.entity.CustomQuery;
import cn.topcheer.halberd.app.api.minidev.service.CustomQueryService;
import cn.topcheer.halberd.biz.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@RequestMapping( "/custom-query")
@Api(value = "代码自定义查询", tags = "API-代码自定义查询接口")
public class CustomQueryController extends BladeController {

	private final CustomQueryService customQueryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入customQuery")
	public R<CustomQuery> detail(CustomQuery customQuery) {
		CustomQuery detail = customQueryService.getOne(Condition.getQueryWrapper(customQuery));
		return R.data(detail);
	}

	/**
	 * 分页 代码自定义查询
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入customQuery")
	public R<IPage<CustomQuery>> list(CustomQuery customQuery, Query query) {
		IPage<CustomQuery> pages = customQueryService.page(Condition.getPage(query), Condition.getQueryWrapper(customQuery).orderByAsc(CommonConstant.SORT_FIELD));
		return R.data(pages);
	}

	/**
	 * 新增 代码自定义查询
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入customQuery")
	public R save(@Valid @RequestBody CustomQuery customQuery) {
		return R.status(customQueryService.save(customQuery));
	}

	/**
	 * 修改 代码自定义查询
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入customQuery")
	public R update(@Valid @RequestBody CustomQuery customQuery) {
		return R.status(customQueryService.updateById(customQuery));
	}

	/**
	 * 新增或修改 代码自定义查询
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入customQuery")
	public R submit(@Valid @RequestBody CustomQuery customQuery) {
		return R.status(customQueryService.saveOrUpdate(customQuery));
	}


	/**
	 * 删除 代码自定义查询
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(customQueryService.removeByIds(Func.toStrList(ids)));
	}

	/**
	 * 查询列表
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "查询列表", notes = "查询列表")
	public R<List<CustomQuery>> select() {
		List<CustomQuery> list = customQueryService.list(Wrappers.<CustomQuery>query().lambda().orderByAsc(CustomQuery::getSort));
		list.forEach(customQuery -> customQuery.setQueryName(customQuery.getQueryName() + StringPool.LEFT_BRACKET + customQuery.getQueryKey() + StringPool.RIGHT_BRACKET));
		return R.data(list);
	}


}
