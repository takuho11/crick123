package cn.topcheer.halberd.app.api.minidev.result;


import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 业务允许修改表
 * </p>
 *
 * @author szs
 * @since 2023-12-11
 */
@Getter
@Setter
public class MiniBizAllowUpdateResult extends MiniBizAllowUpdate {


    @ApiModelProperty(value = "业务模板")
    private String miniBizDefName;


    @ApiModelProperty(value = "业务版本")
    private String miniBizVersionName;


}
