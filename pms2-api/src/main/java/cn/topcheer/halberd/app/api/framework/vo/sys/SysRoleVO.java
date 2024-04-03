package cn.topcheer.halberd.app.api.framework.vo.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.node.INode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysRoleVO extends SysRole implements HalberdRole, INode<SysRoleVO> {


    private String parentName;


    @ApiModelProperty(value = "所属单位id")
    private String enterPriseId;


    @ApiModelProperty(value = "所属单位名称")
    private String enterPriseName;


    @ApiModelProperty(value = "是否仅仅下级（0否，1是）")
    private Integer isNext;


    @Override
    public List<SysRoleVO> getChildren() {
        return new ArrayList<>();
    }


}
