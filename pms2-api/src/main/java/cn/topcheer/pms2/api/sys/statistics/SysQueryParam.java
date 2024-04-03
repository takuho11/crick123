/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.api.sys.statistics;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 *  查询统计参数表
 * @author jzp
 * @since 2024-03-14
 */
@Data
@Entity
@Table(name="SYS_QUERY_PARAM")
@ApiModel(value = "SYS_QUERY_PARAM(查询统计参数表)")
public class SysQueryParam {
	@Id
	@NotBlank
	@ApiModelProperty("主键id")
	private String id;
	

	@ApiModelProperty("代码")
	private String code;
	

	@ApiModelProperty("值")
	private String value;


	@ApiModelProperty("连接sql")
	private String insql;
	

	@ApiModelProperty("类型")
	private String type;


	@ApiModelProperty("连接类别=like")
	private String linktype;
	

	@ApiModelProperty("关联统计表id")
	private String sourceid;


	@ApiModelProperty("创建id")
	private String creatuserid;


	@ApiModelProperty("备注")
	private String memo;


	@ApiModelProperty("更新时间")
	private Date updatetime;


	@ApiModelProperty("排序")
	private Integer seq;

}