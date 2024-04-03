package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * 当所需的表的字段与规定的不一致时，在有问题的字段上使用该注解标识正确的字段名称
 * @author GaoGongxin
 * @date 2021/1/4 14:35
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldOthername {

    String value();

}
