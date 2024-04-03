package cn.topcheer.pms2.api.flow.entity;


import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by peanut.huang on 2019/4/18.
 */
@Entity
@Table(name="FL_FLOWDEFINE")
public class FLowDefine implements Serializable {

    @Id
    private String id;

    @FieldDes(name="姓名",type="String",lenth="50")
    private String name;
    private String memo;
    private int isenabled;
    private String type;
    private String sourceids;
    private String mold;
    private String flowcharttype;

    /**
     * 上报流程图是否显示详细信息
     */
    private String showdetail;

    private Integer showpointsize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(int isenabled) {
        this.isenabled = isenabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceids() {
        return sourceids;
    }

    public void setSourceids(String sourceids) {
        this.sourceids = sourceids;
    }

    public String getMold() {
        return mold;
    }

    public void setMold(String mold) {
        this.mold = mold;
    }

    public Integer getShowpointsize() {
        return showpointsize;
    }

    public void setShowpointsize(Integer showpointsize) {
        this.showpointsize = showpointsize;
    }

    public String getFlowcharttype() {
        return flowcharttype;
    }

    public void setFlowcharttype(String flowcharttype) {
        this.flowcharttype = flowcharttype;
    }

    public String getShowdetail() {
        return showdetail;
    }

    public void setShowdetail(String showdetail) {
        this.showdetail = showdetail;
    }
}
