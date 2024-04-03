package cn.topcheer.halberd.app.api.framework.dto.api;


import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * AmApiDTO
 *
 * @author szs
 * @date 2023-07-26
 */
@Data
public class AmApiDTO extends AmApi {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "服务名称")
    private String serviceName;


    @ApiModelProperty(value = "创建人")
    private String createUserName;


    @ApiModelProperty(value = "是否仅仅查询自己创建（0否，1是）")
    private Integer isMyCreate;


    @ApiModelProperty(value = "是否仅仅查询")
    private Boolean isOnlySelect;


}
