package cn.topcheer.halberd.app.dao.mapper.framework.client;

import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientResult;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientTreeResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BladeClientMapper extends BaseMapper<BladeClient> {


    /**
     * 获取部门应用树
     *
     * @param qw QueryWrapper
     * @return list
     * @author szs
     * @date 2023-07-21
     */
    @Select("SELECT a.id AS code,\n" +
            "         a.dept_name AS name,\n" +
            "         group_concat(distinct c.client_id,\n" +
            "         ':' ,c.name ) AS client\n" +
            "FROM sf_dept a\n" +
            "LEFT JOIN sf_user b\n" +
            "    ON b.dept_id = a.id\n" +
            "LEFT JOIN sf_client c\n" +
            "    ON c.create_user = b.id\n" +
            "LEFT JOIN data_resource d\n" +
            "    ON d.source_system = c.client_id\n" +
            "${ew.customSqlSegment}")
    List<BladeClientTreeResult> getDepartClientTree(@Param("ew") QueryWrapper<BladeClient> qw);


    /**
     * 获取应用列表
     *
     * @param qw QueryWrapper
     * @return list
     * @author szs
     * @date 2023-07-25
     */
    @Select("SELECT a.*,\n" +
            "         b.dept_id AS departmentId\n" +
            "FROM sf_client a\n" +
            "LEFT JOIN sf_user b\n" +
            "    ON b.id = a.create_user\n" +
            "${ew.customSqlSegment}")
    List<BladeClientResult> getClientResultList(@Param("ew") QueryWrapper<BladeClient> qw);


    /**
     * 获取类别+应用树
     *
     * @param qw QueryWrapper
     * @return list
     * @author szs
     * @date 2023-09-07
     */
    @Select("SELECT a.dict_key AS code,\n" +
            "         a.dict_value  AS name,\n" +
            "         group_concat(distinct b.client_id,\n" +
            "         ':' , b.name ) AS client\n" +
            "FROM sf_dict_biz a\n" +
            "LEFT JOIN sf_client b ON b.module = a.dict_key \n" +
            "LEFT JOIN data_resource c ON c.source_system = b.client_id\n" +
            "${ew.customSqlSegment}")
    List<BladeClientTreeResult> getTypeClientTree(@Param("ew") QueryWrapper<BladeClient> qw);


}
