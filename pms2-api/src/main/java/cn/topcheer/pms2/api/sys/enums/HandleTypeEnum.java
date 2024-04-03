package cn.topcheer.pms2.api.sys.enums;

/**
 * 办理形式
 */
public enum HandleTypeEnum {
    /**
     * 窗口办理
     */
    WINDOW_HANDLE("窗口办理","1"),

    /**
     * 网上办理
     */
    ONLINE_HANDLE("网上办理","2"),

    /**
     * 其他申请方式
     */
    OTHER_HANDLE("其他申请方式","3"),

    ;

    /**
     * 办理形式
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 办理形式
     * @param code 代码
     */
    HandleTypeEnum( String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static HandleTypeEnum getEnumByCode(String code) {
        for (HandleTypeEnum handleTypeEnum : HandleTypeEnum.values()) {
            if (handleTypeEnum.getCode().equals(code)) {
                return handleTypeEnum;
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
