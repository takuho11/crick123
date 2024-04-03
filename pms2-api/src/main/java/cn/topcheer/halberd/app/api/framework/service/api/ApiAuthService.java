package cn.topcheer.halberd.app.api.framework.service.api;


import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import cn.topcheer.halberd.app.api.framework.dto.api.AmApiAuthDTO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;

import java.util.List;

/**
 * Api授权类
 *
 * @author Chill
 */
public interface ApiAuthService extends IService<AmApiAuth> {
    List<AmApiAuth> listAuth();

    R validApiAuth(String apiName);

    R updateApi2ApplicationAuth(String apiId, String applicationId, int status);


    /**
     * 分页获取申请API列表
     *
     * @param dto   ApiAuthDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-07-26
     */
    IPage<AmApiAuthDTO> getApiAuthList(AmApiAuthDTO dto, Query query);


    /**
     * 申请
     *
     * @param dto ApiAuth
     * @author szs
     * @date 2023-07-26
     */
    void apply(AmApiAuth dto);


    /**
     * 获取申请API
     *
     * @param apiId  APIID
     * @param userId 　用户id
     * @return　ApiAuth
     * @author szs
     * @date 2023-09-11
     */
    AmApiAuth getApiAuth(String apiId, String userId);


    /**
     * 发起审批
     *
     * @param body JSONObject
     * @author szs
     * @date 2023-06-25
     */
    void startProcess(JSONObject body);
}
