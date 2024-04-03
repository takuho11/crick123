package cn.topcheer.pms2.api.flow.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by peanut.huang on 2019/4/18.
 */
@Entity
@Table(name="FL_FLOWPOINT")
public class FlowPoint implements Serializable{
    @Id
    private String id;

    private String ftype;
    private String name;
    private String memo;
    private int seq;

    private String filt;

    private String fieldname;

    /**
     * 当前流程节点是否显示
     */
    private String showpoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FLOWDEFINEFPID")
    private FLowDefine fLowDefine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
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

    public FLowDefine getFLowDefine() {
        return fLowDefine;
    }

    public void setFLowDefine(FLowDefine fLowDefine) {
        this.fLowDefine = fLowDefine;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getFilt() {
        return filt;
    }

    public void setFilt(String filt) {
        this.filt = filt;
    }

    public String getShowpoint() {
        return showpoint;
    }

    public void setShowpoint(String showpoint) {
        this.showpoint = showpoint;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }
}
