package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefHistoryDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefHistory;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefHistoryResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springblade.core.mp.support.Query;


/**
 * <p>
 * 业务定义历史表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2024-02-19
 */
public interface MiniBizDefHistoryMapper extends BaseMapper<MiniBizDefHistory> {



    /**
     * 分页列表
     *
     * @param page Page
     * @param qw   QueryWrapper
     * @return IPage
     * @author szs
     * @date 2024-03-27
     */
    @Select("SELECT a.id,\n" +
            "         a.biz_def_id ,\n" +
            "         a.biz_version_id ,\n" +
            "         a.create_user ,\n" +
            "         a.create_time ,\n" +
            "         b.name AS createUserName\n" +
            "FROM mini_biz_def_history a\n" +
            "LEFT JOIN sys_user b\n" +
            "    ON b.id = a.create_user\n" +
            "${ew.customSqlSegment}")
    IPage<MiniBizDefHistoryResult> pageList(Page<MiniBizDefHistory> page, @Param("ew") QueryWrapper<MiniBizDefHistory> qw);


}
