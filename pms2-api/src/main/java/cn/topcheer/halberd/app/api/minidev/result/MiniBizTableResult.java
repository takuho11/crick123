package cn.topcheer.halberd.app.api.minidev.result;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 业务数据库表Result
 *
 * @author szs
 * @date 2023-12-29
 */
@Data
@AllArgsConstructor
public class MiniBizTableResult {


    @ApiModelProperty(value = "数据库编码")
    private String tableCode;


    @ApiModelProperty(value = "数据库名称")
    private String tableName;


}

