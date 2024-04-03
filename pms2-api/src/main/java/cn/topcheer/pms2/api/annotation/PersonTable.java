package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 用于标记人员表，例如：Pms_ProjectParty等
 * @author GaoGongxin
 * @date 2021/7/18 17:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersonTable {

}
