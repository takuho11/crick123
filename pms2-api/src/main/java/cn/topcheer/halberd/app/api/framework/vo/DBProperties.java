package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DBProperties implements DBResult {

    private List<DBProperty> properties;

    @Override
    public String getType() {
        return "prop";
    }
}
