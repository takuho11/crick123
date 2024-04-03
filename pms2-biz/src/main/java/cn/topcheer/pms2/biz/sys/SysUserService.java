package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysUserDao;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.bi.*;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.bi.*;
import cn.topcheer.pms2.biz.message.MessageService;
import cn.topcheer.pms2.biz.utils.Base64Convert;
import cn.topcheer.pms2.biz.utils.Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SM3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "sysUserService")
public class SysUserService extends GenericService<SysUser> {
    public SysUserDao getSysUserDao() {
        return (SysUserDao) this.getGenericDao();
    }

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private void setSysUserDao(SysUserDao userDao) {
        this.setGenericDao(userDao);
    }

    @Autowired
    private SysIdentityService sysIdentityService;
    @Autowired
    private PmsRoleService sysRoleService;
    @Autowired
    private PmsEnterpriseService pmsEnterpriseService;
    @Autowired
    private BiMainbaseService biMainbaseService;
    @Autowired
    private BiTalentBiService biTalentBiService;
    @Autowired
    private BiTalentWeService biTalentWeService;
    @Autowired
    private BiEntBiService biEntBiService;
    @Autowired
    private BiEntRyService biEntRyService;
    @Autowired
    private MessageService messageService;


    //校验手机是否合规 2020年最全的国内手机号格式
    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";

    /**
     * 获取当前登录用户信息
     */
    public SysUser getCurrentUser() {
        SysUser user_ = this.findById(AuthUtil.getUserId());
        return this.findById(user_.getId());//不这么处理，可能外键上会存在问题
    }

    /**
     * 获取当前登录用户的角色id集合
     */
    public String getCurrentUserRoleids(String userid) {
        return this.dbHelper.getOnlyStringValue("select listagg(roleid) as roleids " +
                " from sys_identity where userid = ? group by userid", new Object[]{userid});
    }

    /**
     * @param json
     * @return Boolean
     * @throws
     * @Description 根据用户名判断用户是否存在
     * @Author shenxian
     * @Date 2024-01-05
     */
    public Boolean verifyUserid(JSONObject json) {
        String userid = json.get("userid") + "";
        List<SysUser> userList = this.findByProperty("userid", userid);
        if (userList != null && userList.size() > 0) {
            return true;//表示存在
        } else {
            return false;//表示不存在
        }
    }


    /**
     * @param json
     * @return Boolean
     * @throws
     * @Description 根据证件号判断用户是否存在
     * @Author shenxian
     * @Date 2024-01-05
     */
    public Boolean verifyCertificateno(JSONObject json) {
        String certificateno = json.get("certificateno") + "";
        String roleid = json.get("roleid") + "";
        List<Map> userList = this.dbHelper.getRows("select u.id from sys_user u " +
                " left join sys_identity i on u.id = i.userid " +
                " where UPPER(u.certificateno) = ? and i.roleid = ? ", new Object[]{certificateno.toUpperCase(), roleid});
        if (userList != null && userList.size() > 0) {
            return true;//表示存在
        } else {
            return false;//表示不存在
        }
    }

    /**
     * @param json
     * @return Boolean
     * @throws
     * @Description 根据手机号判断用户是否存在
     * @Author shenxian
     * @Date 2024-01-05
     */
    public Boolean verifyMobile(JSONObject json) {
        String mobile = json.get("mobile") + "";
        String roleid = json.get("roleid") + "";
        List<Map> userList = this.dbHelper.getRows("select u.id from sys_user u " +
                " left join sys_identity i on u.id = i.userid " +
                " where u.mobile = ? and i.roleid = ? ", new Object[]{mobile, roleid});
        if (userList != null && userList.size() > 0) {
            return true;//表示存在
        } else {
            return false;//表示不存在
        }
    }

    /**
     * @param json
     * @return Boolean
     * @throws
     * @Description 根据邮箱判断用户是否存在
     * @Author shenxian
     * @Date 2024-01-05
     */
    public Boolean verifyEmail(JSONObject json) {
        String email = json.get("email") + "";
        String roleid = json.get("roleid") + "";
        List<Map> userList = this.dbHelper.getRows("select u.id from sys_user u " +
                " left join sys_identity i on u.id = i.userid " +
                " where u.userstate = 1 and u.email = ? and i.roleid = ? ", new Object[]{email, roleid});
        if (userList != null && userList.size() > 0) {
            return true;//表示存在
        } else {
            return false;//表示不存在
        }
    }


