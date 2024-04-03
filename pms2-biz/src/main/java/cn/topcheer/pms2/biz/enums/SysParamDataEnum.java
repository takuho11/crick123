package cn.topcheer.pms2.biz.enums;

/**
 *
 * @author
 * @date 2022/6/22
 */
public enum SysParamDataEnum {

//    PDFPOSTREDIS("pdfpostredis"),
//    PDFDOWNURL("pdfdownurl"),
//    PDFPOSTURL("pdfposturl"),
    PDFPOSTREDIS("pdfpostredisdev"),
    PDFDOWNURL("pdfdownurldev"),
    PDFPOSTURL("pdfposturldev"),
    AREACODE("areacode"),
    ;

    private String message;

    SysParamDataEnum(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
