package cn.topcheer.pms2.api.sys.enums;

/**
 * 服务对象
 */
public enum ServiceObjectTypeEnum {
    /**
     * 自然人
     */
    NATURAL_PERSON("自然人","1"),

    /**
     * 企业法人
     */
    ENTERPRISE_LEGAL("企业法人","2"),

    /**
     * 行政机关
     */
    ADMINISTRATIVE_ORGANIZATION("行政机关","3"),

    /**
     * 事业法人及其他社团组织
     */
    CAUSE_LEGAL_AND_UNIT("事业法人及其他社团组织","4"),

    /**
     * 其他组织
     */
    OTHER_UNIT("其他组织","5");
    ;


    /**
     * 服务对象
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 服务对象
     * @param code 代码
     */
    ServiceObjectTypeEnum( String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static ServiceObjectTypeEnum getEnumByCode(String code) {
        for (ServiceObjectTypeEnum serviceObjectTypeEnum : ServiceObjectTypeEnum.values()) {
            if (serviceObjectTypeEnum.getCode().equals(code)) {
                return serviceObjectTypeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
