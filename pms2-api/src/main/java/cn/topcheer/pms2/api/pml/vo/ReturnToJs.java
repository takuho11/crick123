package cn.topcheer.pms2.api.pml.vo;

import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;

/**
 * Created by ZK on 2019/4/11.
 */
public class ReturnToJs<T> extends Result {




    /**
     * 后台错误编码
     */
    private String code;

    /**
     *
     */
    private Long ts = System.currentTimeMillis();


    public ReturnToJs(){

    }

    /**
     * 调用成功返回
     * @param data 数据
     * @param success 接口调用是否成功
     */
    public ReturnToJs(T data, boolean success) {
        this.setData(data);
        this.setSuccess(success);
    }

    /**
     * 调用后台接口报错返回
     * @param success 接口调用是否成功
     * @param errMsg 错误信息
     */
    public ReturnToJs(boolean success, String errMsg) {
        this.setSuccess(success);
        this.setErrMsg(errMsg);
    }

    public ReturnToJs(T data, boolean success, String errMsg) {
        this.setData(data);
        this.setSuccess(success);
        this.setErrMsg(errMsg);
    }


    public String getErrMsg() {
        return this.getMsg();
    }

    public void setErrMsg(String errMsg) {
        this.setMsg(errMsg);
    }


    @Override
    public String toString() {
        return "ReturnToJs{" +
                "data=" + this.getData() +
                ", success=" + this.isSuccess() +
                ", errMsg='" + this.getErrMsg() + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    /**
     * 后台接口调用成功
     * @param <T>
     * @return
     */
    public static <T> ReturnToJs success(){
        ReturnToJs returnToJs = new ReturnToJs();
        returnToJs.setSuccess(Boolean.TRUE);
        return returnToJs;
    }


    /**
     * 后台接口调用成功
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ReturnToJs success(T t){
        ReturnToJs returnToJs = new ReturnToJs();
        returnToJs.setData(t);
        returnToJs.setSuccess(Boolean.TRUE);
        return returnToJs;
    }



    public static <T> ReturnToJs failure(ErrorCodeEnum errorCodeEnum){
        return failure(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    /**
     * 后台接口调用失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ReturnToJs failure(String code, String message){
        ReturnToJs returnToJs = new ReturnToJs();
        returnToJs.setSuccess(Boolean.FALSE);
        returnToJs.setCode(Integer.parseInt(code));
        returnToJs.setErrMsg(message);
        return returnToJs;
    }



    /**
     * 后台接口调用失败（默认自定义的系统异常）
     * @param message
     */
    public static <T> ReturnToJs failure(String message){
        ReturnToJs returnToJs = new ReturnToJs();
        returnToJs.setSuccess(Boolean.FALSE);
        returnToJs.setCode(9999);
        returnToJs.setErrMsg(message);
        return returnToJs;
    }

}
