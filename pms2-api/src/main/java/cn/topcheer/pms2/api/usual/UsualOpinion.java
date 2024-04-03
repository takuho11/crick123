package cn.topcheer.pms2.api.usual;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUAL_OPINION")
public class UsualOpinion {

    @Id
    private String id;

    private String userid;


    private String roleid;

    private String backopinion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getBackopinion() {
        return backopinion;
    }

    public void setBackopinion(String backopnion) {
        this.backopinion = backopnion;
    }
}
