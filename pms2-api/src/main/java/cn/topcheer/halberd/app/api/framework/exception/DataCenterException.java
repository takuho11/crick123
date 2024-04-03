package cn.topcheer.halberd.app.api.framework.exception;

import cn.topcheer.halberd.app.api.framework.ResultEnum;
import com.alibaba.fastjson.JSONObject;
import org.springblade.core.tool.api.R;


public class DataCenterException extends RuntimeException {

    private static final long serialVersionUID = -2476460197947848855L;

    private int code;
    private String message;
    private transient Object[] params;
    private Throwable cause;

    public JSONObject getErrJson() {
        JSONObject result = new JSONObject();
        boolean flag = false;
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code == this.code) {
                flag = true;
                String message = String.format(resultEnum.msg, this.params);
                result.put("code", resultEnum.code);
                result.put("message", message);
                break;
            }
        }
        if (!flag) {
            result.put("code", ResultEnum.UNKNOWN_ERROR.code);
            result.put("message", ResultEnum.UNKNOWN_ERROR.msg);
        }
        return result;
    }

    public int getCode() {
        return this.code;
    }

    public Object[] getParams() {
        return this.params;
    }


    public DataCenterException(int code, Object... params) {
        this.code = code;
        this.params = params;
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code == code) {
                this.message = String.format(resultEnum.msg, params);
                break;
            }
        }
    }

    public DataCenterException(Throwable cause, int code, Object... params) {
        super(cause);
        this.code = code;
        this.params = params;
    }


    public String getCodeMsg() {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code == this.code) {
                return String.format(resultEnum.msg, this.params);
            }
        }
        return "not find the same errorCode";
    }

    public DataCenterException cause(Throwable e) {
        this.cause = e;
        return this;
    }

    public DataCenterException message(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getCause() {
        return this.cause;
    }


    public R getR() {
        String message = null;
        boolean flag = false;
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code == this.code) {
                flag = true;
                if(this.params == null || this.params.length == 0) {
                    message = resultEnum.msg;
                }else {
                    message = String.format(resultEnum.msg, this.params);
                }
                break;
            }
        }
        if (!flag) {
            message = ResultEnum.UNKNOWN_ERROR.msg;
        }
        return R.fail(message);
    }


}
