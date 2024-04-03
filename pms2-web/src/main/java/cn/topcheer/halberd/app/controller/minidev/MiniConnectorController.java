package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniConnectorTreeDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.result.MiniConnectorTreeResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniConnectorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniConnector")
@Api(value = "连接器", tags = "低代码-连接器")
public class MiniConnectorController extends BladeController {


    private final MiniConnectorService miniConnectorService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-24
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<MiniConnector>> all() {
        QueryWrapper<MiniConnector> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");
        return R.data(miniConnectorService.list(qw));
    }


    /**
     * 分页
     *
     * @author szs
     * @date 2023-08-24
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<MiniConnector>> list(MiniConnector miniConnector, Query query) {
        QueryWrapper<MiniConnector> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniConnector.getCode()), "code", miniConnector.getCode());
        qw.like(StringUtils.isNotBlank(miniConnector.getName()), "name", miniConnector.getName());
        qw.eq(StringUtils.isNotBlank(miniConnector.getCategary()), "categary", miniConnector.getCategary());
        qw.eq(miniConnector.getType() != null, "type", miniConnector.getType());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniConnectorService.page(Condition.getPage(query), qw));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2023-08-24
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "详情", notes = "详情")
    public R<MiniConnector> detail(@Valid IdDTO dto) {
        MiniConnector detail = miniConnectorService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-08-24
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody MiniConnector miniConnector) {
        // 保存
        miniConnector.setType(2);
        boolean bo = miniConnectorService.saveOrUpdate(miniConnector);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-08-24
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {
        // 查询
        MiniConnector miniConnector = miniConnectorService.getById(dto.getId());
        if (miniConnector == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        miniConnector.setIsDeleted(1);

        // 保存
        boolean bo = miniConnectorService.updateById(miniConnector);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 获取连接器树形数据
     * 连接器类别+数据二级树
     *
     * @author szs
     * @date 2023-08-30
     */
    @GetMapping("/getMiniConnectorTree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取连接器树形数据", notes = "获取连接器树形数据")
    public R<List<MiniConnectorTreeResult>> getMiniConnectorTree(@Valid MiniConnectorTreeDTO dto) {

        return R.data(miniConnectorService.getMiniConnectorTree(dto));
    }


}
