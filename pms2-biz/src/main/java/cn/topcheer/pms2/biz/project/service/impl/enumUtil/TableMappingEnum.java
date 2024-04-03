package cn.topcheer.pms2.biz.project.service.impl.enumUtil;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.bi.*;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.api.project.entity.platform.*;
import cn.topcheer.pms2.api.project.entity.prize.*;
import cn.topcheer.pms2.api.project.entity.projectBase.*;
import cn.topcheer.pms2.api.project.entity.talent.*;
import cn.topcheer.pms2.biz.bi.*;
import cn.topcheer.pms2.biz.project.service.impl.*;
import cn.topcheer.pms2.biz.project.service.impl.platform.*;
import cn.topcheer.pms2.biz.project.service.impl.prize.*;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.*;
import cn.topcheer.pms2.biz.project.service.impl.talent.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台表名与后台数据表映射表
 *
 * @author GaoGongxin
 * @date 2020/9/13 15:44
 */

public enum TableMappingEnum {

    /*******************************************申报模块开始***********************************************/

    /**
     * 计划项目-申报基本信息表
     */
    PMS_PROJECTBASE("TPBase", "计划项目-主表", PmsProjectbase.class, PmsProjectbaseService.class, "sb"),

    /**
     * 计划项目-单位表
     */
    PMS_PROJECTBASE_CDDW("TPCddw", "计划项目-单位表", PmsProjectbaseCddw.class, PmsProjectbaseCddwService.class, "sb"),

    /**
     * 计划项目-人员表
     */
    PMS_PROJECTBASE_XMRY("TPXmry", "计划项目-人员表", PmsProjectbaseXmry.class, PmsProjectbaseXmryService.class, "sb"),

    /**
     * 计划项目-经费其他支出
     */
    PMS_PROJECTBASE_JFYSOUT("TPJfysout", "计划项目-经费其他支出", PmsProjectbaseJfysout.class, PmsProjectbaseJfysoutService.class, "sb"),


    /**
     * 计划项目-统计表
     */
    PMS_PROJECTBASE_TJ("TPTj", "计划项目-统计表", PmsProjectbaseTj.class, PmsProjectbaseTjService.class, "sb"),


    /**
     * 大计划项目-字段
     */
    PMS_PROJECTBASE_CLOB("TPClob", "计划项目-大字段", PmsProjectbaseClob.class, PmsProjectbaseClobService.class, "sb"),

    /**
     * 计划项目-项目、课题
     */
    PMS_PROJECTBASE_PROJECT("TPProject", "计划项目-项目、课题", PmsProjectbaseProject.class, PmsProjectbaseProjectService.class, "sb"),


    /**
     * 计划项目-特征信息指标
     */
    PMS_PROJECTBASE_TARGET("TPTarget", "计划项目-特征信息指标", PmsProjectbaseTarget.class, PmsProjectbaseTargetService.class, "sb"),


    /**
     * 计划项目-特征信息
     */
    PMS_PROJECTFEATUREINFOR("TPInfo", "计划项目-特征信息", PmsProjectfeatureinfor.class, PmsProjectfeatureinforService.class, "sb"),

    /**
     * 计划项目-曾获奖情况
     */
    PMS_PROJECTBASE_AWARD("TPAward", "计划项目-曾获奖情况", PmsProjectbaseAward.class, PmsProjectbaseAwardService.class, "sb"),

    /**
     * 计划项目-论文情况
     */
    PMS_PROJECTBASE_THESIS("TPThesis", "计划项目-论文情况", PmsProjectbaseThesis.class, PmsProjectbaseThesisService.class, "sb"),

    /**
     * 计划项目-专利情况
     */
    PMS_PROJECTBASE_PATENT("TPPatent", "计划项目-专利情况", PmsProjectbasePatent.class, PmsProjectbasePatentService.class, "sb"),

