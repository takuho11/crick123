/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2016-1-8 13:09:43
 */
package cn.topcheer.pms2.api.plan.entity;


import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * 小批次表
 */
@Entity
@Table(name = "PMS_PLANPROJECTBATCH")
public class PmsPlanprojectbatch {

    /**
     *
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 说明
     */
    private String explain;

    /**
     * 备注
     */
    private String memo;

    /**
     * 计划项目类别ID
     */
    private PmsPlanproject pmsPlanproject;

    /**
     * 当前状态 0表示不启用  2表示启用
     */
    private String currentstate;

    /**
     * 年度
     */
    private String annually;

    /**
     * 开放用户
     */
    private String openuser;

    /**
     * 是否网上签约
     */
    private String issignonline;

    /**
     * 模板ID
     */
    private String applicationtableexplain;
    /**
     * 是否主页显示
     */
    private String isdisplay;

    /**
     * 专家网评结果未满足票数导出sql
     */
    private String expertscoreresult_no;

    /**
     * 专家网评结果导出sql
     */
    private String expertscoreresult;

    /**
     * 合同pdf模板名称
     */
    private String htmodelname;

    /**
     * 1：非申报 0或空：申报
     */
    private String issb;

    /**
     * 申报时间结束后，用户是否还能上报 1表示能上报，空表示不能上报
     */
    private String sblimitflag;

    /**
     * 申报时间结束后，审核人是否还能审核  1表示能审核，空表示不能审核
     */
    private String shlimitflag;

    private String modelname;
    private String city;// 所在市
    private String county;// 所在县
    // 浏览上报情况用户
    private String broseruser;

    private String yzphone;

    private String planid;


    /**
     * 短信模板
     */

    private String Messagemodel;
    private String SMSAttend;
    private String SMSNotice;
    private String SMSAccount;
    private Boolean appliylimit; // 該批次是否限制申報數量


    /**
     * 合同生成pdf路径
     */
    private String pdfurlforcontract;

    /**
     * 申请书生成pdf路径
     */
    private String pdfurlforbase;

    /**
     * 合同生成pdf方式
     */
    private String pdfdowntypeforcontract;

    /**
     * 申请书生成pdf方式
     */
    private String pdfdowntypeforbase;

    /**
     * 获取对应的批次生成方法
     */
    private String modeloraffix;

    /**
     * 获取对应的批次生成方法--合同
     */
    private String modeloraffixht;

    /**
     * 插在最后的附件类型
     */
    private String affixtype;


    /**
     * 判断批次是否是老系统批次，1表示是，null表示不是
     */
    private String isoldsystem;


    private Double mortality;// 专家网评控制的淘汰比率


    // 批次是否联审准备状态
    private String isstartuniteapprove;

    /**
     * 状态
     */
    private String status;

    /**
     * 现场评审状态
     */
    private String xcstatus;

    /**
     * 评审说明
     */
    private String reviewexplain;

    /**
     * 专家评审承诺书
     */
    private String undertaking;

    /**
     * 开发用户代码
     */
    private String openusercode;

    // 批次申报树
    private String enterpriseparentidex;

    /**
     * 申报指南ID
     */
    private PmsDeclarationguide pmsDeclarationguide;

    /**
     * 工作流配置
     */
    private String workflowdefineid;

    private String enterpriselimit;

    private String xingshenusers;

    private String newplanprojectid;

    /**
     * 会评规避类型
     */
    private String avoidtype;

    private String nocheck;//是否需要结果验证标记（空为需要，"无需验证码"表示不用再次输入手机验证码）
    private String xtwys_hrefname;//系统外验收，模板下载名称
    private String xtwys_href;//系统外验收，模板下载地址

    private String batchreviewid;//用于评审批次id

    private String frontno;//合同编号前位
    private Date crt_startdate;//合同开始时间
    private Date crt_enddate;//合同结束时间
    private String overage;//是否允许超龄用户申报，1表示允许，空表示不允许
    private String iscanhtchange;//是否可以进行合同变更，1表示允许，空表示不允许
    private String iscankjcxptchange;//是否可以进行平台变更，1表示允许，空表示不允许

    private String otherdepartmentid;//其他处室id,比如根据领域来选择处室,以后可以用 大批次表的处室Id和这个字段一起关联处室


    private String addpagenum;//是否要单独加页码PDF
    private String ishavejdbg;//是否需要填季度报告，1：是，空：否


    private Date modelupdatetime;//模板更新时间--申报

    private Date hbz_startdate;//后补助开始时间
    private Date hbz_enddate;//后补助结束时间


    private Date fbxq_startdate;//发布需求开始时间
    private Date fbxq_enddate;//发布需求结束时间

    private Date kjtpypx_startdate;//培训开始时间
    private Date kjtpypx_enddate;//培训开始时间

    private String exportmethod; //网评导出结果执行方法

    private String auditdepartment; //形审处室
    private String xsfsflag;//形审方式标记
    private String cb_auditdepartment; //初步形审处室


    private Date ys_startdate;//验收申报开始时间
    private Date ys_enddate;//验收结束开始时间

    /**
     * 是否有承诺书
     */
    private Boolean cnsflag;

    /**
     * 推荐单位来源模式（自动，手动，默认自动）
     */
    private String casemanagementmold;

    // 读的脚本
    private String readscripts;

