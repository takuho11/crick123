package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.auth.provider.ITokenGranter;
import cn.topcheer.halberd.biz.modules.auth.provider.TokenGranterBuilder;
import cn.topcheer.halberd.biz.modules.auth.provider.TokenParameter;
import cn.topcheer.halberd.biz.modules.auth.utils.TokenUtil;
import cn.topcheer.halberd.biz.modules.system.entity.UserInfo;
import cn.topcheer.halberd.core.redis.RedisRateLimiterClient;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxInfoUpdate;
import cn.topcheer.pms2.biz.message.MessageService;
import cn.topcheer.pms2.biz.sys.*;
import cn.topcheer.pms2.biz.utils.Base64Convert;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxInfoUpdateService;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxUpdateService;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxxkflUpdateService;
import io.swagger.annotations.Api;
import liquibase.pro.packaged.J;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.core.tool.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/Register")
@Api(value = "注册", tags = "注册页面相关方法")
public class RegisterController extends GenericController<SysUser> {

    public RegisterService getRegisterService() {
        return (RegisterService) this.getGenericService();
    }

    @Autowired
    private void setRegisterService(RegisterService registerService) {
        this.setGenericService(registerService);
    }

    @Autowired
    private PmsEnterpriseService pmsEnterpriseService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;
    @Autowired
    OldSysParamsService oldSysParamsService;
    @Autowired
    ZjkRyjbxxUpdateService zjkRyjbxxUpdateService;
    @Autowired
    ZjkRyjbxxxkflUpdateService zjkRyjbxxxkflUpdateService;
    @Autowired
    ZjkRyjbxxInfoUpdateService zjkRyjbxxInfoUpdateService;
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private BladeRedis bladeRedis;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RedisRateLimiterClient redisRateLimiterClient;
    @Autowired
    private SysUserPhoneService sysUserPhoneService;


    /*============================完美分割线（↑：注入 丨 ↓：方法）============================*/

    @RequestMapping("/findUnitInfoByUnitName.do")
    public @ResponseBody
    ReturnToJs findUnitInfoByUnitName(@RequestBody JSONObject example) {
        return ReturnToJs.success(this.pmsEnterpriseService.findUnitInfoByUnitName(example));
    }


    @RequestMapping("/verifyUserid.do")
    public @ResponseBody
    ReturnToJs verifyUserid(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        try {
            example = Util.deCodeUseridAndPassword(example);
        } catch (Exception e) {
            throw new RuntimeException("用户名或密码解码失败。");
        }
        //true表示存在，false表示不存在
        resJson.put("result", this.sysUserService.verifyUserid(example));
        return ReturnToJs.success(resJson);
    }

    @RequestMapping("/verifyCertificateno.do")
    public @ResponseBody
    ReturnToJs verifyCertificateno(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        //true表示存在，false表示不存在
        resJson.put("result", this.sysUserService.verifyCertificateno(example));
        return ReturnToJs.success(resJson);
    }

    @RequestMapping("/verifyMobile.do")
    public @ResponseBody
    ReturnToJs verifyMobile(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        //true表示存在，false表示不存在
        resJson.put("result", this.sysUserService.verifyMobile(example));
        return ReturnToJs.success(resJson);
    }

    @RequestMapping("/verifyEmail.do")
    public @ResponseBody
    ReturnToJs verifyEmail(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        //true表示存在，false表示不存在
        resJson.put("result", this.sysUserService.verifyEmail(example));
        return ReturnToJs.success(resJson);
    }


    /**
     * 【注册】---验证统一社会信用代码、组织机构代码是否存在
     */
    @RequestMapping("/verifyUoCode.do")
    public @ResponseBody
    ReturnToJs verifyUoCode(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        //true表示存在，false表示不存在
        resJson.put("result", this.pmsEnterpriseService.verifyUoCode(example));
        return ReturnToJs.success(resJson);
    }