    /**
     * 计划项目-成果
     */
    PMS_PROJECTBASE_CG("TPCg", "计划项目-成果", PmsProjectbaseCg.class, PmsProjectbaseCgService.class, "sb"),

    /**
     * 计划项目-标准情况
     */
    PMS_PROJECTBASE_STANDARD("TPStandard", "计划项目-标准情况", PmsProjectbaseStandard.class, PmsProjectbaseStandardService.class, "sb"),

    /**
     * 计划项目-教育/工作经历
     */
    PMS_PROJECTBASE_EXP("TPExp", "计划项目-教育/工作经历", PmsProjectbaseExp.class, PmsProjectbaseExpService.class, "sb"),

    /**
     * 计划项目-经费进度
     */
    PMS_PROJECTBASE_JFJD("TPJfjd", "计划项目-经费进度", PmsProjectbaseJfjd.class, PmsProjectbaseJfjdService.class, "sb"),

    /**
     * 计划项目-投资预算
     */
    PMS_PROJECTBASE_TZYS("TPTzys", "计划项目-投资预算", PmsProjectbaseTzys.class, PmsProjectbaseTzysService.class, "sb"),

    /**
     * 计划项目-仪器设备
     */
    PMS_PROJECTBASE_YQSB("TPYqsb", "计划项目-仪器设备", PmsProjectbaseYqsb.class, PmsProjectbaseYqsbService.class, "sb"),

    /**
     * 计划项目-主要绩效
     */
    PMS_PROJECTBASE_ZYJX("TPZyjx", "计划项目-主要绩效", PmsProjectbaseZyjx.class, PmsProjectbaseZyjxService.class, "sb"),

    /**
     * 计划项目-计划进度
     */
    PMS_PROJECTBASE_JHJD("TPJHJD", "计划项目-计划进度", PmsProjectbaseJhjd.class, PmsProjectbaseJhjdService.class, "sb"),

    /**
     * 计划项目-学科
     */
    PMS_PROJECTBASE_SUBJECT("TPSub", "计划项目-学科", PmsProjectbaseSubject.class, PmsProjectbaseSubjectService.class, "sb") {
    },

    /********************************************平台表***********************************************/
    /**
     * 平台载体-主表
     */
    PMS_PLATFORM("PPlatform", "平台载体-主表", PmsPlatform.class, PmsPlatformService.class, "kjcxpt_sb"),

    /**
     * 平台载体-单位表
     */
    PMS_PLATFORM_CDDW("PPlatformCddw", "平台载体-单位表", PmsPlatformCddw.class, PmsPlatformCddwService.class, "kjcxpt_sb"),

    /**
     * 平台载体-大字段表
     */
    PMS_PLATFORM_CLOB("PPlatformColb", "平台载体-大字段表", PmsPlatformClob.class, PmsPlatformClobService.class, "kjcxpt_sb"),

    /**
     * 平台载体-经费表
     */
    PMS_PLATFORM_JFYSOUT("PPlatformJFYSOUT", "平台载体-经费表", PmsPlatformJfysout.class, PmsPlatformJfysoutService.class, "kjcxpt_sb"),

    /**
     * 平台载体-学科表
     */
    PMS_PLATFORM_SUBJECT("PPlatformSubject", "平台载体-学科表", PmsPlatformSubject.class, PmsPlatformSubjectService.class, "kjcxpt_sb"),

    /**
     * 平台载体-统计表
     */
    PMS_PLATFORM_TJ("PPlatformTJ", "平台载体-统计表", PmsPlatformTj.class, PmsPlatformTjService.class, "kjcxpt_sb"),

    /**
     * 平台载体-人员表
     */
    PMS_PLATFORM_XMRY("PPlatformXMRY", "平台载体-人员表", PmsPlatformXmry.class, PmsPlatformXmryService.class, "kjcxpt_sb"),


    /********************************************科技奖励***********************************************/

    /**
     * 科技奖励-主表
     */
    PMS_PRIZE("RPrize", "科技奖励-主表", PmsPrize.class, PmsPrizeService.class, "jl_sb"),

