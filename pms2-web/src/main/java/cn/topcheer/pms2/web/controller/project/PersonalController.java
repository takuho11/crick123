package cn.topcheer.pms2.web.controller.project;

import cn.topcheer.halberd.core.redis.RedisRateLimiterClient;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.project.dto.MyselfInfoDTO;
import cn.topcheer.pms2.api.project.dto.SendMobileCodeDTO;
import cn.topcheer.pms2.api.project.vo.PensonalInfoVO;
import cn.topcheer.pms2.biz.bi.BiTalentBiService;
import cn.topcheer.pms2.biz.message.MessageService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;
import java.util.List;

/**
 * Copyright (C)
 * FileName: PersonalController
 *
 * @Author: maokai
 * Date:     2024/3/20 9:12
 * Description: 个人信息通用接口
 */
@Slf4j
@NonDS
@RestController
@RequestMapping("/personal")
@Api(value = "个人信息通用接口", tags = "个人信息通用接口")
public class PersonalController {

    @Resource
    private BladeRedis bladeRedis;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private MessageService messageService;
    @Resource
    private BiTalentBiService biTalentBiService;

    @Resource
    private RedisRateLimiterClient redisRateLimiterClient;

    @ApiOperation(value = "通过手机验证码获取个人信息")
    @PostMapping("/myselfInfo")
    public R<PensonalInfoVO> myselfInfo(@Valid @RequestBody MyselfInfoDTO dto) {
        String mobile = dto.getMobile();
        String validCode = dto.getMobileCode();
        String smsCaptchaKey = dto.getSmsCaptchaKey();
        String redisMobileCode = this.bladeRedis.get("halberd:personal::halberd:sms-captcha:" + smsCaptchaKey);
        this.bladeRedis.del("halberd:personal::halberd:sms-captcha:" + smsCaptchaKey);
        if (Func.isBlank(validCode) || !StringUtil.equalsIgnoreCase(redisMobileCode, validCode)) {
            return R.fail("手机验证码错误");
        }
        String validMobile = this.bladeRedis.get("halberd:personal::halberd:sms-mobile:" + smsCaptchaKey);
        this.bladeRedis.del("halberd:personal::halberd:sms-mobile:" + smsCaptchaKey);
        if (Func.isBlank(validMobile) || !StringUtil.equalsIgnoreCase(mobile, validMobile)) {
            return R.fail("手机号错误");
        }
        // 获取用户信息
        PensonalInfoVO vo = biTalentBiService.findPersonalSelfInfo(mobile);
        return R.data(vo);
    }


    /**
     * 发送短信验证码
     */
    @ApiOperation(value = "发送短信验证码")
    @GetMapping(value = "sendMobileCode")
    public Result sendMobileCode(@Valid SendMobileCodeDTO dto, HttpServletRequest request) {
        String requestip = Util.getIpAddr(request);
        String mobile = dto.getMobile();
        boolean ipLimit = redisRateLimiterClient.tryAcquire("halberd:personal::halberd:ipLimit:" + requestip, 5, 60);
        boolean mobileLimit = redisRateLimiterClient.tryAcquire("halberd:personal::halberd:mobileLimit:" + mobile, 5, 60);
        if (!ipLimit) {
            return Result.fail("当前ip调用超过限制！");
        }
        if (!mobileLimit) {
            return Result.fail("当前手机号调用超过限制！");
        }
        // TODO
        String mobileCode = "";
        String property = System.getProperties().getProperty("spring.profiles.active");
        if (Func.equals("dev", property) || Func.equals("gz", property) || Func.equals("test", property)) {
            mobileCode = "5903";
        } else {
            mobileCode = Util.getMobileCode();
        }
        String tempCode = "【省科技厅】您好，您本次的验证码为：" + mobileCode + "，有效期为2分钟。";
        log.info("sendMobileCode - {}", mobileCode);
        //数据库留存一条验证码记录
        this.sysUserService.insertIntoValidCode(mobileCode, mobile, requestip);
        String smsCaptchaKey = Util.NewGuid();
        //redis中存一条短信验证码
        this.bladeRedis.setEx("halberd:personal::halberd:sms-captcha:" + smsCaptchaKey, mobileCode, Duration.ofMinutes(3L));
        this.bladeRedis.setEx("halberd:personal::halberd:sms-mobile:" + smsCaptchaKey, mobile, Duration.ofMinutes(3L));
        //发送验证码
        ReturnToJs<Object> message = this.messageService.sendMessage(mobile, tempCode);
        if (Func.equals("发送成功", message.getMsg())) {
            return Result.data(smsCaptchaKey);
        }
        return Result.fail("验证码发送失败");
    }
}
