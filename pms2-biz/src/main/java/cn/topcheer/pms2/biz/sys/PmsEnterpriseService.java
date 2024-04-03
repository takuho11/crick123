package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysIdentityService;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysRoleService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.PmsEnterpriseDao;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.bi.BiEntBi;
import cn.topcheer.pms2.api.bi.BiEntRy;
import cn.topcheer.pms2.api.bi.BiMainbase;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.bi.BiEntBiService;
import cn.topcheer.pms2.biz.bi.BiEntRyService;
import cn.topcheer.pms2.biz.bi.BiMainbaseService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:shenxian
 * @othername:liuxue
 * @Date:2024-01-05 09:55
 */
@Service(value = "PmsEnterpriseService")
public class PmsEnterpriseService extends GenericService<PmsEnterprise> {

    public PmsEnterpriseDao getPmsEnterpriseDao(){return (PmsEnterpriseDao) this.getGenericDao();}

    @Autowired
    public void setPmsEnterpriseDao(PmsEnterpriseDao pmsEnterpriseDao){this.setGenericDao(pmsEnterpriseDao);}

    @Autowired
    DBHelper dbHelper;
    @Lazy
    @Autowired
    SysUserService sysUserService;
    @Autowired
    PmsRoleService sysRoleService;
    @Autowired
    SysIdentityService sysIdentityService;
    @Autowired
    private BiMainbaseService biMainbaseService;
    @Autowired
    private BiEntBiService biEntBiService;
    @Autowired
    private BiEntRyService biEntRyService;



    /**
     * @Description 用户注册：通过单位名称或统一社会信用代码查单位，最多查出60条
     * @Author shenxian
     * @Date 2024-01-05
     * @param json
     * @return JSONObject
     * @throws
    */
    public JSONObject findUnitInfoByUnitName(JSONObject json) {
        //目前需求只能查出最多60条数据，过多不显示，防止数据被坏人利用
        JSONObject resJson = new JSONObject();
        String unitName = json.get("unitName") + "";
        //必须有单位管理员才能选择
        String sql = " select t.id,t.name,t.uniformcode,b.REGISTERADDRESS,b.OFFICEADDRESS, t.linkman, t.email, t.mobile, t.telephone from pms_enterprise t " +
                " inner join BI_ENT_BI b on t.id = b.mainid " +
                " where (t.name like ? or t.uniformcode like ?) and t.state = '0' and t.selflevel is null and t.id != '98C57F262CB74E12AAC31C9D37533A17' " +
                " and t.id in (select i.purvieworganizeid from sys_identity i where i.id like '%DWGLY%') " +
                " order by t.name limit 60";
        String total = this.dbHelper.getOnlyStringValue(" select count(1) from pms_enterprise t " +
                        " inner join BI_ENT_BI b on t.id = b.mainid " +
                        " where (t.name like ? or t.uniformcode like ?) and t.state = '0' and t.selflevel is null and t.id != '98C57F262CB74E12AAC31C9D37533A17' " +
                        " and t.id in (select i.purvieworganizeid from sys_identity i where i.id like '%DWGLY%') ",
                new Object[]{"%" + unitName + "%", "%" + unitName + "%"});
        List<Map> pmsEnterpriseList = this.dbHelper.getRows(sql, new Object[]{"%" + unitName + "%", "%" + unitName + "%"});
        resJson.put("total", total);
        resJson.put("unitList", pmsEnterpriseList);
        return resJson;
    }