    /**
     * 【注册】---验证单位名称是否存在
     */
    @RequestMapping("/verifyUnitName.do")
    public @ResponseBody
    ReturnToJs verifyUnitName(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        //true表示存在，false表示不存在
        resJson.put("result", this.pmsEnterpriseService.verifyUnitName(example));
        return ReturnToJs.success(resJson);
    }

    /**
     * 【个人注册】
     */
    @RequestMapping("/userRegister.do")
    public @ResponseBody
    ReturnToJs userRegister(@RequestBody JSONObject jsonObject) throws Exception {
        ReturnToJs returnToJs = new ReturnToJs();
        JSONObject json = jsonObject.getJSONObject("registerObj");
        String code = json.getString("CaptchaCode");
        String validCode = json.getString("validCode");
        String mobile = json.getString("mobile");
        String key = json.getString("CaptchaKey");
        String smsCaptchaKey = json.getString("SMS-Captcha-Key");
        String redisCode = (String) this.bladeRedis.get("halberd:auth::halberd:captcha:" + key);
        String redisMobileCode = (String) this.bladeRedis.get("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        this.bladeRedis.del("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
            return ReturnToJs.failure("图形验证码错误");
        }
        if (validCode == null || !StringUtil.equalsIgnoreCase(redisMobileCode, validCode)) {
            return ReturnToJs.failure("手机验证码错误");
        }
        ReturnToJs resJson = this.sysUserService.userRegister(jsonObject, returnToJs);
        return ReturnToJs.success(resJson);
    }


    /**
     * 【单位注册】
     */
    @RequestMapping("/unitRegister.do")
    public @ResponseBody
    ReturnToJs unitRegister(@RequestBody JSONObject jsonObject) throws Exception {
        ReturnToJs returnToJs = new ReturnToJs();
        JSONObject json = jsonObject.getJSONObject("registerObj");
        String code = json.getString("CaptchaCode");
        String validCode = json.getString("validCode");
        String mobile = json.getString("mobile");
        String key = json.getString("CaptchaKey");
        String smsCaptchaKey = json.getString("SMS-Captcha-Key");
        String redisCode = (String) this.bladeRedis.get("halberd:auth::halberd:captcha:" + key);
        String redisMobileCode = (String) this.bladeRedis.get("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        this.bladeRedis.del("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
            return ReturnToJs.failure("图形验证码错误");
        }
        if (validCode == null || !StringUtil.equalsIgnoreCase(redisMobileCode, validCode)) {
            return ReturnToJs.failure("手机验证码错误");
        }
        ReturnToJs resJson = this.pmsEnterpriseService.unitRegister(jsonObject, returnToJs);
        return ReturnToJs.success(resJson);
    }


    /**
     * 【注册】---通过统一社会信用代码/组织机构代码查找单位信息
     */
    @RequestMapping("/findUnitInfoByUoCode.do")
    public @ResponseBody
    ReturnToJs findUnitInfoByUoCode(@RequestBody JSONObject jsonObject) {
        JSONObject resJson = this.pmsEnterpriseService.findUnitInfoByUoCode(jsonObject);
        return ReturnToJs.success(resJson);
    }


    /**
     * 【字典配置】---根据父级value获取他的子级
     */
    @RequestMapping("/initDataByparentvalue.do")
    public @ResponseBody
    ReturnToJs initDataByparentvalue(@RequestBody JSONObject json) {
        Map map = this.oldSysParamsService.initDataByparentvalue(json);
        return ReturnToJs.success(map);
    }