    /**
     * 科技奖励-曾获奖励表
     */
    PMS_PRIZE_AWARD("RPrizeAward", "科技奖励-曾获奖励表", PmsPrizeAward.class, PmsPrizeAwardService.class, "jl_sb"),

    /**
     * 科技奖励-单位表
     */
    PMS_PRIZE_CDDW("RPrizeCddw", "科技奖励-单位表", PmsPrizeCddw.class, PmsPrizeCddwService.class, "jl_sb"),

    /**
     * 科技奖励-大字段表
     */
    PMS_PRIZE_CLOB("RPrizeClob", "科技奖励-大字段表", PmsPrizeClob.class, PmsPrizeClobService.class, "jl_sb"),

    /**
     * 科技奖励-工作简历表
     */
    PMS_PRIZE_EXP("RPrizeExp", "科技奖励-工作简历表", PmsPrizeExp.class, PmsPrizeExpService.class, "jl_sb"),

    /**
     * 科技奖励-完成人合作关系表
     */
    PMS_PRIZE_PARTNERSHIP("RPrizeShip", "科技奖励-完成人合作关系表", PmsPrizePartnership.class, PmsPrizePartnershipService.class, "jl_sb"),

    /**
     * 科技奖励-知识产权表
     */
    PMS_PRIZE_PATENT("RPrizePatent", "科技奖励-知识产权表", PmsPrizePatent.class, PmsPrizePatentService.class, "jl_sb"),

    /**
     * 科技奖励-推荐专家表业务表
     */
    PMS_PRIZE_RECOMMENDED_EXPERTS("RPrizeExperts", "科技奖励-推荐专家表业务表", PmsPrizeRecommendedExperts.class, PmsPrizeRecommendedExpertsService.class, "jl_sb"),

    /**
     * 科技奖励-推荐单位表业务表
     */
    PMS_PRIZE_RECOMMENDED_UNIT("RPrizeUnit", "科技奖励-推荐单位表业务表", PmsPrizeRecommendedUnit.class, PmsPrizeRecommendedUnitService.class, "jl_sb"),

    /**
     * 科技奖励-学科表
     */
    PMS_PRIZE_SUBJECT("RPrizeSubject", "科技奖励-学科表", PmsPrizeSubject.class, PmsPrizeSubjectService.class, "jl_sb"),

    /**
     * 科技奖励-论文表
     */
    PMS_PRIZE_THESIS("RPrizeThesis", "科技奖励-论文表", PmsPrizeThesis.class, PmsPrizeThesisService.class, "jl_sb"),

    /**
     * 科技奖励-论文他引表
     */
    PMS_PRIZE_THESIS_OTHER("RPrizeOther", "科技奖励-论文他引表", PmsPrizeThesisOther.class, PmsPrizeThesisOtherService.class, "jl_sb"),

    /**
     * 科技奖励-人员表
     */
    PMS_PRIZE_XMRY("RPrizeXmry", "科技奖励-人员表", PmsPrizeXmry.class, PmsPrizeXmryService.class, "jl_sb"),

    /**
     * 科技奖励-计划(基金)项目表
     */
    PMS_PRIZE_PRO("RPrizePro", "科技奖励-计划(基金)项目表", PmsPrizePro.class, PmsPrizeProService.class, "jl_sb"),

    /**
     * 科技奖励-合作奖国内联系人表
     */
    PMS_PRIZE_CALINK("RPrizeCaLink", "科技奖励-合作奖国内联系人表", PmsPrizeCalink.class, PmsPrizeCalinkService.class, "jl_sb"),

    /**
     * 科技奖励-统计表
     */
    PMS_PRIZE_TJ("RPrizeTJ", "科技奖励-统计表", PmsPrizeTj.class, PmsPrizeTjService.class, "jl_sb"),


    /********************************************科技人才***********************************************/


