package cn.topcheer.pms2.api.plan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BATCH_GUIDE")
public class BatchGuide {

    private String id;//主id（小批次id）

    private String name;//事项名称

    private String weekdays;//办理总用时(工作日)

    private String dealway;//可办理方式

    private String crowd;//可办理人群

    private String condition;//可办理具体条件

    private String flow;//具体流程

    private String workphone;//窗口-办公电话

    private String techphone;//窗口-技术电话

    private String worktime;//窗口-办公时间

    private String release;//是否发布：是or否or空


    /*----------------------------------分割线----------------------------------*/

    @Id
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

    public String getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays;
    }

    public String getDealway() {
        return dealway;
    }

    public void setDealway(String dealway) {
        this.dealway = dealway;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getTechphone() {
        return techphone;
    }

    public void setTechphone(String techphone) {
        this.techphone = techphone;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
