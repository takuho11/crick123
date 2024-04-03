package cn.topcheer.pms2.api.sys.enums;

/**
 * 证件类型
 */
public enum CertificateTypeEnum {
    /**
     * 统一社会信用代码
     */
    CREDIT_CODE("统一社会信用代码","01"),

    /**
     * 组织机构代码
     */
    UNIT_CODE("组织机构代码","02"),

    /**
     * 工商登记号(营业执照)
     */
    BUSINESS_CODE("工商登记号(营业执照)","03"),

    /**
     * 事业单位法人证书
     */
    UNIT_LEGAL_CERTIFICATE("事业单位法人证书","04"),

    /**
     * 社会团体法人登记证书
     */
    SOCIAL_LEGAL_CERTIFICATE("社会团体法人登记证书","05"),

    /**
     * 党政机关法人（无证书）
     */
    PARTY_GOVERNMENT_LEGAL("党政机关法人（无证书）","06"),

    /**
     * 其他单位证照
     */
    OTHER_ENTER_CERTIFICATE("其他单位证照","07"),

    /**
     * 身份证
     */
    ID_CERTIFICATE("身份证","51"),

    /**
     * 军官证
     */
    OFFICERS_CERTIFICATE("军官证","52"),

    /**
     * 士兵证
     */
    SOLDIER_CERTIFICATE("士兵证","53"),

    /**
     * 护照
     */
    PASSPORT("护照","54"),

    /**
     * 香港身份证
     */
    HONGKONG_CERTIFICATE("香港身份证","55"),

    /**
     * 台湾身份证
     */
    TAIWAN_CERTIFICATE("台湾身份证","56"),

    /**
     * 澳门身份证
     */
    MACAU_CERTIFICATE("澳门身份证","57"),

    /**
     * 港澳居民来往内地通行证
     */
    HONGKONG_MACAU_PERMIT("港澳居民来往内地通行证","58"),

    /**
     * 台湾居民来往内地通行证
     */
    TAIWAN_PERMIT("台湾居民来往大陆通行证","59"),

    /**
     * 其他类型
     */
    OTHER_TYPE("其他有效个人身份证件","99"),
    ;

    /**
     * 证件类型
     */
    private String type;

    /**
     * 代码
     */
    private String code;

    /**
     *
     * @param type 证件类型
     * @param code 代码
     */
    CertificateTypeEnum(String type, String code) {
        this.type = type;
        this.code = code;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static CertificateTypeEnum getEnumByCode(String code) {
        for (CertificateTypeEnum certificateTypeEnum : CertificateTypeEnum.values()) {
            if (certificateTypeEnum.getCode().equals(code)) {
                return certificateTypeEnum;
            }
        }
        return null;
    }

    public static String getCodeByName(String name) {
        for (CertificateTypeEnum certificateTypeEnum : CertificateTypeEnum.values()) {
            if (certificateTypeEnum.getType().equals(name)) {
                return certificateTypeEnum.getType();
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
