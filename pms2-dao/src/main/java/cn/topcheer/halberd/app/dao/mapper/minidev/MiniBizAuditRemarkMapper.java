package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAuditRemark;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAuditRemarkResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 业务批注表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
public interface MiniBizAuditRemarkMapper extends BaseMapper<MiniBizAuditRemark> {


    /**
     * 获取全部列表
     *
     * @param qw QueryWrapper
     * @return List
     * @author szs
     * @date 2023-11-16
     */
    @Select("SELECT a.*,\n" +
            "         b.NAME AS createUserName\n" +
            "FROM MINI_BIZ_AUDIT_REMARK a\n" +
            "LEFT JOIN SYS_USER b\n" +
            "    ON b.id = a.CREATE_USER\n" +
            "${ew.customSqlSegment}")
    List<MiniBizAuditRemarkResult> getAllList(@Param("ew") QueryWrapper<MiniBizAuditRemark> qw);


}
