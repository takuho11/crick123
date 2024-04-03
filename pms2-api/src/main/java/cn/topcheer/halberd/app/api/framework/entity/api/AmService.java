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
@TableName("am_service")
@EqualsAndHashCode()
@ApiModel(value = "Service对象", description = "")
public class AmService extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 服务名称
	 */
	@ApiModelProperty(value = "服务名称")
	@TableField(value = "name")
    private String name;
          
	/**
	 * 服务描述
	 */
	@ApiModelProperty(value = "服务描述")
	@TableField(value = "memo")
    private String memo;
}
