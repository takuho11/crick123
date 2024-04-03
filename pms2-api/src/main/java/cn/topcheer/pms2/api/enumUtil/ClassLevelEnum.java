package cn.topcheer.pms2.api.enumUtil;

/**
 * 系统类层次信息
 *
 * @author GaoGongxin
 * @date 2020/9/13 14:56
 */
public enum ClassLevelEnum {

    /**
     * 默认值（空）
     */
    UNDEFINE("", ""),

    /**
     * Pojo层
     */
    POJO("Pojo层", "POJO"),

    /**
     * Controller层
     */
    CONTROLLER("Controller层", "CONTROLLER"),

    /**
     * Controller层
     */
    SERVICE("Service层", "SERVICE"),

    /**
     * Controller层
     */
    DAO("Dao层", "DAO"),

    /**
     * Util工具类
     */
    UTIL("Util工具类", "UTIL"),

    /**
     * Other其他
     */
    OTHER("Other其他", "OTHER");

    /**
     * 层次名称
     */
    private String name;

    /**
     * 层次编码
     */
    private String code;

    /**
     * 构造方法
     *
     * @param name 层次名称
     * @param code 层次编码
     */
    private ClassLevelEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static ClassLevelEnum getEnumByCode(String code) {
        for (ClassLevelEnum classLevelEnum : ClassLevelEnum.values()) {
            if (classLevelEnum.getCode().equals(code)) {
                return classLevelEnum;
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
