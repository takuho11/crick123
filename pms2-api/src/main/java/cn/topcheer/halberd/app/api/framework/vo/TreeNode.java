package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TreeNode {

    private String label;

    private String type;

    private String memo;

    private String path;

    private boolean leaf;

    private boolean queryDetail;
}
