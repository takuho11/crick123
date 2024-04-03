package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniSdBizDefTabItemFieldDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabItemFieldService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniSdBizDefTabItemFieldMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 标准业务分页子项字段表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniSdBizDefTabItemFieldServiceImpl extends ServiceImpl<MiniSdBizDefTabItemFieldMapper, MiniSdBizDefTabItemField> implements MiniSdBizDefTabItemFieldService {


    /**
     * 批量保存
     *
     * @param dto MiniSdBizDefTabItemFieldDTO
     * @author szs
     * @date 2023-08-09
     */
    @Override
    public void batchSave(MiniSdBizDefTabItemFieldDTO dto) {
        // 查询已有的数据, 获取待删除的ids
        QueryWrapper<MiniSdBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq("sd_biz_def_tab_item_id", dto.getSdBizDefTabItemId());
        qw.eq("is_deleted", 0);
        List<MiniSdBizDefTabItemField> list = this.list(qw);
        Set<Long> toDelIds = list.stream().map(MiniSdBizDefTabItemField::getId).collect(Collectors.toSet());

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        // 遍历处理数据
        for (MiniSdBizDefTabItemField data : dto.getSdBizDefTabItemFields()) {
            if (data.getId() != null) {
                toDelIds.remove(data.getId());
            }

            data.setSdBizDefTabItemId(dto.getSdBizDefTabItemId());
            data.setIsDeleted(0);

            if (data.getId() == null) {
                // 新增
                data.setCreateTime(date);
                data.setCreateUser(userId);
            } else {
                // 编辑
                data.setUpdateTime(date);
                data.setUpdateUser(userId);
            }

        }

        // 批量保存
        if (dto.getSdBizDefTabItemFields().size() > 0) {
            this.saveOrUpdateBatch(dto.getSdBizDefTabItemFields());
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            this.removeBatchByIds(toDelIds);
        }

    }


}
