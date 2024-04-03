/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.vo.sys.PmsDepartmentVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author szs
 * @date 2023-11-01
 */
public class PmsDepartmentWrapper extends BaseEntityWrapper<PmsDepartment, PmsDepartmentVO> {

    public static PmsDepartmentWrapper build() {
        return new PmsDepartmentWrapper();
    }

    @Override
    public PmsDepartmentVO entityVO(PmsDepartment enterprise) {
        PmsDepartmentVO enterpriseVO = Objects.requireNonNull(BeanUtil.copy(enterprise, PmsDepartmentVO.class));

        if (enterprise.getPmsEnterprise() != null) {
            enterpriseVO.setEnterPriseId(enterprise.getPmsEnterprise().getId());
            enterpriseVO.setEnterPriseName(enterprise.getPmsEnterprise().getName());
        }

        return enterpriseVO;
    }


}
