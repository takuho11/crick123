
package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysAgency;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysAgencyVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author szs
 * @date 2024-01-12
 */
public class SysAgencyWrapper extends BaseEntityWrapper<SysAgency, SysAgencyVO> {

    public static SysAgencyWrapper build() {
        return new SysAgencyWrapper();
    }

    @Override
    public SysAgencyVO entityVO(SysAgency data) {
        SysAgencyVO vo = Objects.requireNonNull(BeanUtil.copy(data, SysAgencyVO.class));

        if (data.getSysOrganization() != null) {
            vo.setOrganizationId(data.getSysOrganization().getId());
            vo.setOrganizationName(data.getSysOrganization().getName());
        }

        return vo;
    }


}
