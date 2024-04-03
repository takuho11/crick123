package cn.topcheer.pms2.api.limit.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "LimitConditionVO对象")
public class LimitConditionVO implements Serializable {

	@ApiModelProperty("主键id")
	private String id;

	@ApiModelProperty("条件名称")
	private String name;

	@ApiModelProperty("条件代码，用于后台判断")
	private String code;

	@ApiModelProperty("系统类型：rcsb,kjxm,kjjl...")
	private String system;

	@ApiModelProperty("业务类型：sb,ht...")
	private String type;

	@ApiModelProperty("条件内容")
	private String content;

	@ApiModelProperty("排序")
	private Integer seq;

	@ApiModelProperty("批次名称")
	private List<String> batchNameList;

}