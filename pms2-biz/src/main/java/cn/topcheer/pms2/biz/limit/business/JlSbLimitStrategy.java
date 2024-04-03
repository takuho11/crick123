package cn.topcheer.pms2.biz.limit.business;

import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.project.entity.prize.PmsPrize;
import cn.topcheer.pms2.biz.limit.AbstractLimitStrategy;
import cn.topcheer.pms2.biz.limit.exception.LimitException;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.project.service.impl.prize.PmsPrizeService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JlSbLimitStrategy extends AbstractLimitStrategy {

    @Resource
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Resource
    private PmsPrizeService pmsPrizeService;
    @Resource
    private DBHelper dbHelper;

    @Override
    public void preExecuteConfigCondition(HashMap<String, Object> requestParameter) {
//        String batchId = (String) requestParameter.get("batchId");
//        PmsPlanprojectbatch pmsPlanprojectbatch = pmsPlanprojectbatchService.findById(batchId);
//        if (null == pmsPlanprojectbatch) {
//            throw new ServiceException("当前小批次不存在，请检查。");
//        }
//        Date startDate = pmsPlanprojectbatch.getStarttime();
//        Date endDate = pmsPlanprojectbatch.getEndtime();
//        Date nowDate = new Date();
//        if (Util.isEoN(startDate) || Util.isEoN(endDate)) {
//            throw new LimitException("当前批次未设置开始startDate或者结束endDate时间。");
//        }
//        if (nowDate.getTime() < pmsPlanprojectbatch.getStarttime().getTime() || nowDate.getTime() > pmsPlanprojectbatch.getEndtime().getTime()) {
//            throw new LimitException("当前不在申报时间范围内。");
//        }
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

        PmsPrize pmsPrize = pmsPrizeService.findById(mainId);
        boolean isSb = !Util.isEoN(pmsPrize);
        //judgeItemNum(isSb);
        limitConfigConditionList.forEach(configMap -> {
            String limitConditionCode = configMap.get("limitConditionCode");
            String limitConfigParam = configMap.get("limitConfigParam");//利用limitConfigParam进行限制判断
            switch (limitConditionCode) {
                case "JudgePrizeNum":
                    //提交入口判断允许提交。如果去年已经有通过的奖励了则不允许提交
                    judgePrizeNum(isSb, limitConfigParam);
                    break;
                case "JudgeSbNum":
                    // 当前批次用户申报数量限制，如果超过数量那就不允许提交
                    judgeSbNum(isSb, limitConfigParam, batchId);
                    break;
                default:
                    throw new ServiceException("当前限制条件编码【" + limitConditionCode + "】没有配置。");
            }
        });
    }

    //办事大厅入口判断允许申报。如果当年已经新建了两个事项了则不允许申报
    private void judgeItemNum(boolean isSb) {
        if (!isSb) {
            String count = dbHelper.getOnlyStringValue("select count(1) as count" +
                    " from pms_prize e" +
                    " left join PMS_PRIZE_XMRY ry on e.id = ry.mainid AND ry.TYPE = 'basicInfo'" +
                    " left join PMS_PRIZE_CDDW dw on e.id = dw.mainid AND dw.TYPE = 'mainUnit'" +
                    " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id" +
                    " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id" +
                    " where" +
                    " batch.annually = ?" +
                    " and bigbatch.SYSTEM = ?" +
                    " and e.declarantid = ?", new Object[]{"2024", "jl", AuthUtil.getUser().getUserId()});
            if (!Util.isEoN(count) && Integer.parseInt(count) >= 2) {
                throw new ServiceException("当年已经新建了两个事项了则不允许申报。");
            }
        }
    }

    //提交入口判断允许提交。如果去年已经有通过的奖励了则不允许提交
    private void judgePrizeNum(boolean isSb, String limitConfigParam) {
        if (!isSb) {
            return;
        }
        JSONObject limitConfigParamJson = JSONObject.fromObject(limitConfigParam);
        String year = limitConfigParamJson.getString("year");
        String num = limitConfigParamJson.getString("num");
        String count = dbHelper.getOnlyStringValue("select count(1) as count" +
                " from pms_prize e" +
                " left join PMS_PRIZE_XMRY ry on e.id = ry.mainid AND ry.TYPE = 'basicInfo'" +
                " left join PMS_PRIZE_CDDW dw on e.id = dw.mainid AND dw.TYPE = 'mainUnit'" +
                " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id" +
                " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id" +
                " where" +
                " batch.annually = ?" +
                " and bigbatch.SYSTEM = ?" +
                " and e.minicurrentstate like '%结束%'" +
                " and e.declarantid = ?", new Object[]{year, "jl", AuthUtil.getUser().getUserId()});
        if (!Util.isEoN(count) && Integer.parseInt(count) >= Integer.parseInt(num)) {
            throw new ServiceException(year + "年已经有通过的奖励，不允许提交。");
        }
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

        HqlBuilder<PmsPrize> builder = HqlBuilder.builder();
        builder.eq(PmsPrize::getPlanprojectbatchid, batchId);
        builder.eq(PmsPrize::getDeclarantid, AuthUtil.getUserId());
        builder.eq(PmsPrize::getIsDeleted, 0);
        Long count = pmsPrizeService.findCount(builder);
        if (count >= Long.parseLong(num)) {
            throw new ServiceException("申报数量已达上限：" + count);
        }

    }


}
