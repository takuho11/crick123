package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAllowUpdateResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 * 业务允许修改表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
public interface MiniBizAllowUpdateMapper extends BaseMapper<MiniBizAllowUpdate> {


    /**
     * 分页列表
     *
     * @param page Page
     * @param qw   QueryWrapper
     * @return IPage
     * @author szs
     * @date 2023-12-11
     */
    @Select("SELECT a.*,\n" +
            "         b.NAME AS miniBizDefName,\n" +
            "         c.VERSION_NAME AS miniBizVersionName\n" +
            "FROM MINI_BIZ_ALLOW_UPDATE a\n" +
            "LEFT JOIN MINI_BIZ_DEF b\n" +
            "    ON b.ID = a.MINI_BIZ_DEF_ID\n" +
            "LEFT JOIN MINI_BIZ_VERSION c\n" +
            "    ON c.ID = a.MINI_BIZ_VERSION_ID\n" +
            "${ew.customSqlSegment}")
    IPage<MiniBizAllowUpdateResult> pageList(Page<MiniBizAllowUpdate> page, @Param("ew") QueryWrapper<MiniBizAllowUpdate> qw);


}
