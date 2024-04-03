/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 15:56:31
 */
package cn.topcheer.pms2.biz.bi;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import cn.hutool.core.map.MapUtil;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.SqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.project.vo.PensonalInfoVO;
import net.sf.json.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.bi.*;
import cn.topcheer.pms2.dao.bi.*;

import javax.annotation.Resource;

/**
 * BiTalentBi 服务类
 */
@Service(value = "BiTalentBiService")
public class BiTalentBiService extends GenericService<BiTalentBi> {

    @Resource
    private DBHelper dbHelper;

    public BiTalentBiDao getBiTalentBiDao() {
        return (BiTalentBiDao) this.getGenericDao();
    }

    @Autowired
    public void setBiTalentBiDao(BiTalentBiDao biTalentBiDao) {

        this.setGenericDao(biTalentBiDao);
    }

    public List<BiTalentBi> findByMainid(String mainid) {
        List<BiTalentBi> list = new ArrayList<>();
        list = this.getBiTalentBiDao().findByHql("select t from BiTalentBi t where t.mainid = ?0", mainid);
        return list;
    }

    /**
     * 通过手机查询个人信息
     * @param mobile 手机号
     */
    public PensonalInfoVO findPersonalSelfInfo(String mobile) {
        String sql = "select distinct e.id,\n" +
                "                e.name,\n" +
                "       e.certificatename,\n" +
                "       e.certificatenumber,\n" +
                "       e.gender,\n" +
                "       e.birthday,\n" +
                "       e.nation,\n" +
                "       e.labresearchdirection,\n" +
                "       e.labperson,\n" +
                "       e.labname,\n" +
                "       e.address,\n" +
                "       e.postalcode,\n" +
                "       COALESCE(l.profession, e.major)        as major,\n" +
                "       e.profession,\n" +
                "       e.birthplace,\n" +
                "       e.fax,\n" +
                "       e.mobile,\n" +
                "       e.telephone,\n" +
                "       e.mobile,\n" +
                "       e.telephone,\n" +
                "       e.email,\n" +
                "       e.nationality,\n" +
                "       e.ishavecck,\n" +
                "       l.workplace                            as degreenationality,\n" +
                "       COALESCE(l.degree, e.degree, w.degree) as degree,\n" +
                "       COALESCE(l.degreedate, e.degreedate)   as degreedate,\n" +
                "       COALESCE(l.education, e.education)     as education,\n" +
                "       COALESCE(w.post, e.post)               as education,\n" +
                "       COALESCE(l.enddate, e.graduateddate)   as graduateddate,\n" +
                "       COALESCE(l.unitname, e.workunit)       as graduatedschool,\n" +
                "       COALESCE(spt.code, e.title)            as title\n" +
                "from bi_mainbase t\n" +
                "         left join bi_talent_bi e on e.mainid = t.id\n" +
                "         left join bi_talent_we w on w.mainid = t.id and w.type = 'currentInfo_work'\n" +
                "         left join bi_talent_we l on l.mainid = t.id and l.type = 'currentInfo_learn'\n" +
                "         left join bi_mainbase d\n" +
                "                   on d.enterpriseid = t.enterpriseid and d.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER'\n" +
                "         left join bi_ent_bi b on b.mainid = d.id\n" +
                "         INNER JOIN SYS_PARAMS_TITLE spt ON spt.VALUE = w.DEGREE\n" +
                "where t.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER'" +
                "AND e.mobile =?";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{mobile});
        if (CollectionUtils.isEmpty(rows)) {
            return null;
        }
        Map<String, Object> map = rows.stream().findFirst().get();
        PensonalInfoVO vo = JsonUtil.readValue(JsonUtil.toJson(map), PensonalInfoVO.class);
        return vo;
    }
}
