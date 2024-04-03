package cn.topcheer.halberd.app.api.utils;

import cn.topcheer.halberd.app.api.framework.entity.sys.*;
import cn.topcheer.pms2.api.annotation.JsonIgnore;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GaoGongxin
 */
public class JsonConfigUtil {

    public static JsonConfig getJsonConfig(){
        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.setIgnoreJPATransient(false);

        JsonForeignKeyValueProcessor jsonForeignKeyValueProcessor = new JsonForeignKeyValueProcessor();
        jsonConfig.registerJsonValueProcessor(PmsProjectbase.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsEnterprise.class, jsonForeignKeyValueProcessor);
        //jsonConfig.registerJsonValueProcessor(PmsPlanproject.class, jsonForeignKeyValueProcessor);
        //jsonConfig.registerJsonValueProcessor(PmsPlanprojectbatch.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(SysUser.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(SysIdentity.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(SysRole.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsDepartment.class, jsonForeignKeyValueProcessor);
        //jsonConfig.registerJsonValueProcessor(PmsEnterpriseorganization.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsProjectparty.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsProjectfeatureinfor.class, jsonForeignKeyValueProcessor);
        //jsonConfig.registerJsonValueProcessor(PmsEnterprisehasinstrument.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsProjectexpense.class, jsonForeignKeyValueProcessor);
        //jsonConfig.registerJsonValueProcessor(PmsInstrument.class, jsonForeignKeyValueProcessor);
        jsonConfig.registerJsonValueProcessor(PmsProjectschedule.class, jsonForeignKeyValueProcessor);

        JsonForeignKeyPropertyNameProcessor jsonForeignKeyPropertyNameProcessor = new JsonForeignKeyPropertyNameProcessor();
        jsonConfig.registerJsonPropertyNameProcessor(PmsProjectbase.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(PmsEnterprise.class, jsonForeignKeyPropertyNameProcessor);
        //jsonConfig.registerJsonPropertyNameProcessor(PmsPlanproject.class, jsonForeignKeyPropertyNameProcessor);
        //jsonConfig.registerJsonPropertyNameProcessor(PmsPlanprojectbatch.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(SysUser.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(SysIdentity.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(SysRole.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(PmsDepartment.class, jsonForeignKeyPropertyNameProcessor);
        //jsonConfig.registerJsonPropertyNameProcessor(PmsEnterpriseorganization.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(PmsProjectparty.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(PmsProjectexpense.class, jsonForeignKeyPropertyNameProcessor);
        //jsonConfig.registerJsonPropertyNameProcessor(PmsInstrument.class, jsonForeignKeyPropertyNameProcessor);
        jsonConfig.registerJsonPropertyNameProcessor(PmsProjectschedule.class, jsonForeignKeyPropertyNameProcessor);

        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonBigDecimalValueProcessor());

        jsonConfig.addIgnoreFieldAnnotation(JsonIgnore.class);

        jsonConfig.setExcludes(new String[] {"handler", "hibernateLazyInitializer", "serialVersionUID"});

        jsonConfig.setIgnoreTransientFields(true);

        jsonConfig.registerDefaultValueProcessor(Date.class, new DefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });

        return jsonConfig;
    }
}
