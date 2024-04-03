package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTab;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabItemService;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniSdBizDefTabItemMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 标准业务分页子项表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniSdBizDefTabItemServiceImpl extends ServiceImpl<MiniSdBizDefTabItemMapper, MiniSdBizDefTabItem> implements MiniSdBizDefTabItemService {


    @Resource
    private MiniSdBizDefTabService miniSdBizDefTabService;


    /**
     * 检测是否死循环
     *
     * @param item MiniSdBizDefTabItem
     * @author szs
     * @date 2023-10-27
     */
    @Override
    public void checkIsEndlessLoop(MiniSdBizDefTabItem item) {
        if (item.getId() == null || item.getParentId() == null) {
            return;
        }

        // 已加载子项集合
        List<MiniSdBizDefTabItem> addItems = new ArrayList<>();
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
    private void isEndlessLoop(List<MiniSdBizDefTabItem> addItems, Long id) {
        // 获取数据
        MiniSdBizDefTabItem item = this.getById(id);
        if (item == null || item.getIsDeleted() == 1) {
            return;
        }

        StringBuilder msg = new StringBuilder();
        boolean isExist = false;
        for (MiniSdBizDefTabItem addItem : addItems) {
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
     * 检测编码是否重复
     *
     * @param miniSdBizDefTabItem MiniSdBizDefTabItem
     * @author szs
     * @date 2023-12-15
     */
    @Override
    public void checkCodeIsRepeat(MiniSdBizDefTabItem miniSdBizDefTabItem) {
        // 获取分页
        MiniSdBizDefTab bizDefTab = miniSdBizDefTabService.getById(miniSdBizDefTabItem.getSdBizDefTabId());
        if (bizDefTab == null) {
            throw new ServiceException("分页不存在");
        }

        // 检测子项编码是否重复
        QueryWrapper<MiniSdBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq("b.sd_biz_def_id", bizDefTab.getSdBizDefId());
        qw.eq("a.tab_item_code", miniSdBizDefTabItem.getTabItemCode());
        qw.ne(miniSdBizDefTabItem.getId() != null, "a.id", miniSdBizDefTabItem.getId());
        qw.eq("b.is_deleted", 0);
        qw.eq("a.is_deleted", 0);
        if (baseMapper.countItem(qw) > 0) {
            throw new ServiceException("子项编码已存在：" + miniSdBizDefTabItem.getTabItemCode());
        }

    }


}
