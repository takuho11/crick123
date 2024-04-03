package cn.topcheer.halberd.app.api.framework;

public enum ResultEnum {


    UNKNOWN_ERROR(-10000, "操作失败"),


    DOLPHIN_CONNECT_ERROR(-20000,"连接任务中心失败，请检查任务中心连接情况！"),
    DOLPHIN_GEN_TASK_CODE_ERROR(-20001,"生成任务唯一code失败，请检查任务中心连接情况！"),
    DOLPHIN_CREATE_DATA_INTEGRATE_ERROR(-20002,"新建数据集成任务失败！请检查任务中心！"),
    DATA_SOURCE_NOT_FOUND_ERROR(-20003,"未找到对应的数据源：%s，请检查配置！"),
    DOLPHIN_CREATE_DATA_INTEGRATE_FAIL(-20004,"新建数据集成任务失败: %s "),
    DATA_INTEGRATE_SAVE_ERROR(-20005,"新建数据集成任务已同步集群，但记录保存失败，请检查任务中心！"),
    DOLPHIN_START_DATA_INTEGRATE_ERROR(-20006,"启动数据集成任务失败！请检查任务中心！"),
    DATA_INTEGRATION_NO_TASK_NAME(-20007,"请输入数据集成任务的名称！"),
    ADD_DATA_INTEGRATION_NO_PROCESS_CODE(-20008,"请指明想要操作的工作流！"),
    TEST_SINGLE_TASK_FAIL(-20009,"测试任务实例失败，请检查任务上线状态及任务调度集群！"),
    DATA_FLOW_EXISTED(-20010,"已存在同名的工作流！请修改名称后重试！"),
    NO_TASK_NAME(-20011,"请指明任务名称！"),
    DATA_TASK_EXISTED(-20012,"已存在同名的任务！请修改名称后重试！"),
    NO_SOURCE_INC_COLUMN(-20013,"增量同步配置中未指定原表中存在的基准字段！"),
    INC_COLUMN_SPLIT_TOO_LONG(-20014,"增量同步的基准字段中，年月日间的自定义分隔符太长，请修改！"),
    INC_COLUMN_TYPE_NOT_SUPPORT(-20015,"增量同步的基准字段类型不支持，请修改为时间或字符串类型！"),
    INC_COLUMN_STYLE_NOT_SAME(-20016,"想要新增的增量同步任务中，基准字段的时间格式与该工作流不兼容，请新加一个新的工作流或使用已有的参数格式：%s！"),
    ADD_INC_TO_PROCESS_INTERRUPT(-20017,"新增增量同步任务中断，请检查集群后重试！"),
    FLOW_NOT_EXISTS(-20018,"所选的工作流不存在！"),
    FLOW_EDIT_WHILE_RELEASE(-20019,"所选的工作流是上线状态，请先下线！"),
    NO_PROJECT_CODE(-20020,"请指明項目！"),


    CREATE_TABLE_FAIL(-30001,"新建表失败！请检查表定义或数据存储服务！"),


    SQL_GENERATE_TYPE_ERROR(-40001,"SQL生成错误，配置的数据存储介质不匹配！！"),
    SQL_GENERATE_TYPE_NOT_SUPPORT(-40002,"SQL生成错误，配置的数据存储介质暂不支持！！"),
    INC_TYPE_NOT_SUPPORT(-40003,"SQL生成错误，配置的增量同步表类型暂不支持！！"),
    INC_WHEN_INC_SYNC_IS_FALSE(-40004,"SQL生成错误，请选择增量同步！！"),
    INC_WHEN_DORIS_IS_FALSE(-40005,"增量同步仅支持目标表为DORIS！！"),

    OPT_CHANGE_ERROR_SAVE_FAIL(-50001,"变更信息保存失败，一般是数据库错误！"),
    OPT_CHANGE_NO_ID(-50002,"变更信息中id缺失！"),
    OPT_CHANGE_NO_SUBID(-50003,"变更信息中关键id字段缺失，请检查各id！"),
    OPT_CHANGE_PARSE_FAIL(-50004,"变更信息解析失败，消息不满足约定的格式！"),
    OPT_CHANGE_RESOURCE_NOT_EXISTS(-50005,"变更信息中resourceId对应的数据资源不存在！"),
    OPT_CHANGE_FAIL(-50006,"依据变更信息修改主数据失败！！！"),
    OPT_CHANGE_FAIL_FOR_ADD_EXISTED(-50007,"依据变更信息新增主数据失败，因为已经存在主键列是指定值的记录了！！！"),
    OPT_CHANGE_FAIL_FOR_DELETE_UPDATE_NOT_EXISTED(-50008,"依据变更信息修改主数据失败，因为根据主键列值没有找到相关记录！！！"),
    OPT_CHANGE_SEND_FAIL(-50009,"变更信息发送失败，请检查消息配置、内容或网络！"),
    OPT_CHANGE_KEY_VALUE_LIST_NOT_SAME_LENGTH(-50010,"变更信息内容错误，主键名列表与主键值列表不同长！"),
    OPT_CHANGE_KEY_OR_VALUE_LIST_IS_NULL(-50010,"变更信息内容错误，主键名列表或主键值列表为空！"),
    OPT_CHANGE_CONFIG_NULL(-50011,"变更信息内容错误，依据 infoType，appId，comId，resourceId没有查找到对应的配置,因此不会修改主数据，仅将消息和异常记录！"),

    FILE_IMPORT_RESOURCE_FREQUENCY_NOT_AVAILABLE(-60000, "文件归集时数据资源对应的更新频率无效！"),
    SUCCESS(0, "操作成功");



    public int code;
    public String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
