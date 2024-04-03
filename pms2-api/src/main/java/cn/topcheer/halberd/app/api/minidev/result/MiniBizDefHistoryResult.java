package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 业务定义历史
 * </p>
 *
 * @author szs
 * @date 2024-03-27
 */
@Getter
@Setter
public class MiniBizDefHistoryResult extends MiniBizDefHistory {


    @ApiModelProperty(value = "创建人")
    private String createUserName;


}
