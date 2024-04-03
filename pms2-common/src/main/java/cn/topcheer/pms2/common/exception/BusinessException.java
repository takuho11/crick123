package cn.topcheer.pms2.common.exception;

import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;

/**
 *
 * @author tofu.q
 * @date 2021/9/22
 */
public class BusinessException extends RuntimeException{
    private final String code;

    private Boolean print_error_stack;

    public String getCode() {
        return code;
    }

    public Boolean getPrint_error_stack() {
        return print_error_stack;
    }

    public void setPrint_error_stack(Boolean print_error_stack) {
        this.print_error_stack = print_error_stack;
    }

    public BusinessException(String message){
        super(message);
        //默认自定义的系统异常
        this.code = "9999";
        this.print_error_stack = false;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = false;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String message){
        super(message);
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = false;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable throwable){
        super(throwable);
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = false;
    }

    public BusinessException(String message, String code){
        super(message);
        //特殊使用code，比如当做普通的字符串处理
        this.code = code;
        this.print_error_stack = false;
    }

    public BusinessException(String message, Boolean print_error_stack){
        super(message);
        //默认自定义的系统异常
        this.code = "9999";
        this.print_error_stack = print_error_stack;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Boolean print_error_stack){
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = print_error_stack;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String message, Boolean print_error_stack){
        super(message);
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = print_error_stack;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable throwable, Boolean print_error_stack){
        super(throwable);
        this.code = errorCodeEnum.getCode();
        this.print_error_stack = print_error_stack;
    }

    public BusinessException(String message, String code, Boolean print_error_stack){
        super(message);
        //特殊使用code，比如当做普通的字符串处理
        this.code = code;
        this.print_error_stack = print_error_stack;
    }
}
