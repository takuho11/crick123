package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.SysOperationLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统操作日志 服务类
 * </p>
 *
 * @author szs
 * @since 2024-03-26
 */
public interface SysOperationLogService extends IService<SysOperationLog> {


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
    void addLog(String type, String mainid, String operationResult, String operationComment, String operationNode);


}
