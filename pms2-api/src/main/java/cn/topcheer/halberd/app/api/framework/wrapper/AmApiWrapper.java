package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.dto.api.ApiDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

public class AmApiWrapper extends BaseEntityWrapper<AmApi, ApiDTO> {

    public static AmApiWrapper build() {
        return new AmApiWrapper();
    }

    @Override
    public ApiDTO entityVO(AmApi entity) {
        ApiDTO apiDTO = Objects.requireNonNull(BeanUtil.copy(entity, ApiDTO.class));

//        apiDTO.setFullPath("");
        apiDTO.setRequests(null);
        apiDTO.setResponses(null);
        apiDTO.setIds(entity.getId());
        return apiDTO;
    }
}
