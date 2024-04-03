package cn.topcheer.halberd.app.api.framework.dto.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmApplicationDTO extends AmApplication {

	private static final long serialVersionUID = 1L;

	private Integer validCount;
}
