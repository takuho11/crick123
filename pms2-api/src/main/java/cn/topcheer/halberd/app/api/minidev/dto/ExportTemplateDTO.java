package cn.topcheer.halberd.app.api.minidev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * ImportDataDTO
 *
 * @author szs
 * @date 2023-10-18
 */
@Getter
@Setter
public class ExportTemplateDTO {


    @Valid
    @NotBlank(message = "Excel文件名称必填")
    @ApiModelProperty(value = "Excel文件名称", required = true)
    private String fileName;


    @Valid
    @NotBlank(message = "Excel文件Sheet名称必填")
    @ApiModelProperty(value = "Excel文件Sheet名称", required = true)
    private String sheetName;


    @Valid
    @NotNull(message = "Excel表头数据必填")
    @ApiModelProperty(value = "Excel表头数据", required = true)
    private List<List<String>> headList = new ArrayList<>();


}
