package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdateField;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAllowUpdateFieldService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizAllowUpdateFieldMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 业务允许修改字段表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
@Service
public class MiniBizAllowUpdateFieldServiceImpl extends ServiceImpl<MiniBizAllowUpdateFieldMapper, MiniBizAllowUpdateField> implements MiniBizAllowUpdateFieldService {


    /**
     * 获取允许修改字段列表
     *
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @return List
     * @author szs
     * @date 2023-12-07
     */
    @Override
    public List<MiniBizAllowUpdateField> getAllowUpdateFieldList(Long miniBizAllowUpdateId) {

        QueryWrapper<MiniBizAllowUpdateField> qw = new QueryWrapper<>();
        qw.eq("mini_biz_allow_update_id", miniBizAllowUpdateId);
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return this.list(qw);
    }


    /**
     * 保存
     *
     * @param field MiniBizAllowUpdateField
     * @author szs
     * @date 2023-12-11
     */
    @Override
    public void submit(MiniBizAllowUpdateField field) {
        // 查询
        MiniBizAllowUpdateField data = getAllowUpdateField(field.getMiniBizAllowUpdateId(), field.getItemCode(), field.getFieldType(), field.getFieldCode(), field.getRowNum());
        if (data == null) {
            // 不存在，就新增
            field.setCreateUser(AuthUtil.getUserId());
            field.setCreateTime(new Date());
            field.setIsDeleted(0);
            boolean bo = this.save(field);
            if (!bo) {
                throw new ServiceException("新增失败");
            }

        } else {
            // 存在，就删除
            boolean bo = this.removeById(data);
            if (!bo) {
                throw new ServiceException("删除失败");
            }

        }

        // 字段类型（1字段，2行，3子项）
        if (field.getFieldType() == null) {
            field.setFieldType(1);
        }

        // 删除其他数据
        deleteData(field.getMiniBizAllowUpdateId(), field.getItemCode(), field.getRowNum(), field.getFieldType());

    }


    /**
     * 删除数据
     *
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @param itemCode             子项编码
     * @param rowNum               行数据
     * @param fieldType            字段类型（1字段，2行，3子项）
     * @author szs
     * @date 2023-12-21
     */
    private void deleteData(Long miniBizAllowUpdateId, String itemCode, Integer rowNum, Integer fieldType) {
        QueryWrapper<MiniBizAllowUpdateField> qw = new QueryWrapper<>();
        qw.eq("mini_biz_allow_update_id", miniBizAllowUpdateId);
        qw.eq("item_code", itemCode);

        if (fieldType == 1) {
            // 如果是字段，那就删除子项+所在行
            qw.and(qw3 -> qw3.eq("field_type", 3).or(qw2 -> qw2.eq("field_type", 2).eq("row_num", rowNum)));

        } else if (fieldType == 2) {
            // 如果是行，那就删除子项+所在行字段
            qw.and(qw3 -> qw3.eq("field_type", 3).or(qw2 -> qw2.eq("field_type", 1).eq("row_num", rowNum)));

        } else if (fieldType == 3) {
            // 如果是子项，那就删除行+字段
            qw.in("field_type", 1, 2);
        }

        qw.eq("is_deleted", 0);
        this.remove(qw);
    }


    /**
     * 获取允许修改字段
     *
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @param itemCode             子项编码
     * @param fieldType            字段类型（1字段，2行，3子项）
     * @param fieldCode            字段编码
     * @param rowNum               数据下标，第几条数据
     * @return MiniBizAllowUpdateField
     * @author szs
     * @date 2023-12-11
     */
    private MiniBizAllowUpdateField getAllowUpdateField(Long miniBizAllowUpdateId, String itemCode, Integer fieldType, String fieldCode, Integer rowNum) {
        QueryWrapper<MiniBizAllowUpdateField> qw = new QueryWrapper<>();
        qw.eq("mini_biz_allow_update_id", miniBizAllowUpdateId);
        qw.eq("item_code", itemCode);
        qw.eq("field_type", fieldType);
        qw.eq("field_code", fieldCode);
        qw.eq("row_num", rowNum);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");
        return this.getOne(qw);
    }


}