    /**
     * @param request
     * @param response
     * @return ReturnToJs
     * @throws
     * @Description 判断用户账号信息是否需要完善
     * @Author shenxian
     * @Date 2024-01-16
     */
    @RequestMapping("/checkIfNeedCompleteInfo.do")
    public @ResponseBody
    ReturnToJs checkIfNeedCompleteInfo(
            HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
        if (Util.isEoN(user)) {
            return ReturnToJs.failure("请先登录系统！");
        }
        List<SysIdentity> gj = user.getSysIdentitys().stream().filter(si -> "2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487".equals(si.getSysRole().getId())).collect(Collectors.toList());
        if (!Util.isEoN(gj)) {
            jsonObject.put("needComplete", false);
            return ReturnToJs.success(jsonObject);
        }
        List<SysIdentity> gr = user.getSysIdentitys().stream().filter(si -> "f8a87c80-f89d-48bc-ad96-840ab6aa81b2".equals(si.getSysRole().getId()) || "129947C6-94DC-4A7D-84D2-E78A80E518A3".equals(si.getSysRole().getId())).collect(Collectors.toList());
        List<SysIdentity> fr = user.getSysIdentitys().stream().filter(si -> "aaaa-bbbb-cccc-dddd".equals(si.getSysRole().getId()) || "C7004168-4E0C-4F1F-B341-A225B5644DC5".equals(si.getSysRole().getId())).collect(Collectors.toList());
        if (Util.isEoN(gr) && Util.isEoN(fr)) {
            jsonObject.put("needComplete", false);
            return ReturnToJs.success(jsonObject);
        }
        String type = "gr";
        Boolean needCompleteInfo = false;
        if (!Util.isEoN(gr) && gr.size() > 0) {
            type = "gr";
        } else if (!Util.isEoN(fr) && fr.size() > 0) {
            type = "fr";
        }
        needCompleteInfo = this.sysUserService.checkIfNeedCompleteInfo(user.getId(), type);
        if (needCompleteInfo) {
            if ("gr".equals(type)) {
                jsonObject.put("needComplete", true);
                jsonObject.put("type", "gr");
                jsonObject.put("id", user.getId());
                jsonObject.put("unitid", user.getEnterPriseId());
            } else if ("fr".equals(type)) {
                jsonObject.put("needComplete", true);
                jsonObject.put("type", "fr");
                jsonObject.put("id", user.getEnterPriseId());
                jsonObject.put("userid", user.getId());
            }
            return ReturnToJs.success(jsonObject);
        } else {
            jsonObject.put("needComplete", false);
            return ReturnToJs.success(jsonObject);
        }
    }

