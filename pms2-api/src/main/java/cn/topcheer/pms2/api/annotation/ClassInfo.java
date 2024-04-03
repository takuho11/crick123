package cn.topcheer.pms2.api.annotation;


import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;

import java.lang.annotation.*;

/**
 * 类信息说明
 *
 * @author GaoGongxin
 * @date 2020/9/12 23:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassInfo {

    /**
     * 类注释信息
     *
     * @return
     */
    String name() default "";

    /**
     * 所属系统模块（默认为未定义）
     *
     * @return
     */
    SysModuleEnum module() default SysModuleEnum.UNDEFINE;

    /**
     * 系统类层次信息
     *
     * @return
     */
    ClassLevelEnum level() default ClassLevelEnum.UNDEFINE;
}
