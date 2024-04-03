package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 用于标记变更表，例如：PMS_ANNUALREPORT_CHANGE等
 * @author GaoGongxin
 * @date 2021/7/18 17:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChangeTable {

}
