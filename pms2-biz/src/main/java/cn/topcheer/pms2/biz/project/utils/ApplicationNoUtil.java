package cn.topcheer.pms2.biz.project.utils;

import cn.hutool.extra.spring.SpringUtil;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.pms2.biz.project.service.impl.SysNumbershopService;

public class ApplicationNoUtil {

    /**
     * 生成受理编码
     * @param year 批次年度
     * @param uniformCode 单位统一信用代码
     * @param applicationcode 计划体系编码
     * @param batchname 批次名称
     * @param numberarithmetic 编码规则
     * @return
     * @throws Exception
     */
    public static String getApplicationNo(String year,String uniformCode,String applicationcode,String batchname,String numberarithmetic) {
        String applicationNo =  "";
        try {
            SysNumbershopService sysNumbershopService = SpringUtil.getApplicationContext().getBean("SysNumbershopService",SysNumbershopService.class);
            if ("T10010".equals(applicationcode)){
                if (Util.isEoN(batchname) || Util.isEoN(numberarithmetic) ) {
                    applicationNo =  "";
                } else {
                    applicationNo = sysNumbershopService.GetSingleFlowID(batchname,numberarithmetic,3);
                }
            } else {
                if (Util.isEoN(year) || Util.isEoN(uniformCode) || Util.isEoN(applicationcode) || Util.isEoN(batchname)  ) {
                    applicationNo =  "";
                } else {
                    String organizationCode = uniformCode.substring(8, 17);
                    String projectArea = applicationcode.substring(applicationcode.length() - 1);
                    String tarGetStr = sysNumbershopService.GetSingleFlowID(batchname, "", 4);
                    applicationNo = year + organizationCode + projectArea + tarGetStr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicationNo;
    }

    public static String readWord(StringBuffer Str, String split) {
        String rv = "";
        int index = Str.indexOf(split);
        if (index > -1) {
            rv = Str.substring(0, index);
            Str.delete(0, index + split.length());
        } else {
            rv = Str.toString();
            Str.delete(0, Str.length());
        }
        return rv;
    }
}
