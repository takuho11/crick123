package cn.topcheer.pms2.api.limit.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@TableName("LIMIT_CONDITION")
@ApiModel(value = "LimitCondition对象")
public class LimitCondition implements Serializable {

	@ApiModelProperty("主键id")
	@TableId(type = IdType.INPUT)
//	@TableField(value = "iiid", insertStrategy = FieldStrategy.NOT_EMPTY)//此注解只在wrapper模式有效
	@NotBlank
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

}