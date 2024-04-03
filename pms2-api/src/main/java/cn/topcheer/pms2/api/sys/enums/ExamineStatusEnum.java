package cn.topcheer.pms2.api.sys.enums;

/**
 * 审批类型
 */
public enum ExamineStatusEnum {
    /**
     * 普通办件
     */
    NORMAL_PROGRESS("普通办件","01"),

    /**
     * 绿色通道
     */
    GREEN_CHANNEL("绿色通道","02"),

    /**
     * 联合会审
     */
    JOINT_REVIEW("联合会审","03"),

    /**
     * 并联审批
     */
    PARALLEL_APPROVAL("并联审批","04"),

    /**
     * 其他
     */
    OTHER("其他","99"),
    ;

    /**
     * 名称
     */
    private String status;
    /**
     * 编码
     */
    private String code;

    /**
     *
     * @param status 名称
     * @param code 编码
     */
    ExamineStatusEnum(String status, String code) {
        this.status = status;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static ExamineStatusEnum getEnumByCode(String code) {
        for (ExamineStatusEnum examineStatusEnum : ExamineStatusEnum.values()) {
            if (examineStatusEnum.getCode().equals(code)) {
                return examineStatusEnum;
            }
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
