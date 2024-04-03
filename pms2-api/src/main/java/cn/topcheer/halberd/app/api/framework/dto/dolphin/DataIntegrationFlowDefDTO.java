package cn.topcheer.halberd.app.api.framework.dto.dolphin;


import cn.topcheer.halberd.app.api.framework.entity.dolphin.DataIntegrationFlowDef;
import cn.topcheer.halberd.app.api.framework.entity.dolphin.DataIntegrationTaskDef;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataIntegrationFlowDefDTO extends DataIntegrationFlowDef implements Serializable {

    private static final long serialVersionUID = 1L;


    // 最近一次运行时间
    private String lastStartTime;
    // 最近一次运行时间
    private String lastEndTime;

    // 最近一次运行的结果
    private String lastRunStatus;

    // 子 task 列表
    private List<DataIntegrationTaskDef> children;



}
