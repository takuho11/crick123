package cn.topcheer.pms2.biz.pml.service.impl.enumUtil;

import cn.topcheer.pms2.api.bi.BiMainbase;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.api.project.entity.platform.PmsPlatform;
import cn.topcheer.pms2.api.project.entity.prize.PmsPrize;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.api.project.entity.talent.PmsTalent;

import java.util.Optional;

/**
 * Created by peanut.huang on 2020/6/25.
 */
public enum TableEnum {


    /**
     * 计划项目-主表
     */
    sb("PmsProjectbase", PmsProjectbase.class),


    /**
     * 平台载体-主表
     */
    kjcxpt_sb("PmsPlatform", PmsPlatform.class),


    /**
     * 科技奖励-主表
     */
    jl_sb("PmsPrize", PmsPrize.class),


    /**
     * 科技人才-主表
     */
    rc_sb("PmsTalent", PmsTalent.class),


    /**
     * 数据仓-主表
     */
    bi_sb("BiMainbase", BiMainbase.class),


    grid("pmlGrid", PmlGrid.class),
    ;

    private String systemClassName;
    private Class classname;

    <T> TableEnum(String systemClassName, Class<T> classname) {
        this.systemClassName = systemClassName;
        this.classname = classname;
    }

    /**
     * 根据枚举名称获取对应的枚举，如果为获取到，则返回空
     *
     * @param name
     * @return
     */
    public static TableEnum getEnumByName(String name) {
        TableEnum tableEnum = null;
        try {
            tableEnum = TableEnum.valueOf(name);
        } catch (Exception e) {
            tableEnum = null;
        }
        return tableEnum;
    }

    public String getSystemClassName() {
        return systemClassName;
    }

    public void setSystemClassName(String systemClassName) {
        this.systemClassName = systemClassName;
    }

    public Class getClassname() {
        return classname;
    }

    public void setClassname(Class classname) {
        this.classname = classname;
    }

    public static void main(String[] args) {
        //JSONArray jsonArray = JSONArray.fromObject(TableEnum。);
        Integer qqq = 111;
        Integer ppp = 2;
        Integer t = Optional.ofNullable(qqq).orElse(ppp);
    }
}