    // 读的脚本
    private String limitscripts;
    private Integer jslyzj;//JSLYZJ	NUMBER	Y	0		技术领域专家
    private Integer hybmglry;//	number	y	0		行业部门管理人员
    private Integer qyjdb;//	number	y	0		企业家代表
    private Integer gllyzj;//	number	y	0		管理领域专家
    private Date lsqdksrq;//联审启动开始日期
    private Date lsqdjsrq;//联审启动结束日期
    /**
     * 评审机构
     */
    private String admindepartmentid;

    /**
     * 计划项目类别1
     */
    private String applicationtypecode1;

    /**
     * 计划项目类别2
     */
    private String applicationtypecode2;

    /**
     * 编码规则
     */
    private String numberarithmetic;
    /**
     * 顺序排序
     */
    private Integer seq;
    /**
     * 每个项目评审人数
     */
    private Integer perproject = 0;

    /**
     * 每个组人数
     */
    private Integer perteam = 0;

    /**
     * 通知人数
     */
    private Integer persms = 0;

    /**
     * 每个项目至少人数PERPROJECTLEAST
     */
    private Integer perprojectleast = 0;

    private Date pingshenstarttime;
    private Date pingshenendtime;

    private Date shapeontime;//形审到达时间
    private Date networkevaluationtime;//网评到达时间
    private Date firsttrialtime;//初审到达时间
    private Date unitedreviewtime;//联审到达时间
    private Date competitivereviewtime;//竞争性评议到达时间
    private String mainstatus;//批次主状态
    private Date recommendedunitendtime;//推荐单位截止时间
    private Date relyunitendtime;//依托单位截止时间
    private Date reconsiderendtime;//形审复议期截止时间，超过时间，处室不能形审，形审退回的项目也不能再上报
    private String olddata;//来源于老系统？old：null
    private Integer smsmul = 0;//通知人数比例
    private Integer expertitemnumber = 0;//通知人数比例


    private Date projectstartdate;//默认项目开始时间（业务管理：方便设置定向项目默认开始时间）
    private Date projectenddate;//默认项目结束时间（业务管理：方便设置定向项目默认结束时间）

    private Date collect_startdate;//指南征集-开始时间
    private Date collect_enddate;//指南征集-结束时间
    private Date collect_ytdwdate;//指南征集-依托单位截止审核时间
    private Date collect_tjdwdate;//指南征集-推荐单位截止审核时间
    private Date collect_xsdate;//指南征集-形审截止审核时间

    private Date tjhtjdate;//推荐函提交截止时间

    private String lxflag;//立项结果标记（空：表示还在拟立项中；1：表示已最终确认立项结果）
    private String topictype;//空字符串\专题\专题+方向\专题+方向+子方向

    private String isqzzb;//是否有强制指标，值：是 or 否 or 空（业务管理：说明未选择也没保存，当做"否"处理）  for Gz
    private String obligedtarget;//具体强制指标  for Gz
    private Date releasedate;//发布时间  for Gz
    private Double fundslimit;//经费额度  for Gz
    private String opencollect;//是否公开征集 for Gz
    private String releaseway;//下达方式 for Gz
    private String institutionid;//服务机构id for Gz
    private String innovatechain;//创新链条  for Gz
    private String supportway;//支持方式  for Gz
    private String applicationtypename1;//科技计划  for Gz
    private String belongcategory;
    private String msjxgsflag;//免申即享公示标记 1：需要公示 空：不需要公示
    private String tablename;
    private String message_sb;//第一次上报是否发短信
    private String year;//申请编号用的年份
    private String ccflag;//查重标记  空:未查重(初始状态) 1:查重中 2:查重完成
    private String sgsflag;//'市工商标记 1：调用中 2：完成'

//	private String versionhthtml;//批次关联版本的html(目前是针对合同版本)
//	private String versionhtjs;//批次关联版本的js(目前是针对合同版本)
//	private String projectfillurl;//申报填写路由
//	private String projectdetailurl;//申报查看路由
//	private String contractfillurl;//合同填写页面模板
//	private String contractdetailurl;//合同查看页面模板
//	private String midmanagecheckfillurl;//中期检查填写模版页面url
//	private String midmanagecheckdetailurl;//中期检查查看模版页面url
//	private String annualreportfillurl;//年度报告填写模版页面url
//	private String annualreportdetailurl;//年度报告查看模版页面url
//	private String keepcasefillurl ;//孵化器备案表填写路由
//	private String keepcasedetailurl;//孵化器备案表查看路由
//	private String casereportfillurl   ;//孵化器年报表填写路由
//	private String casereportdetailurl ;// 孵化器年报表查看路由
//	private String kjjlfillurl   ;//科技奖励填写路由
//	private String kjjldetailurl ;//科技奖励查看路由

//	private String versionhtid;//合同版本id
//	private String versionsbid;//申报版本id
//	private String versionzqjcid;//中期检查版本id
//	private String versionndbgid;//年度报告版本id
//	private String versionkjbgid;//科技报告版本id
//	private String versionysid;//验收模板id
//	private String versionkcid;//孵化器备案表模板id
//	private String versioncrid;//孵化器年报表模板id
//	private String versionkjjlid;//科技奖励模板id
//	private String versionfbxqid;//县科技局发布需求模板id
//	private String versionkjtpytjbid;//科技特派员推荐表模板id
//	private String versionkjtpyybid;//科技特派员月报模板id
//	private String versionkjtpycgfbid;//科技特派员成果发布id
//	private String versionkjtpypxid;//科技特派员培训计划id
//	private String versionkjtpygzzjid;//科技特派员工作总结id
//	private String versionsbtjhid;//申报推荐函模板id
//	private String versionkjcxptid;//科技创新平台id
//	private String versionkjcxptndbgid;//科技创新平台年度报告id
//	private String versionzyjgid;
//	private String versionzyjgkhbid;
//	private String versionjsjlrid;
//	private String versionysbid;//预申报版本id

