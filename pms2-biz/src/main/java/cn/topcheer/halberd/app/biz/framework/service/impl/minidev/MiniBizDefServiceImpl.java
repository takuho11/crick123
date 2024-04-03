package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.CopyBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ImportTemplateDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefConfigDTO;
import cn.topcheer.halberd.app.api.minidev.entity.*;
import cn.topcheer.halberd.app.api.minidev.result.*;
import cn.topcheer.halberd.app.api.minidev.service.*;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDefMapper;
import cn.topcheer.pms2.api.annotation.ClobTable;
import cn.topcheer.pms2.api.annotation.MainTable;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 业务模板表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniBizDefServiceImpl extends ServiceImpl<MiniBizDefMapper, MiniBizDef> implements MiniBizDefService {


    @Resource
    private MiniBizDefTabService miniBizDefTabService;

    @Resource
    private MiniBizDefTabItemService miniBizDefTabItemService;

    @Resource
    private MiniBizDefTabItemFieldService miniBizDefTabItemFieldService;

    @Resource
    private MiniSdBizDefService miniSdBizDefService;

    @Resource
    private MiniBizVersionService miniBizVersionService;

    @Resource
    private MiniBizDataInAllowUpdateService miniBizDataInAllowUpdateService;

    @Resource
    private MiniBizAllowUpdateService miniBizAllowUpdateService;

    @Resource
    private MiniBizAllowUpdateFieldService miniBizAllowUpdateFieldService;

    @Resource
    private MiniBizDefHistoryService miniBizDefHistoryService;

    @Resource
    private MiniBizDefConfigService miniBizDefConfigService;


    /**
     * 获取业务模板树
     *
     * @param id           主键ID
     * @param bizVersionId 版本id
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    @Override
    public List<MiniBizDefTabResult> getMiniBizDefTree(Long id, Long bizVersionId) {
        List<MiniBizDefTabResult> list = new ArrayList<>();

        // 查询分页集合
        List<MiniBizDefTab> tabs = getMiniBizDefTabList(id, bizVersionId);
        for (MiniBizDefTab tab : tabs) {
            MiniBizDefTabResult tabResult = new MiniBizDefTabResult();
            BeanUtils.copyProperties(tab, tabResult);

            // 查询子项集合
            List<MiniBizDefTabItem> items = getMiniBizDefTabItemList(tab.getId());
            for (MiniBizDefTabItem item : items) {
                MiniBizDefTabItemResult itemResult = new MiniBizDefTabItemResult();
                BeanUtils.copyProperties(item, itemResult);
                tabResult.getChildren().add(itemResult);
            }

            list.add(tabResult);
        }

        return list;
    }


    /**
     * 获取业务模板配置
     *
     * @param dto MiniBizDefConfigDTO
     * @return MiniBizDefConfigResult
     * @author szs
     * @date 2023-08-22
     */
    @Override
    public MiniBizDefConfigResult getMiniBizDefConfig(MiniBizDefConfigDTO dto) {
        MiniBizDefConfigResult result = new MiniBizDefConfigResult();

        // 查询
        MiniBizDef miniBizDef = this.getById(dto.getId());
        if (miniBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 复制数据
        BeanUtils.copyProperties(miniBizDef, result);

        // 获取业务模板分页列表
        result.setChildren(getMiniBizDefTabResultList(miniBizDef.getId(), dto.getBizVersionId() == null ? miniBizDef.getDefaultVersionId() : dto.getBizVersionId()));

        return result;
    }


    /**
     * 获取业务模板分页列表
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 版本id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniBizDefTabResult> getMiniBizDefTabResultList(Long bizDefId, Long bizVersionId) {
        List<MiniBizDefTabResult> list = new ArrayList<>();

        // 查询分页集合
        List<MiniBizDefTab> tabs = getMiniBizDefTabList(bizDefId, bizVersionId);
        for (MiniBizDefTab tab : tabs) {
            MiniBizDefTabResult tabResult = new MiniBizDefTabResult();
            BeanUtils.copyProperties(tab, tabResult);

            // 查询子项集合
            List<MiniBizDefTabItem> items = getMiniBizDefTabItemList(tab.getId());
            for (MiniBizDefTabItem item : items) {
                MiniBizDefTabItemResult itemResult = new MiniBizDefTabItemResult();
                BeanUtils.copyProperties(item, itemResult);

                // 查询字段集合
                itemResult.setChildren(getMiniBizDefTabItemFieldList(item.getId()));
                tabResult.getChildren().add(itemResult);
            }

            list.add(tabResult);
        }

        return list;
    }


    /**
     * 获取业务模板分页列表
     *
     * @param sdBizDefId   业务模板id
     * @param bizVersionId 版本id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniBizDefTab> getMiniBizDefTabList(Long sdBizDefId, Long bizVersionId) {
        QueryWrapper<MiniBizDefTab> qw = new QueryWrapper<>();
        qw.eq("biz_def_id", sdBizDefId);
        qw.eq("biz_version_id", bizVersionId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniBizDefTabService.list(qw);
    }


    /**
     * 获取业务模板分页子项列表
     *
     * @param sdBizDefTabId 业务模板分页id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniBizDefTabItem> getMiniBizDefTabItemList(Long sdBizDefTabId) {
        QueryWrapper<MiniBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq("biz_def_tab_id", sdBizDefTabId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniBizDefTabItemService.list(qw);
    }


    /**
     * 获取业务模板分页子项字段列表
     *
     * @param bizDefTabItemId 业务模板分页子项id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniBizDefTabItemField> getMiniBizDefTabItemFieldList(Long bizDefTabItemId) {
        QueryWrapper<MiniBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq("biz_def_tab_item_id", bizDefTabItemId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniBizDefTabItemFieldService.list(qw);
    }


    /**
     * 导入标准模板
     *
     * @param dto ImportTemplateDTO
     * @author szs
     * @date 2023-08-23
     */
    @Override
    public void importTemplate(ImportTemplateDTO dto) {
        // 查询
        MiniBizDef miniBizDef = this.getById(dto.getBizDefId());
        if (miniBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 获取标准模板
        MiniSdBizDefConfigResult miniSdBizDefConfig = miniSdBizDefService.getMiniSdBizDefConfig(dto.getSdBizDefId());

        // 组装数据
        List<MiniSdBizDefTabResult> tabResults = new ArrayList<>();
        List<MiniSdBizDefTabItemResult> itemResults = new ArrayList<>();
        List<MiniSdBizDefTabItemField> fieldResults = new ArrayList<>();
        for (MiniSdBizDefTabResult tabResult : miniSdBizDefConfig.getChildren()) {
            // 分页
            tabResults.add(tabResult);

            for (MiniSdBizDefTabItemResult itemResult : tabResult.getChildren()) {
                // 分页子项
                itemResults.add(itemResult);

                // 分页子项字段
                fieldResults.addAll(itemResult.getChildren());
            }
        }

        // 自动生成一个新版本
        MiniBizVersion version = new MiniBizVersion();
        version.setBizDefId(dto.getBizDefId());
        version.setBizTypeId(miniBizDef.getBizTypeId());
        version.setVersionName(miniSdBizDefConfig.getName() + "版本");
        version.setIsDeleted(0);
        version.setCreateUser(AuthUtil.getUserId());
        version.setCreateTime(new Date());
        boolean bo = miniBizVersionService.save(version);
        if (!bo) {
            throw new ServiceException("添加版本失败");
        }

        // 保存业务模板最新版本
        miniBizDef.setDefaultVersionId(version.getId());
        miniBizDef.setDefaultVersionName(version.getVersionName());
        miniBizDef.setUpdateUser(AuthUtil.getUserId());
        miniBizDef.setUpdateTime(new Date());
        bo = this.updateById(miniBizDef);
        if (!bo) {
            throw new ServiceException("更新最新版本失败");
        }

        // 获取并保存分页
        List<MiniBizDefTab> tabs = getAndSaveTabs(tabResults, dto.getBizDefId(), version.getId());

        // 获取并保存分页子项
        List<MiniBizDefTabItem> items = getAndSaveItems(itemResults, tabs);

        // 保存分页子项字段
        saveFields(fieldResults, items);

        // 保存配置
        saveConfig(miniSdBizDefConfig.getConfig(), miniBizDef.getId(), version.getId());

    }


    /**
     * 保存配置
     *
     * @param config          MiniSdBizDefConfig
     * @param newBizDefId     新的业务模板id
     * @param newBizVersionId 新的业务版本id
     * @author szs
     * @date 2024-03-04
     */
    private void saveConfig(MiniSdBizDefConfig config, Long newBizDefId, Long newBizVersionId) {
        if (config == null) {
            return;
        }

        // 新的配置
        MiniBizDefConfig newConfig = new MiniBizDefConfig();
        BeanUtils.copyProperties(config, newConfig);
        newConfig.setBizDefId(newBizDefId);
        newConfig.setBizVersionId(newBizVersionId);
        newConfig.setId(null);
        newConfig.setCreateUser(AuthUtil.getUserId());
        newConfig.setCreateTime(new Date());
        newConfig.setUpdateUser(null);
        newConfig.setUpdateTime(null);
        newConfig.setIsDeleted(0);
        miniBizDefConfigService.save(newConfig);

    }


    /**
     * 获取并保存分页
     *
     * @param sdBizDefTabResults List
     * @param bizDefId           业务模板id
     * @param bizVersionId       版本id
     * @return List
     * @author szs
     * @date 2023-08-23
     */
    private List<MiniBizDefTab> getAndSaveTabs(List<MiniSdBizDefTabResult> sdBizDefTabResults, Long bizDefId, Long bizVersionId) {
        List<MiniBizDefTab> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniSdBizDefTabResult tabResult : sdBizDefTabResults) {
            MiniBizDefTab tab = new MiniBizDefTab();
            BeanUtils.copyProperties(tabResult, tab);
            tab.setBizDefId(bizDefId);
            tab.setBizVersionId(bizVersionId);
            tab.setSdBizDefTabId(tabResult.getId());
            tab.setId(null);
            tab.setCreateUser(userId);
            tab.setCreateTime(date);
            tab.setUpdateUser(null);
            tab.setUpdateTime(null);
            tab.setIsDeleted(0);
            list.add(tab);
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabService.saveBatch(list);
        }

        return list;
    }


    /**
     * 获取并保存分页子项
     *
     * @param itemResults List
     * @param tabs        List
     * @return List
     * @author szs
     * @date 2023-08-23
     */
    private List<MiniBizDefTabItem> getAndSaveItems(List<MiniSdBizDefTabItemResult> itemResults, List<MiniBizDefTab> tabs) {
        List<MiniBizDefTabItem> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniBizDefTab tab : tabs) {
            for (MiniSdBizDefTabItemResult itemResult : itemResults) {
                if (itemResult.getSdBizDefTabId().equals(tab.getSdBizDefTabId())) {
                    MiniBizDefTabItem item = new MiniBizDefTabItem();
                    BeanUtils.copyProperties(itemResult, item);
                    item.setBizDefTabId(tab.getId());
                    item.setSdBizDefTabItemId(itemResult.getId());
                    item.setId(null);
                    item.setCreateUser(userId);
                    item.setCreateTime(date);
                    item.setUpdateUser(null);
                    item.setUpdateTime(null);
                    item.setIsDeleted(0);
                    list.add(item);
                }
            }
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabItemService.saveBatch(list);
        }

        return list;
    }


    /**
     * 保存分页子项
     *
     * @param fieldResults List
     * @param items        List
     * @author szs
     * @date 2023-08-23
     */
    private void saveFields(List<MiniSdBizDefTabItemField> fieldResults, List<MiniBizDefTabItem> items) {
        List<MiniBizDefTabItemField> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniBizDefTabItem item : items) {
            for (MiniSdBizDefTabItemField fieldResult : fieldResults) {
                if (fieldResult.getSdBizDefTabItemId().equals(item.getSdBizDefTabItemId())) {
                    MiniBizDefTabItemField field = new MiniBizDefTabItemField();
                    BeanUtils.copyProperties(fieldResult, field);
                    field.setId(null);
                    field.setBizDefTabItemId(item.getId());
                    field.setSdBizDefTabItemFieldId(fieldResult.getId());
                    field.setCreateUser(userId);
                    field.setCreateTime(date);
                    field.setUpdateUser(null);
                    field.setUpdateTime(null);
                    field.setIsDeleted(0);
                    list.add(field);
                }
            }
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabItemFieldService.saveBatch(list);
        }

    }


    /**
     * 复制
     *
     * @param dto CopyBizDefDTO
     * @author szs
     * @date 2023-08-24
     */
    @Override
    public void copy(CopyBizDefDTO dto) {
        // 查询复制的业务模板
        MiniBizDef copyBizDef = this.getById(dto.getCopyBizDefId());
        if (copyBizDef == null) {
            throw new ServiceException("复制业务模板不存在");
        }

        // 新增新的业务模板
        MiniBizDef newBizDef = new MiniBizDef();
        newBizDef.setBizTypeId(copyBizDef.getBizTypeId());
        newBizDef.setName(dto.getCopyBizDefName());
        newBizDef.setEditComponentType(copyBizDef.getEditComponentType());
        newBizDef.setViewComponentType(copyBizDef.getViewComponentType());
        newBizDef.setEnterpriseId(copyBizDef.getEnterpriseId());
        newBizDef.setIsDeleted(0);
        newBizDef.setCreateUser(AuthUtil.getUserId());
        newBizDef.setCreateTime(new Date());
        boolean bo = this.save(newBizDef);
        if (!bo) {
            throw new ServiceException("复制业务模板失败");
        }

        // 查询复制的版本
        MiniBizVersion copyBizVersion = miniBizVersionService.getById(copyBizDef.getDefaultVersionId());
        if (copyBizVersion == null) {
            return;
        }

        // 新增新的版本
        MiniBizVersion newBizVersion = new MiniBizVersion();
        newBizVersion.setBizDefId(newBizDef.getId());
        newBizVersion.setBizTypeId(newBizDef.getBizTypeId());
        newBizVersion.setVersionName(copyBizVersion.getVersionName());
        newBizVersion.setIsDeleted(0);
        newBizVersion.setCreateUser(AuthUtil.getUserId());
        newBizVersion.setCreateTime(new Date());
        bo = miniBizVersionService.save(newBizVersion);
        if (!bo) {
            throw new ServiceException("复制业务版本失败");
        }

        // 更新新的业务模板的默认版本
        newBizDef.setDefaultVersionId(newBizVersion.getId());
        newBizDef.setDefaultVersionName(newBizVersion.getVersionName());
        newBizDef.setUpdateUser(AuthUtil.getUserId());
        newBizDef.setUpdateTime(new Date());
        bo = this.updateById(newBizDef);
        if (!bo) {
            throw new ServiceException("更新复制业务模板最新版本失败");
        }

        // 复制业务模板数据
        copyBizDefData(copyBizDef.getId(), copyBizVersion.getId(), newBizDef.getId(), newBizVersion.getId());
    }


    /**
     * 复制业务模板数据
     *
     * @param copyBizDefId     复制业务模板id
     * @param copyBizVersionId 复制业务版本id
     * @param newBizDefId      新的业务模板id
     * @param newBizVersionId  新的业务版本id
     * @author szs
     * @date 2023-08-24
     */
    @Override
    public void copyBizDefData(Long copyBizDefId, Long copyBizVersionId, Long newBizDefId, Long newBizVersionId) {
        // 组装数据
        List<MiniBizDefTabResult> tabResults = getMiniBizDefTabResultList(copyBizDefId, copyBizVersionId);
        List<MiniBizDefTabItemResult> itemResults = new ArrayList<>();
        List<MiniBizDefTabItemField> fieldResults = new ArrayList<>();
        for (MiniBizDefTabResult tabResult : tabResults) {
            for (MiniBizDefTabItemResult itemResult : tabResult.getChildren()) {
                // 分页子项
                itemResults.add(itemResult);

                // 分页子项字段
                fieldResults.addAll(itemResult.getChildren());
            }
        }

        // 获取并复制分页
        List<MiniBizDefTab> tabs = getAndCopyTabs(tabResults, newBizDefId, newBizVersionId);

        // 获取并复制分页子项
        List<MiniBizDefTabItem> items = getAndCopyItems(itemResults, tabs);

        // 复制分页子项字段
        copyFields(fieldResults, items);

        // 复制配置
        copyConfig(copyBizDefId, copyBizVersionId, newBizDefId, newBizVersionId);

    }


    /**
     * 复制配置
     *
     * @param copyBizDefId     复制业务模板id
     * @param copyBizVersionId 复制业务版本id
     * @param newBizDefId      新的业务模板id
     * @param newBizVersionId  新的业务版本id
     * @author szs
     * @date 2024-03-04
     */
    private void copyConfig(Long copyBizDefId, Long copyBizVersionId, Long newBizDefId, Long newBizVersionId) {
        // 获取配置
        MiniBizDefConfig config = miniBizDefConfigService.getMiniBizDefConfig(copyBizDefId, copyBizVersionId);
        if (config == null) {
            return;
        }

        // 新的配置
        MiniBizDefConfig newConfig = new MiniBizDefConfig();
        BeanUtils.copyProperties(config, newConfig);
        newConfig.setBizDefId(newBizDefId);
        newConfig.setBizVersionId(newBizVersionId);
        newConfig.setId(null);
        newConfig.setCreateUser(AuthUtil.getUserId());
        newConfig.setCreateTime(new Date());
        newConfig.setUpdateUser(null);
        newConfig.setUpdateTime(null);
        newConfig.setIsDeleted(0);
        miniBizDefConfigService.save(newConfig);

    }


    /**
     * 获取并复制分页
     *
     * @param bizDefTabResults List
     * @param bizDefId         业务模板id
     * @param bizVersionId     版本id
     * @return List
     * @author szs
     * @date 2023-08-24
     */
    private List<MiniBizDefTab> getAndCopyTabs(List<MiniBizDefTabResult> bizDefTabResults, Long bizDefId, Long bizVersionId) {
        List<MiniBizDefTab> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniBizDefTabResult tabResult : bizDefTabResults) {
            MiniBizDefTab tab = new MiniBizDefTab();
            BeanUtils.copyProperties(tabResult, tab);
            // 将旧的tabId存储在CopyBizDefTabId中，用于查询下级子项
            tab.setCopyBizDefTabId(tabResult.getId());
            tab.setBizDefId(bizDefId);
            tab.setBizVersionId(bizVersionId);
            tab.setId(null);
            tab.setCreateUser(userId);
            tab.setCreateTime(date);
            tab.setUpdateUser(null);
            tab.setUpdateTime(null);
            tab.setIsDeleted(0);
            list.add(tab);
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabService.saveBatch(list);
        }

        return list;
    }


    /**
     * 获取并复制分页子项
     *
     * @param itemResults List
     * @param tabs        List
     * @return List
     * @author szs
     * @date 2023-08-24
     */
    private List<MiniBizDefTabItem> getAndCopyItems(List<MiniBizDefTabItemResult> itemResults, List<MiniBizDefTab> tabs) {
        List<MiniBizDefTabItem> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniBizDefTab tab : tabs) {
            for (MiniBizDefTabItemResult itemResult : itemResults) {
                if (itemResult.getBizDefTabId().equals(tab.getCopyBizDefTabId())) {
                    MiniBizDefTabItem item = new MiniBizDefTabItem();
                    BeanUtils.copyProperties(itemResult, item);
                    // 将旧的itemId存储在CopyBizDefTabItemId中，用于查询下级字段
                    item.setCopyBizDefTabItemId(itemResult.getId());
                    item.setBizDefTabId(tab.getId());
                    item.setId(null);
                    item.setCreateUser(userId);
                    item.setCreateTime(date);
                    item.setUpdateUser(null);
                    item.setUpdateTime(null);
                    item.setIsDeleted(0);
                    list.add(item);
                }
            }
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabItemService.saveBatch(list);
        }

        return list;
    }


    /**
     * 复制分页子项字段
     *
     * @param fieldResults List
     * @param items        List
     * @author szs
     * @date 2023-08-24
     */
    private void copyFields(List<MiniBizDefTabItemField> fieldResults, List<MiniBizDefTabItem> items) {
        List<MiniBizDefTabItemField> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniBizDefTabItem item : items) {
            for (MiniBizDefTabItemField fieldResult : fieldResults) {
                if (fieldResult.getBizDefTabItemId().equals(item.getCopyBizDefTabItemId())) {
                    MiniBizDefTabItemField field = new MiniBizDefTabItemField();
                    BeanUtils.copyProperties(fieldResult, field);
                    field.setId(null);
                    field.setBizDefTabItemId(item.getId());
                    field.setCreateUser(userId);
                    field.setCreateTime(date);
                    field.setUpdateUser(null);
                    field.setUpdateTime(null);
                    field.setIsDeleted(0);
                    list.add(field);
                }
            }
        }

        // 批量保存
        if (list.size() > 0) {
            miniBizDefTabItemFieldService.saveBatch(list);
        }

    }


    /**
     * 获取连接器列表
     *
     * @param id 主键ID
     * @return List
     * @author szs
     * @date 2023-08-31
     */
    @Override
    public List<MiniConnector> getMiniConnectorList(Long id) {
        QueryWrapper<MiniBizDef> qw = new QueryWrapper<>();
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.eq("c.is_deleted", 0);
        qw.eq("d.is_deleted", 0);
        qw.eq("c.biz_def_id", id);
        qw.isNotNull("a.req_connector_id");
        qw.and(qw3 -> qw3.eq("a.type", "upload").or(qw2 -> qw2.eq("a.type", "select").eq("a.data_type", "req")));

        return baseMapper.getMiniConnectorList(qw);
    }


    /**
     * 获取业务模板配置
     *
     * @param dto MiniBizDefConfigDTO
     * @return MiniBizDefConfigResult
     * @author szs
     * @date 2023-10-27
     */
    @Override
    public MiniBizDefConfigResult getConfig(MiniBizDefConfigDTO dto) {

        // 获取业务模板配置
        MiniBizDefConfigResult miniBizDefConfig = getMiniBizDefConfig(dto);

        // 遍历处理逻辑
        for (MiniBizDefTabResult tabResult : miniBizDefConfig.getChildren()) {

            // 循环遍历
            for (MiniBizDefTabItemResult itemResult : tabResult.getChildren()) {
                for (MiniBizDefTabItemField field : itemResult.getChildren()) {
                    // 连接器ID处理：reqConnectorId + "-" + id
                    if (StringUtils.isNotBlank(field.getReqConnectorId())) {
                        field.setReqConnectorId(field.getReqConnectorId() + "-" + field.getId());
                    }
                }
            }

            // 重新设置分页的子项集合
            tabResult.setChildren(dealItemResults(tabResult.getChildren()));

        }

        // 获取连接器列表
        miniBizDefConfig.setConnectorList(getMiniConnectorList(dto.getId()));

        // 获取配置
        miniBizDefConfig.setConfig(miniBizDefConfigService.getMiniBizDefConfig(dto.getId(), dto.getBizVersionId()));

        return miniBizDefConfig;
    }


    /**
     * 处理子项集合
     *
     * @param itemResults 子项集合
     * @return List
     * @author szs
     * @date 2023-10-27
     */
    private List<MiniBizDefTabItemResult> dealItemResults(List<MiniBizDefTabItemResult> itemResults) {
        // 主子项，parentId为空
        List<MiniBizDefTabItemResult> mainItemResults = new ArrayList<>();

        // 主子关系Map，parentId->嵌套子项集合
        HashMap<Long, List<MiniBizDefTabItemResult>> childrenMap = new HashMap<>(16);

        // 循环遍历
        for (MiniBizDefTabItemResult itemResult : itemResults) {
            if (itemResult.getParentId() == null) {
                mainItemResults.add(itemResult);

            } else {
                List<MiniBizDefTabItemResult> childrenItemResults = childrenMap.getOrDefault(itemResult.getParentId(), new ArrayList<>());
                childrenItemResults.add(itemResult);
                childrenMap.put(itemResult.getParentId(), childrenItemResults);

            }

        }

        // 加载嵌套子项
        addChildrenSlot(mainItemResults, childrenMap);

        return mainItemResults;
    }


    /**
     * 加载嵌套子项
     *
     * @param mainItemResults 主子项集合
     * @param childrenMap     主子关系Map
     * @author szs
     * @date 2023-10-27
     */
    private void addChildrenSlot(List<MiniBizDefTabItemResult> mainItemResults, HashMap<Long, List<MiniBizDefTabItemResult>> childrenMap) {
        for (MiniBizDefTabItemResult itemResult : mainItemResults) {

            // 获取子集
            List<MiniBizDefTabItemResult> childrenSlot = childrenMap.getOrDefault(itemResult.getId(), new ArrayList<>());
            if (childrenSlot.size() > 0) {
                // 递归下级
                addChildrenSlot(childrenSlot, childrenMap);
            }

            itemResult.setChildrenSlot(childrenSlot);
        }

    }


    /**
     * 检测是否死循环
     *
     * @param addItemResults 已加载的子项集合
     * @param itemResult     子项
     * @author szs
     * @date 2023-10-27
     */
    private void checkIsEndlessLoop(List<MiniBizDefTabItemResult> addItemResults, MiniBizDefTabItemResult itemResult) {
        StringBuilder msg = new StringBuilder();
        boolean isExist = false;
        for (MiniBizDefTabItemResult addItemResult : addItemResults) {
            if (addItemResult.getId().equals(itemResult.getId())) {
                isExist = true;
            }

            if (isExist) {
                if (msg.length() > 0) {
                    msg.append("->");
                }

                msg.append(addItemResult.getShowName());

            }
        }

        if (isExist) {
            msg.append("->");
            msg.append(itemResult.getShowName());
            throw new ServiceException("死循环：" + msg.toString());
        }

        addItemResults.add(itemResult);

    }


    /**
     * 获取业务模板配置-JSON
     *
     * @param dto MiniBizDefConfigDTO
     * @return JSONObject
     * @author szs
     * @date 2023-12-01
     */
    @Override
    public JSONObject getConfigJson(MiniBizDefConfigDTO dto) {
        // 查询
        MiniBizVersion bizVersion = miniBizVersionService.getById(dto.getBizVersionId());
        if (bizVersion == null) {
            throw new ServiceException("暂无数据");
        }

        // 配置JSON
        String configJson = bizVersion.getConfigJson();

        // 如果业务版本中的配置JSON为空，那就获取业务模板配置
        if (StringUtils.isBlank(configJson)) {
            configJson = JSONObject.toJSONString(this.getConfig(dto));
        }

        // 转JOSN
        JSONObject jsonObject = JSONObject.parseObject(configJson);

        // 处理数据-允许修改字段
        dealDataAllowUpdateField(jsonObject, dto.getMainId());

        return jsonObject;
    }


    /**
     * 处理数据-允许修改字段
     *
     * @param jsonObject 配置数据
     * @param mainId     主表id
     * @author szs
     * @date 2023-12-07
     */
    private void dealDataAllowUpdateField(JSONObject jsonObject, String mainId) {
        if (jsonObject == null || StringUtils.isBlank(mainId)) {
            return;
        }

        // 获取中间表
        MiniBizDataInAllowUpdate dataInAllowUpdate = miniBizDataInAllowUpdateService.getDataInAllowUpdate(mainId);
        if (dataInAllowUpdate == null) {
            return;
        }

        // 获取允许修改字段版本
        MiniBizAllowUpdate allowUpdate = miniBizAllowUpdateService.getById(dataInAllowUpdate.getMiniBizAllowUpdateId());
        if (allowUpdate == null) {
            return;
        }

        // 获取允许修改字段列表
        List<MiniBizAllowUpdateField> allowUpdateFieldList = miniBizAllowUpdateFieldService.getAllowUpdateFieldList(dataInAllowUpdate.getMiniBizAllowUpdateId());
        if (allowUpdateFieldList.size() == 0) {
            return;
        }

        if (!jsonObject.containsKey("children")) {
            return;
        }

        // 处理分页
        jsonObject.put("children", dealTabDataAllowUpdateField(jsonObject.getJSONArray("children"), allowUpdateFieldList));

    }


    /**
     * 处理分页数据-允许修改字段
     *
     * @param tabArray             分页数组
     * @param allowUpdateFieldList 允许修改字段列表
     * @return JSONArray
     * @author szs
     * @date 2023-12-07
     */
    private JSONArray dealTabDataAllowUpdateField(JSONArray tabArray, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        // 遍历
        for (int i = 0; i < tabArray.size(); i++) {
            JSONObject tabObject = tabArray.getJSONObject(i);
            if (!tabObject.containsKey("children")) {
                continue;
            }

            // 处理子项
            tabObject.put("children", dealItemDataAllowUpdateField(tabObject.getJSONArray("children"), allowUpdateFieldList));

            // 重置数据
            tabArray.set(i, tabObject);
        }

        return tabArray;
    }


    /**
     * 处理子项数据-允许修改字段
     *
     * @param itemArray            子项数组
     * @param allowUpdateFieldList 允许修改字段列表
     * @return JSONArray
     * @author szs
     * @date 2023-12-07
     */
    private JSONArray dealItemDataAllowUpdateField(JSONArray itemArray, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        // 遍历
        for (int i = 0; i < itemArray.size(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);

            // 如果整个子项都允许修改，那就不用往下继续判断，自定义按钮、字段是否编辑，照原配置不变
            if (checkItemIsAllowUpdate(itemObject.getString("tabItemCode"), allowUpdateFieldList)) {
                continue;
            }

            // 处理自定义按钮，为空
            itemObject.put("customButton", "[]");

            // 处理字段数据-允许修改字段
            if (itemObject.containsKey("children")) {
                if (itemObject.getInteger("dataType") == 3) {
                    JSONObject editFieldRow;

                    // 如果数据类型是列表，那么重置设置子项的编辑字段行，editFieldRow
                    if ("cfv-el-table-list-edit".equals(itemObject.getString("componentTypeEdit"))) {
                        // 经费预算，需要特殊处理
                        editFieldRow = dealItemEditFieldRowJfys(itemObject.getString("tabItemCode"), itemObject.getString("fixedRow"), itemObject.getJSONArray("children"), allowUpdateFieldList);

                    } else {
                        // 其它列表
                        editFieldRow = dealItemEditFieldRow(itemObject.getString("tabItemCode"), itemObject.getJSONArray("children"), allowUpdateFieldList);

                    }

                    itemObject.put("editFieldRow", editFieldRow);

                    // 如果不存在编辑行的白名单设置，那就继续处理字段
//                    if (editFieldRow.getJSONArray("white").size() == 0) {
//                        // 处理字段
//                        itemObject.put("children", dealFieldDataAllowUpdateField(itemObject.getString("tabItemCode"), itemObject.getJSONArray("children"), allowUpdateFieldList));
//
//                    }

                } else {
                    // 处理字段
                    itemObject.put("children", dealFieldDataAllowUpdateField(itemObject.getString("tabItemCode"), itemObject.getJSONArray("children"), allowUpdateFieldList));

                }

            }

            // 子项嵌套，递归
            if (itemObject.containsKey("childrenSlot")) {
                itemObject.put("childrenSlot", dealItemDataAllowUpdateField(itemObject.getJSONArray("childrenSlot"), allowUpdateFieldList));
            }

            // 重置数据
            itemArray.set(i, itemObject);
        }

        return itemArray;
    }


    /**
     * 处理字段数据-允许修改字段
     *
     * @param itemCode             子项编码
     * @param fieldArray           字段数组
     * @param allowUpdateFieldList 允许修改字段列表
     * @return JSONArray
     * @author szs
     * @date 2023-12-07
     */
    private JSONArray dealFieldDataAllowUpdateField(String itemCode, JSONArray fieldArray, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        // 遍历
        for (int i = 0; i < fieldArray.size(); i++) {
            JSONObject fieldObject = fieldArray.getJSONObject(i);

            // 原配置不可编辑，那现在也是不可编辑
            if (!fieldObject.getBoolean("isEdit")) {
                continue;
            }

            // 检查单个字段是否允许修改
            boolean isEdit = checkFieldIsAllowUpdate(itemCode, fieldObject.getString("fieldName"), allowUpdateFieldList);
            fieldObject.put("isEdit", isEdit);

            // 如果不可编辑，那就清空掉字段的自定义按钮
            if (!isEdit) {
                fieldObject.put("customButton", "[]");
            }

            // 处理允许修改字段
            fieldArray.set(i, fieldObject);

        }

        return fieldArray;
    }


    /**
     * 检查整个子项是否允许修改
     *
     * @param itemCode             子项编码
     * @param allowUpdateFieldList 允许修改字段列表
     * @return boolean
     * @author szs
     * @date 2023-12-11
     */
    private boolean checkItemIsAllowUpdate(String itemCode, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        boolean isAllowUpdate = false;

        // 遍历
        for (MiniBizAllowUpdateField allowUpdateField : allowUpdateFieldList) {
            // 字段类型（1字段，2行，3子项）
            if (itemCode.equals(allowUpdateField.getItemCode()) && allowUpdateField.getFieldType() == 3) {
                isAllowUpdate = true;
                break;
            }
        }

        return isAllowUpdate;
    }


    /**
     * 处理子项编辑字段行-经费预算组件
     *
     * @param itemCode             子项编码
     * @param fixedRow             固定行编辑
     * @param fieldArray           字段数组
     * @param allowUpdateFieldList 允许修改字段列表
     * @return JSONObject
     * @author szs
     * @date 2023-12-15
     */
    private JSONObject dealItemEditFieldRowJfys(String itemCode, String fixedRow, JSONArray fieldArray, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        JSONObject object = new JSONObject();
        object.put("editable", false);

        // 获取经费预算编码集合
        List<String> jfysCodeList = getJfysCode(fixedRow);

        // 最大数量
        int maxNum = jfysCodeList.size();

        JSONArray array = new JSONArray();
        // 遍历
        for (MiniBizAllowUpdateField allowUpdateField : allowUpdateFieldList) {
            // 行号或者编码
            String rowNum = maxNum > allowUpdateField.getRowNum() ? jfysCodeList.get(allowUpdateField.getRowNum()) : String.valueOf(allowUpdateField.getRowNum());

            // 字段类型（1字段，2行，3子项）
            if (itemCode.equals(allowUpdateField.getItemCode())) {
                if (allowUpdateField.getFieldType() == 3) {
                    continue;
                }

                if (allowUpdateField.getFieldType() == 2) {
                    // 整行可编辑
                    for (int i = 0; i < fieldArray.size(); i++) {
                        JSONObject fieldObject = fieldArray.getJSONObject(i);
                        array.add(rowNum + "." + fieldObject.getString("fieldName"));
                    }

                } else {
                    // 字段可编辑
                    array.add(rowNum + "." + allowUpdateField.getFieldCode());

                }
            }
        }

        // 设置白名单
        object.put("white", array);

        return object;
    }


    /**
     * 获取经费预算编码集合
     *
     * @param fixedRow 固定编辑行配置参数
     * @return List
     * @author szs
     * @date 2024-03-20
     */
    private List<String> getJfysCode(String fixedRow) {
        List<String> list = new ArrayList<>();
        if (StringUtil.isBlank(fixedRow)) {
            return list;
        }

        // 转JSON
        JSONObject fixedRowJson = JSON.parseObject(fixedRow);
        if (!fixedRowJson.containsKey("structure")) {
            return list;
        }

        // 合计位置
        String considerPosition = fixedRowJson.containsKey("considerPosition") ? fixedRowJson.getString("considerPosition") : "bottom";

        // 转数组
        JSONArray structureArray = fixedRowJson.getJSONArray("structure");

        // 递归获取编码
        recursionJfysCode(structureArray, list);

        if ("top".equals(considerPosition)) {
            list.add(0, "0");
        } else {
            list.add("0");
        }

        return list;
    }


    /**
     * 递归经费预算编码
     *
     * @param array JSONArray
     * @param list  List
     * @author szs
     * @date 2024-03-20
     */
    private void recursionJfysCode(JSONArray array, List<String> list) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject json = array.getJSONObject(i);
            list.add(json.getString("no"));

            if (json.containsKey("children")) {
                // 递归下级
                recursionJfysCode(json.getJSONArray("children"), list);
            }
        }
    }


    /**
     * 处理子项编辑字段行
     *
     * @param itemCode             子项编码
     * @param fieldArray           字段数组
     * @param allowUpdateFieldList 允许修改字段列表
     * @return JSONObject
     * @author szs
     * @date 2023-12-15
     */
    private JSONObject dealItemEditFieldRow(String itemCode, JSONArray fieldArray, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        JSONObject object = new JSONObject();
        object.put("editable", false);

        JSONArray array = new JSONArray();
        // 遍历
        for (MiniBizAllowUpdateField allowUpdateField : allowUpdateFieldList) {
            // 字段类型（1字段，2行，3子项）
            if (itemCode.equals(allowUpdateField.getItemCode())) {
                if (allowUpdateField.getFieldType() == 3) {
                    continue;
                }

                if (allowUpdateField.getFieldType() == 2) {
                    // 整行可编辑
                    for (int i = 0; i < fieldArray.size(); i++) {
                        JSONObject fieldObject = fieldArray.getJSONObject(i);
                        array.add(allowUpdateField.getRowNum() + "." + fieldObject.getString("fieldName"));
                    }

                } else {
                    // 字段可编辑
                    array.add(allowUpdateField.getRowNum() + "." + allowUpdateField.getFieldCode());

                }
            }
        }

        // 设置白名单
        object.put("white", array);

        return object;
    }


    /**
     * 检查单个字段是否允许修改
     *
     * @param itemCode             子项编码
     * @param fieldCode            字段编码
     * @param allowUpdateFieldList 允许修改字段列表
     * @return boolean
     * @author szs
     * @date 2023-12-15
     */
    private boolean checkFieldIsAllowUpdate(String itemCode, String fieldCode, List<MiniBizAllowUpdateField> allowUpdateFieldList) {
        boolean isAllowUpdate = false;

        // 遍历
        for (MiniBizAllowUpdateField allowUpdateField : allowUpdateFieldList) {
            // 字段类型（1字段，2行，3子项）
            if (itemCode.equals(allowUpdateField.getItemCode()) && fieldCode.equals(allowUpdateField.getFieldCode()) && allowUpdateField.getFieldType() == 1) {
                isAllowUpdate = true;
                break;
            }
        }

        return isAllowUpdate;
    }


    /**
     * 转化为配置JSON
     *
     * @param dto MiniBizDefConfigDTO
     * @author szs
     * @date 2023-12-01
     */
    @Override
    public void toConfigJson(MiniBizDefConfigDTO dto) {
        // 从业务版本中获取JSON数据
        MiniBizVersion bizVersion = miniBizVersionService.getById(dto.getBizVersionId());
        if (bizVersion == null) {
            throw new ServiceException("业务版本不存在");
        }

        // 获取业务模板配置
        MiniBizDefConfigResult config = this.getConfig(dto);

        // 判断配置信息
        judgeConfigData(config.getChildren());

        // 更新配置JSON
        bizVersion.setConfigJson(JSONObject.toJSONString(config));

        bizVersion.setUpdateUser(AuthUtil.getUserId());
        bizVersion.setUpdateTime(new Date());

        // 保存
        boolean bo = miniBizVersionService.updateById(bizVersion);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        // 保存历史数据
        miniBizDefHistoryService.addHistoryData(bizVersion.getBizDefId(), bizVersion.getId(), bizVersion.getConfigJson());

    }


    /**
     * 判断配置信息
     *
     * @param tabResults 分页配置数组
     * @author szs
     * @date 2023-12-29
     */
    private void judgeConfigData(List<MiniBizDefTabResult> tabResults) {
        if (tabResults == null || tabResults.size() == 0) {
            return;
        }

        // 对象名，子项配置信息
        HashMap<String, MiniBizDefTabItemResult> objectMap = new HashMap<>();


        // 遍历分页数组
        for (MiniBizDefTabResult tabResult : tabResults) {
            if (tabResult.getChildren() == null || tabResult.getChildren().size() == 0) {
                continue;
            }

            // 处理子项对象配置
            dealItemObjectConfig(objectMap, tabResult.getChildren());

        }

        // 有且只有一个主表对象，并且是表单数据
        List<String> mainTableItems = new ArrayList<>();
        for (String key : objectMap.keySet()) {
            MiniBizDefTabItemResult itemResult = objectMap.get(key);

            if (StringUtils.isBlank(itemResult.getDataMember())) {
                continue;
            }

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(itemResult.getDataMember());

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                throw new ServiceException("数据库表不存在，涉及子项：" + itemResult.getShowName());
            }

            // 主表
            if (tableMappingEnum.getClassname().isAnnotationPresent(MainTable.class)) {
                mainTableItems.add(itemResult.getShowName());

                // 数据类型(1 表单、2 附件、3 列表)"
                if (itemResult.getDataType() != 1) {
                    throw new ServiceException("主表对应子项的数据类型必须是表单，涉及子项：" + itemResult.getShowName());
                }

            }

            // 大字段
            if (tableMappingEnum.getClassname().isAnnotationPresent(ClobTable.class)) {
                // 数据类型(1 表单、2 附件、3 列表)"
                if (itemResult.getDataType() != 1) {
                    throw new ServiceException("大字段对应子项的数据类型必须是表单，涉及子项：" + itemResult.getShowName());
                }

            }
        }

        if (mainTableItems.size() == 0) {
            throw new ServiceException("没有配置主表对应的子项");
        }

        if (mainTableItems.size() > 1) {
            throw new ServiceException("只能配置一个主表对应的子项，涉及子项：" + StringUtils.join(mainTableItems, ","));
        }

    }


    /**
     * 处理子项对象配置
     *
     * @param objectMap   子项配置Map
     * @param itemResults 子项配置数组
     * @author szs
     * @date 2023-12-29
     */
    private void dealItemObjectConfig(HashMap<String, MiniBizDefTabItemResult> objectMap, List<MiniBizDefTabItemResult> itemResults) {
        // 遍历子项数组
        for (MiniBizDefTabItemResult itemResult : itemResults) {
            // 数据类型(1 表单、2 附件、3 列表)
            if (itemResult.getDataType() == 2) {
                // 附件不用考虑
                continue;
            }

            // 判断子项配置Map
            if (objectMap.containsKey(itemResult.getTabItemCode())) {
                MiniBizDefTabItemResult item = objectMap.get(itemResult.getTabItemCode());

                if (!item.getItemType().equals(itemResult.getItemType())) {
                    throw new ServiceException("相同子项编码的初始化类型也需要相同，涉及子项：" + item.getShowName() + "和" + itemResult.getShowName());
                }

                if (!item.getDataType().equals(itemResult.getDataType())) {
                    throw new ServiceException("相同子项编码的数据类型也需要相同，涉及子项：" + item.getShowName() + "和" + itemResult.getShowName());
                }

                if (!item.getDataMember().equals(itemResult.getDataMember())) {
                    throw new ServiceException("相同子项编码的数据库表也需要相同，涉及子项：" + item.getShowName() + "和" + itemResult.getShowName());
                }

            }

            // 添加子项配置Map数据
            objectMap.put(itemResult.getTabItemCode(), itemResult);


            // 如果存在子级，递归处理子项数据
            if (itemResult.getChildrenSlot() != null && itemResult.getChildrenSlot().size() > 0) {
                dealItemObjectConfig(objectMap, itemResult.getChildrenSlot());
            }
        }

    }


    /**
     * 获取对象配置
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return String
     * @author szs
     * @date 2023-12-29
     */
    @Override
    public String getDataObject(Long bizDefId, Long bizVersionId) {
        //  获取业务模板配置-JSON
        MiniBizDefConfigDTO dto = new MiniBizDefConfigDTO();
        dto.setId(bizDefId);
        dto.setBizVersionId(bizVersionId);
        JSONObject configJson = this.getConfigJson(dto);

        // 组装数据
        JSONObject dataObject = new JSONObject();

        // 分页数组
        if (!configJson.containsKey("children")) {
            return "";
        }

        JSONArray tabArray = configJson.getJSONArray("children");
        for (int i = 0; i < tabArray.size(); i++) {
            JSONObject tabObject = tabArray.getJSONObject(i);
            if (!tabObject.containsKey("children")) {
                continue;
            }

            // 处理子项数据对象
            dealItemDataObject(dataObject, tabObject.getJSONArray("children"));

        }

        return JSONObject.toJSONString(dataObject);
    }


    /**
     * 处理子项数据对象
     *
     * @param dataObject 子项数据对象
     * @param itemArray  子项配置数组
     * @author szs
     * @date 2023-12-29
     */
    private void dealItemDataObject(JSONObject dataObject, JSONArray itemArray) {
        for (int i = 0; i < itemArray.size(); i++) {
            // 子项对象
            JSONObject itemObject = itemArray.getJSONObject(i);

            // 对象名
            String itemCode = itemObject.getString("tabItemCode");

            // 新的子项对象，如果对象名已存在，那么字段合并
            JSONObject itemObj = new JSONObject();
            itemObj.put("database", itemObject.getString("dataMember"));
            itemObj.put("type", itemCode);
            itemObj.put("initType", itemObject.getOrDefault("itemType", itemCode));
            itemObj.put("datatype", itemObject.getInteger("dataType") == 3 ? "repeat" : "normal");
            itemObj.put("tablename", itemCode);
            itemObj.put("tablenametext", itemObject.getString("showName"));
            itemObj.put("isloadhis", "normal");
            itemObj.put("isloadaffix", "normal");
            itemObj.put("hisdatasource", new ArrayList<>());
            itemObj.put("isAllCheck", false);

            // 处理子项表单数据
            dealItemData(dataObject, itemObject, itemObj);

            // 添加到总对象中去
            dataObject.put(itemCode, itemObj);

            // 如果存在子级，递归处理子项数据
            if (itemObject.containsKey("childrenSlot")) {
                dealItemDataObject(dataObject, itemObject.getJSONArray("childrenSlot"));
            }

        }

    }


    /**
     * 处理子项表单数据
     *
     * @param dataObject 总数据对象
     * @param itemObject 子项对象
     * @param itemNewObj 子项新数据对象
     * @author szs
     * @date 2024-01-09
     */
    private void dealItemData(JSONObject dataObject, JSONObject itemObject, JSONObject itemNewObj) {
        // 对象名
        String itemCode = itemObject.getString("tabItemCode");

        // 数据类型(1 表单、2 附件、3 列表)
        Integer dataType = itemObject.getInteger("dataType");

        // 下级字段
        JSONArray children = itemObject.getJSONArray("children");

        // 默认表单数据
        JSONObject defaultFormValue = itemObject.containsKey("defaultFormValue") ? itemObject.getJSONObject("defaultFormValue") : new JSONObject();

        // 默认表格数据
        JSONArray defaultTableValue = itemObject.containsKey("defaultTableValue") ? itemObject.getJSONArray("defaultTableValue") : new JSONArray();

        // 已存在的子项
        if (dataObject.containsKey(itemCode)) {
            // 字段配置累加
            children.addAll(dataObject.getJSONObject(itemCode).getJSONArray("children"));

            // 默认数据累加
            if (dataType == 3) {
                // 数组数据，默认表格数据累加
                defaultTableValue.addAll(dataObject.getJSONObject(itemCode).getJSONArray("data"));

            } else {
                // 表单数据，默认表单数据累加
                defaultFormValue.putAll(dataObject.getJSONObject(itemCode).getJSONObject("data"));

            }

        }

        itemNewObj.put("children", children);
        itemNewObj.put("data", dataType == 3 ? defaultTableValue : defaultFormValue);

    }


}
