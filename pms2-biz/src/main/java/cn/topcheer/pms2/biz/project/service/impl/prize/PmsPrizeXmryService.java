/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.biz.project.service.impl.prize;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.biz.project.service.impl.PmsSaveAndInitNewService;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.prize.*;
import cn.topcheer.pms2.dao.project.prize.*;

/**
 * PmsPrizeXmry 服务类
 */
@Service(value = "PmsPrizeXmryService")
public class PmsPrizeXmryService extends GenericService<PmsPrizeXmry> {

    public PmsPrizeXmryDao getPmsPrizeXmryDao() {
        return (PmsPrizeXmryDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPrizeXmryDao(PmsPrizeXmryDao pmsPrizeXmryDao) {

        this.setGenericDao(pmsPrizeXmryDao);
    }

    @Autowired
    private PmsSaveAndInitNewService pmsSaveAndInitNewService;

    @Autowired
    private SysUserServiceImpl sysUserService;


    /**
     * 处理获取到的结果列表
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param saveFlag     是否已保存过（true：已保存，flase：未保存）
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return List
     */
    @Override
    protected List<Map> afterGenericFetchFun(List<Map> result, String mainid, String type, Boolean saveFlag, String batchid, JSONObject tableObject, String[] initParamArr) {
        result = super.afterGenericFetchFun(result, mainid, type, saveFlag, batchid, tableObject, initParamArr);

        // 初始化类型
        String initType = tableObject.getString("initType");

        // 如果子表未保存过，并类型是true时，并且数据为空，返回一些默认数据
        if (!saveFlag && "true".equals(initType) && result.size() == 0) {
            // 获取当前用户
            SysUser user = sysUserService.findById(AuthUtil.getUserId());
            if (user == null) {
                return result;
            }

            // 取用户的相关信息并赋到申报
            JSONObject userInfoForSb = pmsSaveAndInitNewService.getUserInfoForSb(user);

            // JSON的key下划线转驼峰
            userInfoForSb = Util.jsonKeyToCamelCase(userInfoForSb);
            result.add(JSONObject.fromObject(JSON.toJSONString(userInfoForSb).replaceAll("null", "\"\"")));
        }
        // 如果子表未保存过，并类型是true1时，并且数据为空，返回一些默认数据
        if (!saveFlag && "true1".equals(initType) && result.size() == 0) {
            // 获取当前用户
            SysUser user = sysUserService.findById(AuthUtil.getUserId());
            if (user == null) {
                return result;
            }

            // 取用户的相关信息并赋到申报
            JSONArray userEducationForSb = pmsSaveAndInitNewService.getUserEducationExpSb(user);

            // JSON的key下划线转驼峰
//            userInfoForSb = Util.jsonKeyToCamelCase(userInfoForSb);
            result.addAll(userEducationForSb);
        }

        return result;
    }


}
