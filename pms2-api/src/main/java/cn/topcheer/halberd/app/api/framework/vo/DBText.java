package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DBText implements DBResult {

    private String text;

    private String textType;

    @Override
    public String getType() {
        return "text";
    }
}
