package cn.topcheer.halberd.app.dao.validation;


import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 仅适用于Mybatis-plus
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = UniqueEntityValidator.class)
public @interface UniqueEntity {

    String message() default "与已有数据发生重复！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * entity 对应的IService
     * @return
     */
    Class<? extends IService> entityService();

    /**
     * 需要判断唯一的字段名(是类字段名，不是表字段名)
     * 多个字段用and逻辑连接
     * @return
     */
    String[] uniqueFields() default {};

}
