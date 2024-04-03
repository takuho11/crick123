package cn.topcheer.halberd.app.api.framework.dto.dolphin;


import lombok.Data;

// datasource在dolphin与中台的属性维护
@Data
public class IntegrationDataSourceInfoBothSide {

    // 中台的源、目的id
    String centerDsId;
    String centerDtId;

    // 两边公用的 name，关联
    String dataSourceName;
    String dataTargetName;

    // dolphin中维护的属性
    String dsType;
    String dtType;

    long dsCode = 0;
    long dtCode = 0;


}
