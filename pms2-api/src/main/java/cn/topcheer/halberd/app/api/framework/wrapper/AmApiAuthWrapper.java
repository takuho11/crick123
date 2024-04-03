package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.dto.api.AmApiAuthDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

public class AmApiAuthWrapper extends BaseEntityWrapper<AmApiAuth, AmApiAuthDTO> {

    public static AmApiAuthWrapper build() {
        return new AmApiAuthWrapper();
    }

    @Override
    public AmApiAuthDTO entityVO(AmApiAuth entity) {
        AmApiAuthDTO dto = Objects.requireNonNull(BeanUtil.copy(entity, AmApiAuthDTO.class));
        return dto;
    }
}
