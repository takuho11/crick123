package cn.topcheer.halberd.app.api.framework.dto.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmService;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmServiceDTO extends AmService {

	private static final long serialVersionUID = 1L;

	private Integer validCount;

}