    /**
     * 科技人才-主表
     */
    PMS_TALENT("TATalent", "科技人才-主表", PmsTalent.class, PmsTalentService.class, "rc_sb"),

    /**
     * 科技人才-申报情况表
     */
    PMS_TALENT_APPLICATION("TATalentApp", "科技人才-申报情况表", PmsTalentApplication.class, PmsTalentApplicationService.class, "rc_sb"),

    /**
     * 科技人才-奖励表
     */
    PMS_TALENT_AWARD("TATalentAward", "科技人才-奖励表", PmsTalentAward.class, PmsTalentAwardService.class, "rc_sb"),

    /**
     * 科技人才-单位表
     */
    PMS_TALENT_CDDW("TATalentCddw", "科技人才-单位表", PmsTalentCddw.class, PmsTalentCddwService.class, "rc_sb"),

    /**
     * 科技人才-大字段表
     */
    PMS_TALENT_CLOB("TATalentClob", "科技人才-大字段表", PmsTalentClob.class, PmsTalentClobService.class, "rc_sb"),

    /**
     * 科技人才-会议表
     */
    PMS_TALENT_CONFERENCE("TATalentConf", "科技人才-会议表", PmsTalentConference.class, PmsTalentConferenceService.class, "rc_sb"),

    /**
     * 科技人才-教育工作表
     */
    PMS_TALENT_EXP("TATalentExp", "科技人才-教育工作表", PmsTalentExp.class, PmsTalentExpService.class, "rc_sb"),

    /**
     * 科技人才-专利知识产权表
     */
    PMS_TALENT_PATENT("TATalentPatent", "科技人才-专利知识产权表", PmsTalentPatent.class, PmsTalentPatentService.class, "rc_sb"),

    /**
     * 科技人才-新产品表
     */
    PMS_TALENT_PRODUCT("TATalentProduct", "科技人才-新产品表", PmsTalentProduct.class, PmsTalentProductService.class, "rc_sb"),

    /**
     * 科技人才-项目表
     */
    PMS_TALENT_PROJECT("TATalentProject", "科技人才-项目表", PmsTalentProject.class, PmsTalentProjectService.class, "rc_sb"),

    /**
     * 科技人才-标准表
     */
    PMS_TALENT_STANDARD("TATalentStandard", "科技人才-标准表", PmsTalentStandard.class, PmsTalentStandardService.class, "rc_sb"),

    /**
     * 科技人才-推荐汇总表
     */
    PMS_TALENT_SUMMARY("TATalentSummary", "科技人才-推荐汇总表", PmsTalentSummary.class, PmsTalentSummaryService.class, "rc_sb"),

    /**
     * 科技人才-论文专著表
     */
    PMS_TALENT_THESIS("TATalentThesis", "科技人才-论文专著表", PmsTalentThesis.class, PmsTalentThesisService.class, "rc_sb"),

    /**
     * 科技人才-人员表
     */
    PMS_TALENT_XMRY("TATalentXmry", "科技人才-人员表", PmsTalentXmry.class, PmsTalentXmryService.class, "rc_sb"),

    /**
     * 科技人才-学科
     */
    PMS_TALENT_SUBJECT("TATalentSub", "科技人才-学科", PmsTalentSubject.class, PmsTalentSubjectService.class, "rc_sb"),

    /**
     * 科技人才-经营情况表
     */
    PMS_TALENT_BUSINESS("TATalentBusuness", "科技人才-经营情况表", PmsTalentBusiness.class, PmsTalentBusinessService.class, "rc_sb"),

    /**
     * 科技人才-研发经费投入情况表
     */
    PMS_TALENT_RD("TATalentRd", "科技人才-研发经费投入情况表", PmsTalentRd.class, PmsTalentRdService.class, "rc_sb"),

    /**
     * 科技人才-科技创新平台和人才情况表
     */
    PMS_TALENT_STAFFEDU("TATalentStaffedu", "科技人才-科技创新平台和人才情况表", PmsTalentStaffedu.class, PmsTalentStaffeduService.class, "rc_sb"),

