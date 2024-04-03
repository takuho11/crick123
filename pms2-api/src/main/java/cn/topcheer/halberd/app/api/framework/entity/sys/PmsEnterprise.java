/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2016-1-27 15:53:46
 */
package cn.topcheer.halberd.app.api.framework.entity.sys;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业
 */
@Entity
@Table(name = "PMS_ENTERPRISE")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PmsEnterprise implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @Id
    private String id;
    /**
     * 企业关系层级
     */
    @Transient
    private String enterpriseadminparent;
    // 机构树整理更新
    private String bcgx;
    /**
     * 推荐单位代码
     */
    private String tjdwcode;
    /**
     * 名称
     */
    private String name;
    /**
     * 院所名称
     */
    private String institutesname;
    /**
     * 归口单位\上级部门名称
     */
    private String adminname;
    /**
     * 归口单位编码\上级部门代码
     */
    private String admincode;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 银行
     */
    private String bank;
    //单位类型，网上申报开放用户选择用
    private String enterprisetype;
    // 所在市
    private String belongcity;
    // 所在县
    private String belongcountry;

    /**
     * 城市名称
     */
    private String cityname;
    /**
     * 状态（0-有效，1-无效）
     */
    private Boolean state;
    /**
     * 简称
     */
    private String shortname;
    private String organizationhead;
    private Integer zjkseq;
    private String dwdm;
    //	@Transient
    @Column(name = "ENTERPRISETYPENAME")
    private String enterpriseTypeName;
    /**
     * 法人代码
     */
    private String certificate;
    @Column(name = "COMEFROMYPT")
    private String comeFromYpt; // 从云平台过来的标志
    /**
     * 邮编
     */
    private String postalcode;
    /**
     * 联系人
     */
    private String linkman;
    // 注册时间
    private Date registerdate;
    // 工商局注册时间
    private Date registertime;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 传真
     */
    private String fax;
    /**
     * 联系人手机
     */
    private String mobile;
    /**
     * 银行账号
     */
    private String bankaccount;
    /**
     * 企业类型（高等院校；2、科研院所；3、企业；4、其他）
     */
    private String unittype;
    /**
     * 科技厅审核标志（1：审核通过；0：审核不通过；空：审核中）--hlj
     */
    private Boolean signed;
    /**
     * 组织机构代码
     */
    private String orginizationcode;
    /**
     * 统一代码
     */
    private String uniformcode;
    /**
     * 法人代表
     */
    private String legalrepresentative;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 银行名称
     */
    private String bankname;
    /**
     * 项目id
     */
    private String projectbaseid;


    /**
     * 管理部门编码
     */
    private String departmentcode;
    //所属归口部门类型（N经过二级归口单位，H直接到一级归口单位）
    @Column(name = "ADMINUNITTYPE")
    private String adminUnitType;
    /**
     * 资产总额
     */
    private Double totalassets;
    /**
     * 本单位归口部门代码
     */
    private String managecode;

    private String schoolname;

    private String collegename;
    @OneToMany(mappedBy = "pmsEnterprise", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<SysIdentity> sysIdentitys;
    /**
     * 上级单位(地域行政上级)
     */
    @OneToMany(mappedBy = "pmsParentEnterprise", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises;

    /**
     * 上级单位(地域行政上级)
     */
    @ManyToOne
//			(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID")
    @NotFound(action = NotFoundAction.IGNORE)
    private PmsEnterprise pmsParentEnterprise;

    @OneToMany(mappedBy = "pmsEnterprise", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsDepartment> pmsDepartments;

    @Transient
    private String leader;//负责人
    /**
     * 上级单位(其他,自行定义吧)
     */
    @OneToMany(mappedBy = "pmsParentEnterprise2", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises2;
    @OneToMany(mappedBy = "pmsParentEnterprise3", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises3;
    @OneToMany(mappedBy = "pmsParentEnterprise4", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises4;
    @OneToMany(mappedBy = "pmsParentEnterprise5", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises5;
    @OneToMany(mappedBy = "pmsParentEnterprise6", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises6;
    @OneToMany(mappedBy = "pmsParentEnterprise7", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises7;
    @OneToMany(mappedBy = "pmsParentEnterprise8", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises8;
    @OneToMany(mappedBy = "pmsParentEnterprise9", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises9;
    @OneToMany(mappedBy = "pmsParentEnterprise10", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises10;
    @OneToMany(mappedBy = "pmsParentEnterprise11", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises11;
    @OneToMany(mappedBy = "pmsParentEnterprise12", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises12;
    @OneToMany(mappedBy = "pmsParentEnterprise13", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsChildEnterprises13;
    /**
     * 上级单位(其他,自行定义吧)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID2")
    private PmsEnterprise pmsParentEnterprise2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID3")
    private PmsEnterprise pmsParentEnterprise3;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID4")
    private PmsEnterprise pmsParentEnterprise4;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID5")
    private PmsEnterprise pmsParentEnterprise5;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID6")
    private PmsEnterprise pmsParentEnterprise6;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID7")
    private PmsEnterprise pmsParentEnterprise7;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID8")
    private PmsEnterprise pmsParentEnterprise8;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID9")
    private PmsEnterprise pmsParentEnterprise9;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID10")
    private PmsEnterprise pmsParentEnterprise10;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID11")
    private PmsEnterprise pmsParentEnterprise11;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID12")
    private PmsEnterprise pmsParentEnterprise12;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTID13")
    private PmsEnterprise pmsParentEnterprise13;
    private String locationcode;
    @OneToMany(mappedBy = "pmsSubEnterprises", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<PmsEnterprise> pmsSubEnterprises;
    /**
     * 总公司(子公司，附属子机构该值有效)
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEADOFFICEID")
    private PmsEnterprise pmsHeadEnterprise;

    private String legalName;    //法定代表人姓名--hlj
    private String legalSex;    //法定代表人性别--hlj
    private String legalEducation;    //法定代表人学历--hlj
    private String legalSpecialty;    //法定代表人专业--hlj
    private String legalPosition;    //法定代表人职务--hlj
    private String legalIdcard;    //法定代表人身份证--hlj
    private String legalIdtype;    //法定代表人证件类型--hlj
    private String legalPhone;    //法定代表人手机--hlj
    private String legalTel;    //法定代表人座机--hlj
    private String legalEmail;    //法定代表人邮箱--hlj
    private String legalTitle;    //法定代表人职称--hlj
    private String legalTitleseries;    //法定代表人职称系列--hlj
    private String legalTitlename;    //法定代表人职称名字--hlj
    private String treasuryName;    //财务部门负责人姓名--hlj
    private String treasuryTel;    //财务部门负责人座机--hlj
    private String treasuryPhone;    //财务部门负责人手机--hlj
    private String creditrating;    //银行信用等级--hlj
    private String scidept;    //科研管理部门--hlj
    private String scideptTel;    //科研管理部门电话--hlj
    private String scideptName;    //科研管理部门负责人--hlj
    private String scideptPhone;    //科研管理部门负责人手机--hlj
    private String scideptEmail;    //科研管理部门负责人邮箱--hlj
    private String industry;    //单位所属行业--hlj
    private String affiliation;    //隶属关系---hlj
    private String bankinterbank;//银行联号---hlj
    private String judgefield;//判断必填字段是否完整，空：未完整，1：完整--hlj
    private String webpage;//网站主页 --hlj
    private String introduction;//网站主页 --hlj
    private String accountname;//单位开户名称--hlj
    private String istmzjdw;//提名专家（校长，院所长）注册时候可以选择的单位，1：校长选的单位；2：院所长选的单位
    private String superiorunitid;//主管科技部门id，关联pms_enterprise表的id
    private String superiorunitlevel;//主管科技部门级别：省，市，县
    private String unitlevel;//自身的级别：省，市，县
    private String countryname;//县级名称
    private Double sciencescore;//诚信分数
    private Date unitestablishedate; //单位成立时间
    private String selflevel;//是否是高校本级 1：是  空：否
    private String belongnation;//所属国家
    private String isindependentlegalman;//是否是独立法人
    private String isotherunittype;//是否港澳台、外籍单位enterprisepms
    private Date gsjkdate;
    /**
     *  县推荐单位(二级归口)
     */
    @ApiModelProperty("县推荐单位")
    @TableField("COUNTYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENT")
    private String countycasemanagement;

    /**
     *  县推荐单位ID(二级归口)
     */
    @ApiModelProperty("县推荐单位ID")
    @TableField("COUNTYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENTID")
    private String countycasemanagementid;

    /**
     *  市推荐单位(一级归口)
     */
    @ApiModelProperty("市推荐单位")
    @TableField("CITYCASEMANAGEMENT")
    @Column(columnDefinition = "CITYCASEMANAGEMENT")
    private String citycasemanagement;

    /**
     *  市推荐单位ID(一级归口)
     */
    @ApiModelProperty("市推荐单位ID")
    @TableField("CITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CITYCASEMANAGEMENTID")
    private String citycasemanagementid;

    @Transient
    private String leadertel;//负责人电话

    @ApiModelProperty("所辖区域")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AREAID")
    @NotFound(action = NotFoundAction.IGNORE)
    private PmsArea pmsArea;

    @ApiModelProperty("省市县三级一体层级")
    @TableField("LEVEL")
    @Column(columnDefinition = "LEVEL")
    private String level;

    /**
     *  流程状态
     */
    @ApiModelProperty("流程状态")
    @TableField("MINICURRENTSTATE")
    @Column(columnDefinition = "MINICURRENTSTATE")
    private String minicurrentstate;

    /**
     *  流程状态ID
     */
    @ApiModelProperty("流程状态ID")
    @TableField("MINICURRENTSTATEID")
    @Column(columnDefinition = "MINICURRENTSTATEID")
    private String minicurrentstateid;

    /**
     *  流程节点定义key
     */
    @ApiModelProperty("流程节点定义key")
    @TableField("MINI_CURRENT_TASK_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_TASK_DEF_KEY")
    private String miniCurrentTaskDefKey;

    /**
     *  流程定义key
     */
    @ApiModelProperty("流程定义key")
    @TableField("MINI_CURRENT_PROCESS_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_PROCESS_DEF_KEY")
    private String miniCurrentProcessDefKey;

    /*-----------------------------------------------get/set方法------------------------------------------------*/

    private Boolean inaprove;

    /**
     * 管理部门编码
     */

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
    }

}