    /**
     * @param
     * @return ReturnToJs
     * @throws
     * @Description 获取用户相关的各种id
     * @Author shenxian
     * @Date 2024-01-16
     */
    @RequestMapping("/getAllRelateIds.do")
    public @ResponseBody
    ReturnToJs getAllRelateIds() {
        SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
        JSONObject resJson = new JSONObject();
        resJson.put("userid", user.getId());
        String entid = user.getSysIdentitys().get(0).getPmsEnterprise().getId();
        resJson.put("entid", entid);
        StringBuilder roleids = new StringBuilder();
        user.getSysIdentitys().stream().forEach(si -> roleids.append(si.getSysRole().getId()).append(";"));
        resJson.put("roleids", roleids);
//        List<SysIdentity> gr = user.getSysIdentitys().stream().filter(si -> "129947C6-94DC-4A7D-84D2-E78A80E518A3".equals(si.getSysRole().getId())).collect(Collectors.toList());
        List<SysIdentity> gr = user.getSysIdentitys().stream().filter(si -> "f8a87c80-f89d-48bc-ad96-840ab6aa81b2".equals(si.getSysRole().getId()) || "129947C6-94DC-4A7D-84D2-E78A80E518A3".equals(si.getSysRole().getId())).collect(Collectors.toList());
//        List<SysIdentity> fr = user.getSysIdentitys().stream().filter(si -> "C7004168-4E0C-4F1F-B341-A225B5644DC5".equals(si.getSysRole().getId())).collect(Collectors.toList());
        List<SysIdentity> fr = user.getSysIdentitys().stream().filter(si -> "aaaa-bbbb-cccc-dddd".equals(si.getSysRole().getId()) || "C7004168-4E0C-4F1F-B341-A225B5644DC5".equals(si.getSysRole().getId())).collect(Collectors.toList());
        String type = "DATAWAREHOUSE-USER-REGISTER";
        if (!Util.isEoN(gr) && gr.size() > 0) {
            type = "DATAWAREHOUSE-USER-REGISTER";
        } else if (!Util.isEoN(fr) && fr.size() > 0) {
            type = "DATAWAREHOUSE-UNIT-REGISTER";
        }
        String mainbaseid;
//        String sql = "select e.id,e.minicurrentstate,u.minicurrentstate as userminicurrentstate from bi_mainbase e left join Sys_User u on e.declarantid = u.id where planprojectbatchid = ? and declarantid = ? and enterpriseid = ?";
        String sql = "select e.id,e.minicurrentstate,u.minicurrentstate as userminicurrentstate from bi_mainbase e left join Sys_User u on e.declarantid = u.id where planprojectbatchid = ? and declarantid = ? ";
//        List<Map> rows = this.dbHelper.getRows(sql, new Object[]{type, user.getId(), entid});
        List<Map> rows = this.dbHelper.getRows(sql, new Object[]{type, user.getId()});
        if (rows.size() > 0) {
            rows.forEach(row -> {
                resJson.put("mainbaseid", String.valueOf(row.get("id")));
                resJson.put("minicurrentstate", String.valueOf(row.get("minicurrentstate")));
                resJson.put("userminicurrentstate", String.valueOf(row.get("userminicurrentstate")));
            });
        }
//        resJson.put("mainbaseid", mainbaseid);
        return ReturnToJs.success(resJson);
    }


    /**
     * 【专家注册】---保存专家所有信息
     *
     * @param json
     */
    @RequestMapping("/saveExpertInfo.do")
    public @ResponseBody
    ReturnToJs saveExpertInfo(@RequestBody JSONObject json) {
        //专家主表对象
        JSONObject ryjbxxJson = json.getJSONObject("ryjbxx");
        //专家主表id
        String expertid = ryjbxxJson.get("id") + "";
        //保存专家主表信息 zjk_ryjbxx_update
        this.zjkRyjbxxUpdateService.saveRyjbxxUpdate(ryjbxxJson);
        //保存专家学科
        this.zjkRyjbxxxkflUpdateService.saveRyjbxxXkflNew(expertid, json);
        JSONObject ryjbxxinfoJson = json.getJSONObject("ryjbxxinfo");
        ZjkRyjbxxInfoUpdate zjkRyjbxxInfoUpdate = new ZjkRyjbxxInfoUpdate();
        ZjkRyjbxxInfoUpdate zjkRyjbxxInfoUpdateTemp = this.zjkRyjbxxInfoUpdateService.getDataByZjid(expertid);
        Util.ApplyObject(zjkRyjbxxInfoUpdate, ryjbxxinfoJson);
        if (Util.isEoN(zjkRyjbxxInfoUpdate.getId())) {
            if (!Util.isEoN(zjkRyjbxxInfoUpdateTemp)) {
                zjkRyjbxxInfoUpdate.setId(zjkRyjbxxInfoUpdateTemp.getId());
            } else {
                zjkRyjbxxInfoUpdate.setId(Util.NewGuid());
            }
        }
        zjkRyjbxxInfoUpdate.setZjkryjbxxupdateid(expertid);
        zjkRyjbxxInfoUpdateService.merge(zjkRyjbxxInfoUpdate);

        return ReturnToJs.success();
    }


