package cn.topcheer.pms2.biz.utils;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;

public class BizUtil {


    /**
     * 通过前台映射表名获取Class对象
     *
     * @param tableMappingName
     * @return
     */
    public static Class getClassByTableObject(String tableMappingName) {
        // 前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableMappingName);

        // 判断枚举信息中是否包含service名称
        if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            return null;
        }

        // 存储字符串型的service类名，例如：Pms_ProjectbaseService
        String tempServiceNameStr;

        // serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
        String servicePathNameStr;

        // 判断枚举信息中是否包含service名称
        if (!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
        } else {
            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
        }

        Class clazz = null;
        try {
            // 通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
            clazz = Class.forName(servicePathNameStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazz;
    }


}
