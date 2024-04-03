package cn.topcheer.halberd.app.api.minidev.result;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAuditRemark;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * <p>
 * 业务批注表
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
@Getter
@Setter
public class MiniBizAuditRemarkResult extends MiniBizAuditRemark {


    @ApiModelProperty(value = "创建人")
    private String createUserName;


}
