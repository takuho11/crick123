package cn.topcheer.pms2.api.sys.enums;

/**
 * 办理结果
 */
public enum HandleResultEnum {
    ALLOWED("准予许可/同意","1"),

    NOT_ALLOWED("不予许可/不同意","0"),
    ;

    /**
     * 办理结果
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 办理结果
     * @param code 代码
     */
    HandleResultEnum( String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static HandleResultEnum getEnumByCode(String code) {
        for (HandleResultEnum handleResultEnum : HandleResultEnum.values()) {
            if (handleResultEnum.getCode().equals(code)) {
                return handleResultEnum;
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
