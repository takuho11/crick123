/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-11-23 16:19:31
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.dao.project.PmsSaveAndInitNewDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * PmsAward 服务类
 */
@Service(value = "PmsSaveNewService")
public class PmsSaveAndInitNewService extends GenericService<PmsProjectbase> {


    @Autowired
    private DBHelper dbHelper;


    public PmsSaveAndInitNewDao getPmsSaveAndInitNewDao() {
        return (PmsSaveAndInitNewDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsSaveAndInitNewDao(PmsSaveAndInitNewDao pmsSaveAndInitNewDao) {
        this.setGenericDao(pmsSaveAndInitNewDao);
    }


    private SysIdentity getSysIdentity(SysUser user) {
        Iterator<SysIdentity> iterator = user.getSysIdentitys().iterator();

        return iterator.hasNext() ? iterator.next() : null;
    }


    /*取用户的相关信息并赋到申报*/
    public JSONObject getUserInfoForSb(SysUser user) {
        JSONObject resJson = new JSONObject();
        String userid = user.getId();
        SysIdentity sysIdentity = getSysIdentity(user);
        String schoolname = sysIdentity != null ? sysIdentity.getPmsEnterprise().getSchoolname() : "";

        String sql = "select e.name,e.certificatename,e.certificatenumber,e.gender,e.birthday,e.nation,e.labresearchdirection, " +
                "l.workplace as degreenationality,l.degree,l.degreedate,l.education,l.enddate as graduatedtime,e.mobile,e.telephone,e.email,e.nationality,e.ishavecck, " +
                "w.post,w.degree,l.profession as major,l.unitname as graduateschool,b.unitname as workunit,b.creditcode," +
                "e.labperson,e.labname,e.address,e.postalcode,e.major as profession,e.profession as eprofession,e.profession as workforprofession,e.fax," +
                "e.mobile,e.telephone,b.unitname as workplace,spt.code as title " +
                "from bi_mainbase t  " +
                "left join bi_talent_bi e on e.mainid = t.id  " +
                "left join bi_talent_we w on w.mainid = t.id and w.type = 'currentInfo_work' " +
                "left join bi_talent_we l on l.mainid = t.id and l.type = 'currentInfo_learn' " +
                "left join bi_mainbase d on d.enterpriseid = t.enterpriseid and d.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER'  " +
                "left join bi_ent_bi b on b.mainid = d.id  " +
                "INNER JOIN  SYS_PARAMS_TITLE spt ON spt.VALUE = w.DEGREE " +
                "where t.declarantid = ? " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER'";

        List<Map> userList = dbHelper.getRows(sql, new Object[]{userid});
        if (userList != null && userList.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            JSONArray jsonArray = JSONArray.fromObject(userList, jsonConfig);
            resJson = jsonArray.getJSONObject(0);
            if (!Util.isEoN(resJson.get("title")) && ("初级员级".equals(resJson.get("title")) || "初级助理级".equals(resJson.get("title")))) {
                resJson.put("title", "初级");
            }
        }


        if (!Util.isEoN(schoolname)) { //是二级学院，找高校本级
            resJson.put("workunit", schoolname);
            resJson.put("creditcode", sysIdentity != null ? sysIdentity.getPmsEnterprise().getPmsParentEnterprise().getUniformcode() : "");
        }

        return resJson;
    }

    /*取用户的单位信息并赋到申报*/
    public JSONObject getUserEnterForSb(SysUser user) {
        JSONObject resJson = new JSONObject();
        JSONObject tempresJson = new JSONObject();

        SysIdentity sysIdentity = getSysIdentity(user);
        String unitid = sysIdentity != null ? sysIdentity.getPmsEnterprise().getId() : "";
        String schoolname = sysIdentity != null ? sysIdentity.getPmsEnterprise().getSchoolname() : "";

        if (!Util.isEoN(schoolname)) { //是二级学院，找高校本级
            String tempsql = "select r.id as citycasemanagementid,r.name as citycasemanagement," +
                    "e.unitname,e.creditcode " +
                    "from bi_mainbase t  " +
                    "left join bi_ent_bi e on e.mainid = t.id  " +
                    "left join pms_enterprise w on w.id = t.enterpriseid " +
                    "left join pms_enterprise r on r.id = w.parentid " +
                    "where t.enterpriseid = ? and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER'";

            List<Map> userList = dbHelper.getRows(tempsql, new Object[]{unitid});
            if (userList != null && userList.size() > 0) {
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
                JSONArray jsonArray = JSONArray.fromObject(userList, jsonConfig);
                tempresJson = jsonArray.getJSONObject(0);
            }


            unitid = sysIdentity != null ? sysIdentity.getPmsEnterprise().getPmsParentEnterprise().getId() : "";
        }


        String sql = "select u.name as lxr_name,u.certificatenumber as lxr_certificatenumber,u.certificatename as lxr_certificatename," +
                "u.mobile as lxr_mobile,u.telephone as lxr_telephone,u.telephone as lxr_officephone,u.fax,e.zgtotalnum as employeenum," +
                "to_char(e.establishdate,'yyyy-MM-dd') as establishdate,e.registercapital as registercaptial,e.yfrytotalnum as researchernum," +
                "u.email as lxr_email,u.administrativeduty as lxr_duty,e.yjcxjdname,e.foreignpercent,e.isyfjg," +
                "u1.name as legal_name, u1.certificatenumber as legal_certificatenumber, u1.certificatename as legal_certificatename, " +
                "u1.mobile as legal_mobile, u1.telephone as legal_telephone, u1.email as legal_email,u1.administrativeduty as legal_duty," +
                "u2.name as finance_name,u2.certificatenumber as finance_certificatenumber, u2.certificatename as finance_certificatename, " +
                "u2.mobile as finance_mobile,u2.telephone as finance_telephone,u3.name as scienceleadername,u3.telephone as scienceleadertel," +
                "u2.telephone as finance_officephone,u2.email as finance_email,u2.administrativeduty as finance_duty," +
                "u1.name as sciencecontactname,u1.mobile as sciencecontactmobile,u1.email as sciencecontactemail,u1.fax as sciencecontactfax," +
                "e.unitname,e.creditcode,e.establishdate,e.unittype, " +
                "t.enterpriseid, w.name as enterprisename," +
                "w.id as countycasemanagementid, w.name as countycasemanagement, w.uniformcode as countycasemanagementcreditcode," +
                "r.id as citycasemanagementid, r.name as citycasemanagement, r.uniformcode as citycasemanagementcreditcode," +
                "e.registeraddress,e.officeaddress,e.officeaddress as commintucateaddress,e.accountname,e.bankname,e.bankaccount,e.simpleunittype,e.belongnation," +
                "e.province,e.province belongprovince,e.city,e.city belongcity,e.county,e.county belongcounty,e.gxqyno,e.kjxzxqyno," +
                "e.islistedqy as islist,e.listedsector,e.enterprisescale,e.isgxqy as ishigh,e.gxqyenddate,e.isgxqnqy,e.belonggxq," +
                "e.isgxqnqy as isingxq,e.belonggxq as gxqname,e.gxqtype,e.postalcode," +
                "e.bankprovince,e.bankcity,e.bankcounty,e.bankaddress,e.bankno,(case when e.isgxqy = '是' and e.iskjxzxqy = '是' then '高新技术企业,科技型中小企业' " +
                "when e.isgxqy = '是' then '高新技术企业' when e.iskjxzxqy = '是' then '科技型中小企业' else '' end) as enterprisetypenew " +
                "from bi_mainbase t  " +
                "left join bi_ent_bi e on e.mainid = t.id  " +
                "left join bi_ent_ry u on u.mainid = t.id and u.rytype = '单位联系人' " +
                "left join bi_ent_ry u1 on u1.mainid = t.id and u1.rytype = '法定代表人' " +
                "left join bi_ent_ry u2 on u2.mainid = t.id and u2.rytype = '财务负责人' " +
                "left join bi_ent_ry u3 on u3.mainid = t.id and u3.rytype = '科研负责人' " +
                "left join pms_enterprise w on w.id = t.enterpriseid " +
                "left join pms_enterprise r on r.id = w.parentid " +
                "where t.enterpriseid = ? and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER'";

        List<Map> userList = dbHelper.getRows(sql, new Object[]{unitid}, false);
        if (userList != null && userList.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            JSONArray jsonArray = JSONArray.fromObject(userList, jsonConfig);
            resJson = jsonArray.getJSONObject(0);
        }

        String platformsql = "select replace(wm_concat(e.carriername||'('||e.carriertype||')'),',',';') as researchplatform " +
                "from bi_mainbase t " +
                "left join bi_c_bi e on e.mainid = t.id " +
                "where t.enterpriseid = ? and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER'";
        String platform = this.dbHelper.getOnlyStringValue(platformsql, new Object[]{unitid});
        resJson.put("researchplatform", "()".equals(platform) ? "无" : platform);

        String benefitsql = "select top 3 t.* from(select e.id,round(e.businessincome,2) as businessincome," +
                "round(e.mainbusinessincome,2) as mainbusinessincome,round(e.totalprofit,2) as profit," +
                "round(e.totalassets,2) as totalassets,round(e.netassets,2) as netassets,round(e.rdfund,2) as rdfund," +
                "round(e.rdfundpercent,2) as rdfundpercent,e.year,e.officename,e.creditcode,e.auditreportno," +
                "round(e.ownerequity,2) as ownerequity,round(e.totalliability,2) as totalliability," +
                "round(e.totalrevenue,2) as totalrevenue,round(e.totalprofit,2) as totalprofit, " +
                "round(e.cashbusiness,2) as cashbusiness,round(e.cashinvestment,2) as cashinvestment," +
                "round(e.earnforeignexchange,2) as earnforeignexchange,round(e.salesincome,2) as salesincome," +
                "round(e.paidtax,2) as paidtax,round(e.totalprofit,2) as netprofit,round(e.assetsliabilityratio,2) as tdr," +
                "round(e.grownetprofit,2) as grownetprofit,round(e.flowrate,2) as flowrate," +
                "e.reportdate,e.zkxbjvalue,e.zkxbj " +
                "from bi_mainbase t  " +
                "left join bi_ent_fss e on e.mainid = t.id  " +
                "where t.enterpriseid = ? and e.year is not null " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER' order by e.year desc) t ";

        List<Map> benefitList = this.dbHelper.getRows(benefitsql, new Object[]{unitid}, false);
        resJson.put("benefitList", benefitList);

        String productsql = "select e.id,e.name,e.percentinsale  " +
                "from bi_mainbase t  " +
                "left join bi_ent_cp e on e.mainid = t.id  " +
                "where t.enterpriseid = ?  " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER' order by e.seq ";

        List<Map> productList = this.dbHelper.getRows(productsql, new Object[]{unitid}, false);
        resJson.put("productList", productList);

        String achsql = "select e.id,e.achievementname,e.selfevaluate  " +
                "from bi_mainbase t  " +
                "left join bi_ent_ach e on e.mainid = t.id  " +
                "where t.enterpriseid = ?  " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER' order by e.seq ";

        List<Map> achList = this.dbHelper.getRows(achsql, new Object[]{unitid}, false);
        resJson.put("achList", achList);

        String ossql = "select e.id,e.shareholder,e.equityratio  " +
                "from bi_mainbase t  " +
                "left join bi_ent_os e on e.mainid = t.id  " +
                "where t.enterpriseid = ?  " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER' order by e.seq ";

        List<Map> osList = this.dbHelper.getRows(ossql, new Object[]{unitid}, false);
        resJson.put("osList", osList);

        resJson.put("collegeObj", tempresJson);

        return resJson;
    }


    /*取用户的单位信息--财务负责人并赋到申报*/
    public JSONObject getUserEnterCwForSb(SysUser user) {
        JSONObject resJson = new JSONObject();
        String userid = user.getId();
        List<Map> userList = dbHelper.getRows("select pe.treasury_name as name,pe.treasury_tel as telphone,pe.treasury_phone as mobilephone" +
                " from sys_identity i left join sys_user sy on sy.id=i.userid" +
                " inner join pms_enterprise pe on pe.id=i.purvieworganizeid" +
                " inner join sys_identity si on si.purvieworganizeid=pe.id and si.roleid='C7004168-4E0C-4F1F-B341-A225B5644DC5' " +
                " inner join sys_user sy2 on sy2.id=si.userid" +
                " where sy.id =?  and rownum<2", new Object[]{userid});
        if (userList != null && userList.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            JSONArray jsonArray = JSONArray.fromObject(userList, jsonConfig);
            resJson = jsonArray.getJSONObject(0);
        }
        return resJson;
    }

    /**
     * 获取用户的工作经历
     * @param user
     * @return
     */
    public JSONArray getUserWorkExpSb(SysUser user) {
        JSONArray resJsonArr = new JSONArray();
        String userid = user.getId();
        List<Map> workExpList = dbHelper.getRows("select w.startdate,w.enddate,w.unitname as name,w.post,spt.CODE as title " +
                "FROM BI_MAINBASE t " +
                "LEFT JOIN BI_TALENT_WE w ON w.MAINID = t.ID " +
                "INNER JOIN  SYS_PARAMS_TITLE spt ON spt.VALUE = w.DEGREE " +
                "WHERE  w.TYPE = 'experience_work' AND t.DECLARANTID =? " +
                "and t.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER'", new Object[]{userid});
        if (workExpList != null && workExpList.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            resJsonArr = JSONArray.fromObject(workExpList, jsonConfig);
        }
        return resJsonArr;
    }

    /**
     * 获取用户的学习经历
     * @param user
     * @return
     */
    public JSONArray getUserEducationExpSb(SysUser user) {
        JSONArray resJsonArr = new JSONArray();
        String userid = user.getId();
        List<Map> educationExpList = dbHelper.getRows("select w.degreedate as graduatedtime,q.cnname as graduatedschool,w.profession as major,w.degree " +
                "from " +
                "bi_mainbase t " +
                "left join bi_talent_we w on w.mainid = t.id " +
                "left join qsuniversity q on q.id  = w.unitname " +
                "where  w.type = 'experience_learn' and t.declarantid = ? and t.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER'", new Object[]{userid});
        if (educationExpList != null && educationExpList.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            resJsonArr = JSONArray.fromObject(educationExpList, jsonConfig);
        }
        return resJsonArr;
    }


}
