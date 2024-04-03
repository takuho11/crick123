package cn.topcheer.halberd.app.api.framework.entity;

import org.springblade.core.mp.base.BaseCommonEntity;
import org.springblade.core.tool.utils.Func;

/**
 *
 */
public abstract class BasicUuidEntity extends BaseCommonEntity<String> {
    @Override
    public boolean isIdNull() {
        return Func.isEmpty(this.getId());
    }
}
