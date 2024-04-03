package cn.topcheer.halberd.app.api.utils;

import net.sf.json.processors.PropertyNameProcessor;

import javax.persistence.JoinColumn;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author GaoGongxin
 */
public class JsonForeignKeyPropertyNameProcessor implements PropertyNameProcessor {
    @Override
    public String processPropertyName(Class aClass, String s) {

        try {
            Field declaredField = aClass.getDeclaredField(s);
            if(declaredField.isAnnotationPresent(JoinColumn.class)) {
                return declaredField.getDeclaredAnnotation(JoinColumn.class).name().toLowerCase();
            } else {
                PropertyDescriptor pd=new PropertyDescriptor(declaredField.getName(), aClass);
                Method readMethod = pd.getReadMethod();
                Method writeMethod = pd.getWriteMethod();
                if(readMethod.isAnnotationPresent(JoinColumn.class)) {
                    return readMethod.getDeclaredAnnotation(JoinColumn.class).name().toLowerCase();
                } else if(writeMethod.isAnnotationPresent(JoinColumn.class)) {
                    return writeMethod.getDeclaredAnnotation(JoinColumn.class).name().toLowerCase();
                } else {
                    return s;
                }
            }
        } catch (NoSuchFieldException | IntrospectionException e) {
            System.out.println(e.getMessage());
            return s;
        }
    }
}
