package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DBTree implements DBResult {

    private List<TreeNode> nodes;

    @Override
    public String getType() {
        return "tree";
    }
}
