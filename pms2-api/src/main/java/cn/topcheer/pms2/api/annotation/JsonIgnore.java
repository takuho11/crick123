package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * @author GaoGongxin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonIgnore {

}
