package cn.topcheer.halberd.app.biz.framework.service.impl.client;


import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientResult;
import cn.topcheer.halberd.app.api.framework.entity.result.client.BladeClientTreeResult;
import cn.topcheer.halberd.app.api.framework.service.client.BladeClientService;
import cn.topcheer.halberd.app.dao.mapper.framework.client.BladeClientMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BladeClientImpl extends ServiceImpl<BladeClientMapper, BladeClient> implements BladeClientService {


    /**
     * 获取部门应用树
     *
     * @return list
     * @author szs
     * @date 2023-07-21
     */
    @Override
    public List<BladeClientTreeResult> getDepartClientTree() {

        // 查询有数据的部门应用
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.eq("c.is_deleted", 0);
        qw.eq("d.is_deleted", 0);
        qw.isNotNull("c.client_id");
        qw.groupBy("a.id");

        return getClientTreeData(baseMapper.getDepartClientTree(qw));
    }


    /**
     * 获取应用列表
     *
     * @return list
     * @author szs
     * @date 2023-07-25
     */
    @Override
    public List<BladeClientResult> getClientResultList() {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("a.is_deleted", 0);
        // 如果是管理员就查全部，如果非管理员就查自己
        qw.eq(!AuthUtil.isAdmin(), "a.create_user", AuthUtil.getUserId());

        return baseMapper.getClientResultList(qw);
    }


    /**
     * 获取类别+应用树
     *
     * @return list
     * @author szs
     * @date 2023-09-07
     */
    @Override
    public List<BladeClientTreeResult> getTypeClientTree() {
        // 查询有数据的部门应用
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.eq("c.is_deleted", 0);
        qw.isNotNull("b.client_id");
        qw.groupBy("a.id");

        return getClientTreeData(baseMapper.getTypeClientTree(qw));
    }


    /**
     * 获取应用树形数据
     *
     * @param list List
     * @return List
     * @author szs
     * @date 2023-09-07
     */
    private List<BladeClientTreeResult> getClientTreeData(List<BladeClientTreeResult> list) {
        for (BladeClientTreeResult result : list) {
            List<BladeClientTreeResult> children = new ArrayList<>();
            if (StringUtils.isBlank(result.getClient())) {
                continue;
            }

            String[] clientArr = result.getClient().split(",");
            for (String client : clientArr) {
                if (StringUtils.isBlank(client)) {
                    continue;
                }

                String[] data = client.split(":");
                children.add(new BladeClientTreeResult(data[0], data[1]));
            }

            result.setChildren(children);
        }

        return list;
    }


    /**
     * 根据clientId获取应用
     *
     * @param clientId 应用ID
     * @return BladeClient
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public BladeClient getBladeClientByClientId(String clientId) {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("client_id", clientId);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");

        return this.getOne(qw);
    }


    /**
     * 检测clientId是否存在
     *
     * @param clientId 应用ID
     * @param id       主键ID
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public void checkClientIdIsExist(String clientId, String id) {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("client_id", clientId);
        qw.ne(id != null, "id", id);
        qw.eq("is_deleted", 0);
        if (this.count(qw) > 0) {
            throw new ServiceException("应用ID已存在：" + clientId);
        }

    }


    /**
     * 根据userId获取应用
     *
     * @param userId 用户id
     * @return List
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public List<BladeClient> getBladeClientsByUserId(String userId) {
        QueryWrapper<BladeClient> qw = new QueryWrapper<>();
        qw.eq("create_user", userId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("create_time");

        return this.list(qw);
    }


}
