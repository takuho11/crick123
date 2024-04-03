package cn.topcheer.pms2.api.plan.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BATCH_GUIDE_AFFIX")
public class BatchGuideAffix {

    private String id;//主id

    private String sourceid;//关联id（小批次id）

    private String name;//材料名称

    private String tips;//材料提示

    private Integer seq;//排序


    /*----------------------------------分割线----------------------------------*/

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
