package cn.topcheer.halberd.app.api.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DbTableDTO {

    private DbTableInfoDTO tableInfoDTO;

    @ApiModelProperty(value = "列")
    private List<DbColumnDTO> columns;

}
