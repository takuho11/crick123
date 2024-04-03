package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 用于标记大字段表，例如：Pms_Clob等
 * @author GaoGongxin
 * @date 2021/1/21 16:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClobTable {

}