    //是否参与形审。1表示参与，0或null表示不参与
    private Integer xs_necessary;

    /*//是否参与评审。1表示参与，0或null表示不参与*/
    //评审类型 1表示网评，2表示会评，3表示网评+会评，4表示会评加会评，0或null表示不参与
    private Integer ps_necessary;


    /**
     * 业务类型  补助bz  申报sb  平台pt
     */
    private String ywtype;

    /**
     * 是否已方向为主（值：是or否）
     */
    private String ismainfx;

    /**
     * 业务处室id
     */
    private String departmentid;

    /**
     * 系统类型（值：kjxm、kjcxpt...）
     */
    private String system;

    /**
     * 是否办事大厅显示；值：1表示显示；0 or空 表示不显示
     */
    private String ishallshow;


    /**
     * 平台年报开始时间
     */
    private Date ptnbstarttime;

    /**
     * 平台年报结束时间
     */
    private Date ptnbendtime;

    /**
     * 政务网办理code，有值说明是政务网跳转过来的业务；空表示是系统自身的业务
     */
    private String transactcode;

    /**
     * 编号规则
     */
    private String plantype;

    /**
     * 申报流水号编号规则
     */
    private String applicationCodeRule;

    /**
     * 流水号前缀
     */
    private String applicationnoprefix;

    /**
     * 基金专用，指标限制的合并批次id
     */
    private String limitbatchid;

    /*-----------------------维护政务网接口调用字段分割线-----------------------------*/

    /**
     *  政务网事项编码
     */
    @ApiModelProperty("政务网事项编码")
    @TableField("MATTER_CODE")
    @Column(columnDefinition = "MATTER_CODE")
    private String matterCode;

    /**
     *  政务网事项类别
     */
    @ApiModelProperty("政务网事项类别")
    @TableField("MATTER_TYPE")
    @Column(columnDefinition = "MATTER_TYPE")
    private String matterType;

    /**
     * 政务网办件类型
     */
    @ApiModelProperty("政务网办件类型")
    @TableField("HANDLE_DOCUMENT_TYPE")
    @Column(columnDefinition = "HANDLE_DOCUMENT_TYPE")
    private String handleDocumentType;

    /**
     * 政务网办件层级
     */
    @ApiModelProperty("政务网办件层级")
    @TableField("HANDLE_GRADE")
    @Column(columnDefinition = "HANDLE_GRADE")
    private String handleGrade;

    /**
     * 政务网业务申报系统
     */
    @ApiModelProperty("政务网业务申报系统")
    @TableField("BUSINESS_SYSTEM")
    @Column(columnDefinition = "BUSINESS_SYSTEM")
    private String businessSystem;

    /**
     * 政务网业务审批系统
     */
    @ApiModelProperty("政务网业务审批系统")
    @TableField("BUSINESS_EXAMINE_SYSTEM")
    @Column(columnDefinition = "BUSINESS_EXAMINE_SYSTEM")
    private String businessExamineSystem;

    /**
     *  事项所属区划
     */
    @ApiModelProperty("事项所属区划")
    @TableField("ITEM_REGION_CODE")
    @Column(columnDefinition = "ITEM_REGION_CODE")
    private String itemRegionCode;

    /**
     *  事项所属区划名称
     */
    @ApiModelProperty("事项所属区划名称")
    @TableField("ITEM_REGION_NAME")
    @Column(columnDefinition = "ITEM_REGION_NAME")
    private String itemRegionName;

    /**
     *  事项所属部门
     */
    @ApiModelProperty("事项所属部门")
    @TableField("ITEM_ORGAN_CODE")
    @Column(columnDefinition = "ITEM_ORGAN_CODE")
    private String itemOrganCode;

    /**
     *  事项所属部门名称
     */
    @ApiModelProperty("事项所属部门名称")
    @TableField("ITEM_ORGAN_NAME")
    @Column(columnDefinition = "ITEM_ORGAN_NAME")
    private String itemOrganName;

    /**
     *  事项办理承诺时限
     */
    @ApiModelProperty("事项办理承诺时限")
    @TableField("PROMISETIME_LIMIT")
    @Column(columnDefinition = "PROMISETIME_LIMIT")
    @FieldDes(name = "promisetime_limit", type = "Integer", memo = "事项办理承诺时限")
    private Integer promisetimeLimit;

    /**
     *  事项办理承诺时限单位
     */
    @ApiModelProperty("事项办理承诺时限单位")
    @TableField("PROMISETIME_UNIT")
    @Column(columnDefinition = "PROMISETIME_UNIT")
    private String promisetimeUnit;

    /**
     *  法定时限
     */
    @ApiModelProperty("法定时限")
    @TableField("TIME_LIMIT")
    @Column(columnDefinition = "TIME_LIMIT")
    @FieldDes(name = "time_limit", type = "Integer", memo = "法定时限")
    private Integer timeLimit;

    /**
     *  规定办理时限的单位
     */
    @ApiModelProperty("规定办理时限的单位")
    @TableField("TIME_UNIT")
    @Column(columnDefinition = "TIME_UNIT")
    private String timeUnit;

    /**
     * 归口单位
     */
    @ApiModelProperty("归口单位")
    @TableField("ORGANIZE_ID")
    @Column(columnDefinition = "ORGANIZE_ID")
    private String organizeId;

