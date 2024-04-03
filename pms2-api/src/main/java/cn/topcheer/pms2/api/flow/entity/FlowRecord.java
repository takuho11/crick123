package cn.topcheer.pms2.api.flow.entity;

import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by peanut.huang on 2019/4/22.
 */
@Entity
@Table(name="FL_FLOWRECORD")
public class FlowRecord implements Serializable {
    @Id
    private String id;

    private String currentflowpointname;
    private String currentflowpointid;
    private String currentoperationname;
    private String currentoperationid;
    private String currentoperationresultname;
    private String currentoperationresultid;
    private String currentoperatorid;
    private String currentoperatorname;
    @FieldDes(name="operationtime",type="Date",memo="变更时间")
    private Date operationtime;
    private String sourceid;
    private String operationcontent;

    //操作时处于的流程节点
    private String sourceflowpointid;
    private String sourceflowpointname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentflowpointname() {
        return currentflowpointname;
    }

    public void setCurrentflowpointname(String currentflowpointname) {
        this.currentflowpointname = currentflowpointname;
    }

    public String getCurrentflowpointid() {
        return currentflowpointid;
    }

    public void setCurrentflowpointid(String currentflowpointid) {
        this.currentflowpointid = currentflowpointid;
    }

    public String getCurrentoperationname() {
        return currentoperationname;
    }

    public void setCurrentoperationname(String currentoperationname) {
        this.currentoperationname = currentoperationname;
    }

    public String getCurrentoperationid() {
        return currentoperationid;
    }

    public void setCurrentoperationid(String currentoperationid) {
        this.currentoperationid = currentoperationid;
    }

    public String getCurrentoperationresultname() {
        return currentoperationresultname;
    }

    public void setCurrentoperationresultname(String currentoperationresultname) {
        this.currentoperationresultname = currentoperationresultname;
    }

    public String getCurrentoperationresultid() {
        return currentoperationresultid;
    }

    public void setCurrentoperationresultid(String currentoperationresultid) {
        this.currentoperationresultid = currentoperationresultid;
    }

    public String getCurrentoperatorid() {
        return currentoperatorid;
    }

    public void setCurrentoperatorid(String currentoperatorid) {
        this.currentoperatorid = currentoperatorid;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getCurrentoperatorname() {
        return currentoperatorname;
    }

    public void setCurrentoperatorname(String currentoperatorname) {
        this.currentoperatorname = currentoperatorname;
    }

    public String getOperationcontent() {
        return operationcontent;
    }

    public void setOperationcontent(String operationcontent) {
        this.operationcontent = operationcontent;
    }

    public String getSourceflowpointid() {
        return sourceflowpointid;
    }

    public void setSourceflowpointid(String sourceflowpointid) {
        this.sourceflowpointid = sourceflowpointid;
    }

    public String getSourceflowpointname() {
        return sourceflowpointname;
    }

    public void setSourceflowpointname(String sourceflowpointname) {
        this.sourceflowpointname = sourceflowpointname;
    }
}
