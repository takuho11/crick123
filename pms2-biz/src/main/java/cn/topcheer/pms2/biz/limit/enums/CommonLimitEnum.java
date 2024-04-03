package cn.topcheer.pms2.biz.limit.enums;

import cn.topcheer.pms2.biz.limit.business.JlSbLimitStrategy;
import cn.topcheer.pms2.biz.limit.LimitStrategy;
import cn.topcheer.pms2.biz.limit.business.KjcxptSbLimitStrategy;
import cn.topcheer.pms2.biz.limit.business.RcSbLimitStrategy;
import cn.topcheer.pms2.biz.limit.business.SbLimitStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springblade.core.tool.utils.SpringUtil;

@Getter
@AllArgsConstructor
public enum CommonLimitEnum {
    SB("sb", SpringUtil.getBean(SbLimitStrategy.class)),

    KJCXPT_SB("kjcxpt_sb", SpringUtil.getBean(KjcxptSbLimitStrategy.class)),

    JL_SB("jl_sb", SpringUtil.getBean(JlSbLimitStrategy.class)),

    RC_SB("rc_sb", SpringUtil.getBean(RcSbLimitStrategy.class)),
    ;

    private String type;
    private LimitStrategy limitStrategy;

    public static CommonLimitEnum getByType(String type) {
        for (CommonLimitEnum commonLimitEnum : CommonLimitEnum.values()) {
            if (commonLimitEnum.getType().equals(type)) {
                return commonLimitEnum;
            }
        }
        return null;
    }

}
