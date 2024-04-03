package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 用于标记单位表，例如：Pms_EnterpriseOrigination等
 * @author GaoGongxin
 * @date 2021/7/18 17:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnterpriseTable {

}
