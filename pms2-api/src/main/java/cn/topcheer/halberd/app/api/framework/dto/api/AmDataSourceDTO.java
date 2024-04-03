package cn.topcheer.halberd.app.api.framework.dto.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 模型DTO
 *
 * @author Chill
 */
@Data
public class AmDataSourceDTO extends AmDataSource {

    private static final long serialVersionUID = 1L;

    private int DolphinDataSourceId;


    @ApiModelProperty(value = "是否仅仅查询自己创建（0否，1是）")
    private Integer isMyCreate;


    @ApiModelProperty(value = "开始日期（yyyy-MM-dd）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @ApiModelProperty(value = "结束日期（yyyy-MM-dd）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


}
