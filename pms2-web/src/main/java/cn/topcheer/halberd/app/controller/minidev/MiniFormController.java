package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;
import cn.topcheer.halberd.app.api.minidev.service.MiniFormService;
import cn.topcheer.halberd.app.biz.framework.service.impl.minidev.MiniFormSingleModelConverter;
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
@RequestMapping("/minidev/miniForm")
@Api(value = "表单管理", tags = "低代码-表单管理")
public class MiniFormController extends BladeController {

	private final MiniFormService miniFormService;

	private final MiniFormSingleModelConverter converter;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入miniForm")
	public R detail(MiniForm miniForm) {
		MiniForm detail = miniFormService.getOne(Condition.getQueryWrapper(miniForm));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入miniForm")
	public R<IPage<MiniForm>> list(MiniForm miniForm, Query query) {
		miniForm.setIsDeleted(0);
		IPage<MiniForm> pages = miniFormService.pageList(miniForm, query);
		return R.data(pages);
	}

	/**
	 * 获取列表
	 */
	@GetMapping("/getList")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "获取列表", notes = "传入miniForm")
	public R<List<MiniForm>> getList(MiniForm miniForm) {
		miniForm.setIsDeleted(0);
		List<MiniForm> list = miniFormService.list(Condition.getQueryWrapper(miniForm));
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入miniForm")
	public R save(@Valid @RequestBody MiniForm miniForm) {
		return R.status(miniFormService.saveForm(miniForm));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入miniForm")
	public R update(@Valid @RequestBody MiniForm miniForm) {
		return R.status(miniFormService.updateById(miniForm));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入miniForm")
	public R submit(@Valid @RequestBody MiniForm miniForm) {
		return R.status(miniFormService.submit(miniForm));
	}


	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入id")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String id) {
		return R.status(miniFormService.removeById(id));
	}


	@PostMapping("/convert")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入miniForm")
	public R convert(@RequestParam String type,  @Valid @RequestBody MiniForm miniForm) {
		try {
			if ("1".equals(type)) {
				converter.convert(miniForm);
				return R.success("");
			} else {
				return R.fail("未找到设计器模板");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
