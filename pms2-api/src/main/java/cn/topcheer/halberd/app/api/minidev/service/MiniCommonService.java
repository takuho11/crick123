package cn.topcheer.halberd.app.api.minidev.service;


import cn.topcheer.halberd.app.api.minidev.dto.*;
import cn.topcheer.halberd.app.api.minidev.result.JudgeTaskResult;
import cn.topcheer.halberd.app.api.minidev.result.ProcessPathResult;
import cn.topcheer.halberd.app.api.minidev.result.ProcessTransferDataResult;
import cn.topcheer.halberd.app.api.minidev.result.ProcessTransferResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 公共 服务类
 * </p>
 *
 * @author szs
 * @since 2023-11-03
 */
public interface MiniCommonService {


    /**
     * 判断全部Table
     *
     * @param dto MiniJudgeAllTableDTO
     * @author szs
     * @since 2023-12-19
     */
    void judgeAllTable(MiniJudgeAllTableDTO dto);


    /**
     * 初始化全部Table
     *
     * @param dto MiniCommonDataDTO
     * @return MiniCommonDataDTO
     * @author szs
     * @since 2023-11-03
     */
    MiniInitAllTableDTO initAllTable(MiniInitAllTableDTO dto);


    /**
     * 保存全部Table
     *
     * @param dto     MiniSaveAllTableDTO
     * @param request HttpServletRequest
     * @author szs
     * @since 2023-11-03
     */
    void saveAllTable(MiniSaveAllTableDTO dto, HttpServletRequest request);


    /**
     * 获取流程路径
     *
     * @param dto ProcessPathDTO
     * @return ProcessPathVO
     * @author szs
     * @date 2023-11-22
     */
    List<ProcessPathResult> getProcessPath(ProcessPathDTO dto);


    /**
     * 发起流程
     *
     * @param dto StartProcessDTO
     * @author szs
     * @date 2023-11-22
     */
    void startProcess(StartProcessDTO dto);


    /**
     * 删除附件
     *
     * @param attachId 附件id
     * @author szs
     * @date 2023-11-24
     */
    void deleteFileByAttachId(String attachId);


    /**
     * 获取流程流转列表
     *
     * @param dto ProcessTransferDTO
     * @return List
     * @author szs
     * @date 2023-11-30
     */
    List<ProcessTransferResult> getProcessTransferList(ProcessTransferDTO dto);


    /**
     * 获取流程流转数据
     *
     * @param dto ProcessTransferDTO
     * @return ProcessTransferDataResult
     * @author szs
     * @date 2023-12-21
     */
    ProcessTransferDataResult getProcessTransferData(ProcessTransferDTO dto);


    /**
     * 判断任务
     *
     * @param dto JudgeTaskDTO
     * @return List
     * @author szs
     * @date 2023-12-20
     */
    List<JudgeTaskResult> judgeTask(JudgeTaskDTO dto);


    /**
     * 批量完成任务
     *
     * @param dto BatchCompleteTaskDTO
     * @author szs
     * @date 2023-12-05
     */
    void batchCompleteTask(BatchCompleteTaskDTO dto);


    /**
     * 批量流程用户任务
     *
     * @param userId               操作用户id
     * @param taskIds              任务ids
     * @param resultName           任务节点名称
     * @param taskResultCustom     任务操作结果自定义
     * @param taskResultCustomName 任务操作结果自定义名称
     * @param taskComment          任务操作意见
     * @param gridType             业务类型
     * @author szs
     * @date 2024-03-14
     */
    void batchFlowUserTask(String userId, List<String> taskIds, String resultName, String taskResultCustom, String taskResultCustomName, String taskComment, String gridType);


    /**
     * 撤回任务
     *
     * @param dto WithdrawTaskDTO
     * @author szs
     * @date 2024-03-15
     */
    void withdrawTask(WithdrawTaskDTO dto);


    /**
     * 终止任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    void terminateTask(ProcessTaskDTO dto);


    /**
     * 受理任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    void admissibleTask(ProcessTaskDTO dto);


    /**
     * 重置任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    void resetTask(ProcessTaskDTO dto);


}