    /**
     * 【专家注册】---获取专家信息
     *
     * @param json
     */
    @RequestMapping("/getExpertInfo.do")
    public @ResponseBody
    ReturnToJs getExpertInfo(@RequestBody JSONObject json) {
        JSONObject resJson = new JSONObject();
        String userid = json.get("userid") + "";
        String unitid = json.get("unitid") + "";
        if (Util.isEoN(unitid)) {
            unitid = this.sysUserService.findById(userid).getSysIdentitys().get(0).getPmsEnterprise().getId();
        }
        //省内省外专家
        String snsw = json.get("snsw") + "";
        //申报人信息
        JSONObject useridJson = new JSONObject();
        useridJson.put("userid", userid);
        JSONObject userInfo = this.sysUserService.getBaseInfoByUseridForExpert(userid);
        //返回前台用户信息
        resJson.put("userInfo", userInfo);
        //单位信息
        if ("sn".equals(snsw)) {
            JSONObject enterpriseInfo = this.sysUserService.getBaseInfoByUnitidForExpert(unitid);
            enterpriseInfo.put("jtgzdw", userInfo.get("jtgzdw") + "");
            //返回前台单位信息
            resJson.put("enterpriseInfo", enterpriseInfo);
        }
        //专家信息
        boolean hasExpertInfo = this.zjkRyjbxxUpdateService.judgeExpertInfoByUserid(useridJson);
        //返回前台是否有专家信息的判断
        resJson.put("hasExpertInfo", hasExpertInfo);
        if (hasExpertInfo) {
            //再获取单位，因为专家修改页面  snsw可能为空
            JSONObject enterpriseInfo = this.sysUserService.getBaseInfoByUnitidForExpert(unitid);
            enterpriseInfo.put("jtgzdw", userInfo.get("jtgzdw") + "");
            //返回前台单位信息
            resJson.put("enterpriseInfo", enterpriseInfo);
            //说明已经保存过专家信息
            JSONObject ryjbxx = this.zjkRyjbxxUpdateService.getRyjbxx(useridJson);

            String expertid = ryjbxx.get("id") + "";
            //获取学科
            JSONArray subjectArr = this.zjkRyjbxxxkflUpdateService.getRyjbxxXkflNew(expertid);

            //返回前台专家主表信息
            resJson.put("ryjbxx", ryjbxx);
            //返回前台专家学科信息
            resJson.put("subjectArr", subjectArr);
            ZjkRyjbxxInfoUpdate zjkRyjbxxInfoUpdate = this.zjkRyjbxxInfoUpdateService.getDataByZjid(expertid);
            resJson.put("ryjbxxinfo", Util.isEoN(zjkRyjbxxInfoUpdate) ? new ZjkRyjbxxInfoUpdate() : zjkRyjbxxInfoUpdate);
        } else {
            //说明没有保存过专家信息
        }

        return ReturnToJs.success(resJson);
    }

