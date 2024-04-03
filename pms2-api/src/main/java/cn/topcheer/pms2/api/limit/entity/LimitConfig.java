package cn.topcheer.pms2.api.limit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@TableName("LIMIT_CONFIG")
@ApiModel(value = "LimitConfig对象")
//@Alias("LimitConfig")//typeAliasesPackage别名默认首字母大小写都行
public class LimitConfig implements Serializable {

	@ApiModelProperty("主键id")
	@TableId(type = IdType.INPUT)
	@NotBlank
	private String id;

	@ApiModelProperty("小批次id")
	private String batchId;

	@ApiModelProperty("条件id")
	private String conditionId;

	@ApiModelProperty("参数配置")
	private String param;

	@ApiModelProperty("排序")
	private Integer seq;

	@ApiModelProperty("备注")
	private String memo;

}