    /**
     * 【单位注册】
     *
     * @param json
     */
    @Transactional
    public ReturnToJs unitRegister(JSONObject json, ReturnToJs returnToJs) throws Exception {
        JSONObject registerObj = Util.deCodeUseridAndPassword(json.getJSONObject("registerObj"));//前台传来的对象

//        //校验验证码
//        JSONObject judgeValJson = judgeValidCode(registerObj);
//
//        if (!judgeValJson.getBoolean("flag")) {
//            returnToJs.setErrMsg(judgeValJson.getString("reason"));
//            returnToJs.setSuccess(false);
//            return returnToJs;
//        }

        //单位信息注册
        //pms_enterprise表
        PmsEnterprise pmsEnterprise = new PmsEnterprise();
        Util.ApplyObject(pmsEnterprise, registerObj);
        pmsEnterprise.setName(registerObj.get("unitname")+"");
        pmsEnterprise.setState(true);//0表示有效（启用），1表示无效（未启用）
        pmsEnterprise.setBcgx("true");//该字段暂时用不到，和导入数据保持一致
        pmsEnterprise.setCitycasemanagementid(registerObj.getString("citycasemanagementid"));
        pmsEnterprise.setCitycasemanagement(registerObj.getString("citycasemanagement"));
        pmsEnterprise.setCountycasemanagementid(registerObj.getString("countrycasemanagementid"));
        pmsEnterprise.setCountycasemanagement(registerObj.getString("countrycasemanagement"));
        pmsEnterprise.setRegisterdate(new Date());//注册时间

        //港澳台、外籍单位  统一社会信用代码流水生成
        if("是".equals(registerObj.get("isotherunittype"))){
            //查找当前最大的流水号
            String maxCreditcode = this.dbHelper.getOnlyStringValue("select * from (select uniformcode from pms_enterprise " +
                    "where uniformcode like '1000000000000%' order by uniformcode desc) where rownum = 1 ",null);
            System.out.println("maxCreditcode---"+maxCreditcode);

            //最大的流水号+1
            BigInteger max = new BigInteger(maxCreditcode);
            BigInteger num = new BigInteger("1");

            pmsEnterprise.setUniformcode((max.add(num)).toString());
        }

        this.merge(pmsEnterprise);

        //单位管理员信息注册
        //sys_user表
        if (!filterUser(registerObj)){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("注册要求：\\r\\n1、用户名不能带有中文；\\r\\n2、密码必须有数字和字母组成；\\r\\n3、密码中不能包含有用户名；\\r\\n4、根据网络信息安全保护条例用户名请勿使用包含‘admin’、‘root’等管理字样。");
            return returnToJs;
        }
        SysUser user = new SysUser();
        Util.ApplyObject(user, registerObj);
        user.setUserstate(0);// 0:不启用；1:启用
        user.setApprovestate(null);// 空：审核中，1:审核成功，0:审核不成功
        user.setUseridentity("0");//暂时不清楚具体用途，参考之前注册，先保留
        user.setIsfreeze(true);//暂时不清楚具体用途，参考之前注册，先保留
        user.setDefaultSystem("sb");//这个字段要赋值，不然新首页登录会失败
        user.setUserid(user.getUserid());
        user.setPassword(sysUserService.encrypt(user,user.getPassword()));//加密密码
        user.setRegisterdate(new Date());//注册时间
        user.setIsDeleted(0);
        this.sysUserService.merge(user);

        //sys_identity表
        SysIdentity sysIdentity = new SysIdentity();
        sysIdentity.setId(Util.NewGuid());
//        SysRole sysRole = this.sysRoleService.findbyRoleCode("0005");//单位管理员角色
        SysRole sysRole = this.sysRoleService.findbyRoleCode("sjcfr");//数舱法人角色
        if (sysRole != null) {
            sysIdentity.setSysRole(sysRole);//赋予单位管理员角色
        }
        sysIdentity.setSysUser(user);//用户信息关联
        sysIdentity.setPmsEnterprise(pmsEnterprise);//单位信息关联
        sysIdentity.setCreatedate(new Date());//创建时间
        sysIdentity.setEnabled(0);//0:表示申请中，1:表示通过
        sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
        this.sysIdentityService.merge(sysIdentity);

        //BI_MAINBASE
        BiMainbase biMainbase = new BiMainbase();
        biMainbase.setId(user.getId());
        biMainbase.setEnterpriseid(pmsEnterprise.getId());
        biMainbase.setDeclarantid(user.getId());
        biMainbase.setSavedate(new Date());
        biMainbase.setUpdatelasttime(new Date());
        biMainbase.setPlanprojectbatchid("DATAWAREHOUSE-UNIT-REGISTER");
        biMainbase.setPlanprojectid("DATAWAREHOUSE-UNIT");
        biMainbase.setCitycasemanagementid(registerObj.getString("citycasemanagementid"));
        biMainbase.setCitycasemanagement(registerObj.getString("citycasemanagement"));
        biMainbase.setCountrycasemanagementid(registerObj.getString("countrycasemanagementid"));
        biMainbase.setCountrycasemanagement(registerObj.getString("countrycasemanagement"));
        biMainbase.setCountycasemanagementid(registerObj.getString("countrycasemanagementid"));
        biMainbase.setCountycasemanagement(registerObj.getString("countrycasemanagement"));
        this.biMainbaseService.merge(biMainbase);

        //BI_ENT_BI
        BiEntBi biEntBi = new BiEntBi();
        biEntBi.setId(Util.NewGuid());
        biEntBi.setMainid(biMainbase.getId());
        biEntBi.setUnitname(registerObj.getString("unitname"));
        biEntBi.setCreditcode(registerObj.getString("uniformcode"));
        biEntBi.setBelongnation(registerObj.getString("belongnation"));
        biEntBi.setIsindependentlegalman(registerObj.getString("isindependentlegalman"));
        biEntBi.setIsotherunittype(registerObj.getString("isotherunittype"));
        biEntBi.setType("unitInfo_basic");
        biEntBi.setCitycasemanagementid(registerObj.getString("citycasemanagementid"));
        biEntBi.setCitycasemanagement(registerObj.getString("citycasemanagement"));
        biEntBi.setCountrycasemanagementid(registerObj.getString("countrycasemanagementid"));
        biEntBi.setCountrycasemanagement(registerObj.getString("countrycasemanagement"));
        biEntBi.setCasemanagementtype(registerObj.getString("casemanagementtype"));
        this.biEntBiService.merge(biEntBi);

        //BI_ENT_RY
        BiEntRy fr = new BiEntRy();
        fr.setId(Util.NewGuid());
        fr.setMainid(biMainbase.getId());
        fr.setType("member_legal");
        fr.setRytype("法定代表人");
        this.biEntRyService.merge(fr);

        BiEntRy dwlxr = new BiEntRy();
        dwlxr.setId(Util.NewGuid());
        dwlxr.setMainid(biMainbase.getId());
        dwlxr.setType("member_link");
        dwlxr.setRytype("单位联系人");
        dwlxr.setName(registerObj.getString("name"));
        dwlxr.setCertificatename(registerObj.getString("certificatetype"));
        dwlxr.setCertificatenumber(registerObj.getString("certificateno"));
        dwlxr.setMobile(registerObj.getString("mobile"));
        dwlxr.setTelephone(registerObj.getString("contactphone"));
        dwlxr.setEmail(registerObj.getString("email"));
        this.biEntRyService.merge(dwlxr);

        BiEntRy kyfzr = new BiEntRy();
        kyfzr.setId(Util.NewGuid());
        kyfzr.setMainid(biMainbase.getId());
        kyfzr.setType("member_leading");
        kyfzr.setRytype("科研负责人");
        this.biEntRyService.merge(kyfzr);


        returnToJs.setSuccess(true);
        return returnToJs;
    }


