package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTab;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItemField;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabItemFieldService;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabItemService;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDefTabItemMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 业务分页子项表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniBizDefTabItemServiceImpl extends ServiceImpl<MiniBizDefTabItemMapper, MiniBizDefTabItem> implements MiniBizDefTabItemService {


    @Resource
    private MiniBizDefTabService miniBizDefTabService;

    @Resource
    private MiniBizDefTabItemFieldService miniBizDefTabItemFieldService;


    /**
     * 检测是否死循环
     *
     * @param item MiniBizDefTabItem
     * @author szs
     * @date 2023-10-27
     */
    @Override
    public void checkIsEndlessLoop(MiniBizDefTabItem item) {
        if (item.getId() == null || item.getParentId() == null) {
            return;
        }

        // 已加载子项集合
        List<MiniBizDefTabItem> addItems = new ArrayList<>();
        addItems.add(item);

        // 检测是否死循环
        isEndlessLoop(addItems, item.getParentId());
    }


    /**
     * 检测是否死循环
     *
     * @param addItems List 已加载子项集合
     * @param id       主键id
     * @author szs
     * @date 2023-10-27
     */
    private void isEndlessLoop(List<MiniBizDefTabItem> addItems, Long id) {
        // 获取数据
        MiniBizDefTabItem item = this.getById(id);
        if (item == null || item.getIsDeleted() == 1) {
            return;
        }

        StringBuilder msg = new StringBuilder();
        boolean isExist = false;
        for (MiniBizDefTabItem addItem : addItems) {
            if (addItem.getId().equals(item.getId())) {
                isExist = true;
            }

            if (isExist) {
                if (msg.length() > 0) {
                    msg.append("->");
                }

                msg.append(addItem.getShowName());
            }
        }

        if (isExist) {
            msg.append("->");
            msg.append(item.getShowName());
            throw new ServiceException("死循环：" + msg.toString());
        }

        // 递归判断
        addItems.add(item);
        if (item.getParentId() != null) {
            isEndlessLoop(addItems, item.getParentId());
        }

    }


    /**
     * 获取全部子项配置
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return JSONObject
     * @author szs
     * @date 2023-11-17
     */
    @Override
    public JSONObject getAllItemConfig(Long bizDefId, Long bizVersionId) {
        JSONObject object = new JSONObject();

        // 获取全部子项列表
        List<MiniBizDefTabItem> itemList = getAllItemList(bizDefId, bizVersionId);
        for (MiniBizDefTabItem item : itemList) {
            JSONObject itemObj = new JSONObject();
            itemObj.put("database", item.getDataMember());
            itemObj.put("type", item.getTabItemCode());
            itemObj.put("datatype", item.getDataType() == 3 ? "repeat" : "normal");
            itemObj.put("tablename", item.getTabItemCode());
            itemObj.put("tablenametext", item.getShowName());
            itemObj.put("isloadhis", "normal");
            itemObj.put("isloadaffix", "normal");
            itemObj.put("hisdatasource", new ArrayList<>());
            itemObj.put("isAllCheck", false);
            itemObj.put("children", getMiniBizDefTabItemFieldList(item.getId()));
            object.put(item.getTabItemCode(), itemObj);
        }

        return object;
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
     * 获取全部子项列表
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return List
     * @author szs
     * @date 2023-11-17
     */
    private List<MiniBizDefTabItem> getAllItemList(Long bizDefId, Long bizVersionId) {
        QueryWrapper<MiniBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq("b.biz_def_id", bizDefId);
        qw.eq("b.biz_version_id", bizVersionId);
        qw.ne("a.data_type", 2);
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.orderByAsc("b.seq");
        qw.orderByAsc("a.seq");

        return baseMapper.getAllItemList(qw);
    }


    /**
     * 检测编码是否重复
     *
     * @param miniBizDefTabItem MiniBizDefTabItem
     * @author szs
     * @date 2023-12-15
     */
    @Override
    public void checkCodeIsRepeat(MiniBizDefTabItem miniBizDefTabItem) {
        // 获取分页
        MiniBizDefTab bizDefTab = miniBizDefTabService.getById(miniBizDefTabItem.getBizDefTabId());
        if (bizDefTab == null) {
            throw new ServiceException("分页不存在");
        }

        // 检测子项编码是否重复
        QueryWrapper<MiniBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq("b.biz_version_id", bizDefTab.getBizVersionId());
        qw.eq("a.tab_item_code", miniBizDefTabItem.getTabItemCode());
        qw.ne(miniBizDefTabItem.getId() != null, "a.id", miniBizDefTabItem.getId());
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        if (baseMapper.countItem(qw) > 0) {
            throw new ServiceException("子项编码已存在：" + miniBizDefTabItem.getTabItemCode());
        }

    }


}
