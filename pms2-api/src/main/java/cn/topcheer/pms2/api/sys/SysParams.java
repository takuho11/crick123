package cn.topcheer.pms2.api.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@TableName("SYS_PARAMS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysParams {

	/**
 	 *  唯一id
 	 */
	@NotBlank
	private String id;

	/**
 	 *  父级id
 	 */
	private String parentid;

	/**
 	 *  显示在页面的名称
 	 */
	private String name;

	/**
 	 *  需要存在业务数据的值
 	 */
	private String value;

	/**
 	 *  应用类型，比如学科是来自专家库的，或者学科是来自项目系统的...
 	 */
	private String applytype;

	/**
	 *  非必填代码，如学科代码、所在地代码
	 */
	private String code;

	/**
 	 *  备注
 	 */
	private String memo;

	/**
 	 *  排序
 	 */
	private Integer seq;

	/**
	 *  需要存在业务数据的值(int类型)
	 */
	private Integer intvalue;

}