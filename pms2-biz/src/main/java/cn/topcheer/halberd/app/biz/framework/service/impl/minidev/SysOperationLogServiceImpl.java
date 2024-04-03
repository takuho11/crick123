package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.SysOperationLog;
import cn.topcheer.halberd.app.api.minidev.service.SysOperationLogService;
import cn.topcheer.halberd.app.dao.mapper.minidev.SysOperationLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author szs
 * @since 2024-03-23
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements SysOperationLogService {


    /**
     * 添加日志
     *
     * @param type             业务类型
     * @param mainid           主表id
     * @param operationResult  操作结果
     * @param operationComment 操作意见
     * @param operationNode    操作节点
     * @author szs
     * @date 2024-03-26
     */
    @Override
    public void addLog(String type, String mainid, String operationResult, String operationComment, String operationNode) {
        SysOperationLog data = new SysOperationLog();
        data.setType(type);
        data.setMainid(mainid);
        data.setOperationResult(operationResult);
        data.setOperationComment(operationComment);
        data.setOperationNode(operationNode);
        data.setCreateUser(AuthUtil.getUserId());
        data.setCreateTime(new Date());
        data.setIsDeleted(0);
        this.save(data);

    }


}
