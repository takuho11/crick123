package cn.topcheer.halberd.app.api.framework.dto.dolphin;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class TaskRelation {

    private long postTaskCode;

    private String name;
    private long preTaskCode;
    private Integer preTaskVersion;
    private Integer postTaskVersion;
    private String conditionType;
    private JSONObject conditionParams;

    public TaskRelation(long postTaskCode) {
        this.postTaskCode = postTaskCode;
        this.name = "";
        this.preTaskCode = 0;
        this.preTaskVersion = 0;
        this.postTaskVersion = 0;
        this.conditionType = "NONE";
        this.conditionParams = new JSONObject();
    }


}