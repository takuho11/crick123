package cn.topcheer.pms2.api.sys.enums;

/**
 * 办件阶段
 */
public enum HandleStageEnum {

    DECLARE("申报","1"),

    ACCEPTANCE("受理","2"),

    EXAMINE("审批","3"),

    COMPLETION("办结","4"),
    ;

    /**
     * 办件阶段
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 办件阶段
     * @param code 代码
     */
    HandleStageEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static HandleStageEnum getEnumByCode(String code) {
        for (HandleStageEnum handleStageEnum : HandleStageEnum.values()) {
            if (handleStageEnum.getCode().equals(code)) {
                return handleStageEnum;
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
