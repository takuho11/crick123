package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * BatchAddModelDTO
 *
 * @author szs
 * @date 2024-01-08
 */
@Data
public class BatchAddModelDTO {


    @Valid
    @NotBlank(message = "数据源id必填")
    @ApiModelProperty(value = "数据源id", required = true)
    private String datasourceId;


    @Valid
    @NotBlank(message = "数据库必填")
    @ApiModelProperty(value = "数据库", required = true)
    private String schemaName;


    @Valid
    @NotNull(message = "物理表名必填")
    @ApiModelProperty(value = "物理表名", required = true)
    private List<String> modelTables;


    @Valid
    @NotNull(message = "模型类型必填")
    @ApiModelProperty(value = "模型类型id")
    private Long modelTypeId;


}

