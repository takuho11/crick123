package cn.topcheer.halberd.app.api.framework.wrapper;
 
import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.dto.api.AmDataSourceDTO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

public class AmDataSourceWrapper extends BaseEntityWrapper<AmDataSource, AmDataSourceDTO> {

    public static AmDataSourceWrapper build() {
        return new AmDataSourceWrapper();
    }

    @Override
    public AmDataSourceDTO entityVO(AmDataSource entity) {
        AmDataSourceDTO amDataSourceDTO = Objects.requireNonNull(BeanUtil.copy(entity, AmDataSourceDTO.class));
        return amDataSourceDTO;
    }
}
