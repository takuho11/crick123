package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 业务分页子项表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizDefTabItemMapper extends BaseMapper<MiniBizDefTabItem> {


    /**
     * 获取全部子项列表
     *
     * @param qw QueryWrapper
     * @return List
     * @author szs
     * @date 2023-11-17
     */
    @Select("SELECT a.*\n" +
            "FROM mini_biz_def_tab_item a\n" +
            "LEFT JOIN mini_biz_def_tab b\n" +
            "    ON b.id = a.biz_def_tab_id\n" +
            "${ew.customSqlSegment}")
    List<MiniBizDefTabItem> getAllItemList(@Param("ew") QueryWrapper<MiniBizDefTabItem> qw);


    /**
     * 统计子项数量
     *
     * @param qw QueryWrapper
     * @return long
     * @author szs
     * @date 2023-12-15
     */
    @Select("SELECT COUNT(a.id)\n" +
            "FROM MINI_BIZ_DEF_TAB_ITEM a\n" +
            "LEFT JOIN MINI_BIZ_DEF_TAB b\n" +
            "    ON b.id = a.BIZ_DEF_TAB_ID\n" +
            "${ew.customSqlSegment}")
    long countItem(@Param("ew") QueryWrapper<MiniBizDefTabItem> qw);


}
