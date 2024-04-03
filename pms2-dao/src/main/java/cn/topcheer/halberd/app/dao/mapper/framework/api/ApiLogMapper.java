package cn.topcheer.halberd.app.dao.mapper.framework.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApiLog;
import cn.topcheer.halberd.app.api.framework.entity.result.api.AmApiLogResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * API调用日志表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-09-11
 */
public interface ApiLogMapper extends BaseMapper<AmApiLog> {


    /**
     * 分页
     *
     * @param page  分页参数
     * @param qw    查询条件
     * @return IPage
     * @author szs
     * @date 2023-09-11
     */
    @Select("SELECT\n" +
            "a.*,\n" +
            "b.name as apiName,\n" +
            "c.client_secret as clientSecret,\n" +
            "d.name as userName,\n" +
            "d.account as userAccount\n" +
            "FROM\n" +
            "api_log a\n" +
            "LEFT JOIN  am_api b ON b.id = a.api_id\n" +
            "LEFT JOIN  sf_client c ON c.client_id = a.client_id\n" +
            "LEFT JOIN  sf_user d ON d.id = a.user_id\n" +
            "${ew.customSqlSegment}")
    IPage<AmApiLogResult> getList(@Param("page") Page<AmApiLog> page, @Param("ew") QueryWrapper<AmApiLog> qw);


}