    /**
     * 归口父级单位ID
     */
    @ApiModelProperty("归口父级单位ID")
    @TableField("ORGANIZE_PARENT_ID")
    @Column(columnDefinition = "ORGANIZE_PARENT_ID")
    private String organizeParentId;

    /**
     * 访问权限
     *
     * @return
     */
    @ApiModelProperty("访问权限")
    @TableField("ROLE_IDS")
    @Column(columnDefinition = "ROLE_IDS")
    private String roleIds;

    /**
     * 创建用户id
     * @return
     */
    @ApiModelProperty("创建用户id")
    @TableField("CREATE_USER")
    @Column(columnDefinition = "CREATE_USER")
    private String createUser;

    /*----------------------------------分割线----------------------------------*/


    @Id
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * 名称
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getXingshenusers() {
        return xingshenusers;
    }

    public void setXingshenusers(String xingshenusers) {
        this.xingshenusers = xingshenusers;
    }

    /**
     * 开始时间
     */

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 结束时间
     */

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 说明
     */

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    /**
     * 备注
     */

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 计划项目类别ID
     */

    @ManyToOne
    @JoinColumn(name = "PLANPROJECTID")
    public PmsPlanproject getPmsPlanproject() {
        return pmsPlanproject;
    }

    public void setPmsPlanproject(PmsPlanproject pmsPlanproject) {
        this.pmsPlanproject = pmsPlanproject;
    }

    /**
     * 当前状态
     */

    public String getCurrentstate() {
        return currentstate;
    }

    public void setCurrentstate(String currentstate) {
        this.currentstate = currentstate;
    }


    public String getReadscripts() {
        return readscripts;
    }

    public void setReadscripts(String readscripts) {
        this.readscripts = readscripts;
    }


    public String getLimitscripts() {
        return limitscripts;
    }

    public void setLimitscripts(String limitscripts) {
        this.limitscripts = limitscripts;
    }

    public Date getLsqdksrq() {
        return lsqdksrq;
    }

    public void setLsqdksrq(Date lsqdksrq) {
        this.lsqdksrq = lsqdksrq;
    }

    public Date getLsqdjsrq() {
        return lsqdjsrq;
    }

    public void setLsqdjsrq(Date lsqdjsrq) {
        this.lsqdjsrq = lsqdjsrq;
    }

    public Integer getJslyzj() {
        return jslyzj;
    }

    public void setJslyzj(Integer jslyzj) {
        this.jslyzj = jslyzj;
    }

    public Integer getHybmglry() {
        return hybmglry;
    }

    public void setHybmglry(Integer hybmglry) {
        this.hybmglry = hybmglry;
    }

    public Integer getQyjdb() {
        return qyjdb;
    }

    public void setQyjdb(Integer qyjdb) {
        this.qyjdb = qyjdb;
    }

    public Integer getGllyzj() {
        return gllyzj;
    }

    public void setGllyzj(Integer gllyzj) {
        this.gllyzj = gllyzj;
    }

    /**
     * 年度
     */

    public String getAnnually() {
        return annually;
    }

    public void setAnnually(String annually) {
        this.annually = annually;
    }

    /*
     * public PmsDeclarationguide getPmsDeclarationguide() { return
     * this.pmsDeclarationguide; }
     */

    /**
     * 开放用户
     */

    public String getOpenuser() {
        return openuser;
    }

    public void setOpenuser(String openuser) {
        this.openuser = openuser;
    }

    /**
     * 是否网上签约
     */

    public String getIssignonline() {
        return issignonline;
    }

    public void setIssignonline(String issignonline) {
        this.issignonline = issignonline;
    }

    /**
     * 模板ID
     */

    public String getApplicationtableexplain() {
        return applicationtableexplain;
    }

    public void setApplicationtableexplain(String applicationtableexplain) {
        this.applicationtableexplain = applicationtableexplain;
    }

    /**
     * 开发用户代码
     */

    public String getOpenusercode() {
        return openusercode;
    }

    public void setOpenusercode(String openusercode) {
        this.openusercode = openusercode;
    }

    /**
     * 申报指南ID
     */

    @ManyToOne
    @JoinColumn(name = "DECLARATIONGUIDEID")
    public PmsDeclarationguide getPmsDeclarationguide() {
        return pmsDeclarationguide;
    }

    public void setPmsDeclarationguide(PmsDeclarationguide pmsDeclarationguide) {
        this.pmsDeclarationguide = pmsDeclarationguide;
    }


    public String getAdmindepartmentid() {
        return admindepartmentid;
    }

    public void setAdmindepartmentid(String admindepartmentid) {
        this.admindepartmentid = admindepartmentid;
    }


    public String getApplicationtypecode1() {
        return applicationtypecode1;
    }

    public void setApplicationtypecode1(String applicationtypecode1) {
        this.applicationtypecode1 = applicationtypecode1;
    }


    public String getApplicationtypecode2() {
        return applicationtypecode2;
    }

    public void setApplicationtypecode2(String applicationtypecode2) {
        this.applicationtypecode2 = applicationtypecode2;
    }


    public String getNumberarithmetic() {
        return numberarithmetic;
    }

    public void setNumberarithmetic(String numberarithmetic) {
        this.numberarithmetic = numberarithmetic;
    }


    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getPerproject() {
        return perproject;
    }

    public void setPerproject(Integer perproject) {
        this.perproject = perproject;
    }

    public Integer getPerteam() {
        return perteam;
    }

    public void setPerteam(Integer perteam) {
        this.perteam = perteam;
    }

    public Integer getPersms() {
        return persms;
    }

