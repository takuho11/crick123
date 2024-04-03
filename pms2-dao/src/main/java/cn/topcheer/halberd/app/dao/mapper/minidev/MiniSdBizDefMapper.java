package cn.topcheer.halberd.app.dao.mapper.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDef;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 标准业务模板表 Mapper 接口
 * </p>
 *
 * @author szs
 * @since 2023-08-21
 */
public interface MiniSdBizDefMapper extends BaseMapper<MiniSdBizDef> {


    /**
     * 获取连接器列表
     *
     * @param qw MiniSdBizDef
     * @return List
     * @author szs
     * @date 2023-08-31
     */
    @Select("SELECT concat(a.req_connector_id, '-', a.id) AS id, concat(a.req_connector_id, '-', a.id) AS code , d.name, d.type, d.is_form, \n" +
            "\ta.add_to_runtime, d.url, d.method , d.description , a.before_req_script , a.fix_script , a.data_script\n" +
            "FROM mini_sd_biz_def_tab_item_field a\n" +
            "LEFT JOIN mini_sd_biz_def_tab_item b ON b.id = a.sd_biz_def_tab_item_id\n" +
            "LEFT JOIN mini_sd_biz_def_tab c ON c.id = b.sd_biz_def_tab_id\n" +
            "LEFT JOIN mini_connector d ON d.id = a.req_connector_id\n" +
            "${ew.customSqlSegment}")
    List<MiniConnector> getMiniConnectorList(@Param("ew") QueryWrapper<MiniSdBizDef> qw);


}
