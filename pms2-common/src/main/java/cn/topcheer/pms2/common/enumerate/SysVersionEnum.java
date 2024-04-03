package cn.topcheer.pms2.common.enumerate;


import cn.topcheer.halberd.biz.common.utils.Util;

/**
 * \* @author zhengke
 * \* @date: 2020/10/15
 * \* @time: 9:58
 * \* Description: 枚举类
 *
 *  versionid字段去掉后，目前该枚举主要给列表使用
 *
 */
public enum SysVersionEnum {

    SB("申报",""),//申报
    HT("合同填报",""),//合同
    HTBG("合同变更",""),//合同变更
    ZQJC("中期检查",""),//中期检查
    JB("季度报告",""),//季度报告
    NDBG("年度报告","NB"),//年度报告
    KJBG("科技报告",""),//科技报告
    YS("验收申请",""),//验收
    YSCGDJ("验收申请-成果登记",""),//验收-成果登记

    KC("孵化器",""),//孵化器备案
    KCBG("孵化器变更",""),//孵化器变更
    KCZX("孵化器注销",""),//孵化器注销
    CR("孵化器年度报告",""),//孵化器年报
    FHQHBZ("孵化器后补助",""),//孵化器后补助
    FHQTJH("孵化器推荐函",""),//孵化器推荐函

    TECHAWARDS("科技奖励","RW"),//科技奖励

    KJCXPT("科技创新平台",""),//科技创新平台
    KJCXPT_NDBG("科技创新平台-年度报告",""),//科技创新平台-年度报告
    KJCXPT_HT("科技创新平台-任务书",""),//科技创新平台-任务书

    REGISTER_USER("注册人",""),

    GUIDE_COLLECT("指南征集",""),//指南征集
    MSJX("免申即享",""),//免申即享
    ANNUAL_INSPECT("年度自查表",""),//年度自查表
    CGDJ("成果登记","")//成果登记
    ;


    //旧版本，先注释保留
//    SB("versionsbid","申报",""),//申报
//    HT("versionhtid","合同填报",""),//合同
//    HTBG("","合同变更",""),//合同变更
//    ZQJC("versionzqjcid","中期检查",""),//中期检查
//    JB("","季度报告",""),//季度报告
//    NDBG("versionndbgid","年度报告","NB"),//年度报告
//    KJBG("versionkjbgid","科技报告",""),//科技报告
//    YS("versionysid","验收申请",""),//验收
//    CGDJ("","验收申请-成果登记",""),//验收-成果登记
//
//    KC("versionkcid","孵化器",""),//孵化器备案
//    KCBG("","孵化器变更",""),//孵化器变更
//    KCZX("","孵化器注销",""),//孵化器注销
//    CR("versioncrid","孵化器年度报告",""),//孵化器年报
//    FHQHBZ("","孵化器后补助",""),//孵化器后补助
//    FHQTJH("","孵化器推荐函",""),//孵化器推荐函
//
//    TECHAWARDS("versionkjjlid","科技奖励","RW"),//科技奖励
//
////    FBXQ("versionfbxqid","特派员发布需求",""),//县科技局发布需求
////    KJTPY_TJB("versionkjtpytjbid","特派员推荐表",""),//科技特派员推荐表
////    KJTPY_YB("versionkjtpyybid","特派员月报",""),//科技特派员月报
////    KJTPY_CGFB("versionkjtpycgfbid","特派员成果发布",""),//科技特派员成果发布
////    KJTPY_PX("versionkjtpypxid","特派员培训计划",""),//科技特派员培训计划
////    KJTPY_GZZJ("versionkjtpygzzjid","特派员工作总结",""),//科技特派员工作总结
//
//    KJCXPT("versionkjcxptid","科技创新平台",""),//科技创新平台
//    KJCXPT_NDBG("versionkjcxptndbgid","科技创新平台-年度报告",""),//科技创新平台-年度报告
//    KJCXPT_HT("versionkjcxpthtid","科技创新平台-任务书",""),//科技创新平台-任务书
//
//
////    SBTJH("versionsbtjhid","申报推荐函",""),//申报推荐函
//
////    ZYJG("versionzyjgid","转移机构",""),//转移机构
////    ZYJG_KHB("versionzyjgkhbid","转移机构考核表",""),//转移机构考核表
////    ZYJG_JSJLR("versionjsjlrid","转移机构技术经理人",""),//转移机构技术经理人
//
////    PREDECLARE("versionysbid","预申报",""),//预申报
////    WAREHOUSE("versionysbid","预申报",""),//预申报--cxs说数据仓列表页需要预申报的应用批次
//
//    REGISTER_USER("versionsbid","注册人","");
    ;



    /*-----------------------------分割线-----------------------------*/

    //属性
//    private String versionidName;//批次表对应的versionid字段名称
    private String typeName;//列表配置用到，业务类型名称
    private String priorType;//列表配置用到，优先获取，为了是列表权限结合流程相关处理


    /*-----------------------------分割线-----------------------------*/


    //构造函数
    private SysVersionEnum(String typeName, String priorType){
//        this.versionidName = versionidName;
        this.typeName = typeName;
        this.priorType = priorType;
    }


    /*-----------------------------分割线-----------------------------*/

    /**
     * 通过 业务类型名称，获取Type值，列表获取使用
     */
    public static String getTypeByName(String typeName){
        for(SysVersionEnum e : SysVersionEnum.values()){
            if(e.getTypeName().equals(typeName)){
                //优先取priorType
                if(!Util.isEoN(e.getPriorType())){
                    return e.getPriorType().toLowerCase();
                }else{
                    return e.name().toLowerCase();
                }
            }
        }
        return "";
    }

    /*-----------------------------分割线-----------------------------*/


//    public String getVersionidName() {
//        return versionidName;
//    }
//
//    public void setVersionidName(String versionidName) {
//        this.versionidName = versionidName;
//    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPriorType() {
        return priorType;
    }

    public void setPriorType(String priorType) {
        this.priorType = priorType;
    }
}
