package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DBProperty {

    private String label;

    private Object value;

    private String path;

    private boolean original;

    private boolean lazy;
}
