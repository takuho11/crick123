/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.api.sys.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 *  查询统计大字段
 * @author jzp
 * @since 2024-03-14
 */
@Data
@Entity
@Table(name="SYS_QUERY_CLOB")
@ApiModel(value = "SYS_QUERY_CLOB(查询统计大字段)")
public class SysQueryClob {
	@Id
	@NotBlank
	@ApiModelProperty("主键id")
	private String id;

	@ApiModelProperty("类型")
	private String type;

	@ApiModelProperty("数据")
	private String source;

	@ApiModelProperty("关联")
	private String sourceid;

	@ApiModelProperty("最后更新时间")
	private Date updatelasttime;

}