    /**
     * 注册时发送短信验证码
     */
    @PostMapping(value = "/sendMobileCode.do")
    public @ResponseBody
    ReturnToJs sendMobileCode(@RequestBody JSONObject json,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mobile = json.getString("mobile");
        String key = json.getString("CaptchaKey");
        String code = json.getString("CaptchaCode");
        String redisCode = (String) this.bladeRedis.get("halberd:auth::halberd:captcha:" + key);

        String requestip = Util.getIpAddr(request);

        boolean ipLimit = redisRateLimiterClient.tryAcquire("halberd:auth::halberd:ipLimit:" + requestip, 5, 60);
        boolean mobileLimit = redisRateLimiterClient.tryAcquire("halberd:auth::halberd:mobileLimit:" + mobile, 5, 60);

        if (!ipLimit) {
            return ReturnToJs.failure("当前ip调用超过限制！");
        }
        if (!mobileLimit) {
            return ReturnToJs.failure("当前手机号调用超过限制！");
        }

        if (code != null && StringUtil.equalsIgnoreCase(redisCode, code) || "dev".equals(System.getProperties().getProperty("spring.profiles.active")) && "tz85118011".equals(code)) {
//            String mobileCode = Util.getMobileCode();
            String mobileCode = "5903";
            String tempCode = "【省科技厅】您好，您本次的验证码为：" + mobileCode + "，有效期为2分钟。";
            System.out.println("sendMobileCode-" + mobileCode);

            //数据库留存一条验证码记录
            this.sysUserService.insertIntoValidCode(mobileCode, mobile, requestip);

            String smsCaptchaKey = Util.NewGuid();
            //redis中存一条短信验证码
            this.bladeRedis.setEx("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey, mobileCode, Duration.ofMinutes(3L));

            JSONObject result = new JSONObject();
            result.put("SMS-Captcha-Key", smsCaptchaKey);
            //发送验证码
            try {
                ReturnToJs<Object> message = this.messageService.sendMessage(mobile, tempCode);
                if ("发送成功".equalsIgnoreCase(message.getMsg())) {
                    return ReturnToJs.success(result);
                } else {
                    return ReturnToJs.failure("验证码发送失败");
                }
            } catch (Exception e) {
                return ReturnToJs.failure("短信接口错误。");
            }
        } else {
            return ReturnToJs.failure("图形验证码错误。");
        }
    }


    /**
     * 登录时发送短信验证码--需要验证传过来的账号密码以及图形验证码
     */
    @RequestMapping(value = "/sendMobileCodeLogin.do")
    public @ResponseBody
    ReturnToJs sendMobileCodeLogin(@RequestBody JSONObject json,
                                   HttpServletRequest request, HttpServletResponse response) {
        String tempMobile = json.getString("mobile");
        String key = json.getString("CaptchaKey");
        String code = json.getString("CaptchaCode");
        String redisCode = (String) this.bladeRedis.get("halberd:auth::halberd:captcha:" + key);

        String userid = json.getString("username");
        String password = json.getString("password");

        UserInfo userInfo = this.sysUserServiceImpl.judgePassword(userid, password);
        if (Util.isEoN(userInfo)) {
            return ReturnToJs.failure("账号或密码错误!");
        }

        SysUser user = this.sysUserServiceImpl.userByAccount(userid);

        String mobile = "";
        //判断是不是多个手机号
        List<Map> list_temp = this.dbHelper.getRows("select mobile from sys_user_phone " +
                        "where regexp_replace(mobile,'(\\d{3})(\\d{4})(\\d{4})','\\1****\\3') = ? and userid = ?",
                new Object[]{tempMobile, user.getId()});
        if (list_temp.size() > 0) {
            mobile = list_temp.get(0).get("mobile") + "";
        } else {
            mobile = user.getMobile();
        }

        String requestip = Util.getIpAddr(request);
        boolean ipLimit = redisRateLimiterClient.tryAcquire("halberd:auth::halberd:ipLimit:" + requestip, 5, 60);
        boolean mobileLimit = redisRateLimiterClient.tryAcquire("halberd:auth::halberd:mobileLimit:" + mobile, 5, 60);

        if (!ipLimit) {
            return ReturnToJs.failure("当前ip调用超过限制!");
        }
        if (!mobileLimit) {
            return ReturnToJs.failure("当前手机号调用超过限制!");
        }

        if (code != null && StringUtil.equalsIgnoreCase(redisCode, code) || "dev".equals(System.getProperties().getProperty("spring.profiles.active")) && "tz85118011".equals(code)) {
//            String mobileCode = Util.getMobileCode();
            String mobileCode = "5903";

            String tempCode = "【省科技厅】您好，您本次的验证码为：" + mobileCode + "，有效期为2分钟。";
            System.out.println("sendMobileCode-" + mobileCode);

            //数据库留存一条验证码记录
            this.sysUserService.insertIntoValidCode(mobileCode, mobile, requestip);

            String smsCaptchaKey = Util.NewGuid();
            //redis中存一条短信验证码
            this.bladeRedis.setEx("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey, mobileCode, Duration.ofMinutes(3L));

            JSONObject result = new JSONObject();
            result.put("SMS-Captcha-Key", smsCaptchaKey);
            //发送验证码
            try {
                ReturnToJs<Object> message = this.messageService.sendMessage(mobile, tempCode);
                if ("发送成功".equalsIgnoreCase(message.getMsg())) {
                    return ReturnToJs.success(result);
                } else {
                    return ReturnToJs.failure("验证码发送失败。");
                }
            } catch (Exception e) {
                return ReturnToJs.failure("短信接口错误。");
            }
        } else {
            return ReturnToJs.failure("图形验证码错误。");
        }
    }

    /**
     * 登录时获取手机号--需要验证传过来的账号密码以及图形验证码
     */
    @RequestMapping(value = "/getMobile.do")
    public @ResponseBody
    ReturnToJs getMobile(@RequestBody JSONObject json,
                         HttpServletRequest request, HttpServletResponse response) {
        String key = json.getString("CaptchaKey");
        String code = json.getString("CaptchaCode");
        String redisCode = (String) this.bladeRedis.get("halberd:auth::halberd:captcha:" + key);

        String userid = json.getString("username");
        String password = json.getString("password");

        UserInfo userInfo = this.sysUserServiceImpl.judgePassword(userid, password);
        if (Util.isEoN(userInfo)) {
            return ReturnToJs.failure("账号或密码错误!");
        }

        if (code != null && StringUtil.equalsIgnoreCase(redisCode, code) || "dev".equals(System.getProperties().getProperty("spring.profiles.active")) && "tz85118011".equals(code)) {
            SysUser sysUser = this.sysUserServiceImpl.userByAccount(userid);
            List<Map<String, String>> mobileList = this.sysUserPhoneService.initDataByUserid(sysUser);
            return ReturnToJs.success(mobileList);
        } else {
            return ReturnToJs.failure("图形验证码错误。");
        }
    }

    /**
     * 单纯验证手机验证码
     */
    @RequestMapping(value = "/judgeMobileCode.do")
    public @ResponseBody
    ReturnToJs judgeMobileCode(@RequestBody JSONObject json) {
        String smsCaptchaKey = json.getString("SMS-Captcha-Key");
        String validCode = json.getString("validCode");
        String redisMobileCode = (String) this.bladeRedis.get("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        this.bladeRedis.del("halberd:auth::halberd:sms-captcha:" + smsCaptchaKey);
        if (validCode == null || !StringUtil.equalsIgnoreCase(redisMobileCode, validCode)) {
            return ReturnToJs.failure("手机验证码错误");
        }
        return ReturnToJs.success();
    }

    @PostMapping(value = "/getUserState")
    @ResponseBody
    public ReturnToJs getUserState() {
        SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
        JSONObject resJson = new JSONObject();
        resJson.put("userid", user.getId());
        resJson.put("minicurrentstate", user.getMinicurrentstate());
        return ReturnToJs.success(resJson);
    }

    /**
     * 用户密码找回
     */
    @PostMapping("/passwordRetrieval.do")
    public void passwordRetrieval(@RequestBody String example, @RequestParam String type,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        R<Object> r = new R<>();
        try {
            JSONObject json = Util.checkFromJson(example);
            json.put("userInfo", Util.deCodeUseridAndPassword(json.getJSONObject("userInfo")));
//            判断验证码是否正确以及是否在有效时间内
            JSONObject judgeUserInfoJson = this.getRegisterService().judgeValidCode(json.getJSONObject("userInfo"), type);
            if (judgeUserInfoJson.getBoolean("flag")) {
                //验证通过，开始发送
                r = sysUserService.passwordRetrieval(type, json.getJSONObject("userInfo"), request);
            } else {
                r.setMsg(judgeUserInfoJson.getString("reason"));
                r.setSuccess(false);
            }
        } catch (Exception e) {
            r.setSuccess(false);
            r.setMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            e.printStackTrace();
            System.out.println("/Register/passwordRetrieval.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(r).toString());
    }
}
