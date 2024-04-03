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
 *  查询统计主表
 * @author jzp
 * @since 2024-03-14
 */
@Data
@Entity
@Table(name="SYS_QUERY_TABLE")
@ApiModel(value = "SYS_QUERY_TABLE(查询统计主表)")
public class SysQueryTable {

	@Id
	@NotBlank
	@ApiModelProperty("主键id")
	private String id;
	

	@ApiModelProperty("类型")
	private String type;
	

	@ApiModelProperty("类型1")
	private String typeone;
	

	@ApiModelProperty("类型2")
	private String typetwo;


	@ApiModelProperty("名称")
	private String name;


	@ApiModelProperty("主语句")
	private String sql;


	@ApiModelProperty("结束语句")
	private String endsql;


	@ApiModelProperty("大语句id")
	private String sqlid;


	@ApiModelProperty("返回数据key")
	private String datakey;


	@ApiModelProperty("原因")
	private String errmsg;


	@ApiModelProperty("备注")
	private String memo;


	@ApiModelProperty("排序")
	private Integer seq;

	@ApiModelProperty("创建人")
	private String creatname;


	@ApiModelProperty("创建id")
	private String creatuserid;

	@ApiModelProperty("创建时间")
	private Date creattime;


	@ApiModelProperty("更新时间")
	private Date updatetime;


}