    /********************************************数据仓***********************************************/


    /**
     * 数据仓-主表
     */
    BI_MAINBASE("BiMainbase", "数据仓-主表", BiMainbase.class, BiMainbaseService.class, "bi_sb"),


    /**
     * 数据仓-期刊任职表
     */
    BI_ACH_AO("BIAchAo", "数据仓-期刊任职表", BiAchAo.class, BiAchAoService.class, "bi_sb"),

    /**
     * 数据仓-派生企业表
     */
    BI_ACH_DTE("BIAchDte", "数据仓-派生企业表", BiAchDte.class, BiAchDteService.class, "bi_sb"),

    /**
     * 数据仓-知识产权表
     */
    BI_ACH_IPR("BIAchIpr", "数据仓-知识产权表", BiAchIpr.class, BiAchIprService.class, "bi_sb"),

    /**
     * 数据仓-承担项目表
     */
    BI_ACH_PRO("BIAchPro", "数据仓-承担项目表", BiAchPro.class, BiAchProService.class, "bi_sb"),

    /**
     * 数据仓-标准制定表
     */
    BI_ACH_SS("BIAchSs", "数据仓-标准制定表", BiAchSs.class, BiAchSsService.class, "bi_sb"),

    /**
     * 数据仓-科技奖励表
     */
    BI_ACH_STA("BIAchSta", "数据仓-科技奖励表", BiAchSta.class, BiAchStaService.class, "bi_sb"),

    /**
     * 数据仓-人/单位信息表
     */
    BI_ACH_INFO("BiAchInfo", "数据仓-人/单位信息表", BiAchInfo.class, BiAchInfoService.class, "bi_sb"),

    /**
     * 数据仓-论文专著表
     */
    BI_ACH_TM("BIAchTm", "数据仓-论文专著表", BiAchTm.class, BiAchTmService.class, "bi_sb"),

    /**
     * 数据仓-载体信息
     */
    BI_C_BI("BICBi", "数据仓-载体信息", BiCBi.class, BiCBiService.class, "bi_sb"),

    /**
     * 数据仓-主要研发成果表
     */
    BI_ENT_ACH("BIEntAch", "数据仓-主要研发成果表", BiEntAch.class, BiEntAchService.class, "bi_sb"),

    /**
     * 数据仓-单位信息表
     */
    BI_ENT_BI("BIEntBi", "数据仓-单位信息表", BiEntBi.class, BiEntBiService.class, "bi_sb"),

    /**
     * 数据仓-主要产品表
     */
    BI_ENT_CP("BIEntCp", "数据仓-主要产品表", BiEntCp.class, BiEntCpService.class, "bi_sb"),

    /**
     * 数据仓-财务情况表
     */
    BI_ENT_FSS("BiEntFss", "数据仓-财务情况表", BiEntFss.class, BiEntFssService.class, "bi_sb"),

    /**
     * 数据仓-股权信息
     */
    BI_ENT_OS("BiEntOs", "数据仓-股权信息", BiEntOs.class, BiEntOsService.class, "bi_sb"),

    /**
     * 数据仓-企业相关人员表
     */
    BI_ENT_RY("BiEntRy", "数据仓-企业相关人员表", BiEntRy.class, BiEntRyService.class, "bi_sb"),


    /**
     * 数据仓-人才-个人信息
     */
    BI_TALENT_BI("BiTalentBi", "数据仓-人才-个人信息", BiTalentBi.class, BiTalentBiService.class, "bi_sb"),

    /**
     * 数据仓-人才-合作人员表
     */
    BI_TALENT_RY("BiTalentRy", "数据仓-人才-合作人员表", BiTalentRy.class, BiTalentRyService.class, "bi_sb"),

    /**
     * 数据仓-人才-经历表
     */
    BI_TALENT_WE("BiTalentWe", "数据仓-人才-经历表", BiTalentWe.class, BiTalentWeService.class, "bi_sb"),