    public void setPersms(Integer persms) {
        this.persms = persms;
    }

    public Integer getPerprojectleast() {
        return perprojectleast;
    }

    public void setPerprojectleast(Integer perprojectleast) {
        this.perprojectleast = perprojectleast;
    }


    public String getOlddata() {
        return olddata;
    }

    public void setOlddata(String olddata) {
        this.olddata = olddata;
    }

    public Date getRecommendedunitendtime() {
        return recommendedunitendtime;
    }

    public void setRecommendedunitendtime(Date recommendedunitendtime) {
        this.recommendedunitendtime = recommendedunitendtime;
    }

    public Date getRelyunitendtime() {
        return relyunitendtime;
    }

    public void setRelyunitendtime(Date relyunitendtime) {
        this.relyunitendtime = relyunitendtime;
    }

    public String getMainstatus() {
        return mainstatus;
    }

    public void setMainstatus(String mainstatus) {
        this.mainstatus = mainstatus;
    }

    public Date getShapeontime() {
        return shapeontime;
    }

    public void setShapeontime(Date shapeontime) {
        this.shapeontime = shapeontime;
    }

    public Date getNetworkevaluationtime() {
        return networkevaluationtime;
    }

    public void setNetworkevaluationtime(Date networkevaluationtime) {
        this.networkevaluationtime = networkevaluationtime;
    }

    public Date getFirsttrialtime() {
        return firsttrialtime;
    }

    public void setFirsttrialtime(Date firsttrialtime) {
        this.firsttrialtime = firsttrialtime;
    }

    public Date getUnitedreviewtime() {
        return unitedreviewtime;
    }

    public void setUnitedreviewtime(Date unitedreviewtime) {
        this.unitedreviewtime = unitedreviewtime;
    }

    public Date getCompetitivereviewtime() {
        return competitivereviewtime;
    }

    public void setCompetitivereviewtime(Date competitivereviewtime) {
        this.competitivereviewtime = competitivereviewtime;
    }

    public Date getPingshenstarttime() {
        if (pingshenstarttime != null) {
            return Util.parseDate(pingshenstarttime.toString(), "yyyy-MM-dd");
        } else {
            return pingshenstarttime;
        }
    }

    public void setPingshenstarttime(Date pingshenstarttime) {
        this.pingshenstarttime = pingshenstarttime;
    }

    public Date getPingshenendtime() {
        if (pingshenendtime != null) {
            return Util.parseDate(pingshenendtime.toString(), "yyyy-MM-dd");
        } else {
            return pingshenendtime;
        }
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public void setPingshenendtime(Date pingshenendtime) {
        this.pingshenendtime = pingshenendtime;
    }


    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }


    public String getIssb() {
        return issb;
    }

    public void setIssb(String issb) {
        this.issb = issb;
    }

    public String getSblimitflag() {
        return sblimitflag;
    }

    public void setSblimitflag(String sblimitflag) {
        this.sblimitflag = sblimitflag;
    }

    public String getShlimitflag() {
        return shlimitflag;
    }

    public void setShlimitflag(String shlimitflag) {
        this.shlimitflag = shlimitflag;
    }

    public String getAvoidtype() {
        return avoidtype;
    }

    public void setAvoidtype(String avoidtype) {
        this.avoidtype = avoidtype;
    }


    public Date getReconsiderendtime() {
        return reconsiderendtime;
    }

    public void setReconsiderendtime(Date reconsiderendtime) {
        this.reconsiderendtime = reconsiderendtime;
    }

    public String getXtwys_hrefname() {
        return xtwys_hrefname;
    }

    public void setXtwys_hrefname(String xtwys_hrefname) {
        this.xtwys_hrefname = xtwys_hrefname;
    }

    public String getXtwys_href() {
        return xtwys_href;
    }

    public void setXtwys_href(String xtwys_href) {
        this.xtwys_href = xtwys_href;
    }

    public String getNocheck() {
        return nocheck;
    }

    public void setNocheck(String nocheck) {
        this.nocheck = nocheck;
    }

    public String getBatchreviewid() {
        return batchreviewid;
    }

    public void setBatchreviewid(String batchreviewid) {
        this.batchreviewid = batchreviewid;
    }


    public String getFrontno() {
        return frontno;
    }

    public void setFrontno(String frontno) {
        this.frontno = frontno;
    }

    public Date getCrt_startdate() {
        return crt_startdate;
    }

    public void setCrt_startdate(Date crt_startdate) {
        this.crt_startdate = crt_startdate;
    }

    public Date getCrt_enddate() {
        return crt_enddate;
    }

    public void setCrt_enddate(Date crt_enddate) {
        this.crt_enddate = crt_enddate;
    }

    public String getOverage() {
        return overage;
    }

    public void setOverage(String overage) {
        this.overage = overage;
    }

    public String getOtherdepartmentid() {
        return otherdepartmentid;
    }

    public void setOtherdepartmentid(String otherdepartmentid) {
        this.otherdepartmentid = otherdepartmentid;
    }

    public String getIscanhtchange() {
        return iscanhtchange;
    }

    public void setIscanhtchange(String iscanhtchange) {
        this.iscanhtchange = iscanhtchange;
    }

    public Integer getSmsmul() {
        return smsmul;
    }

    public void setSmsmul(Integer smsmul) {
        this.smsmul = smsmul;
    }

    public Integer getExpertitemnumber() {
        return expertitemnumber;
    }

    public void setExpertitemnumber(Integer expertitemnumber) {
        this.expertitemnumber = expertitemnumber;
    }

