package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApplication;
import cn.topcheer.halberd.app.api.framework.dto.api.AmApplicationDTO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

public class AmApplicationWrapper extends BaseEntityWrapper<AmApplication, AmApplicationDTO> {

    public static AmApplicationWrapper build() {
        return new AmApplicationWrapper();
    }

    @Override
    public AmApplicationDTO entityVO(AmApplication entity) {
        AmApplicationDTO dto = Objects.requireNonNull(BeanUtil.copy(entity, AmApplicationDTO.class));
        return dto;
    }
}