    /**
     * 过滤用户名是否带中文
     * 过滤密码是否纯数字
     *
     * @param json
     */
    public boolean filterUser(JSONObject json) {
        if (json.has("userid") && json.has("password")) {
            String userid = json.get("userid") + "";
            String password = json.get("password") + "";
            //判断用户名是否带中文
            if (Util.isContainChinese(userid)) {
                return false;
            }
            //判断密码是否含有数字，字母
            if (!(Util.HasDigit(password) && Util.judgeContainsStr(password))) {
                return false;
            }
            if (userid.toUpperCase().contains("ADMIN") || userid.toUpperCase().contains("ROOT")){
                return false;
            }
            if (password.toUpperCase().contains(userid.toUpperCase())){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断当前注册用户
     * 手机验证码和邮箱验证码是否通过
     *
     * @param json
     * @return
     */
    public JSONObject judgeValidCode(JSONObject json) throws ParseException {
        JSONObject resJson = judgeValidCode(json, "mobile,email");
        return resJson;
    }

    public JSONObject judgeValidCode(JSONObject json, String type) throws ParseException {
        JSONObject resJson = new JSONObject();
        if (type.contains("mobile")) {
            String judgeMobileCode = judgeMobileCode(json);
            if (!Util.isEoN(judgeMobileCode)) {
                resJson.put("flag", false);
                resJson.put("reason", judgeMobileCode);
                return resJson;
            }
        }
        if (type.contains("email")) {
            String judgeEmailCode = judgeEmailCode(json);
            if (!Util.isEoN(judgeEmailCode)) {
                resJson.put("flag", false);
                resJson.put("reason", judgeEmailCode);
                return resJson;
            }
        }
        resJson.put("flag", true);
        resJson.put("reason", "验证通过");
        return resJson;
    }


    public String judgeMobileCode(JSONObject json) throws ParseException {
        Date date = DateUtils.addMinutes(new Date(), -3);
        String mobile = json.getString("mobile");
        String validCode = json.getString("validCode");
        String sql = "select * from PMS_VALIDCODERECORD where mobile = ? order by createdate DESC limit 1";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{mobile});
        if (rows.size() == 0) {
            return "请检查：手机验证码是否正确.";
        }
        String mobiledate = (String) rows.get(0).get("createdate");
        Date mobiledateD = DateUtils.parseDate(mobiledate.substring(0, mobiledate.length() - 2), "yyyy-MM-dd HH:mm:ss");
        if (mobiledateD.getTime() < date.getTime()) {
            return "请检查：手机验证码已超期。";
        } else {
            String content = (String) rows.get(0).get("content");
            if (!content.contains("[" + validCode + "]")) {
                return "请检查：手机验证码是否正确。";
            }
        }
        return "";
    }

    public String judgeEmailCode(JSONObject json) throws ParseException {
        Date date = DateUtils.addMinutes(new Date(), -3);
        String email = json.getString("email");
        String validmailcode = json.getString("validemailcode");
        String sql = "select * from PMS_VALMAILCODERECORD where email = ? order by createdate DESC limit 1";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{email});
        if (rows.size() == 0) {
            return "请检查：邮箱验证码是否正确.";
        }
        String emaildate = (String) rows.get(0).get("createdate");
        Date emaildateD = DateUtils.parseDate(emaildate.substring(0, emaildate.length() - 2), "yyyy-MM-dd HH:mm:ss");
        if (emaildateD.getTime() < date.getTime()) {
            return "请检查：邮箱验证码已超期。";
        } else {
            String content = (String) rows.get(0).get("content");
            if (!content.contains("[" + validmailcode + "]")) {
                return "请检查：邮箱验证码是否正确。";
            }
        }
        return "";
    }


    /**
     * 【注册】---通过统一社会信用代码/组织机构代码查找单位信息
     */
    public JSONObject findUnitInfoByUoCode(JSONObject json) {
        JSONObject resJson = new JSONObject();
        String uoCode = json.get("uoCode") + "";
        //必须有单位管理员才能选择
        String sql = "select t.* from pms_enterprise t where (t.uniformcode = ? or t.orginizationcode = ?) and t.state = '0' and t.selflevel is null " +
                " and t.id in (select i.purvieworganizeid from sys_identity i where i.roleid = 'C7004168-4E0C-4F1F-B341-A225B5644DC5')";
        List<Map> pmsEnterpriseList = this.dbHelper.getRows(sql, new Object[]{uoCode, uoCode});
        if (pmsEnterpriseList != null && pmsEnterpriseList.size() > 0) {
            resJson.put("findUnit", true);//找到单位
            resJson.put("unitInfo", pmsEnterpriseList.get(0));
        } else {
            resJson.put("findUnit", false);//没有找到单位
        }
        return resJson;
    }


    /**
     * 【注册】---验证单位名称是否存在
     */
    public Boolean verifyUnitName(JSONObject json) {
        String unitName = json.get("unitName") + "";
        List<Map> pmsEnterpriseList = this.dbHelper.getRows("select e.id from pms_enterprise e  " +
                " left join sys_identity i on e.id = i.purvieworganizeid " +
                " where i.roleid = 'C7004168-4E0C-4F1F-B341-A225B5644DC5' and e.name = ?", new Object[]{unitName});
        if (pmsEnterpriseList != null && pmsEnterpriseList.size() > 0) {
            return true;//表示存在
        } else {
            return false;//表示不存在
        }
    }



    /**
     * 【注册】---验证统一社会信用代码、组织机构代码是否存在
     */
    public Boolean verifyUoCode(JSONObject json) {
        String uniformcode = json.get("uniformcode") + "";
        String orginizationcode = json.get("orginizationcode") + "";
        boolean uniformcode_flag = false;
        boolean orginizationcode_flag = false;
        List<Map> uniformcodeList = this.dbHelper.getRows("select e.id from pms_enterprise e  " +
                " left join sys_identity i on e.id = i.purvieworganizeid " +
                " where i.roleid = 'C7004168-4E0C-4F1F-B341-A225B5644DC5' and e.uniformcode = ?", new Object[]{uniformcode});

        if (uniformcodeList != null && uniformcodeList.size() > 0) {
            uniformcode_flag = true;//表示存在统一社会信用代码
        }
        List<Map> orginizationcodeList = this.dbHelper.getRows("select e.id from pms_enterprise e  " +
                " left join sys_identity i on e.id = i.purvieworganizeid " +
                " where i.roleid = 'C7004168-4E0C-4F1F-B341-A225B5644DC5' and e.orginizationcode = ?", new Object[]{orginizationcode});
        if (orginizationcodeList != null && orginizationcodeList.size() > 0) {
            orginizationcode_flag = true;//表示存在组织机构代码
        }
        if (uniformcode_flag || orginizationcode_flag) {
            return true;//表示统一社会信用代码或者组织机构代码已经存在
        } else {
            return false;
        }
    }

}
