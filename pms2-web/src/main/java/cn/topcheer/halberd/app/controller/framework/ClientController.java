package cn.topcheer.halberd.app.controller.framework;


import cn.topcheer.halberd.app.api.framework.dto.client.BladeClientDTO;
import cn.topcheer.halberd.app.api.framework.dto.client.UpdatePublicKeyDTO;
import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientResult;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientTreeResult;
import cn.topcheer.halberd.app.api.framework.service.client.BladeClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "客户端-应用", tags = "系统-客户端-应用")
@RequestMapping("/bladeClient")
@Validated
public class ClientController {


    @Resource
    private final BladeClientService bladeClientService;


    /**
     * 获取应用列表
     *
     * @author szs
     * @date 2023-07-21
     */
    @GetMapping("/getClientList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取应用列表", notes = "获取应用列表")
    public R<List<BladeClient>> getClientList() {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);
        // 如果是管理员就查全部，如果非管理员就查自己
        qw.eq(!AuthUtil.isAdmin(), "create_user", AuthUtil.getUserId());

        return R.data(bladeClientService.list(qw));
    }


    /**
     * 分页
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<BladeClient>> list(BladeClientDTO dto, Query query) {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(dto.getName()), "name", dto.getName());
        qw.like(StringUtils.isNotBlank(dto.getModule()), "module", dto.getModule());
        qw.eq(dto.getIsMyCreate() != null && dto.getIsMyCreate() == 1, "create_user", AuthUtil.getUserId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");
        return R.data(bladeClientService.page(Condition.getPage(query), qw));
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-08-07
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody BladeClient bladeClient) {
        if (bladeClient.getId() == null) {
            // 默认启用
            bladeClient.setStatus(2);
            bladeClient.setIsDeleted(0);
        }

        // clientId不能重复
        bladeClientService.checkClientIdIsExist(bladeClient.getClientId(), bladeClient.getId());

        boolean res = bladeClientService.saveOrUpdate(bladeClient);
        if (!res) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 修改公钥
     *
     * @author szs
     * @date 2023-08-07
     */
    @PostMapping("/updatePublicKey")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "修改公钥", notes = "修改公钥")
    public R<String> updatePublicKey(@Valid @RequestBody UpdatePublicKeyDTO dto) {
        // 获取数据
        BladeClient bladeClient = bladeClientService.getById(dto.getId());
        if (bladeClient == null) {
            throw new ServiceException("该应用不存在");
        }

        // 保存
        bladeClient.setPublicKey(dto.getPublicKey());
        boolean res = bladeClientService.updateById(bladeClient);
        if (!res) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 获取全部应用列表
     *
     * @author szs
     * @date 2023-07-21
     */
    @GetMapping("/getAllClientList")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "获取全部应用列表", notes = "获取全部应用列表")
    public R<List<BladeClient>> getAllClientList() {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);

        return R.data(bladeClientService.list(qw));
    }


    /**
     * 获取部门应用树
     *
     * @author szs
     * @date 2023-07-21
     */
    @GetMapping("/getDepartClientTree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取部门应用树", notes = "获取部门应用树")
    public R<List<BladeClientTreeResult>> getDepartClientTree() {

        return R.data(bladeClientService.getDepartClientTree());
    }


    /**
     * 获取应用列表
     *
     * @author szs
     * @date 2023-07-25
     */
    @GetMapping("/getClientResultList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取应用列表", notes = "获取应用列表")
    public R<List<BladeClientResult>> getClientResultList() {

        return R.data(bladeClientService.getClientResultList());
    }


    /**
     * 获取类别+应用树
     *
     * @author szs
     * @date 2023-09-07
     */
    @GetMapping("/getTypeClientTree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取类别+应用树", notes = "获取类别+应用树")
    public R<List<BladeClientTreeResult>> getTypeClientTree() {

        return R.data(bladeClientService.getTypeClientTree());
    }


}
