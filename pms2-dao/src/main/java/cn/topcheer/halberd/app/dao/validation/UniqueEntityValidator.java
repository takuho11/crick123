package cn.topcheer.halberd.app.dao.validation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

/**
 * 仅适用于Mybatis-plus
 */
public class UniqueEntityValidator implements ConstraintValidator<UniqueEntity, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private TableInfo tableInfo;

    private IService entityService;

    private List<TableFieldInfo> uniqueFieldInfoList = new ArrayList<>();


    @Override
    public void initialize(UniqueEntity constraintAnnotation) {
        entityService = applicationContext.getBean(constraintAnnotation.entityService());
        tableInfo = SqlHelper.table(entityService.getEntityClass());
        Set<String> uniqueFieldSet = new HashSet<>(Arrays.asList(constraintAnnotation.uniqueFields()));
        Map<String, TableFieldInfo> uniqueFieldInfoMap = new HashMap<>();
        for(TableFieldInfo fieldInfo : tableInfo.getFieldList()){
            if(uniqueFieldSet.contains(fieldInfo.getProperty())){
                uniqueFieldInfoMap.put(fieldInfo.getProperty(), fieldInfo);
            }
        }
        uniqueFieldInfoList = new ArrayList<>(uniqueFieldInfoMap.values());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //若唯一字段中有null值，则不验证
        for (TableFieldInfo fieldInfo : uniqueFieldInfoList){
            if(tableInfo.getPropertyValue(value, fieldInfo.getProperty()) == null){
                return true;
            }
        }

        QueryWrapper<?> wrapper = Wrappers.query();
        if(tableInfo.havePK()){
            wrapper = wrapper.select(tableInfo.getKeyColumn());
        }

        for (TableFieldInfo fieldInfo : uniqueFieldInfoList){
            wrapper = wrapper.eq(fieldInfo.getColumn(), tableInfo.getPropertyValue(value, fieldInfo.getProperty()));
        }
        //如果有主键，且主键有值，则加上 id != ?
        if(tableInfo.havePK() && !isNullOrBlankString(tableInfo.getPropertyValue(value, tableInfo.getKeyProperty()))){
            wrapper = wrapper.ne(tableInfo.getKeyColumn(), tableInfo.getPropertyValue(value, tableInfo.getKeyProperty()));
        }

        if(entityService.getOne(wrapper, false) == null){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 若是null值或空字符串，返回true
     * @param value
     * @return
     */
    private static boolean isNullOrBlankString(Object value){
        return  (value == null)
                ||
                (value instanceof String && StringUtils.isBlank((String) value));
    }
}