    /**
     * 数据仓-大字段表
     */
    BI_CLOB("BiClob", "数据仓-大字段表", BiClob.class, BiClobService.class, "bi_sb"),

    /**
     * 数据仓-学科表
     */
    BI_ENT_SUBJECT("BiEntSubject", "数据仓-学科表", BiEntSubject.class, BiEntSubjectService.class, "bi_sb"),


    ;
    /**
     * 前台表名
     */
    private String tablename;

    /**
     * 前台表名
     */
    private String cnname;

    /**
     * 后台基类Class信息
     */
    private Class classname;

    /**
     * 后台Service类Class信息
     */
    private Class serviceclassname;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 基类信息
     */
    private ClassInfo classInfo;

    /**
     * 构造方法(service命名不规范的情况)
     *
     * @param tablename        前台表名
     * @param cnname           中文名称
     * @param classname        后台基类Class信息
     * @param serviceclassname 后台Service类Class信息
     * @param type             业务类型
     */
    private TableMappingEnum(String tablename, String cnname, Class classname, Class serviceclassname, String type) {
        this.tablename = tablename;
        this.classname = classname;
        this.cnname = cnname;
        this.serviceclassname = serviceclassname;
        this.type = type;
        this.classInfo = (ClassInfo) classname.getAnnotation(ClassInfo.class);
    }

    /**
     * 构造方法
     *
     * @param tablename 前台表名
     * @param classname 后台基类Class信息
     */
    private TableMappingEnum(String tablename, Class classname) {
        this.tablename = tablename;
        this.classname = classname;
        this.classInfo = (ClassInfo) classname.getAnnotation(ClassInfo.class);
    }

    /**
     * 通过前台表名查询枚举信息
     *
     * @param tablename 前台表名
     * @return TableMappingEnum
     */
    public static TableMappingEnum getEnumByTablename(String tablename) {
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            if (tableMappingEnum.getTablename().equals(tablename)) {
                return tableMappingEnum;
            }
        }

        return null;
    }

    /**
     * 后台基类Class查询枚举信息
     *
     * @param classname 后台基类Class信息
     * @return TableMappingEnum
     */
    public static TableMappingEnum getEnumByClassname(Class classname) {
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            if (tableMappingEnum.getClassname().equals(classname)) {
                return tableMappingEnum;
            }
        }
        return null;
    }

    /**
     * 通过name获取相关枚举数组
     *
     * @param name 名称
     * @return List
     */
    public static List<TableMappingEnum> getEnumListByName(String name) {
        List<TableMappingEnum> list = new ArrayList<>();
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            if (tableMappingEnum.name().contains(name)) {
                list.add(tableMappingEnum);
            }
        }
        return list;
    }

    public static TableMappingEnum getEnumByTablename(SysModuleEnum module, Class personTableClass) {

        if (module == null) {
            return null;
        }
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            if (tableMappingEnum.getClassname().isAnnotationPresent(ClassInfo.class)) {
                if (((ClassInfo) tableMappingEnum.getClassname().getAnnotation(ClassInfo.class)).module().equals(module) && tableMappingEnum.getClassname().isAnnotationPresent(personTableClass)) {
                    return tableMappingEnum;
                }
            }
        }
        return null;
    }

    /**
     * 根据数据库表名获取枚举信息
     *
     * @param databaseTablename 后台基类Class信息
     * @return
     */
    public static TableMappingEnum getEnumByDatabaseTablename(String databaseTablename) {
        databaseTablename = databaseTablename.toUpperCase();
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            if (tableMappingEnum.name().equals(databaseTablename)) {
                return tableMappingEnum;
            }
        }
        return null;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Class getClassname() {
        return classname;
    }

    public void setClassname(Class classname) {
        this.classname = classname;
    }

    public Class getServiceclassname() {
        return serviceclassname;
    }

    public void setServiceclassname(Class serviceclassname) {
        this.serviceclassname = serviceclassname;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
