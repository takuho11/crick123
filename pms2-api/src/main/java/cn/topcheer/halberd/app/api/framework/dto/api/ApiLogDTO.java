package cn.topcheer.halberd.app.api.framework.dto.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * ApiLogDTO
 *
 * @author szs
 * @date 2023-09-11
 */
@Getter
@Setter
public class ApiLogDTO {


    @ApiModelProperty(value = "APIID")
    private String apiId;


    @ApiModelProperty(value = "应用ID")
    private String clientId;


    @ApiModelProperty(value = "状态（0失败，1成功）")
    private Integer status;


    @ApiModelProperty(value = "创建时间（时间范围，逗号隔开）")
    private String createTimeStr;


    @ApiModelProperty(value = "开始日期（yyyy-MM-dd）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @ApiModelProperty(value = "结束日期（yyyy-MM-dd）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


}
