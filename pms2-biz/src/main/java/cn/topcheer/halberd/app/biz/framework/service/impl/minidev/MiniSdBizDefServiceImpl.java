package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.CopySdBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.entity.*;
import cn.topcheer.halberd.app.api.minidev.result.*;
import cn.topcheer.halberd.app.api.minidev.service.*;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniSdBizDefMapper;
import cn.topcheer.pms2.api.annotation.ClobTable;
import cn.topcheer.pms2.api.annotation.MainTable;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 标准业务模板表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-21
 */
@Service
public class MiniSdBizDefServiceImpl extends ServiceImpl<MiniSdBizDefMapper, MiniSdBizDef> implements MiniSdBizDefService {


    @Resource
    private MiniSdBizDefTabService miniSdBizDefTabService;

    @Resource
    private MiniSdBizDefTabItemService miniSdBizDefTabItemService;

    @Resource
    private MiniSdBizDefTabItemFieldService miniSdBizDefTabItemFieldService;

    @Resource
    private MiniSdBizDefConfigService miniSdBizDefConfigService;


    /**
     * 获取标准模板树
     *
     * @param id 主键ID
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    @Override
    public List<MiniSdBizDefTabResult> getMiniSdBizDefTree(Long id) {
        List<MiniSdBizDefTabResult> list = new ArrayList<>();

        // 查询分页集合
        List<MiniSdBizDefTab> tabs = getMiniSdBizDefTabList(id);
        for (MiniSdBizDefTab tab : tabs) {
            MiniSdBizDefTabResult tabResult = new MiniSdBizDefTabResult();
            BeanUtils.copyProperties(tab, tabResult);

            // 查询子项集合
            List<MiniSdBizDefTabItem> items = getMiniSdBizDefTabItemList(tab.getId());
            for (MiniSdBizDefTabItem item : items) {
                MiniSdBizDefTabItemResult itemResult = new MiniSdBizDefTabItemResult();
                BeanUtils.copyProperties(item, itemResult);
                tabResult.getChildren().add(itemResult);
            }

            list.add(tabResult);
        }

        return list;
    }


    /**
     * 获取标准模板配置
     *
     * @param id 主键ID
     * @return MiniSdBizDefConfigResult
     * @author szs
     * @date 2023-08-22
     */
    @Override
    public MiniSdBizDefConfigResult getMiniSdBizDefConfig(Long id) {
        MiniSdBizDefConfigResult result = new MiniSdBizDefConfigResult();

        // 查询
        MiniSdBizDef miniSdBizDef = this.getById(id);
        if (miniSdBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 复制数据
        BeanUtils.copyProperties(miniSdBizDef, result);

        // 获取标准模板分页列表
        result.setChildren(getMiniSdBizDefTabResultList(miniSdBizDef.getId()));

        // 获取配置
        result.setConfig(miniSdBizDefConfigService.getMiniSdBizDefConfig(id));

        return result;
    }


    /**
     * 获取标准模板分页列表
     *
     * @param sdBizDefId 标准模板id
     * @return List
     * @author szs
     * @date 2023-10-23
     */
    private List<MiniSdBizDefTabResult> getMiniSdBizDefTabResultList(Long sdBizDefId) {
        List<MiniSdBizDefTabResult> list = new ArrayList<>();

        // 查询分页集合
        List<MiniSdBizDefTab> tabs = getMiniSdBizDefTabList(sdBizDefId);
        for (MiniSdBizDefTab tab : tabs) {
            MiniSdBizDefTabResult tabResult = new MiniSdBizDefTabResult();
            BeanUtils.copyProperties(tab, tabResult);

            // 查询子项集合
            List<MiniSdBizDefTabItem> items = getMiniSdBizDefTabItemList(tab.getId());
            for (MiniSdBizDefTabItem item : items) {
                MiniSdBizDefTabItemResult itemResult = new MiniSdBizDefTabItemResult();
                BeanUtils.copyProperties(item, itemResult);

                // 查询字段集合
                itemResult.setChildren(getMiniSdBizDefTabItemFieldList(item.getId()));
                tabResult.getChildren().add(itemResult);
            }

            list.add(tabResult);
        }

        return list;
    }


    /**
     * 获取标准模板分页列表
     *
     * @param sdBizDefId 标准模板id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniSdBizDefTab> getMiniSdBizDefTabList(Long sdBizDefId) {
        QueryWrapper<MiniSdBizDefTab> qw = new QueryWrapper<>();
        qw.eq("sd_biz_def_id", sdBizDefId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniSdBizDefTabService.list(qw);
    }


    /**
     * 获取标准模板分页子项列表
     *
     * @param sdBizDefTabId 标准模板分页id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniSdBizDefTabItem> getMiniSdBizDefTabItemList(Long sdBizDefTabId) {
        QueryWrapper<MiniSdBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq("sd_biz_def_tab_id", sdBizDefTabId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniSdBizDefTabItemService.list(qw);
    }


    /**
     * 获取标准模板分页子项字段列表
     *
     * @param sdBizDefTabItemId 标准模板分页子项id
     * @return List
     * @author szs
     * @date 2023-08-22
     */
    private List<MiniSdBizDefTabItemField> getMiniSdBizDefTabItemFieldList(Long sdBizDefTabItemId) {
        QueryWrapper<MiniSdBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq("sd_biz_def_tab_item_id", sdBizDefTabItemId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return miniSdBizDefTabItemFieldService.list(qw);
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
        QueryWrapper<MiniSdBizDef> qw = new QueryWrapper<>();
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.eq("c.is_deleted", 0);
        qw.eq("d.is_deleted", 0);
        qw.eq("c.sd_biz_def_id", id);
        qw.isNotNull("a.req_connector_id");
        qw.and(qw3 -> qw3.eq("a.type", "upload").or(qw2 -> qw2.eq("a.type", "select").eq("a.data_type", "req")));

        return baseMapper.getMiniConnectorList(qw);
    }


    /**
     * 复制
     *
     * @param dto CopySdBizDefDTO
     * @author szs
     * @date 2023-10-23
     */
    @Override
    public void copy(CopySdBizDefDTO dto) {
        // 查询复制的标准模板
        MiniSdBizDef copySdBizDef = this.getById(dto.getCopySdBizDefId());
        if (copySdBizDef == null) {
            throw new ServiceException("复制标准模板不存在");
        }

        // 新增新的标准模板
        MiniSdBizDef newSdBizDef = new MiniSdBizDef();
        newSdBizDef.setCode(dto.getCopySdBizDefCode());
        newSdBizDef.setName(dto.getCopySdBizDefName());
        newSdBizDef.setName(dto.getCopySdBizDefName());
        newSdBizDef.setBizTypeId(copySdBizDef.getBizTypeId());
        newSdBizDef.setEnterpriseId(copySdBizDef.getEnterpriseId());
        newSdBizDef.setStatus(0);
        newSdBizDef.setIsDeleted(0);
        newSdBizDef.setCreateUser(AuthUtil.getUserId());
        newSdBizDef.setCreateTime(new Date());
        boolean bo = this.save(newSdBizDef);
        if (!bo) {
            throw new ServiceException("复制标准模板失败");
        }


        // 复制标准定义数据
        copySdBizDefData(copySdBizDef.getId(), newSdBizDef.getId());
    }


    /**
     * 复制标准模板数据
     *
     * @param copySdBizDefId 复制标准模板id
     * @param newSdBizDefId  新的标准模板id
     * @author szs
     * @date 2023-10-23
     */
    private void copySdBizDefData(Long copySdBizDefId, Long newSdBizDefId) {
        // 组装数据
        List<MiniSdBizDefTabResult> tabResults = getMiniSdBizDefTabResultList(copySdBizDefId);
        List<MiniSdBizDefTabItemResult> itemResults = new ArrayList<>();
        List<MiniSdBizDefTabItemField> fieldResults = new ArrayList<>();
        for (MiniSdBizDefTabResult tabResult : tabResults) {
            for (MiniSdBizDefTabItemResult itemResult : tabResult.getChildren()) {
                // 分页子项
                itemResults.add(itemResult);

                // 分页子项字段
                fieldResults.addAll(itemResult.getChildren());
            }
        }

        // 获取并复制分页
        List<MiniSdBizDefTab> tabs = getAndCopyTabs(tabResults, newSdBizDefId);

        // 获取并复制分页子项
        List<MiniSdBizDefTabItem> items = getAndCopyItems(itemResults, tabs);

        // 复制分页子项字段
        copyFields(fieldResults, items);

        // 复制配置
        copyConfig(copySdBizDefId, newSdBizDefId);

    }


    /**
     * 复制配置
     *
     * @param copySdBizDefId 复制业务模板id
     * @param newSdBizDefId  新的业务模板id
     * @author szs
     * @date 2024-03-04
     */
    private void copyConfig(Long copySdBizDefId, Long newSdBizDefId) {
        // 获取配置
        MiniSdBizDefConfig config = miniSdBizDefConfigService.getMiniSdBizDefConfig(copySdBizDefId);
        if (config == null) {
            return;
        }

        // 新的配置
        MiniSdBizDefConfig newConfig = new MiniSdBizDefConfig();
        BeanUtils.copyProperties(config, newConfig);
        newConfig.setSdBizDefId(newSdBizDefId);
        newConfig.setId(null);
        newConfig.setCreateUser(AuthUtil.getUserId());
        newConfig.setCreateTime(new Date());
        newConfig.setUpdateUser(null);
        newConfig.setUpdateTime(null);
        newConfig.setIsDeleted(0);
        miniSdBizDefConfigService.save(newConfig);

    }


    /**
     * 获取并复制分页
     *
     * @param sdBizDefTabResults List
     * @param sdBizDefId         标准模板id
     * @return List
     * @author szs
     * @date 2023-10-23
     */
    private List<MiniSdBizDefTab> getAndCopyTabs(List<MiniSdBizDefTabResult> sdBizDefTabResults, Long sdBizDefId) {
        List<MiniSdBizDefTab> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniSdBizDefTabResult tabResult : sdBizDefTabResults) {
            MiniSdBizDefTab tab = new MiniSdBizDefTab();
            BeanUtils.copyProperties(tabResult, tab);
            // 将旧的tabId存储在CopySdBizDefTabId中，用于查询下级子项
            tab.setCopySdBizDefTabId(tabResult.getId());
            tab.setSdBizDefId(sdBizDefId);
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
            miniSdBizDefTabService.saveBatch(list);
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
     * @date 2023-10-23
     */
    private List<MiniSdBizDefTabItem> getAndCopyItems(List<MiniSdBizDefTabItemResult> itemResults, List<MiniSdBizDefTab> tabs) {
        List<MiniSdBizDefTabItem> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniSdBizDefTab tab : tabs) {
            for (MiniSdBizDefTabItemResult itemResult : itemResults) {
                if (itemResult.getSdBizDefTabId().equals(tab.getCopySdBizDefTabId())) {
                    MiniSdBizDefTabItem item = new MiniSdBizDefTabItem();
                    BeanUtils.copyProperties(itemResult, item);
                    // 将旧的itemId存储在CopySdBizDefTabItemId中，用于查询下级字段
                    item.setCopySdBizDefTabItemId(itemResult.getId());
                    item.setSdBizDefTabId(tab.getId());
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
            miniSdBizDefTabItemService.saveBatch(list);
        }

        return list;
    }


    /**
     * 复制分页子项字段
     *
     * @param fieldResults List
     * @param items        List
     * @author szs
     * @date 2023-10-23
     */
    private void copyFields(List<MiniSdBizDefTabItemField> fieldResults, List<MiniSdBizDefTabItem> items) {
        List<MiniSdBizDefTabItemField> list = new ArrayList<>();

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        for (MiniSdBizDefTabItem item : items) {
            for (MiniSdBizDefTabItemField fieldResult : fieldResults) {
                if (fieldResult.getSdBizDefTabItemId().equals(item.getCopySdBizDefTabItemId())) {
                    MiniSdBizDefTabItemField field = new MiniSdBizDefTabItemField();
                    BeanUtils.copyProperties(fieldResult, field);
                    field.setId(null);
                    field.setSdBizDefTabItemId(item.getId());
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
            miniSdBizDefTabItemFieldService.saveBatch(list);
        }

    }


    /**
     * 获取标准模板配置
     *
     * @param id 主键ID
     * @return MiniSdBizDefConfigResult
     * @author szs
     * @date 2023-10-27
     */
    @Override
    public MiniSdBizDefConfigResult getConfig(Long id) {

        // 获取标准模板配置
        MiniSdBizDefConfigResult miniSdBizDefConfig = getMiniSdBizDefConfig(id);

        // 遍历处理逻辑
        for (MiniSdBizDefTabResult tabResult : miniSdBizDefConfig.getChildren()) {

            // 循环遍历
            for (MiniSdBizDefTabItemResult itemResult : tabResult.getChildren()) {
                for (MiniSdBizDefTabItemField field : itemResult.getChildren()) {
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
        miniSdBizDefConfig.setConnectorList(getMiniConnectorList(id));

        return miniSdBizDefConfig;
    }


    /**
     * 处理子项集合
     *
     * @param itemResults 子项集合
     * @return List
     * @author szs
     * @date 2023-10-27
     */
    private List<MiniSdBizDefTabItemResult> dealItemResults(List<MiniSdBizDefTabItemResult> itemResults) {
        // 主子项，parentId为空
        List<MiniSdBizDefTabItemResult> mainItemResults = new ArrayList<>();

        // 主子关系Map，parentId->嵌套子项集合
        HashMap<Long, List<MiniSdBizDefTabItemResult>> childrenMap = new HashMap<>(16);

        // 循环遍历
        for (MiniSdBizDefTabItemResult itemResult : itemResults) {
            if (itemResult.getParentId() == null) {
                mainItemResults.add(itemResult);

            } else {
                List<MiniSdBizDefTabItemResult> childrenItemResults = childrenMap.getOrDefault(itemResult.getParentId(), new ArrayList<>());
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
    private void addChildrenSlot(List<MiniSdBizDefTabItemResult> mainItemResults, HashMap<Long, List<MiniSdBizDefTabItemResult>> childrenMap) {
        for (MiniSdBizDefTabItemResult itemResult : mainItemResults) {

            // 获取子集
            List<MiniSdBizDefTabItemResult> childrenSlot = childrenMap.getOrDefault(itemResult.getId(), new ArrayList<>());
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
    private void checkIsEndlessLoop(List<MiniSdBizDefTabItemResult> addItemResults, MiniSdBizDefTabItemResult itemResult) {
        StringBuilder msg = new StringBuilder();
        boolean isExist = false;
        for (MiniSdBizDefTabItemResult addItemResult : addItemResults) {
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
     * 获取标准模板配置-JSON
     *
     * @param id 主键ID
     * @return JSONObject
     * @author szs
     * @date 2023-12-01
     */
    @Override
    public JSONObject getConfigJson(Long id) {
        // 查询
        MiniSdBizDef miniSdBizDef = this.getById(id);
        if (miniSdBizDef == null) {
            throw new ServiceException("暂无数据");
        }

        // 配置JSON
        String configJson = miniSdBizDef.getConfigJson();

        // 如果业务版本中的配置JSON为空，那就获取业务模板配置
        if (StringUtils.isBlank(configJson)) {
            configJson = JSONObject.toJSONString(this.getConfig(id));
        }

        return JSONObject.parseObject(configJson);
    }


    /**
     * 转化为配置JSON
     *
     * @param id 主键ID
     * @author szs
     * @date 2023-12-01
     */
    @Override
    public void toConfigJson(Long id) {
        // 查询
        MiniSdBizDef miniSdBizDef = this.getById(id);
        if (miniSdBizDef == null) {
            throw new ServiceException("暂无数据");
        }

        // 获取业务模板配置
        MiniSdBizDefConfigResult config = this.getConfig(id);

        // 判断配置信息
        judgeConfigData(config.getChildren());

        // 更新配置JSON
        miniSdBizDef.setConfigJson(JSONObject.toJSONString(config));

        miniSdBizDef.setUpdateUser(AuthUtil.getUserId());
        miniSdBizDef.setUpdateTime(new Date());

        // 保存
        boolean bo = this.updateById(miniSdBizDef);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

    }


    /**
     * 判断配置信息
     *
     * @param tabResults 分页配置数组
     * @author szs
     * @date 2023-12-29
     */
    private void judgeConfigData(List<MiniSdBizDefTabResult> tabResults) {
        if (tabResults == null || tabResults.size() == 0) {
            return;
        }

        // 对象名，子项配置信息
        HashMap<String, MiniSdBizDefTabItemResult> objectMap = new HashMap<>();

        // 遍历分页数组
        for (MiniSdBizDefTabResult tabResult : tabResults) {
            if (tabResult.getChildren() == null || tabResult.getChildren().size() == 0) {
                continue;
            }

            // 处理子项对象配置
            dealItemObjectConfig(objectMap, tabResult.getChildren());

        }

        // 有且只有一个主表对象，并且是表单数据
        List<String> mainTableItems = new ArrayList<>();
        for (String key : objectMap.keySet()) {
            MiniSdBizDefTabItemResult itemResult = objectMap.get(key);

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
    private void dealItemObjectConfig(HashMap<String, MiniSdBizDefTabItemResult> objectMap, List<MiniSdBizDefTabItemResult> itemResults) {
        // 遍历子项数组
        for (MiniSdBizDefTabItemResult itemResult : itemResults) {
            // 数据类型(1 表单、2 附件、3 列表)
            if (itemResult.getDataType() == 2) {
                // 附件不用考虑
                continue;
            }

            // 判断子项配置Map
            if (objectMap.containsKey(itemResult.getTabItemCode())) {
                MiniSdBizDefTabItemResult item = objectMap.get(itemResult.getTabItemCode());
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
     * @param id 主键ID
     * @return String
     * @author szs
     * @date 2023-12-29
     */
    @Override
    public String getDataObject(Long id) {
        //  获取标准模板配置-JSON
        JSONObject configJson = this.getConfigJson(id);

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
            itemObj.put("type", itemObject.getOrDefault("itemType", itemCode));
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
