package cn.topcheer.pms2.api.sys.enums;

/**
 * 办件类型
 */
public enum HandleDocumentEnum {

    IMMEDIATELY_HANDLE("即办件", "1"),

    COMMITMENT_HANDLE("承诺件", "2"),

    ESCALATION_HANDLE("上报件", "3"),

    FIRST_ESCALATION_HANDLE("初审上报件", "4"),


    ;

    /**
     * 办件类型
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 办件类型
     * @param code 代码
     */
    HandleDocumentEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static HandleDocumentEnum getEnumByCode(String code) {
        for (HandleDocumentEnum handleDocumentEnum : HandleDocumentEnum.values()) {
            if (handleDocumentEnum.getCode().equals(code)) {
                return handleDocumentEnum;
            }
        }
        return null;
    }

    /**
     * 通过name获取枚举对象
     *
     * @param name
     * @return
     */
    public static HandleDocumentEnum getEnumByName(String name) {
        for (HandleDocumentEnum handleDocumentEnum : HandleDocumentEnum.values()) {
            if (handleDocumentEnum.getName().equals(name)) {
                return handleDocumentEnum;
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
