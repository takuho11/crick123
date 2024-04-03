package cn.topcheer.halberd.app.api.framework.dto.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int result;
    private String msg;
    private T data;
    private boolean success;

    public static <T> Result<T> of(int result, String msg, T data, boolean success){
        Result dto = new Result();
        dto.setMsg(msg);
        dto.setResult(result);
        dto.setSuccess(success);
        dto.setData(data);
        return dto;
    }

    public static <T> Result<T> successOf(T data){
        Result dto = new Result();
        dto.setMsg("成功");
        dto.setResult(0);
        dto.setSuccess(true);
        dto.setData(data);
        return dto;
    }

    public static <T> Result<T> failOf(String msg){
        Result dto = new Result();
        dto.setMsg(msg);
        dto.setResult(-1);
        dto.setSuccess(false);
        dto.setData(null);
        return dto;
    }
}
