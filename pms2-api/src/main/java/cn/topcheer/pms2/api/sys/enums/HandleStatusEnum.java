package cn.topcheer.pms2.api.sys.enums;

import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;

/**
 * 政务网接口，办件状态
 * @author wanghaoqin
 * @date 2024/3/8
 */
public enum HandleStatusEnum {
    /**
     * 草稿
     */
    DRAFT("草稿","01","申报阶段","公众网上申报暂存到草稿箱的办件状态"),

    /**
     * 收件
     */
    RECEIVING("收件","02","申报阶段","公众网上申报提交成功或到部门窗口直接办理，由部门窗口申报窗口人员登记申报信息"),

    /**
     * 预受理
     */
    PRE_ACCEPTANCE("预受理","03","申报阶段","网上申报件，审核通过后还需申报者到大厅正式受理的办件。（直接到部门窗口申报可以跳过预受理状态）"),

    /**
     * 预受理退回
     */
    PRE_ACCEPTANCE_BACK("预受理退回","04","申报阶段","网上申报件，审核不通过后，申报者可以修改信息后，重新提交给部门进行审核。"),

    /**
     * 受理
     */
    ACCEPTANCE("受理","05","受理阶段","正式受理、开始计算承诺期限"),

    /**
     * 补齐补正
     */
    COMPLETE_ANDCORRECT("补齐补正","06","申报阶段","对申报办件做出补齐补正材料后的状态"),

    /**
     * 不予受理
     */
    INADMISSIBLE("不予受理","07","申报阶段","对申报办件做出不予受理操作后的状态。"),

    /**
     * 在办
     */
    IN_PROGRESS("在办","08","审批阶段","对申报办件进行内部环节流转后的状态"),

    /**
     * 挂起
     */
    PENDING("挂起","09","审批阶段","对申报办件做出需要现场勘查、专家会审等需要进行时限暂停的业务操作后的状态"),

    /**
     * 办结
     */
    COMPLETION("办结","10","办结阶段","对申报办件做出决定之后的状态"),

    /**
     * 在办
     */
    TRANSFER_COMPLETION("在办","11","办结阶段","指转报其他单位或上级单位的办结情况"),

    /**
     * 作废办结
     */
    CANCELL_COMPLETION("作废办结","12","办结阶段","业务处理上无效的纪录、或者录入错误数据"),

    /**
     * 退件
     */
    RETURN_STEP("退件","13","办结阶段","当服务项目不符合申报的条件或者因为其他原因不能进行办理"),

    /**
     * 制证
     */
    MAKE_CERTIFICATE("制证","14","办结阶段","制证"),

    /**
     * 发证
     */
    ISSUE_CERTIFICATE("发证","15","办结阶段","发证"),
    ;


    /**
     * 办件状态
     */
    private String status;
    /**
     * 编码
     */
    private String code;
    /**
     * 对应阶段
     */
    private String stage;
    /**
     * 说明
     */
    private String memo;

    /**
     * 构造方法
     *
     * @param status 办件状态
     * @param code 编码
     * @param stage 对应阶段
     * @param memo 说明
     */
    HandleStatusEnum(String status, String code,String stage,String memo) {
        this.status = status;
        this.code = code;
        this.stage = stage;
        this.memo = memo;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static HandleStatusEnum getEnumByCode(String code) {
        for (HandleStatusEnum handleStatusEnum : HandleStatusEnum.values()) {
            if (handleStatusEnum.getCode().equals(code)) {
                return handleStatusEnum;
            }
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
