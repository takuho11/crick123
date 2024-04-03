package cn.topcheer.halberd.app.api.framework.dto.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmApiAuthDTO extends AmApiAuth {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "组织名称")
    private String deptName;


    @ApiModelProperty(value = "服务名称")
    private String serviceName;


    @ApiModelProperty(value = "API类型名称")
    private String apiTypeName;


    @ApiModelProperty(value = "申请组织")
    private String applyOrganization;


    @ApiModelProperty(value = "申请人")
    private String applyOwner;


    @ApiModelProperty(value = "Api对象")
    private AmApi api;


    @ApiModelProperty(value = "申请对象")
    private Object applier;


    @ApiModelProperty(value = "创建人")
    private String createUserName;


    @ApiModelProperty(value = "API名称")
    private String apiName;


    @ApiModelProperty(value = "服务id")
    private Long serviceId;


    @ApiModelProperty(value = "API类型")
    private String apiType;


    @ApiModelProperty(value = "API路径")
    private String apiPath;


}
