package cn.topcheer.halberd.app.api.minidev.dto;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 标准业务分页子项字段表
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Setter
@Getter
@ApiModel(value = "MiniSdBizDefTabItemField对象", description = "标准业务分页子项字段表")
public class MiniSdBizDefTabItemFieldDTO {


    @ApiModelProperty(value = "标准业务分页子项id")
    private Long sdBizDefTabItemId;


    @ApiModelProperty(value = "业务分页子项字段列表")
    private List<MiniSdBizDefTabItemField> sdBizDefTabItemFields;


}
