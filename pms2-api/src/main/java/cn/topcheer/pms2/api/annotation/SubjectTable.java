package cn.topcheer.pms2.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记业务学科表，例如：Pms_ProjectbaseSubject等
 * @author GaoGongxin
 * @date 2021/1/21 15:55
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubjectTable {

}
