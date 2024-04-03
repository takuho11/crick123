package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 标准业务分页子项表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniSdBizDefTabItemMapper extends BaseMapper<MiniSdBizDefTabItem> {


    /**
     * 统计子项数量
     *
     * @param qw QueryWrapper
     * @return long
     * @author szs
     * @date 2023-12-15
     */
    @Select("SELECT COUNT(a.id)\n" +
            "FROM MINI_SD_BIZ_DEF_TAB_ITEM a\n" +
            "LEFT JOIN MINI_SD_BIZ_DEF_TAB b\n" +
            "    ON b.id = a.SD_BIZ_DEF_TAB_ID\n" +
            "${ew.customSqlSegment}")
    long countItem(@Param("ew") QueryWrapper<MiniSdBizDefTabItem> qw);


}
