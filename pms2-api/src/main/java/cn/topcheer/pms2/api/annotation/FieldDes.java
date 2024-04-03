package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.*;

/**
 * Created by peanut.huang on 2020/7/23.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDes {
    String name() default "";
    String type() default "";
    String lenth() default "";
    String memo() default "";
}
