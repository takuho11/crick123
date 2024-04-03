package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysDept;
import lombok.Data;
import org.springblade.core.tool.node.INode;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysDeptVO extends SysDept implements INode<SysDeptVO> {
    private String parentName;

    private String deptCategoryName;

    @Override
    public List<SysDeptVO> getChildren() {
        return new ArrayList<>();
    }
}
