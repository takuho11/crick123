package cn.topcheer.pms2.web.controller.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.biz.modules.auth.utils.TokenUtil;
import cn.topcheer.halberd.biz.modules.system.entity.LoginLog;
import cn.topcheer.halberd.biz.modules.system.entity.UserInfo;
import cn.topcheer.halberd.biz.modules.system.service.ILoginLogService;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.sys.impl.SysParamsServiceImpl;
import cn.topcheer.pms2.biz.utils.Util;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.inspur.dzzw.oauth.util.OauthClientSecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * @Author:shenxian
 * @othername:liuxue
 * @Date:2024-02-01 11:13
 */
@Slf4j
@RestController
@RequestMapping("/ZwwOauth")
@Api(value = "政务网",tags = "政务网相关方法")
public class ZwwController {

    private static final String APPCODE = "LKAOUIICC";
    private static final String APPSECRET = "LBDZAWMWNYXFG8CCGMEE";

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BladeRedis bladeRedis;
    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;
    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private SysParamsServiceImpl sysParamsService;

    @RequestMapping("/loginFromZww")
    public void loginFromZww(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String postResult = request.getParameter("assertion");
        String sp = request.getParameter("goto");
        String planid = request.getParameter("planid");
        if(planid != null){
            log.info("planid : {}", planid);
        }else {
            planid = "businesshall";
        }

        //法人
        // postResult = "SU5TUFVSQ1NHjhmO1+TedpC5LgAmT1c7qy0nNOCwJqE0VtA6jRcRzW1QylWIJtT+Wd2r/EA6xTrlG3pIc1pmu0fVLlxM+uQzI2AQ8NOk98hJ5wdrypnB4CGw3vS5xG6reqDIQwiZhgdlEqqvqpStzD3KNDUQhlxGO2AyBMgPAcK8tiZwgPjIiTBpNy9FIEQSfjEY9qvhgEOS/eVWzrufNZGjPS3V5MkWRwhGogEGvZVx8BPykbeeQ/LZEzr7SNonO+Ebdu/1akX/yhn90WToUFinRgJ2dR48iDivay2QhFIycnRekikKl4HWQcPcaGN+4jhSlz7TDeyd7hqwfSLhB7VIHyY1hyiV2z2bq3Aux3pP5+mBz1jId6V/1en16uMTESNfDqogN+hyI9d3NMblUKqzg+StJxm35QKNPy1nyABpvGkawc4EK3HUGdn4O1MYE4ZGADBXUNyZywZ9xcG0iIkNFmnssB8lprRKpg9JqOwmHvOzR1W8/r6uqIHhCA2vOW4F31UBsWvD+jAV0b7JENHRnq8TjEvtzwce0mzzLHBXWOakgeElEtJjEtFI4vSZ91rTIiQFu14Iane0QZUOBTvWAt1Jc8bu6BRVorCcVfq5Y1pwHdJF27bMpx0yk1hIaBwr/bPrldriUYngxqw/ZiTXTAdZwekKzybjoW0YNtkYDIBH9aBcx/PfNAVqPkvjCz07NHYKmQ4EaHIqgFoxlOSUlwB+t/j7hipfII9itb0SZI4lFs4UnxWd0o8wvnp2YL8lI+lWsUKMo4lFOM4XKY5YogXKuOIp25ksBuPk5DoB4HobCw1+U+7srfthn4BxDytnWXru8IWzui5a/6RSCUolHuWwE/tKNLRUSoceSfn8+kSYyICdWeGMXQDDF3VsGxmWwLSynNffBNxhMn+D1mcoD160zcNqtWBvvMNgSi/EKnsMZBnv+wJhbnan0QKmZArVDZOHOS4W6QI2DvC6wR9wuZST9nwxHzwZqCfU8hXWiJzAOFKmgWcOmXkMlci51loernZCRVGUunkN93YxiBsKts9l6yc13Q==";
        //个人
        // postResult = "SU5TUFVSQ1NHyYXTvA0vbpREA/D+p+LUcQflP9eDBwktP103EJzwiWk8jzKkcraTDz4tRNICwEp18PypiWdykCAkLEKuVYiUIFErbxZloOPmUJWShkj9c4SA9KIIrZ4RCNPOLIjUOyWRR0G7LKX7czUAOcZf6fJfR/qGFGRHSc3iCSdIu3lop8k4QI7XmutQPjLqYWnTDp6+hwxylNFkwBU4rK3VsSHcO2oe7njvVdBFaRypYoxTIZ38exlWulQjb66xw30ulWVWRjAwMpFxyMwg387LaA2SVMxKLomaLH5qSLJT+IP6u/5d2ItEiuBX020iWQ5YoHBmaNOmQVLiYxSo4E36VxEMkttvLqrXEyxVdZBOotz2julgPLXlQRQcd2xTeGt9wz33TiC6ZEz4S5nmjfZoM5TRkn/gKbP2Tc/dLBFJBA8Rvxb3mxHbjUBwURtyeMfJYs0to0MjjXaHBfw7OSEa2PImbK0aa3xdp8+15aGieZrY82AAm5GfxsNl3dyaYuKDNiw9ESOeUIFeB/NAAkLLSvKYm+q0wgwXzdFDrNUOreUV+TB0wm2+zyUPtiyMiONC22YMsboMY1PfZ6F+beytqngfxp6oCLsFe/T8OwhJ3kDIe2j08l5qJAjCmlwJfuP8gR0VK605IH3/LqVExtB5DuGS+gYzUdWUctskPVO7rb2Eh1yeEMmac65jKvE5Tzv2MeYP4+a5pPX5/RMSY1PEU/b7TmSqFYtxOdDwqgcAaTeQLIwJQkHMIkl3csBqQfrkErlLzmEGdAbw3Eic38FP/sNxpvhgdRbM4djwU1wXRamp5EUuRAiHfCZurYBdnc+1vtxz6nE9tPClTgdARuSa69G7i1AEB2zOmVZoddQWsHSefOrcTL3dtRwCXyaYNsEY4TtbTt2uoh360TY=";

        //具体事项页面地址 如http://aa.com/item?id=3232
        // log.info("-----------postResult:{}", postResult);
        // log.info("-----------sp:{}", sp);

        //sp是2表示退出登录,1表示注册
        if (sp!=null && ("2".equals(sp) || "1".equals(sp))){
            response.sendRedirect("https://smrz.zwfw.guizhou.gov.cn/sso/register");
            return;
        }
        if (null != postResult && !"".equals(postResult)) {
            OauthClientSecureUtil client = new OauthClientSecureUtil();
            Map secureAttrs;
            try {
                secureAttrs = client.buildUserInfo(postResult, APPSECRET, APPSECRET);
                JSONObject userInfoJson = JSONObject.fromObject(secureAttrs);
                log.info("获取用户信息：{}" , userInfoJson);
                //后续步骤
                String userType = userInfoJson.getString("USER_TYPE");
                // userType 1->个人 2->法人
                if("1".equals(userType)){
                    String certificateno = userInfoJson.getString("CARD_NO");
                    SysUser user = this.sysUserService.findUserByIdNumberOrCreditcode("gr", certificateno, "129947C6-94DC-4A7D-84D2-E78A80E518A3");
                    if (Util.isEoN(user)) {
                        user = this.sysUserService.findUserByIdNumberOrCreditcode("gr", certificateno, "f8a87c80-f89d-48bc-ad96-840ab6aa81b2");
                    }
                    if(!Util.isEoN(user)){
                        //说明有用户，直接生成userInfo和一个key返回前台页面
                        String uuid = Util.NewGuid();
                        //redis中存一条数据用于给前台调用获取token,value存用户id
                        this.bladeRedis.setEx("halberd:auth::halberd:user-key:" + uuid, user.getId(), Duration.ofMinutes(30L));
                        //判断是否需要完善
                        Boolean needComplete = this.sysUserService.checkIfNeedCompleteInfo(user.getId(), "gr");
                        if(needComplete){
                            this.jumpToUrl(uuid, "1", "gr", user.getId(), planid, request, response);
                            // response.sendRedirect("http://61.243.3.47:8081/#/jump?typr=gr&key=" + uuid + "&needComplete=1&mainid=" + user.getId());
                        }else {
                            this.jumpToUrl(uuid, "2", "gr", user.getId(), planid,  request, response);
                            // response.sendRedirect("http://61.243.3.47:8081/#/jump?type=gr&key=" + uuid + "&needComplete=2&mainid=" + user.getId());
                        }
                    } else {
                        // log.info("未匹配到个人用户 : {}", certificateno);
                        //生成user与其他表
                        user = this.sysUserService.userRegisterForZwwGrUser(userInfoJson);
                        // log.info("个人用户生成成功 : {}", user.getId());
                        String uuid = Util.NewGuid();
                        //redis中存一条数据用于给前台调用获取token,value存用户id
                        this.bladeRedis.setEx("halberd:auth::halberd:user-key:" + uuid, user.getId(), Duration.ofMinutes(30L));
                        this.jumpToUrl(uuid, "2", "gr", user.getId(), planid, request, response);
                        // response.sendRedirect("http://61.243.3.47:8081/#/jump?type=gr&key=" + uuid + "&needComplete=2&mainid=" + user.getId());
                    }
                }else if("2".equals(userType)){
                    String creditcode = userInfoJson.getString("CORPORATE_CODE");
                    SysUser user = this.sysUserService.findUserByIdNumberOrCreditcode("fr", creditcode, "C7004168-4E0C-4F1F-B341-A225B5644DC5");
                    //未找到单位管理员，尝试查找未审核通过的管理员
                    if (Util.isEoN(user)) {
                        user = this.sysUserService.findUserByIdNumberOrCreditcode("fr", creditcode, "aaaa-bbbb-cccc-dddd");
                    }
                    if(!Util.isEoN(user)){
                        //说明有用户，直接生成userInfo和一个key返回前台页面
                        String uuid = Util.NewGuid();
                        //redis中存一条数据用于给前台调用获取token,value存用户id
                        this.bladeRedis.setEx("halberd:auth::halberd:user-key:" + uuid, user.getId(), Duration.ofMinutes(30L));
                        //判断是否需要完善
                        Boolean needComplete = this.sysUserService.checkIfNeedCompleteInfo(user.getId(), "gr");
                        if(needComplete){
                            this.jumpToUrl(uuid, "1", "fr", user.getId(), planid, request, response);
                            // response.sendRedirect("http://61.243.3.47:8081/#/jump?type=fr&key=" + uuid + "&needComplete=1&mainid=" + user.getId());
                        }else {
                            this.jumpToUrl(uuid, "2", "fr", user.getId(), planid, request, response);
                            // response.sendRedirect("http://61.243.3.47:8081/#/jump?type=frkey=" + uuid + "&needComplete=2&mainid=" + user.getId());
                        }
                    }else {
                        // log.info("未匹配到法人用户 : {}", creditcode);
                        //生成user与其他表
                        user = this.sysUserService.userRegisterForZwwFrUser(userInfoJson);
                        // log.info("法人用户生成成功 : {}", user.getId());
                        String uuid = Util.NewGuid();
                        //redis中存一条数据用于给前台调用获取token,value存用户id
                        this.bladeRedis.setEx("halberd:auth::halberd:user-key:" + uuid, user.getId(), Duration.ofMinutes(30L));
                        this.jumpToUrl(uuid, "2", "fr", user.getId(), planid, request, response);
                        // response.sendRedirect("http://61.243.3.47:8081/#/jump?type=gr&key=" + uuid + "&needComplete=2&mainid=" + user.getId());
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("https://smrz.zwfw.guizhou.gov.cn/sso/login?utype=0&client_id=" + APPCODE + "&goto=http://61.243.4.71:8081/api/ZwwOauth/loginFromZww?planid=businesshall");
        }
    }

    public void jumpToUrl(String key, String needComplete, String type, String mainid, String planid,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "";
        SysParams zwwUrl = this.sysParamsService.getById("zwwUrl");
        if (!Util.isEoN(zwwUrl)) {
            url = zwwUrl.getValue();
        }
        if (Util.isEoN(url)) {
            url = "http://61.243.4.71:8081/#/jump";
        }
        response.sendRedirect(url + "?key=" + key + "&needComplete=" + needComplete + "&type=" + type + "&mainid=" + mainid + "&planid=" + planid);
    }

    @RequestMapping("/grantUser")
    public Kv grantUser(@RequestParam String key){
        String id = this.bladeRedis.get("halberd:auth::halberd:user-key:" + key);
        SysUser user = this.sysUserService.findById(id);
        UserInfo userInfo = this.sysUserServiceImpl.buildUserInfo(user);
        this.recordLoginLog(user.getUserid(), "政务网统一身份认证登录", true, "");
        return TokenUtil.createAuthInfo(userInfo);
    }

    private void recordLoginLog(String account, String loginType, boolean result, String memo) {
        LoginLog loginLog = new LoginLog();
        loginLog.setOperatorResult(result);
        loginLog.setRecordTime(new Date());
        loginLog.setAuthService("ZwwOauth");
        loginLog.setClientIp(WebUtil.getIP());
        loginLog.setLoginType(loginType);
        loginLog.setAccount(account);
        loginLog.setRemark(memo);
        this.loginLogService.save(loginLog);
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "测试跳转",notes = "测试跳转")
    @PostMapping("testJumpUrl")
    @ResponseBody
    public ReturnToJs testJumpUrl(@RequestBody String planid, HttpServletRequest request, HttpServletResponse response){
        planid = JSONObject.fromObject(planid).get("planid").toString();
        String url = "";
        String userId = AuthUtil.getUserId();
        SysUser user = sysUserService.getById(userId);
        String uuid = Util.NewGuid();
        //redis中存一条数据用于给前台调用获取token,value存用户id
        this.bladeRedis.setEx("halberd:auth::halberd:user-key:" + uuid, user.getId(), Duration.ofMinutes(30L));
        Boolean needComplete = this.sysUserService.checkIfNeedCompleteInfo(user.getId(), "gr");
        try {
            if(needComplete){
                url = this.jumpToUrlTest(uuid, "1", "gr", user.getId(), planid, request, response);
//                response.sendRedirect("http://61.243.3.47:8081/#/jump?typr=gr&key=" + uuid + "&needComplete=1&mainid=" + user.getId());
            }else {
                url = this.jumpToUrlTest(uuid, "2", "gr", user.getId(), planid,  request, response);
//                response.sendRedirect("http://61.243.3.47:8081/#/jump?type=gr&key=" + uuid + "&needComplete=2&mainid=" + user.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        returnToJs.setData(url);
        return returnToJs;
    }

    public String jumpToUrlTest(String key, String needComplete, String type, String mainid, String planid,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "";
        SysParams zwwUrl = this.sysParamsService.getById("zwwUrl");
        if (!Util.isEoN(zwwUrl)) {
            url = zwwUrl.getValue();
        }
        if (Util.isEoN(url)) {
            url = "http://localhost:9000/#/jump";
        }
        return url + "?key=" + key + "&needComplete=" + needComplete + "&type=" + type + "&mainid=" + mainid + "&planid=" + planid;
    }
}
