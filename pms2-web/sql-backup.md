### 企业信息查询sql备份

```
SELECT bi.id,
       bi.id as value,
    COALESCE(bi.unitname, p.name)                 as name,
    COALESCE(bi.bankname, p.bank)                 as bankname,
    COALESCE(bi.registeraddress, p.address)       as address,
    COALESCE(bi.enterprisetype, p.enterprisetype) as enterprisetype,
    COALESCE(bi.city, p.belongcity)               as city,
    COALESCE(bi.county, p.belongcountry)          as county,
    COALESCE(bi.postalcode, p.postalcode)         as postalcode,
    COALESCE(bi.email, p.email)                   as email,
    COALESCE(bi.fax, p.fax)                       as fax,
    COALESCE(bi.tzjgmobile, p.mobile)             as mobile,
    COALESCE(bi.enterprisetype, p.unittype)       as unittype,
    COALESCE(bi.creditcode, p.uniformcode)        as creditcode,
    COALESCE(bi.telephone, p.telephone)           as telephone,
    COALESCE(bi.locationcode, p.AREAID) as pmsArea,
    bi.province                                   as province,
    bi.street                                     as street,
    p.certificate                                 as certificate,
    p.orginizationcode                            as orginizationcode,
    COALESCE(ery.name, p.legalrepresentative )   as legalrepresentative,
    bi.enterprisetype,
    bi.isotherunittype,
    bi.officegetmethod,
    bi.belongnation,
    bi.industryphy,
    bi.officeprovince,
    bi.officecity,
    bi.officecounty,
    bi.officestreet,
    bi.officeaddress,
    bi.accountname,
    bi.belongindustry,
    bi.mainbusiness,
    bi.registeraddress,
    bi.type,
    bi.belongnation,
    bi.officeaddress,
    ry.name as linkName
    ry.certificatename as linkCertificatename,
    ry.certificatenumber as linkCertificatenumber,
    ry.telephone as linkTelephone,
    ry.email as linkEmail
FROM BI_MAINBASE m
    JOIN BI_ENT_BI bi
ON bi.mainid = m.id
    LEFT JOIN PMS_ENTERPRISE p ON p.id = m.enterpriseid
    LEFT JOIN BI_ENT_RY ry m.id = ry.mainid
    LEFT JOIN BI_ENT_RY ery m.id = ery.mainid
WHERE p.state = '0'
  AND ry.rytype = '单位联系人'
  AND ry.type = 'member_link'
  AND ery.rytype = '法定代表人'
  AND ery.type = 'member_legal'
  AND m.minicurrentstate = '申报完成'
  AND bi.creditcode = ${uniformcode}
  
  SELECT bi.id,bi.id as value,COALESCE(bi.unitname, p.name) as name,COALESCE(bi.bankname, p.bank) as bankname,COALESCE(bi.registeraddress, p.address)as address,COALESCE(bi.enterprisetype, p.enterprisetype) as enterprisetype,COALESCE(bi.city, p.belongcity)    as city,COALESCE(bi.county, p.belongcountry)   as county,COALESCE(bi.postalcode, p.postalcode)  as postalcode,COALESCE(bi.email, p.email)   as email,COALESCE(bi.fax, p.fax)as fax,COALESCE(bi.tzjgmobile, p.mobile)  as mobile,COALESCE(bi.enterprisetype, p.unittype)as unittype,COALESCE(bi.creditcode, p.uniformcode) as creditcode,COALESCE(bi.telephone, p.telephone)as telephone,COALESCE(bi.locationcode, p.AREAID)as pmsArea,bi.province   as province,bi.street     as street,p.certificate as certificate,p.orginizationcode as orginizationcode,COALESCE(ery.name, p.legalrepresentative)   as legalrepresentative,bi.enterprisetype,bi.isotherunittype,bi.officegetmethod,bi.belongnation,bi.industryphy,bi.officeprovince,bi.officecity,bi.officecounty,bi.officestreet,bi.officeaddress,bi.accountname,bi.belongindustry,bi.mainbusiness,bi.registeraddress,bi.type,bi.belongnation,bi.officeaddress,ry.name as linkName,ry.certificatename as linkCertificatename,ry.certificatenumber as linkCertificatenumber,ry.telephone as linkTelephone,ry.email as linkEmail FROM BI_MAINBASE m JOIN BI_ENT_BI bi ON bi.mainid = m.id LEFT JOIN PMS_ENTERPRISE p ON p.id = m.enterpriseid LEFT JOIN BI_ENT_RY ry ON m.id = ry.mainid LEFT JOIN BI_ENT_RY ery ON m.id = ery.mainid WHERE p.state = '0' AND ry.rytype = '单位联系人' AND ry.type = 'member_link' AND ery.rytype = '法定代表人' AND ery.type = 'member_legal'  AND bi.creditcode = ${uniformcode}    

```

#### 返回字段备注

```
[ 
    {
        // 统一社会信用编码
        "creditcode": "12520000429401122B",
        // 所属行业
        "belongindustry": "",
        // 是否港澳台或国外企业
        "isotherunittype": "",
        // 办公地址
        "officeaddress": "",
        // 市
        "city": "遵义市",
        // 办公地址-街道
        "officestreet": "",
        // 县区
        "county": "",
        // 法人代码
        "certificate": "",
        // 法人代表
        "legalrepresentative": "",
        // 
        "type": "",
        // 
        "officegetmethod": "",
        // 所属国别
        "belongnation": "",
        // 主营业务
        "mainbusiness": "",
        // 办公地址-省
        "officeprovince": "",
        // 省
        "province": "贵州省",
        // 开户名
        "accountname": "",
        // 邮编
        "postalcode": "",
        // 街道
        "street": "",
        // 办公地址-市
        "officecity": "",
        // 注册地址
        "registeraddress": "贵州省遵义市汇川区大连路149号",
        // id
        "id": "5BEF9CC921BE4B278349E9E2D",
        // 传真
        "fax": "",
        // 邮箱
        "email": "",
        // 区域
        "pmsarea": "",
        // 办公地址-县区
        "officecounty": "",
        // 地址
        "address": "贵州省遵义市汇川区大连路149号",
        // 单位类型
        "unittype": "医疗机构",
        // 手机
        "mobile": "",
        // 联系电话
        "telephone": "",
        // 组织机构代码
        "orginizationcode": "",
        // 单位类型，网上申报开放用户选择用
        "enterprisetype": "",
        // 银行名称
        "bankname": "",
        // 行业领域类别
        "industryphy": ""
          // 联系人名字
        linkName
          // 联系人证件类型
        linkCertificatename,
          // 联系人证件号码
        linkCertificatenumber,
          //联系人电话
        linkTelephone,
          //联系人邮箱
        linkEmail
    }
]
```
