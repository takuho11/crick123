package cn.topcheer.pms2.api.sys.enums;

/**
 * 收取方式
 */
public enum CollectionTypeEnum {

    PAPER_MATERIAL("收取纸质材料","1"),

    ELECTRONIC_MATERIAL("上传电子文件","2"),

    QUOTE("证件库引用","3"),

    ;
    /**
     * 收取方式
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * @param name 收取方式
     * @param code 代码
     */
    CollectionTypeEnum( String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static CollectionTypeEnum getEnumByCode(String code) {
        for (CollectionTypeEnum collectionTypeEnum : CollectionTypeEnum.values()) {
            if (collectionTypeEnum.getCode().equals(code)) {
                return collectionTypeEnum;
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
