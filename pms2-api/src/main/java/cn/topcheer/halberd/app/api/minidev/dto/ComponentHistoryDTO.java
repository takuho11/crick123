package cn.topcheer.halberd.app.api.minidev.dto;

import cn.topcheer.halberd.app.api.minidev.entity.ComponentHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  模型DTO
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ComponentHistoryDTO extends ComponentHistory {

	private static final long serialVersionUID = 1L;

}
