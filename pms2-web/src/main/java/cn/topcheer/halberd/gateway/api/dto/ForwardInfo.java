package cn.topcheer.halberd.gateway.api.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ForwardInfo {

    String url;
    Map<String,Object> params;
    Map<String,String> headers;
    boolean canForward=false;
    String  errorMsg;

    public static ForwardInfo fail(String errorMsg){
        ForwardInfo info = new ForwardInfo();
        info.setCanForward(false);
        info.setErrorMsg(errorMsg);
        return info;
    }

        public static ForwardInfo ok(String url){
        ForwardInfo info = new ForwardInfo();
        info.setCanForward(true);
        info.setUrl(url);
        return info;
    }
    
}
