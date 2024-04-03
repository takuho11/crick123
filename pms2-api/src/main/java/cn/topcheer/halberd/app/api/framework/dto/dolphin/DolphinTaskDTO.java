package cn.topcheer.halberd.app.api.framework.dto.dolphin;

import cn.topcheer.halberd.app.api.framework.dto.dolphin.taskparams.TaskParams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DolphinTaskDTO {


    private long code;

    private String delayTime;

    private String description;

    private int environmentCode;

    private String failRetryInterval;

    private String failRetryTimes;

    private String flag;

    private String name;

    private TaskParams taskParams;

    private String taskPriority;

    private String taskType;

    private int timeout;

    private String timeoutFlag;

    private String timeoutNotifyStrategy;

    private String workerGroup;

    private int cpuQuota;

    private int memoryMax;

    private String taskExecuteType;


    private Integer version;

    private Integer id;

    private String taskGroupId;

    private String taskGroupPriority;



    private static DolphinTaskDTO generateDefaultTaskDTO() {

        DolphinTaskDTO dolphinTaskDTO = new DolphinTaskDTO();
        dolphinTaskDTO.setCpuQuota(-1);
        dolphinTaskDTO.setCode(-1);
        dolphinTaskDTO.setDelayTime("0");

        dolphinTaskDTO.setDescription("");
        dolphinTaskDTO.setEnvironmentCode(-1);
        dolphinTaskDTO.setFailRetryInterval("1");
        dolphinTaskDTO.setFailRetryTimes("0");
        dolphinTaskDTO.setFlag("YES");

        dolphinTaskDTO.setTaskExecuteType("BATCH");
        dolphinTaskDTO.setTaskPriority("MEDIUM");
        dolphinTaskDTO.setTimeout(0);
        dolphinTaskDTO.setTimeoutFlag("CLOSE");
        dolphinTaskDTO.setTimeoutNotifyStrategy("");
        dolphinTaskDTO.setWorkerGroup("default");
        dolphinTaskDTO.setMemoryMax(-1);

        return dolphinTaskDTO;
    }


    public static DolphinTaskDTO assemblyDolphinTaskDTO(DolphinTaskDTO dolphinTaskDTO,
                                                        long code,
                                                        String name,
                                                        String description,
                                                        TaskParams taskParams,
                                                        String taskType
    ) {
        if (null == dolphinTaskDTO) {
            dolphinTaskDTO = generateDefaultTaskDTO();
        }
        dolphinTaskDTO.setName(name);
        dolphinTaskDTO.setCode(code);
        dolphinTaskDTO.setDescription(description);
        if (null != taskParams) {
            dolphinTaskDTO.setTaskParams(taskParams);
        }
        dolphinTaskDTO.setTaskType(taskType);
        return dolphinTaskDTO;
    }


}

