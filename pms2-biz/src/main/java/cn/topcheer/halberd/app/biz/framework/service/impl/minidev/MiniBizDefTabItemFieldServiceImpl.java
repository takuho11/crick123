package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefTabItemFieldDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItemField;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabItemFieldService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDefTabItemFieldMapper;
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
 * 业务分页子项字段表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniBizDefTabItemFieldServiceImpl extends ServiceImpl<MiniBizDefTabItemFieldMapper, MiniBizDefTabItemField> implements MiniBizDefTabItemFieldService {


    /**
     * 批量保存
     *
     * @param dto MiniBizDefTabItemFieldDTO
     * @author szs
     * @date 2023-08-09
     */
    @Override
    public void batchSave(MiniBizDefTabItemFieldDTO dto) {
        // 查询已有的数据, 获取待删除的ids
        QueryWrapper<MiniBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq("biz_def_tab_item_id", dto.getBizDefTabItemId());
        qw.eq("is_deleted", 0);
        List<MiniBizDefTabItemField> list = this.list(qw);
        Set<Long> toDelIds = list.stream().map(MiniBizDefTabItemField::getId).collect(Collectors.toSet());

        String userId = AuthUtil.getUserId();
        Date date = new Date();

        // 遍历处理数据
        for (MiniBizDefTabItemField data : dto.getBizDefTabItemFields()) {
            if (data.getId() != null) {
                toDelIds.remove(data.getId());
            }

            data.setBizDefTabItemId(dto.getBizDefTabItemId());
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
        if (dto.getBizDefTabItemFields().size() > 0) {
            this.saveOrUpdateBatch(dto.getBizDefTabItemFields());
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            this.removeBatchByIds(toDelIds);
        }

    }


}
