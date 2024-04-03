package cn.topcheer.halberd.app.api.minidev.dto;


import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItemField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 业务分页子项字段表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@ApiModel(value = "MiniBizDefTabItemField对象", description = "业务分页子项字段表")
public class MiniBizDefTabItemFieldDTO {


    @ApiModelProperty(value = "业务分页子项id")
    private Long bizDefTabItemId;


    @ApiModelProperty(value = "业务分页子项字段列表")
    private List<MiniBizDefTabItemField> bizDefTabItemFields;


}