    public String getAddpagenum() {
        return addpagenum;
    }

    public void setAddpagenum(String addpagenum) {
        this.addpagenum = addpagenum;
    }

    public String getIshavejdbg() {
        return ishavejdbg;
    }

    public void setIshavejdbg(String ishavejdbg) {
        this.ishavejdbg = ishavejdbg;
    }


    public Date getModelupdatetime() {
        return modelupdatetime;
    }

    public void setModelupdatetime(Date modelupdatetime) {
        this.modelupdatetime = modelupdatetime;
    }


    public Date getHbz_startdate() {
        return hbz_startdate;
    }

    public void setHbz_startdate(Date hbz_startdate) {
        this.hbz_startdate = hbz_startdate;
    }

    public Date getHbz_enddate() {
        return hbz_enddate;
    }

    public void setHbz_enddate(Date hbz_enddate) {
        this.hbz_enddate = hbz_enddate;
    }

    public Date getFbxq_startdate() {
        return fbxq_startdate;
    }

    public void setFbxq_startdate(Date fbxq_startdate) {
        this.fbxq_startdate = fbxq_startdate;
    }

    public Date getFbxq_enddate() {
        return fbxq_enddate;
    }

    public void setFbxq_enddate(Date fbxq_enddate) {
        this.fbxq_enddate = fbxq_enddate;
    }


    public String getExportmethod() {
        return exportmethod;
    }

    public void setExportmethod(String exportmethod) {
        this.exportmethod = exportmethod;
    }


    public Date getKjtpypx_startdate() {
        return kjtpypx_startdate;
    }

    public void setKjtpypx_startdate(Date kjtpypx_startdate) {
        this.kjtpypx_startdate = kjtpypx_startdate;
    }

    public Date getKjtpypx_enddate() {
        return kjtpypx_enddate;
    }

    public void setKjtpypx_enddate(Date kjtpypx_enddate) {
        this.kjtpypx_enddate = kjtpypx_enddate;
    }


    public String getAuditdepartment() {
        return auditdepartment;
    }

    public void setAuditdepartment(String auditdepartment) {
        this.auditdepartment = auditdepartment;
    }

    public String getXsfsflag() {
        return xsfsflag;
    }

    public void setXsfsflag(String xsfsflag) {
        this.xsfsflag = xsfsflag;
    }

    public String getCb_auditdepartment() {
        return cb_auditdepartment;
    }

    public void setCb_auditdepartment(String cb_auditdepartment) {
        this.cb_auditdepartment = cb_auditdepartment;
    }


    public Date getYs_startdate() {
        return ys_startdate;
    }

    public void setYs_startdate(Date ys_startdate) {
        this.ys_startdate = ys_startdate;
    }

    public Date getYs_enddate() {
        return ys_enddate;
    }

    public void setYs_enddate(Date ys_enddate) {
        this.ys_enddate = ys_enddate;
    }


    public Boolean getCnsflag() {
        return cnsflag;
    }

    public void setCnsflag(Boolean cnsflag) {
        this.cnsflag = cnsflag;
    }

    public String getCasemanagementmold() {
        return casemanagementmold;
    }

    public void setCasemanagementmold(String casemanagementmold) {
        this.casemanagementmold = casemanagementmold;
    }

    public String getIsoldsystem() {
        return isoldsystem;
    }

    public void setIsoldsystem(String isoldsystem) {
        this.isoldsystem = isoldsystem;
    }


    public String getNewplanprojectid() {
        return newplanprojectid;
    }

    public void setNewplanprojectid(String newplanprojectid) {
        this.newplanprojectid = newplanprojectid;
    }

    public String getAffixtype() {
        return affixtype;
    }

    public void setAffixtype(String affixtype) {
        this.affixtype = affixtype;
    }


    public String getYzphone() {
        return yzphone;
    }

    public void setYzphone(String yzphone) {
        this.yzphone = yzphone;
    }

    public String getBroseruser() {
        return broseruser;
    }

    public void setBroseruser(String broseruser) {
        this.broseruser = broseruser;
    }


    public Double getMortality() {
        return mortality;
    }

    public void setMortality(Double mortality) {
        this.mortality = mortality;
    }

    public String getExpertscoreresult_no() {
        return expertscoreresult_no;
    }

    public void setExpertscoreresult_no(String expertscoreresult_no) {
        this.expertscoreresult_no = expertscoreresult_no;
    }

    public String getExpertscoreresult() {
        return expertscoreresult;
    }

    public void setExpertscoreresult(String expertscoreresult) {
        this.expertscoreresult = expertscoreresult;
    }


    public Boolean getAppliylimit() {
        return appliylimit;
    }

    public void setAppliylimit(Boolean appliyLimit) {
        appliylimit = appliyLimit;
    }

    public String getMessagemodel() {
        return Messagemodel;
    }

    public void setMessagemodel(String messagemodel) {
        Messagemodel = messagemodel;
    }

    public String getCity() {
        return city;
    }


    public String getHtmodelname() {
        return htmodelname;
    }

