package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonDbTableOrView implements DbTableOrView {

    private String dbType;

    private String schema;

    private String name;

    private String fullName;

    private String comment;

    private boolean view;

}
