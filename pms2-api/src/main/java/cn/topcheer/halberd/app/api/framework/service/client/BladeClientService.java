package cn.topcheer.halberd.app.api.framework.service.client;

import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientResult;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientTreeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 客户端表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-07-21
 */
public interface BladeClientService extends IService<BladeClient> {


    /**
     * 获取部门应用树
     *
     * @return list
     * @author szs
     * @date 2023-07-21
     */
    List<BladeClientTreeResult> getDepartClientTree();


    /**
     * 获取应用列表
     *
     * @return list
     * @author szs
     * @date 2023-07-25
     */
    List<BladeClientResult> getClientResultList();


    /**
     * 获取类别+应用树
     *
     * @return list
     * @author szs
     * @date 2023-09-07
     */
    List<BladeClientTreeResult> getTypeClientTree();


    /**
     * 根据clientId获取应用
     *
     * @param clientId 应用ID
     * @return BladeClient
     * @author szs
     * @date 2023-09-11
     */
    BladeClient getBladeClientByClientId(String clientId);


    /**
     * 检测clientId是否存在
     *
     * @param clientId 应用ID
     * @param id       主键ID
     * @author szs
     * @date 2023-09-11
     */
    void checkClientIdIsExist(String clientId, String id);


    /**
     * 根据userId获取应用
     *
     * @param userId 用户id
     * @return List
     * @author szs
     * @date 2023-09-11
     */
    List<BladeClient> getBladeClientsByUserId(String userId);


}
