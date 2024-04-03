package cn.topcheer.pms2.biz.utils;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.pms2.biz.project.service.impl.PmsSaveAndInitNewService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 初始化人员、单位等数据
 *
 * @author GaoGongxin
 * @date 2020/9/16 20:39
 */
@Component(value = "GenericInitData")
public class GenericInitData {


    @Autowired
    private PmsSaveAndInitNewService pmsSaveAndInitNewService;
    @Autowired
    private SysUserServiceImpl sysUserService;


    /**
     * 获取当前登录人的信息
     *
     * @return JSONObject
     */
    public JSONObject getUserInfoForSb() {
        SysUser user = sysUserService.findById(AuthUtil.getUserId());
        if (user == null) {
            return new JSONObject();
        }

        return pmsSaveAndInitNewService.getUserInfoForSb(user);
    }

    /**
     * 通过用户的单位信息初始化财务负责人的信息
     *
     * @return JSONObject
     */
    public JSONObject getUserEnterCwForSb() {
        SysUser user = sysUserService.findById(AuthUtil.getUserId());
        if (user == null) {
            return new JSONObject();
        }

        return pmsSaveAndInitNewService.getUserEnterCwForSb(user);
    }

    /**
     * 通过用户信息初始化单位的信息
     *
     * @return JSONObject
     */
    public JSONObject getUserEnterForSb() {
        SysUser user = sysUserService.findById(AuthUtil.getUserId());
        if (user == null) {
            return new JSONObject();
        }

        return pmsSaveAndInitNewService.getUserEnterForSb(user);
    }


    /**
     * 通过用户信息初始化单位的信息
     *
     * @return JSONArray
     */
    // TODO: 2021/10/29 需要修正
    public JSONArray getChangeData(String linkid) {
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }


}
