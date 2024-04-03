package cn.topcheer.halberd.app.dao.mapper.framework.api;

import cn.topcheer.halberd.app.api.framework.dto.api.AmApiAuthDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface ApiAuthMapper extends BaseMapper<AmApiAuth> {


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
            "         b.name AS apiName,\n" +
            "         b.type AS apiType,\n" +
            "         b.path AS apiPath,\n" +
            "         c.name AS serviceName,\n" +
            "         d.name AS createUserName\n" +
            "FROM am_api_auth a\n" +
            "LEFT JOIN am_api b ON b.id = a.api_id\n" +
            "LEFT JOIN am_service c ON c.id = b.service_id\n" +
            "LEFT JOIN sf_user d ON d.id = b.create_user\n" +
            "${ew.customSqlSegment}")
    IPage<AmApiAuthDTO> getApiAuthList(Page<AmApiAuth> page, @Param("ew") QueryWrapper<AmApiAuth> qw);


}
