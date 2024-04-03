package cn.topcheer.pms2.api.sys.enums;

public enum UserLimitEnum {

    NOT_LOGIN("未登录", "NOT_LOGIN", "用户未登录"),

    ALL_ALLOW("允许申报","ALL_ALLOW","用户数据已完善且审核通过允许申报"),

    WAIT_EXAMINE("等待单位审核信息","WAIT_EXAMINE","用户数据已提交,等待单位审核"),

    NEED_COM_SUB("用户信息未完善或未上报","NEED_COM_SUB","用户信息未完善或上报");
    ;

    /**
     * 限制类型
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 备注
     */
    private String memo;

    /**
     * @param name 限制类型
     * @param code 代码
     */
    UserLimitEnum(String name, String code, String memo) {
        this.name = name;
        this.code = code;
        this.memo = memo;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static UserLimitEnum getEnumByCode(String code) {
        for (UserLimitEnum userLimitEnum : UserLimitEnum.values()) {
            if (userLimitEnum.getCode().equals(code)) {
                return userLimitEnum;
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