    public String encrypt(SysUser user, String password) {
        if (user.getId() == null) // 初始用户
        {
            user.setSalt(UUID.randomUUID().toString());
        } else {
            SysUser cUser = getById(user.getId());
            if (cUser == null) {
                user.setSalt(UUID.randomUUID().toString());
            } else {
                if (Func.isEmpty(cUser.getSalt())) {
                    user.setSalt(UUID.randomUUID().toString());
                }
            }
        }
        return encryptSha256(DigestUtil.sha256Hex(password), user.getSalt());
    }


    public String encryptSha256(String password256, String salt) {
        return SM3Utils.encrypt(password256 + salt);
    }

    @Transactional(rollbackFor = Exception.class)
    public ReturnToJs userRegister(JSONObject json, ReturnToJs returnToJs) throws Exception {
        JSONObject registerObj = Util.deCodeUseridAndPassword(json.getJSONObject("registerObj"));//前台传来的对象

        String unitid = registerObj.get("enterpriseid").toString();
        PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(unitid);

        //单位管理员信息注册
        //sys_user表
        if (!this.pmsEnterpriseService.filterUser(registerObj)) {
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
        user.setPassword(this.encrypt(user, user.getPassword()));//加密密码
        user.setRegisterdate(new Date());//注册时间
        user.setIsDeleted(0);
        this.merge(user);

//        SysRole role = this.sysRoleService.findById("129947C6-94DC-4A7D-84D2-E78A80E518A3");//普通用户
        SysRole role = this.sysRoleService.findById("f8a87c80-f89d-48bc-ad96-840ab6aa81b2");//数舱个人
        this.sysIdentityService.generateIdentity(user, pmsEnterprise, role);

        //bi_mainbase
        BiMainbase biMainbase = new BiMainbase();
        biMainbase.setId(user.getId());
        biMainbase.setEnterpriseid(pmsEnterprise.getId());
        biMainbase.setDeclarantid(user.getId());
        biMainbase.setPlanprojectbatchid("DATAWAREHOUSE-USER-REGISTER");
        biMainbase.setPlanprojectid("DATAWAREHOUSE-USER");
        biMainbase.setSavedate(new Date());
        biMainbase.setUpdatelasttime(new Date());
        this.biMainbaseService.merge(biMainbase);

        //bi_talent_bi
        BiTalentBi biTalentBi = new BiTalentBi();
        biTalentBi.setId(Util.NewGuid());
        biTalentBi.setMainid(biMainbase.getId());
        biTalentBi.setType("memberInfo");
        biTalentBi.setName(registerObj.getString("name"));
        biTalentBi.setCertificatename(registerObj.getString("certificatetype"));
        biTalentBi.setCertificatenumber(registerObj.getString("certificateno"));
        biTalentBi.setGender(registerObj.containsKey("sex") ? "1".equals(registerObj.getString("sex")) ? "男" : "女" : "");
        biTalentBi.setEmail(registerObj.getString("email"));
        biTalentBi.setMobile(registerObj.getString("mobile"));
        biTalentBi.setNationality(registerObj.getString("nationality"));
        biTalentBi.setBirthday(registerObj.has("birthday") ? DateUtil.parse(registerObj.getString("birthday"), "yyyy-MM-dd") : null);
        biTalentBi.setSavedate(new Date());
        biTalentBi.setUpdatelasttime(new Date());
        this.biTalentBiService.merge(biTalentBi);

        BiTalentWe work = new BiTalentWe();
        work.setId(Util.NewGuid());
        work.setMainid(biMainbase.getId());
        work.setType("currentInfo_work");
        work.setUnitname(pmsEnterprise.getName());
        work.setCreditcode(pmsEnterprise.getUniformcode());
        this.biTalentWeService.merge(work);

        BiTalentWe learn = new BiTalentWe();
        learn.setId(Util.NewGuid());
        learn.setMainid(biMainbase.getId());
        learn.setType("currentInfo_learn");
        this.biTalentWeService.merge(learn);

        return ReturnToJs.success();
    }


    public Boolean checkIfNeedCompleteInfo(String id, String type) {
        if ("gr".equals(type)) {//个人用户 BiMainbase主表id为Sys_user的id
            BiMainbase gryh = this.biMainbaseService.findById(id);
            if (Util.isEoN(gryh) || Util.isEoN(gryh.getEnterpriseid()) || "用户注册:用户填报".equals(gryh.getMinicurrentstate())) {
                return true;
            } else {
                List<BiTalentBi> biTalentBis = this.biTalentBiService.findByMainid(gryh.getId());
                if (biTalentBis == null || biTalentBis.size() == 0) {
                    return true;
                }
                for (BiTalentBi biTalentBi : biTalentBis) {//判断个人信息是否全
                    if (Util.isEoN(biTalentBi.getName()) || Util.isEoN(biTalentBi.getMobile()) || Util.isEoN(biTalentBi.getBirthday()) ||
                            Util.isEoN(biTalentBi.getCertificatename()) || Util.isEoN(biTalentBi.getCertificatenumber()) ||
                            /*Util.isEoN(biTalentBi.getWorkunit()) || */Util.isEoN(biTalentBi.getNation()) ||
                            Util.isEoN(biTalentBi.getGender()) || Util.isEoN(biTalentBi.getEmail())) {
                        return true;
                    }
                    List<BiTalentWe> biTalentWes = this.biTalentWeService.findByMainid(gryh.getId());
                    if (biTalentWes == null) {
                        return true;
                    } else {
                        int count = 0;
                        Boolean flag = false;
                        for (BiTalentWe biTalentWe : biTalentWes) {
                            if ("currentInfo_work".equals(biTalentWe.getType())) {
                                count++;
                                flag = true;
                                if (Util.isEoN(biTalentWe.getPost()) || Util.isEoN(biTalentWe.getDegree()) ||
                                        Util.isEoN(biTalentWe.getTitledetailone()) || Util.isEoN(biTalentWe.getTitledetailtwo())) {
                                    return true;
                                }
                            }
                            if ("currentInfo_learn".equals(biTalentWe.getType())) {
                                count--;
                                flag = true;
                                if (Util.isEoN(biTalentWe.getEducation()) || Util.isEoN(biTalentWe.getDegree())) {
                                    return true;
                                }
                            }
                        }
                        if (!flag || count != 0) {
                            return true;
                        }
                    }
                }
            }
        } else if ("fr".equals(type)) {//法人用户
            BiMainbase fryh = this.biMainbaseService.findById(id);
            if (Util.isEoN(fryh)) {
                return true;
            } else {
                List<BiEntBi> biEntBis = this.biEntBiService.findByMainid(fryh.getId());
                if (biEntBis == null || biEntBis.size() == 0) {
                    return true;
                }
                for (BiEntBi biEntBi : biEntBis) {
                    if (Util.isEoN(biEntBi.getCreditcode()) || Util.isEoN(biEntBi.getUnitname()) || Util.isEoN(biEntBi.getUnittype()) ||
                            Util.isEoN(biEntBi.getBelongindustry()) || Util.isEoN(biEntBi.getEstablishdate()) ||
                            Util.isEoN(biEntBi.getRegistercapital()) || Util.isEoN(biEntBi.getRegisteraddress()) ||
                            Util.isEoN(biEntBi.getOfficeaddress()) || Util.isEoN(biEntBi.getBankname()) ||
                            Util.isEoN(biEntBi.getAccountname()) || Util.isEoN(biEntBi.getBankaccount())
//                            Util.isEoN(biEntBi.getBankprovince()) || Util.isEoN(biEntBi.getBankcity()) || Util.isEoN(biEntBi.getBankcounty())
                    ) {
                        return true;
                    }
                    List<BiEntRy> biEntRys = this.biEntRyService.findByMainid(fryh.getId());
                    if (biEntRys == null) {
                        return true;
                    } else {
                        Set<String> set = new HashSet<>();
                        for (BiEntRy biEntRy : biEntRys) {
                            if (!set.add(biEntRy.getRytype()) || Util.isEoN(biEntRy.getName()) || Util.isEoN(biEntRy.getMobile()) || Util.isEoN(biEntRy.getCertificatenumber())
                                    || Util.isEoN(biEntRy.getCertificatename()) || Util.isEoN(biEntRy.getEmail()) || Util.isEoN(biEntRy.getAdministrativeduty())) {
                                return true;
                            }
                        }
                        if (set.size() != 3) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * 【专家注册】根据用户id获取个人信息
     */
    public JSONObject getBaseInfoByUseridForExpert(String id) {
        JSONObject userInfo = new JSONObject();

        String usersql = "select e.name,e.gender,e.certificatename as id_type,e.certificatenumber as id_number, " +
                "e.nation,e.birthday,e.profession as workforprofession,e.email,e.mobile as mobile_telephone, " +
                "e.doctortutor as subsidy,e.ispostdoctoral as istutor,e.telephone as office_telephone, " +
                "e.address as postal_address,e.postalcode as postcode,e.familyphone as home_telephone, " +
                "e.fax,e.website,e.keywords,e.birthplace,e.foreignlanguage,e.languagelevel,e.remittanceaddress, " +
                "e.introduce,e.nationality," +
                "w1.unitname as school,w1.profession as major,w1.degree as degree,w1.education as education, " +
                "w2.post as professional_title,w2.degree as position_title,w2.degreedate as positiondate,w2.jtgzdw " +
                "from sys_user t " +
                "left join bi_mainbase b on b.declarantid = t.id " +
                "and b.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER' " +
                "left join bi_talent_bi e on e.mainid = b.id " +
                "left join bi_talent_we w1 on w1.mainid = b.id and w1.type = 'currentInfo_learn' " +
                "left join bi_talent_we w2 on w2.mainid = b.id and w2.type = 'currentInfo_work' " +
                "where t.id = ?";
        List<Map> userlist = this.dbHelper.getRows(usersql, new Object[]{id});

        if (userlist != null && userlist.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            JSONArray jsonArray = JSONArray.fromObject(userlist, jsonConfig);
            userInfo = jsonArray.getJSONObject(0);
        }

        return userInfo;
    }


    /**
     * 【专家注册】根据单位id获取单位信息
     */
    public JSONObject getBaseInfoByUnitidForExpert(String id) {
        JSONObject unitInfo = new JSONObject();

        String unitsql = "select e.province as organization_province,e.city as organization_city, " +
                "e.county as organization_county,e.unitname as organization,e.simpleunittype, " +
                "e.officeaddress as organization_address,e.creditcode as uniformcode, " +
                "e.unittype as organization_property,e.bankname as bank_name,t.selflevel, " +
                "e.accountname as bank_accountname,e.bankaccount as bank_account, " +
                "w.telephone as organization_contactphone,w.name as organization_contactman,e.isgxqy, " +
                "to_char(e.gxqyenddate, 'yyyy-mm-dd') gxqyenddate " +
                "from pms_enterprise t " +
                "left join bi_mainbase b on b.enterpriseid = t.id " +
                "and b.planprojectbatchid = 'DATAWAREHOUSE-UNIT-REGISTER' " +
                "left join bi_ent_bi e on e.mainid = b.id " +
                "left join bi_ent_ry w on w.mainid = b.id and w.rytype = '单位联系人' " +
                "where t.id = ?";
        List<Map> unitlist = this.dbHelper.getRows(unitsql, new Object[]{id});

        if (unitlist != null && unitlist.size() > 0) {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
            JSONArray jsonArray = JSONArray.fromObject(unitlist, jsonConfig);
            unitInfo = jsonArray.getJSONObject(0);
        }

        return unitInfo;
    }

    /**
     * @param mobile
     * @param ip
     * @param content
     * @return void
     * @throws
     * @Description 插入验证码到数据库
     * @Author shenxian
     * @Date 2024-01-18
     */
    public void insertIntoValidCode(String content, String mobile, String ip) {
        String sql = "insert into PMS_VALIDCODERECORD(id, Content,mobile,ip,CREATEDATE) values" +
                "(?,?,?,?,sysdate)";
        try {
            dbHelper.runSql(sql, new Object[]{Util.NewGuid(), content, mobile, ip});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return SysUser
     * @throws
     * @Description 通过身份证号及角色id找用户
     * @Author shenxian
     * @Date 2023/2/5
     */
    public SysUser findUserByIdNumberOrCreditcode(String type, String certificateno, String roleid) {
        String hql = "";
        if ("gr".equals(type)) {
            hql = "select u from SysUser u,SysIdentity i,SysRole r where u.certificateno = ?0 and u = i.sysUser and i.sysRole = r and r.id = ?1";
        } else if ("fr".equals(type)) {
            hql = "select u from SysUser u,SysIdentity i,SysRole r,PmsEnterprise pe where u = i.sysUser and i.sysRole = r and i.pmsEnterprise = pe and pe.uniformcode = ?0 and r.id = ?1";
        }
        List<SysUser> userLists = this.getSysUserDao().findByHql(hql, new Object[]{certificateno, roleid});
        if (userLists.size() > 0) {
            return userLists.get(0);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public SysUser userRegisterForZwwGrUser(JSONObject json) throws Exception {
        SysUser user = new SysUser();
        user.setId(Util.NewGuid());
        user.setName(json.getString("USER_NAME"));
        user.setUserid(json.getString("CARD_NO"));
        String certificatetypeno = json.getString("CARD_TYPE");
        String certificatetype = "";
        switch (certificatetypeno) {
            case "111":
                certificatetype = "居民身份证";
                break;
            case "414":
                certificatetype = "护照";
                break;
            case "511":
                certificatetype = "台胞证";
                break;
            case "516":
                certificatetype = "港澳通行证";
                break;
            default:
                certificatetype = "居民身份证";
                break;
        }
        user.setCertificatetype(certificatetype);
        user.setCertificateno(json.getString("CARD_NO"));
        user.setMobile(json.getString("MOBILE"));
        user.setUserstate(0);
        user.setIsfreeze(true);
        user.setDefaultSystem("sb");
        user.setRegisterdate(new Date());
        user.setPassword("BECC356B0E8CA9ADFA76341C9F133B24BD71B50E7EBE3388C754D9BE92973671");
        user.setSalt("0b65d6de-6e75-11ee-9f5e-0242ac120002");
        this.merge(user);
        // log.info("sys_user表完毕");

        //绑定一个临时单位
        PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById("TEMPUNIT-123456");

        SysRole role = this.sysRoleService.findById("129947C6-94DC-4A7D-84D2-E78A80E518A3");
        this.sysIdentityService.generateIdentity(user, pmsEnterprise, role);
        // log.info("sys_identity表完毕");

        //bi_mainbase
        BiMainbase biMainbase = new BiMainbase();
        biMainbase.setId(user.getId());
        biMainbase.setEnterpriseid(pmsEnterprise.getId());
        biMainbase.setDeclarantid(user.getId());
        biMainbase.setSavedate(new Date());
        biMainbase.setUpdatelasttime(new Date());
        this.biMainbaseService.merge(biMainbase);
        // log.info("bi_mainbase表完毕");

        //bi_talent_bi
        BiTalentBi biTalentBi = new BiTalentBi();
        biTalentBi.setId(Util.NewGuid());
        biTalentBi.setMainid(biMainbase.getId());
        biTalentBi.setType("memberInfo");
        biTalentBi.setName(json.getString("USER_NAME"));
        biTalentBi.setCertificatename(certificatetype);
        biTalentBi.setCertificatenumber(json.getString("CARD_NO"));
        biTalentBi.setGender(json.containsKey("SEX") ? "1".equals(json.getString("SEX")) ? "男" : "女" : "");
        biTalentBi.setEmail(json.containsKey("EMAIL") ? json.getString("EMAIL") : "");
        biTalentBi.setMobile(json.getString("MOBILE"));
        // biTalentBi.setBirthday(json.has("BIRTHDAY") ? DateUtil.parse(json.getString("birthday"), "yyyyMMdd") : null);
        biTalentBi.setSavedate(new Date());
        biTalentBi.setUpdatelasttime(new Date());
        this.biTalentBiService.merge(biTalentBi);
        // log.info("bi_talent_bi表完毕");

        BiTalentWe work = new BiTalentWe();
        work.setId(Util.NewGuid());
        work.setMainid(biMainbase.getId());
        work.setType("currentInfo_work");
        this.biTalentWeService.merge(work);
        // log.info("bi_talent_we表1完毕");

        BiTalentWe learn = new BiTalentWe();
        learn.setId(Util.NewGuid());
        learn.setMainid(biMainbase.getId());
        learn.setType("currentInfo_learn");
        this.biTalentWeService.merge(learn);
        // log.info("bi_talent_bi表2完毕");

        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public SysUser userRegisterForZwwFrUser(JSONObject json) throws Exception {
        String creditcode = json.getString("CORPORATE_CODE");
        PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findOne(HqlBuilder.builder().eq("uniformcode", creditcode));
        if (Util.isEoN(pmsEnterprise)) {
            pmsEnterprise = new PmsEnterprise();
            pmsEnterprise.setId(Util.NewGuid());
            pmsEnterprise.setName(json.getString("CORPORATE_NAME"));
            pmsEnterprise.setUniformcode(creditcode);
            this.pmsEnterpriseService.merge(pmsEnterprise);
        }
        // log.info("pms_enterprise表完毕");

        SysUser user = new SysUser();
        user.setId(pmsEnterprise.getId());
        user.setUserid(pmsEnterprise.getUniformcode());
        user.setName(pmsEnterprise.getName());
        user.setUserstate(0);
        user.setIsfreeze(true);
        user.setDefaultSystem("sb");
        user.setRegisterdate(new Date());
        this.merge(user);
        // log.info("sys_user表完毕");

        SysRole role = this.sysRoleService.findById("C7004168-4E0C-4F1F-B341-A225B5644DC5");
        this.sysIdentityService.generateIdentity(user, pmsEnterprise, role);
        // log.info("sys_identity表完毕");

        //bi_mainbase
        BiMainbase biMainbase = new BiMainbase();
        biMainbase.setId(user.getId());
        biMainbase.setEnterpriseid(pmsEnterprise.getId());
        biMainbase.setDeclarantid(user.getId());
        biMainbase.setSavedate(new Date());
        biMainbase.setUpdatelasttime(new Date());
        this.biMainbaseService.merge(biMainbase);
        log.info("bi_mainbase表完毕");

        //bi_ent_bi
        BiEntBi biEntBi = new BiEntBi();
        biEntBi.setId(Util.NewGuid());
        biEntBi.setMainid(biMainbase.getId());
        biEntBi.setUnitname(pmsEnterprise.getName());
        biEntBi.setCreditcode(pmsEnterprise.getUniformcode());
        biEntBi.setType("unitInfo_basic");
        this.biEntBiService.merge(biEntBi);
        // log.info("bi_ent_bi表完毕");

        //BI_ENT_RY
        BiEntRy fr = new BiEntRy();
        fr.setId(Util.NewGuid());
        fr.setMainid(biMainbase.getId());
        fr.setType("member_legal");
        fr.setRytype("法定代表人");
        fr.setName(json.getString("REPRESENTATIVE_NAME"));
        fr.setCertificatenumber(json.getString("REPRESENTATIVE_CARD_NO"));
        fr.setMobile(json.getString("MOBILE"));
        this.biEntRyService.merge(fr);
        // log.info("bi_ent_ry表1完毕");

        BiEntRy dwlxr = new BiEntRy();
        dwlxr.setId(Util.NewGuid());
        dwlxr.setMainid(biMainbase.getId());
        dwlxr.setType("member_link");
        dwlxr.setRytype("单位联系人");
        this.biEntRyService.merge(dwlxr);
        // log.info("bi_ent_ry表2完毕");

        BiEntRy kyfzr = new BiEntRy();
        kyfzr.setId(Util.NewGuid());
        kyfzr.setMainid(biMainbase.getId());
        kyfzr.setType("member_leading");
        kyfzr.setRytype("科研负责人");
        this.biEntRyService.merge(kyfzr);
        // log.info("bi_ent_ry表3完毕");

        return user;
    }

    // 限制单个IP地址24小时内请求验证码的次数；建议50次
    // 超过50次则返回 false
    public boolean limitSingleIPMobile(String ip) {
        String sql = "select count(id) as mcount from PMS_VALIDCODERECORD where ip = ?  "
                + " and createdate >= trunc(sysdate) and createdate < trunc(sysdate + 1)";
        int mcount = dbHelper.getRowsCount(sql, new Object[]{ip});
        return mcount <= 50;
    }

    //限制24小时内，针对同一手机号码发送验证码的总次数；默认20次
    // 超过20次返回 false
    public boolean limitSingleMobile(String mobile) {
        String sql = "select count(id) as mcount from PMS_VALIDCODERECORD where content like ?"
                + " and createdate >= trunc(sysdate) and createdate < trunc(sysdate + 1)";
        int mcount = dbHelper.getRowsCount(sql,  new Object[]{"%" + mobile + "%"});
        return mcount <= 20;
    }

    /**
     * 找回用户密码
     */
    public R passwordRetrieval(String type, JSONObject json, HttpServletRequest request) {
//        DESEncrypt des = new DESEncrypt();
        //多加了一个userstate='4'，为的是将那些因超期未登录而冻结的可以通过找回密码的方式解冻
        String sql = "select id,userid,mobile,email,password from SYS_USER where temp = ? and userstate in('1','4')  ";
        String rolename = "";
        if (!Util.isEoN(json.get("rolename"))) {
            rolename = json.getString("rolename");
        }
        if (type.contains("mobile")) {
            sql = sql.replace("temp", "mobile");
            String mobile = json.getString("mobile");
            List<Map> sysUserList = null;
            if (!Util.isEoN(rolename)) {
                sql = "select t.id ,t.userid,t.mobile,t.email,t.password,r.id as rid from SYS_USER t left join sys_identity si on t.id = si.userid left join sys_role r on si.roleid = r.id" +
                        " where t.id in (select userid from SYS_IDENTITY a left join SYS_ROLE b on a.ROLEID = b.id where b.ROLENAME = ?) and MOBILE = ? and userstate in('1','4')  ";
                sysUserList = dbHelper.getRows(sql, new Object[]{rolename,mobile});
            }else {
                sysUserList = dbHelper.getRows(sql, new Object[]{mobile});
            }
            boolean anyMatch = sysUserList.stream().anyMatch(m ->
                    m.containsKey("rid") && (
                            m.get("rid").toString().equals("DDE79A799A63461EB3FAF078970F038E") || m.get("rid").toString().equals("3B83B457-F208-CF71-A34E-23ACB5A56061")
                                    || m.get("rid").toString().equals("C79CBC99-DC6A-95A3-186D-324FEF4E6C1F") || m.get("rid").toString().equals("BD6951C0-F2AD-4CA4-9019-65D3069ABF1D")
                                    || m.get("rid").toString().equals("5B1F6340-B6AB-4477-BF3E-51ECBF4E38FB") || m.get("rid").toString().equals("ADF5DB39-77A6-4A5D-9367-564A3FDB2036")
                    )
            ); //判断是否是专家

            if (sysUserList.size() == 0) {
                return R.fail(1000,"此手机号码未绑定系统账号。");
            }
            if (sysUserList.size() > 1) {
                List<String> userid = sysUserList.stream().map(m -> m.get("userid").toString()).distinct().collect(Collectors.toList());
                if (userid.size() > 1) {
                    //专家用户由于评审、会有多个账号、不受限制
                    //评审专家角色
                    //DDE79A799A63461EB3FAF078970F038E
                    //3B83B457-F208-CF71-A34E-23ACB5A56061
                    //C79CBC99-DC6A-95A3-186D-324FEF4E6C1F
                    //BD6951C0-F2AD-4CA4-9019-65D3069ABF1D
                    //5B1F6340-B6AB-4477-BF3E-51ECBF4E38FB
                    //ADF5DB39-77A6-4A5D-9367-564A3FDB2036
                    sql = "select p.cc,q.rolename,q.id from (select count(*) as cc,ROLEID from SYS_IDENTITY a left join SYS_USER b on a.USERID = b.id where b.MOBILE = ? group by a.ROLEID) p " +
                            "left join SYS_ROLE q on p.ROLEID = q.ID ";
                    List<String> roleList = new ArrayList<>();
                    List<Map> rows = dbHelper.getRows(sql, new Object[]{mobile});
                    for (Map map : rows) {
                        if (Integer.parseInt(map.get("cc").toString()) > 1) {
                            if (map.get("id").equals("DDE79A799A63461EB3FAF078970F038E") || map.get("id").equals("3B83B457-F208-CF71-A34E-23ACB5A56061")
                                    || map.get("id").equals("C79CBC99-DC6A-95A3-186D-324FEF4E6C1F") || map.get("id").equals("BD6951C0-F2AD-4CA4-9019-65D3069ABF1D")
                                    || map.get("id").equals("5B1F6340-B6AB-4477-BF3E-51ECBF4E38FB") || map.get("id").equals("ADF5DB39-77A6-4A5D-9367-564A3FDB2036")) {
                                roleList.add(map.get("rolename").toString());
                                continue;
                            }
                            return R.fail(2000,"您的手机号绑定了多个账号，请联系客服处理");
                        } else {
                            roleList.add(map.get("rolename").toString());
                        }
                    }
                    if (!anyMatch) {
                        return R.data(3000,roleList,"您的手机号绑定了多个账号，请选择角色以找回对应的账号密码");
                    }
                }
            }

            String tempCode = "您好，您本次找回的账号的密码：" + Base64Convert.decode(sysUserList.get(0).get("password").toString()) + ",请妥善保管。";

            if (anyMatch) {
                tempCode = "您好，您本次找回的：";
                for (int i = 0; i < sysUserList.size(); i++) {
                    tempCode += "【账号:" + sysUserList.get(i).get("userid") + ", 密码:" + Base64Convert.decode(sysUserList.get(0).get("password").toString()) + "】";
                }
            }
            //String tempCode = "您好，您本次找回的账号：" + sysUserList.get(0).get("userid").toString() + ",密码：" + des.decrypt(sysUserList.get(0).get("password").toString()) + ",请妥善保管。";
            System.out.println(tempCode);
            String requestip = Util.getIpAddr(request);
            // 同一ip每天只能最多发送 10 条
            if (!this.limitSingleIPMobile(requestip)) {
                return R.fail("每天同一个IP发送短信次数超过限制。");
            }
            // 同一号码每天只能发送5次
            if (!this.limitSingleMobile(mobile)) {
                return R.fail("每天同一个手机发送短信次数超过限制。");
            }
            this.insertIntoValidCode(tempCode, mobile, requestip);
            try {
                JSONObject msgJson = new JSONObject();
                msgJson.put("cellphones", mobile);
                msgJson.put("content", tempCode);
                msgJson.put("bussiness", "找回用户密码短信通知");
                ReturnToJs<Object> message = messageService.sendMessage(mobile, msgJson.toString());
//                JSONObject msgResJson = messageService.sendMessage(mobile,msgJson.toString());
                if (message != null && "发送成功".equals(message.getMsg())) {
                    //更新用户最后登陆时间，避免登陆时被冻结
                    SysUser sysUser = this.getById(sysUserList.get(0).get("userid") + "");
                    sysUser.setLastLoginTime(LocalDateTime.now());
                    sysUser.setUserstate(1);
                    getSysUserDao().merge(sysUser);
                    return R.success("找回成功");
                } else {
                    return R.fail("找回短信发送失败");
                }
            } catch (Exception e) {
                return R.fail("接口异常，找回短信发送失败");
            }
        }
//        if (type.contains("email")) {
//            sql = sql.replace("temp", "email");
//            String email = json.getString("email");
//            List<Map> sysUserList = null;
//            if (!Util.isEoN(rolename)) {
//                sql = "select id,userid,mobile,email,password from SYS_USER where id in (select userid from SYS_IDENTITY a left join SYS_ROLE b on a.ROLEID = b.id where b.ROLENAME = ?) and email = ?";
//                sysUserList = dbHelper.getRows(sql, new Object[]{rolename,email});
//            }else {
//                sysUserList = dbHelper.getRows(sql, new Object[]{email});
//            }
//
//            if (sysUserList.size() == 0) {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("此邮箱未绑定系统账号。");
//                return returnToJs;
//            }
//            if (sysUserList.size() > 1) {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("您的邮箱绑定了多个账号，请联系客服处理");
//                return returnToJs;
//            }
//            String tempCode = "您好，您本次找回的账号的密码：" + des.decrypt(sysUserList.get(0).get("password").toString()) + ",请妥善保管。";
//            //String tempCode = "您好，您本次找回的账号：" + sysUserList.get(0).get("userid").toString() + ",密码：" + des.decrypt(sysUserList.get(0).get("password").toString()) + ",请妥善保管。";
//            System.out.println(tempCode);
//            String requestip = Util.getIpAddr(request);
//            // 同一ip每天只能最多发送 10 条
//            if (!this.limitSingleIPMail(requestip)) {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("每天同一个IP发送邮件次数超过限制。");
//                return returnToJs;
//            }
//            // 同一号码每天只能发送5次
//            if (!this.limitSingleMail(email)) {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("每天同一个邮箱发送邮件次数超过限制。");
//                return returnToJs;
//            }
//            this.insertIntoValMailCode(tempCode, email, requestip);
//            try {
//                boolean res = emailService.sendMail("系统验证码", email, "广州市科技大脑系统验证码", tempCode);
//                if (res) {
//                    //更新用户最后登陆时间，避免登陆时被冻结
//                    SysUser sysUser = getSysUserDao().getUserByUserID(sysUserList.get(0).get("userid") + "");
//                    sysUser.setLastlogindate(new Date());
//                    sysUser.setUserstate(1);
//                    getSysUserDao().merge(sysUser);
//
//                    returnToJs.setSuccess(true);
//                } else {
//                    returnToJs.setSuccess(false);
//                    returnToJs.setErrMsg("找回邮件发送失败");
//                }
//                return returnToJs;
//            } catch (Exception e) {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("接口异常，找回邮件发送失败");
//                return returnToJs;
//            }
//        }

        return R.success("");
    }
}
