package cn.topcheer.pms2.api.pml.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by peanut.huang on 2020/3/18.
 */
@Entity
@Table(name="FL_AUTHORITY_GRID")
public class FlAuthorityGrid implements Serializable {
    @Id
    private String id;
    private String type;
    private String roleid;
    private String gridtype;
    private String wheresql;
    private String filt;
    private String memo;
    private String flowpoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getGridtype() {
        return gridtype;
    }

    public void setGridtype(String gridtype) {
        this.gridtype = gridtype;
    }

    public String getWheresql() {
        return wheresql;
    }

    public void setWheresql(String wheresql) {
        this.wheresql = wheresql;
    }

    public String getFilt() {
        return filt;
    }

    public void setFilt(String filt) {
        this.filt = filt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFlowpoints() {
        return flowpoints;
    }

    public void setFlowpoints(String flowpoints) {
        this.flowpoints = flowpoints;
    }



}
