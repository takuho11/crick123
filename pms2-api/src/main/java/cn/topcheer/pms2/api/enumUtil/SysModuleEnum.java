package cn.topcheer.pms2.api.enumUtil;

/**
 * 系统模块信息
 *
 * @author GaoGongxin
 * @date 2020/9/12 23:21
 */
public enum SysModuleEnum {

    /**
     * 默认值（空）
     */
    UNDEFINE("", ""),


    /**
     * 申报模块
     */
    DECLARE("申报", "DECLARE"),


    /**
     * 人才团队
     */
    RCTD("人才团队", "RCTD"),


    /**
     * 科技奖励
     */
    TECH_AWARDS("科技奖励", "TECH_AWARDS"),


    /**
     * 平台载体
     */
    TECH_PLATFORM("平台载体", "TECH_PLATFORM"),


    /**
     * 数据仓
     */
    DATAWAREHOUSE("数据仓", "DATAWAREHOUSE"),


    /**
     * 数据仓-单位信息-注册信息
     */
    WAREHOUSE_UNIT("数据仓-单位信息-注册信息", "WAREHOUSE_UNIT"),


    /**
     * 数据仓-个人信息-注册信息
     */
    WAREHOUSE_USER("数据仓-个人信息-注册信息", "WAREHOUSE_USER"),


    ;


    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块编码
     */
    private String code;

    /**
     * 构造方法
     *
     * @param name 模块名称
     * @param code 模块编码
     */
    SysModuleEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static SysModuleEnum getEnumByCode(String code) {
        for (SysModuleEnum sysModuleEnum : SysModuleEnum.values()) {
            if (sysModuleEnum.getCode().equals(code)) {
                return sysModuleEnum;
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
