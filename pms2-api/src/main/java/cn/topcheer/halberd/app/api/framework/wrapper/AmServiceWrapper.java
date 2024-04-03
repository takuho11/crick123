package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.entity.api.AmService;
import cn.topcheer.halberd.app.api.framework.dto.api.AmServiceDTO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

public class AmServiceWrapper extends BaseEntityWrapper<AmService, AmServiceDTO> {

    public static AmServiceWrapper build() {
        return new AmServiceWrapper();
    }

    @Override
    public AmServiceDTO entityVO(AmService entity) {
        AmServiceDTO dto = Objects.requireNonNull(BeanUtil.copy(entity, AmServiceDTO.class));
        return dto;
    }
}
