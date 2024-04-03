package cn.topcheer.pms2.biz.limit.business;

import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.biz.limit.AbstractLimitStrategy;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SbLimitStrategy extends AbstractLimitStrategy {
    
    @Resource
    private PmsProjectbaseService pmsProjectbaseService;


    @Override
    public void preExecuteConfigCondition(HashMap<String, Object> requestParameter) {

    }

    @Override
    public void executeConfigCondition(List<Map<String, String>> limitConfigConditionList, HashMap<String, Object> requestParameter) {
        String batchId = (String) requestParameter.get("batchId");
        if (Util.isEoN(batchId)) {
            throw new ServiceException("前端参数batchId不存在，请检查。");
        }

        String mainId = (String) requestParameter.get("mainId");
        if (Util.isEoN(mainId)) {
            throw new ServiceException("前端参数mainId不存在，请检查。");
        }

        PmsProjectbase pmsProjectbase = pmsProjectbaseService.findById(mainId);
        boolean isSb = !Util.isEoN(pmsProjectbase);
        limitConfigConditionList.forEach(configMap -> {
            String limitConditionCode = configMap.get("limitConditionCode");
            String limitConfigParam = configMap.get("limitConfigParam");//利用limitConfigParam进行限制判断
            switch (limitConditionCode) {
                case "JudgeSbNum":
                    // 当前批次用户申报数量限制，如果超过数量那就不允许提交
                    judgeSbNum(isSb, limitConfigParam, batchId);
                    break;
                default:
                    throw new ServiceException("当前限制条件编码【" + limitConditionCode + "】没有配置。");
            }
        });
    }
    

    /**
     * 判断申报数量
     *
     * @param limitConfigParam 限制参数
     * @author szs
     * @date 2024-03-22
     */
    private void judgeSbNum(boolean isSb, String limitConfigParam, String batchId) {
        if (isSb) {
            return;
        }

        JSONObject limitConfigParamJson = JSONObject.fromObject(limitConfigParam);
        String num = limitConfigParamJson.getString("num");

        HqlBuilder<PmsProjectbase> builder = HqlBuilder.builder();
        builder.eq(PmsProjectbase::getPlanprojectbatchid, batchId);
        builder.eq(PmsProjectbase::getDeclarantid, AuthUtil.getUserId());
        builder.eq(PmsProjectbase::getIsDeleted, 0);
        Long count = pmsProjectbaseService.findCount(builder);
        if (count >= Long.parseLong(num)) {
            throw new ServiceException("申报数量已达上限：" + count);
        }

    }


}
