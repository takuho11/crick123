package cn.topcheer.halberd.app.dao.mapper.framework.api;

import cn.topcheer.halberd.app.api.framework.dto.api.AmApiDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface ApiMapper extends BaseMapper<AmApi> {

    //    List<Map<String,Object>> findFieldByTableName(@Param(value="value") String sql);
    List<Map<String, Object>> findFieldByTableName(@Param(value = "value") String sql);


    @Select("select d.dept_name as dept_name, s.name as create_user_name, " +
            "(select u.status from am_api_auth u where u.api_id=a.id and u.auth_type='User' and create_user=#{user}) auth_status,a.* " +
            "from am_api a left join sf_user s on s.id=a.update_user left join sf_dept d on s.dept_id=d.id " +
            "${ew.customSqlSegment}")
    IPage<Map<String, Object>> listAndAuth(IPage page, @Param("user") String userId, @Param("ew") QueryWrapper wrapper);


    /**
     * 分页获取申请API列表
     *
     * @param page Page
     * @param qw   QueryWrapper
     * @return IPage
     * @author szs
     * @date 2023-07-26
     */
    @Select("SELECT a.*,\n" +
            "         b.name AS createUserName,\n" +
            "         c.name AS serviceName\n" +
            "FROM am_api a\n" +
            "LEFT JOIN sf_user b ON b.id = a.create_user\n" +
            "LEFT JOIN am_service c ON c.id = a.service_id\n" +
            "${ew.customSqlSegment}")
    IPage<AmApiDTO> getApiList(Page<AmApi> page, @Param("ew") QueryWrapper<AmApi> qw);


}
