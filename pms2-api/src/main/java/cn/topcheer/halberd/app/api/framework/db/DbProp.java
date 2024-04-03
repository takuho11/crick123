package cn.topcheer.halberd.app.api.framework.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbProp {

    String label() default "";

    /**
     * 是否是探查出的原始属性
     * @return
     */
    boolean original() default true;

    boolean showInGrid() default true;

    boolean showInProp() default true;

    int seq() default 0;
}
