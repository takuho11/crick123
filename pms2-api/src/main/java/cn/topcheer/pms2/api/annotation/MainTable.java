package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 用于标记业务主表，例如：Pms_Projectbase等
 *
 * @author GaoGongxin
 * @date 2021/1/21 15:55
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MainTable {

}
