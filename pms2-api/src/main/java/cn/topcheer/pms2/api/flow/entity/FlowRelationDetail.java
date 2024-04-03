package cn.topcheer.pms2.api.flow.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by peanut.huang on 2019/4/24.
 */
@Entity
@Table(name="FL_FLOWRELATIONDETAIL")
public class FlowRelationDetail implements Serializable{
    @Id
    private String id;

    //当前流程状态名
    private String state;

    //当前流程flowpiontid
    private String flowpointid;

    //被退回前的流程节点
    private String b_flowpointid;

    //被退回的操作人id
    private String b_operator;

    //被退回前的流程名(被XXX退回)
    private String b_state;

    //流程所有环节涉及的单位id flowpoitid 用户id 基本信息
    private String allflowdetail;

    //当前流转操作者id集合 若两个高管 存入2个用户id
    private String currentrelationuserids;

    //上报时间
    private Date submitdate;

    //上一步完成的操作时间
    private Date operationdate;

    //流程定义类别
    private String fltype;

    //业务数据id(projectbaseid 或者 contractid)
    private String sourceid;

    //主业务id 暂为合同id
    private String mainsourceid;

    private Integer b_watermark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFlowpointid() {
        return flowpointid;
    }

    public void setFlowpointid(String flowpointid) {
        this.flowpointid = flowpointid;
    }

    public String getB_flowpointid() {
        return b_flowpointid;
    }

    public void setB_flowpointid(String b_flowpointid) {
        this.b_flowpointid = b_flowpointid;
    }

    public String getB_operator() {
        return b_operator;
    }

    public void setB_operator(String b_operator) {
        this.b_operator = b_operator;
    }

    public String getB_state() {
        return b_state;
    }

    public void setB_state(String b_state) {
        this.b_state = b_state;
    }

    public String getAllflowdetail() {
        return allflowdetail;
    }

    public void setAllflowdetail(String allflowdetail) {
        this.allflowdetail = allflowdetail;
    }

    public String getCurrentrelationuserids() {
        return currentrelationuserids;
    }

    public void setCurrentrelationuserids(String currentrelationuserids) {
        this.currentrelationuserids = currentrelationuserids;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    public String getFltype() {
        return fltype;
    }

    public void setFltype(String fltype) {
        this.fltype = fltype;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getMainsourceid() {
        return mainsourceid;
    }

    public void setMainsourceid(String mainsourceid) {
        this.mainsourceid = mainsourceid;
    }

    public Integer getB_watermark() {
        return b_watermark;
    }

    public void setB_watermark(Integer b_watermark) {
        this.b_watermark = b_watermark;
    }
}
