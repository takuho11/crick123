package cn.topcheer.pms2.common.enumerate;

/**
 *
 * @author tofu.q
 * @date 2021/9/22
 */
public enum ErrorCodeEnum {

    MessageCode_BussinessError("90","业务处理错误"),
    MessageCode_InvalidParamter("11","无效的参数"),
    MessageCode_UnkownError("99","未知错误"),


    SUCCESS("200", "操作成功"),

    PARAM_ERROR("1001", "参数异常"),

    PARAM_NULL("1002", "参数为空"),

    PARAM_FORMAT_ERROR("1003", "参数格式不正确"),

    PARAM_VALUE_ERROR("1004", "参数值不正确"),

    PARAM_COMPLETE_ERROR("1005","前台参数不完整"),

    SYSTEM_ERROR_CODE_ERROR("2001", "服务异常"),

    UNKNOW_ERROR("2002", "未知异常"),

    XXX("3001", "业务异常"),
    INSERT_ERROR("3002", "新增失败"),
    UPDATE_ERROR("3003", "更新失败"),
    DELETE_ERROR("3004", "删除失败"),
    RATE_LIMIT_ERROR("3005", "限流异常"),
    FILE_UPLOAD_FAILURE("3006", "文件上传失败"),



    ;

    private String code;
    private String message;

    ErrorCodeEnum(String code,
                  String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