    public void setHtmodelname(String htmodelname) {
        this.htmodelname = htmodelname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSMSAttend() {
        return SMSAttend;
    }

    public void setSMSAttend(String SMSAttend) {
        this.SMSAttend = SMSAttend;
    }

    public String getSMSNotice() {
        return SMSNotice;
    }

    public void setSMSNotice(String SMSNotice) {
        this.SMSNotice = SMSNotice;
    }

    public String getSMSAccount() {
        return SMSAccount;
    }

    public void setSMSAccount(String SMSAccount) {
        this.SMSAccount = SMSAccount;
    }


    public String getModeloraffixht() {
        return modeloraffixht;
    }

    public void setModeloraffixht(String modeloraffixht) {
        this.modeloraffixht = modeloraffixht;
    }

    public String getPdfurlforcontract() {
        return pdfurlforcontract;
    }

    public String getPdfurlforbase() {
        return pdfurlforbase;
    }

    public void setPdfurlforcontract(String pdfurlforcontract) {
        this.pdfurlforcontract = pdfurlforcontract;
    }

    public void setPdfurlforbase(String pdfurlforbase) {
        this.pdfurlforbase = pdfurlforbase;
    }

    public String getPdfdowntypeforcontract() {
        return pdfdowntypeforcontract;
    }

    public void setPdfdowntypeforcontract(String pdfdowntypeforcontract) {
        this.pdfdowntypeforcontract = pdfdowntypeforcontract;
    }

    public String getPdfdowntypeforbase() {
        return pdfdowntypeforbase;
    }

    public void setPdfdowntypeforbase(String pdfdowntypeforbase) {
        this.pdfdowntypeforbase = pdfdowntypeforbase;
    }

    public String getModeloraffix() {
        return modeloraffix;
    }

    public void setModeloraffix(String modeloraffix) {
        this.modeloraffix = modeloraffix;
    }


    public String getXcstatus() {
        return xcstatus;
    }

    public void setXcstatus(String xcstatus) {
        this.xcstatus = xcstatus;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getReviewexplain() {
        return reviewexplain;
    }

    public void setReviewexplain(String reviewexplain) {
        this.reviewexplain = reviewexplain;
    }


    public String getUndertaking() {
        return undertaking;
    }

    public void setUndertaking(String undertaking) {
        this.undertaking = undertaking;
    }

    public String getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(String isdisplay) {
        this.isdisplay = isdisplay;
    }


    public String getIsstartuniteapprove() {
        return isstartuniteapprove;
    }

    public void setIsstartuniteapprove(String isstartuniteapprove) {
        this.isstartuniteapprove = isstartuniteapprove;
    }


    public String getEnterpriseparentidex() {
        return enterpriseparentidex;
    }

    public void setEnterpriseparentidex(String enterpriseparentidex) {
        this.enterpriseparentidex = enterpriseparentidex;
    }

    public String getEnterpriselimit() {
        return enterpriselimit;
    }

    public void setEnterpriselimit(String enterpriselimit) {
        this.enterpriselimit = enterpriselimit;
    }

    public String getWorkflowdefineid() {
        return workflowdefineid;
    }

    public void setWorkflowdefineid(String workflowdefineid) {
        this.workflowdefineid = workflowdefineid;
    }


    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Double getFundslimit() {
        return fundslimit;
    }

    public void setFundslimit(Double fundslimit) {
        this.fundslimit = fundslimit;
    }

    public String getIsqzzb() {
        return isqzzb;
    }

    public void setIsqzzb(String isqzzb) {
        this.isqzzb = isqzzb;
    }

    public String getObligedtarget() {
        return obligedtarget;
    }

    public void setObligedtarget(String obligedtarget) {
        this.obligedtarget = obligedtarget;
    }

    public Date getProjectstartdate() {
        return projectstartdate;
    }

    public void setProjectstartdate(Date projectstartdate) {
        this.projectstartdate = projectstartdate;
    }

    public Date getProjectenddate() {
        return projectenddate;
    }

    public void setProjectenddate(Date projectenddate) {
        this.projectenddate = projectenddate;
    }

    public Date getCollect_startdate() {
        return collect_startdate;
    }

    public void setCollect_startdate(Date collect_startdate) {
        this.collect_startdate = collect_startdate;
    }

    public Date getCollect_enddate() {
        return collect_enddate;
    }

    public void setCollect_enddate(Date collect_enddate) {
        this.collect_enddate = collect_enddate;
    }

    public Date getCollect_ytdwdate() {
        return collect_ytdwdate;
    }

    public void setCollect_ytdwdate(Date collect_ytdwdate) {
        this.collect_ytdwdate = collect_ytdwdate;
    }

    public Date getCollect_tjdwdate() {
        return collect_tjdwdate;
    }

    public void setCollect_tjdwdate(Date collect_tjdwdate) {
        this.collect_tjdwdate = collect_tjdwdate;
    }

    public Date getCollect_xsdate() {
        return collect_xsdate;
    }

    public void setCollect_xsdate(Date collect_xsdate) {
        this.collect_xsdate = collect_xsdate;
    }

    public String getLxflag() {
        return lxflag;
    }

    public void setLxflag(String lxflag) {
        this.lxflag = lxflag;
    }

    public String getTopictype() {
        return topictype;
    }

    public void setTopictype(String topictype) {
        this.topictype = topictype;
    }

    public String getOpencollect() {
        return opencollect;
    }

    public void setOpencollect(String opencollect) {
        this.opencollect = opencollect;
    }

    public String getReleaseway() {
        return releaseway;
    }

    public void setReleaseway(String releaseway) {
        this.releaseway = releaseway;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getInnovatechain() {
        return innovatechain;
    }

    public void setInnovatechain(String innovatechain) {
        this.innovatechain = innovatechain;
    }

    public String getSupportway() {
        return supportway;
    }

    public void setSupportway(String supportway) {
        this.supportway = supportway;
    }

    public String getApplicationtypename1() {
        return applicationtypename1;
    }

    public void setApplicationtypename1(String applicationtypename1) {
        this.applicationtypename1 = applicationtypename1;
    }

    public String getBelongcategory() {
        return belongcategory;
    }

    public void setBelongcategory(String belongcategory) {
        this.belongcategory = belongcategory;
    }

    public String getMsjxgsflag() {
        return msjxgsflag;
    }

    public void setMsjxgsflag(String msjxgsflag) {
        this.msjxgsflag = msjxgsflag;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getMessage_sb() {
        return message_sb;
    }

    public void setMessage_sb(String message_sb) {
        this.message_sb = message_sb;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCcflag() {
        return ccflag;
    }

    public void setCcflag(String ccflag) {
        this.ccflag = ccflag;
    }

    public String getSgsflag() {
        return sgsflag;
    }

    public void setSgsflag(String sgsflag) {
        this.sgsflag = sgsflag;
    }


    public Integer getXs_necessary() {
        return xs_necessary;
    }

    public void setXs_necessary(Integer xs_necessary) {
        this.xs_necessary = xs_necessary;
    }

    public Integer getPs_necessary() {
        return ps_necessary;
    }

    public void setPs_necessary(Integer ps_necessary) {
        this.ps_necessary = ps_necessary;
    }

    public String getYwtype() {
        return ywtype;
    }

    public void setYwtype(String ywtype) {
        this.ywtype = ywtype;
    }

    public String getIsmainfx() {
        return ismainfx;
    }

    public void setIsmainfx(String ismainfx) {
        this.ismainfx = ismainfx;
    }

    public String getIscankjcxptchange() {
        return iscankjcxptchange;
    }

    public void setIscankjcxptchange(String iscankjcxptchange) {
        this.iscankjcxptchange = iscankjcxptchange;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIshallshow() {
        return ishallshow;
    }

    public void setIshallshow(String ishallshow) {
        this.ishallshow = ishallshow;
    }


    public Date getPtnbstarttime() {
        return ptnbstarttime;
    }

    public void setPtnbstarttime(Date ptnbstarttime) {
        this.ptnbstarttime = ptnbstarttime;
    }

    public Date getPtnbendtime() {
        return ptnbendtime;
    }

    public void setPtnbendtime(Date ptnbendtime) {
        this.ptnbendtime = ptnbendtime;
    }

    public String getTransactcode() {
        return transactcode;
    }

    public void setTransactcode(String transactcode) {
        this.transactcode = transactcode;
    }

    public String getPlantype() {
        return plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }

    public String getApplicationnoprefix() {
        return applicationnoprefix;
    }

    public void setApplicationnoprefix(String applicationnoprefix) {
        this.applicationnoprefix = applicationnoprefix;
    }

    public String getLimitbatchid() {
        return limitbatchid;
    }

    public void setLimitbatchid(String limitbatchid) {
        this.limitbatchid = limitbatchid;
    }

    public Date getTjhtjdate() {
        return tjhtjdate;
    }

    public void setTjhtjdate(Date tjhtjdate) {
        this.tjhtjdate = tjhtjdate;
    }

    public String getMatterCode() {
        return matterCode;
    }

    public void setMatterCode(String matterCode) {
        this.matterCode = matterCode;
    }

    public String getMatterType() {
        return matterType;
    }

    public void setMatterType(String matterType) {
        this.matterType = matterType;
    }

    public String getHandleDocumentType() {
        return handleDocumentType;
    }

    public void setHandleDocumentType(String handleDocumentType) {
        this.handleDocumentType = handleDocumentType;
    }

    public String getHandleGrade() {
        return handleGrade;
    }

    public void setHandleGrade(String handleGrade) {
        this.handleGrade = handleGrade;
    }

    public String getBusinessSystem() {
        return businessSystem;
    }

    public void setBusinessSystem(String businessSystem) {
        this.businessSystem = businessSystem;
    }

    public String getBusinessExamineSystem() {
        return businessExamineSystem;
    }

    public void setBusinessExamineSystem(String businessExamineSystem) {
        this.businessExamineSystem = businessExamineSystem;
    }

    public String getItemRegionCode() {
        return itemRegionCode;
    }

    public void setItemRegionCode(String itemRegionCode) {
        this.itemRegionCode = itemRegionCode;
    }

    public String getItemRegionName() {
        return itemRegionName;
    }

    public void setItemRegionName(String itemRegionName) {
        this.itemRegionName = itemRegionName;
    }

    public String getItemOrganCode() {
        return itemOrganCode;
    }

    public void setItemOrganCode(String itemOrganCode) {
        this.itemOrganCode = itemOrganCode;
    }

    public String getItemOrganName() {
        return itemOrganName;
    }

    public void setItemOrganName(String itemOrganName) {
        this.itemOrganName = itemOrganName;
    }

    public Integer getPromisetimeLimit() {
        return promisetimeLimit;
    }

    public void setPromisetimeLimit(Integer promisetimeLimit) {
        this.promisetimeLimit = promisetimeLimit;
    }

    public String getPromisetimeUnit() {
        return promisetimeUnit;
    }

    public void setPromisetimeUnit(String promisetimeUnit) {
        this.promisetimeUnit = promisetimeUnit;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeParentId() {
        return organizeParentId;
    }

    public void setOrganizeParentId(String organizeParentId) {
        this.organizeParentId = organizeParentId;
    }

    public String getApplicationCodeRule() {
        return applicationCodeRule;
    }

    public void setApplicationCodeRule(String applicationCodeRule) {
        this.applicationCodeRule = applicationCodeRule;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}

