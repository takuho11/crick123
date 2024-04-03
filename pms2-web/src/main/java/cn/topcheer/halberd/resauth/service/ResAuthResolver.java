package cn.topcheer.halberd.resauth.service;

/**
 * 资源权限判断器
 */
public interface ResAuthResolver {
    
    /**
     * 当前用户是否有权访问
     * @param resourceType
     * @param resourceValue
     * @return
     */
   public boolean hasAuth(String resourceType,String resourceId);


    /**
     * 指定用户是否有权访问
     * @param resourceType
     * @param resourceValue
     * @return
     */
   public boolean hasAuth(String resourceType,String resourceId,String userId);


   /**
    * 通过调用{@link ResAuthService#register(ResAuthResolver, String),
       将服务注册为某一种类型或多种类型资源的权限判断器
    * @param resAuthService
    * @return
    */
   public boolean register(ResAuthService resAuthService);
}
