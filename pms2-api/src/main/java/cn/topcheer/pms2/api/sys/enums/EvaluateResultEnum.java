package cn.topcheer.pms2.api.sys.enums;

/**
 * 评价结果
 */
public enum EvaluateResultEnum {
    VERY_DISSATISFIED("非常不满意","1"),

    DISSATISFIED("不满意","2"),

    ORDINARY("一般","3"),

    SATISFIED("满意","4"),

    VERY_SATISFIED("非常满意","5"),
    ;

    /**
     * 评价结果
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 评价结果
     * @param code 代码
     */
    EvaluateResultEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static EvaluateResultEnum getEnumByCode(String code) {
        for (EvaluateResultEnum evaluateResultEnum : EvaluateResultEnum.values()) {
            if (evaluateResultEnum.getCode().equals(code)) {
                return evaluateResultEnum;
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
