/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.web.controller.sys.statistics;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.sys.statistics.SysQueryTable;
import cn.topcheer.pms2.biz.sys.statistics.SysQueryTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping({"/SysQueryTable" })
@Api(value = "通用-附件表",tags = "通用-附件表")
public class SysQueryTableController extends GenericController<SysQueryTable> {
	 	 

	public SysQueryTableService getSysQueryTableService()
	{
		return (SysQueryTableService) this.getGenericService();
	}
	
	@Autowired
	private void setSysQueryTableService(SysQueryTableService service)
	{
		this.setGenericService(service);
	}


	@ApiOperation(value = "查询-根据memo获取数据",notes = "根据memo获取数据")
	@PostMapping("/getQueryTableByMemo")
	public R getQueryTableByMemo(@RequestBody JSONObject json,
								 HttpServletRequest request, HttpServletResponse response) throws Exception{
		return R.data(this.getSysQueryTableService().getQueryTableByMemo(json));
	}

	@ApiOperation(value = "查询-根据类别获取数据",notes = "根据类别获取数据")
	@PostMapping("/getDataByType")
	public R getDataByType(@RequestBody JSONObject json,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
		return R.data(this.getSysQueryTableService().getDataByType(json));
	}

	@ApiOperation(value = "查询-根据id获取数据",notes = "根据id获取数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sourceId",value = "查询id",required = true)})
	@PostMapping("/getDataById")
	public R getDataById(@RequestBody JSONObject json, @RequestParam(value="sourceId") String sourceId,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
		return R.data(this.getSysQueryTableService().getDataById(json,sourceId));
	}

	@ApiOperation(value = "查询-根据id数组获取数据",notes = "根据id数组获取数据")
	@PostMapping("/getDataByIds")
	public R getDataByIds(@RequestBody JSONObject json,
								HttpServletRequest request, HttpServletResponse response) throws Exception{
		return R.data(this.getSysQueryTableService().getDataByIds(json));
	}

}
