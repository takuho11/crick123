package cn.topcheer.pms2.api.sys.enums;

/**
 * 业务动作
 */
public enum BusinessActionEnum {
    /**
     * 通过
     */
    PASS("通过","1"),

    /**
     * 退回
     */
    ROLLBACK("退回","2"),

    /**
     * 其他
     */
    OTHER("其他","3"),
    ;


    /**
     * 业务动作
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 业务动作
     * @param code 代码
     */
    BusinessActionEnum( String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static BusinessActionEnum getEnumByCode(String code) {
        for (BusinessActionEnum businessActionEnum : BusinessActionEnum.values()) {
            if (businessActionEnum.getCode().equals(code)) {
                return businessActionEnum;
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
