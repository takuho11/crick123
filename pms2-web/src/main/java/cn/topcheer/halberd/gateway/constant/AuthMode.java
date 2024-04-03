package cn.topcheer.halberd.gateway.constant;

public  class AuthMode {
    /**
     * 不需要授权
     */
    public static  final int NONE=0;

    /**
     * 登录成功就行
     */
    public static final int LOGIN=1;


    /**
     * 自定义授权，需要实现cn.topcheer.halberd.resauth.service.ResAuthResolver接口的bean来处理
     */
    public static final int CUSTOM=2;
    
}
