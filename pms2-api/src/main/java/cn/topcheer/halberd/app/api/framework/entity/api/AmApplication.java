package cn.topcheer.halberd.app.api.framework.entity.api;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

import java.io.Serializable;

/**
 *  实体类
 *
 * @author templateTool
 */
@Data
@TableName("am_application")
@EqualsAndHashCode()
@ApiModel(value = "Application对象", description = "")
public class AmApplication extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 应用名称
	 */
	@ApiModelProperty(value = "应用名称")
	@TableField(value = "name")
    private String name;
          
	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "负责人")
	@TableField(value = "owner")
    private String owner;
          
	/**
	 * 联系方式
	 */
	@ApiModelProperty(value = "联系方式")
	@TableField(value = "telphone")
    private String telphone;
          
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	@TableField(value = "email")
    private String email;
          
	/**
	 * 白名单
	 */
	@ApiModelProperty(value = "白名单")
	@TableField(value = "white")
    private String white;
          
	/**
	 * 应用描述
	 */
	@ApiModelProperty(value = "应用描述")
	@TableField(value = "memo")
    private String memo;
          
	/**
	 * clientid
	 */
	@ApiModelProperty(value = "clientid")
	@TableField(value = "client_id")
    private String clientId;

}
