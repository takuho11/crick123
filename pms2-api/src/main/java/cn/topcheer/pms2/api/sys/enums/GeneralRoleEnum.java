package cn.topcheer.pms2.api.sys.enums;

public enum GeneralRoleEnum {

    /***
     * 没错，数据库改了这里要同步改下
     */

    /***===常用角色===***/
    ORDINARY_PEOPLE("科研人员", "1001", "129947C6-94DC-4A7D-84D2-E78A80E518A3", "普通用户角色"),
    ORDINARY_ENTERPRISE("企业单位管理员", "0005", "C7004168-4E0C-4F1F-B341-A225B5644DC5", "法人用户角色(企业单位管理员)"),
    NEW_PEOPLE("数据仓个人", "sjcgr", "f8a87c80-f89d-48bc-ad96-840ab6aa81b2", "新建账号的个人用户角色"),
    NEW_UNIT("数据仓法人","sjcfr","aaaa-bbbb-cccc-dddd","新建账号的法人用户角色"),
    ;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 释义用途
     */
    private String description;

    /**
     * 通过code获取枚举对象
     *
     * @param code
     * @return
     */
    public static GeneralRoleEnum getEnumByCode(String code) {
        for (GeneralRoleEnum generalRoleEnum : GeneralRoleEnum.values()) {
            if (generalRoleEnum.getCode().equals(code)) {
                return generalRoleEnum;
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
    public static GeneralRoleEnum getEnumByName(String name) {
        for (GeneralRoleEnum generalRoleEnum : GeneralRoleEnum.values()) {
            if (generalRoleEnum.getName().equals(name)) {
                return generalRoleEnum;
            }
        }
        return null;
    }

    GeneralRoleEnum(String name, String code, String roleId, String description) {
        this.name = name;
        this.code = code;
        this.roleId = roleId;
        this.description = description;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
