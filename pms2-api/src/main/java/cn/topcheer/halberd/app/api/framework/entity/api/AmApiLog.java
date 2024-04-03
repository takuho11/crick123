package cn.topcheer.halberd.app.api.framework.entity.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "am_api_log")
public class AmApiLog {

    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;


    @ApiModelProperty(value = "APIID")
    @TableField("api_id")
    private String apiId;


    @ApiModelProperty(value = "API路径")
    @TableField("api_path")
    private String apiPath;


    @ApiModelProperty(value = "应用ID")
    @TableField("client_id")
    private String clientId;


    @ApiModelProperty(value = "请求参数")
    @TableField("request_param")
    private String requestParam;


    @ApiModelProperty(value = "返回参数")
    @TableField("response_param")
    private String responseParam;


    @ApiModelProperty(value = "请求IP")
    @TableField("request_ip")
    private String requestIp;


    @ApiModelProperty(value = "状态（0失败，1成功）")
    @TableField("status")
    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("request_time")
    private LocalDateTime requestTime;


    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;


}
