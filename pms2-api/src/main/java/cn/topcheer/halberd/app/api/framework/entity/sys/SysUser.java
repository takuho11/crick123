package cn.topcheer.halberd.app.api.framework.entity.sys;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdUser;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.Func;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "SYS_USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedQuery(name = "SysUser.findAll", query = "select s from SysUser s")
public class SysUser implements HalberdUser {

    /**
     * 账号
     */

    @Override
    @Transient
    public String getAccount() {

        return this.userid;
    }


    /**
     * 这个接口方法将在下个版本去除，对于HalberdUser,主要用于鉴权，而不会对用户进行操作，
     * 所以基本上所有set方法都不应该被调用。
     */
    public void setAccount(String account) {
        System.out.println("setAccount no use");
    }


    /**
     * 真名
     */
    @Transient
    private String realName;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 流程状态
     */
    @ApiModelProperty("流程状态")
    @TableField("MINICURRENTSTATE")
    @Column(columnDefinition = "MINICURRENTSTATE")
    private String minicurrentstate;

    /**
     * 流程状态ID
     */
    @ApiModelProperty("流程状态ID")
    @TableField("MINICURRENTSTATEID")
    @Column(columnDefinition = "MINICURRENTSTATEID")
    private String minicurrentstateid;

    /**
     * 流程节点定义key
     */
    @ApiModelProperty("流程节点定义key")
    @TableField("MINI_CURRENT_TASK_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_TASK_DEF_KEY")
    private String miniCurrentTaskDefKey;

    /**
     * 流程定义key
     */
    @ApiModelProperty("流程定义key")
    @TableField("MINI_CURRENT_PROCESS_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_PROCESS_DEF_KEY")
    private String miniCurrentProcessDefKey;

    /**
     * 角色id
     */
    @Override
    @Transient
    public String getRoleId() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getSysRole() != null).map(r -> r.getSysRole().getId()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    @Override
    public void setRoleId(String roleId) {

    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getSysRole() != null).map(r -> r.getSysRole().getRoleName()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    /**
     * 角色ids
     */
    public List<String> getRoleIds() {
        if (this.getSysIdentitys() != null) {
            return this.getSysIdentitys().stream().filter(r -> r.getSysRole() != null).map(r -> r.getSysRole().getId()).distinct().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * 企业ID/单位ID
     */
    @Override
    @Transient
    public String getEnterPriseId() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getPmsEnterprise() != null).map(r -> r.getPmsEnterprise().getId()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    /**
     * 单位名称
     */
    public String getEnterPriseName() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getPmsEnterprise() != null).map(r -> r.getPmsEnterprise().getName()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }


    /**
     * 统一社会代码
     */
    public String getUniformcode() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getPmsEnterprise() != null).map(r -> r.getPmsEnterprise().getUniformcode()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }


    /**
     * 单位
     */
    @Transient
    public PmsEnterprise getEnterPrise() {
        List<PmsEnterprise> list = this.getEnterPriseList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }


    /**
     * 单位
     */
    @Transient
    public List<PmsEnterprise> getEnterPriseList() {
        if (this.getSysIdentitys() != null) {
            return this.getSysIdentitys().stream().filter(r -> r.getPmsEnterprise() != null).map(SysIdentity::getPmsEnterprise).distinct().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * 部门ID
     */
    @Override
    @Transient
    public String getDeptId() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getPmsDepartment() != null).map(r -> r.getPmsDepartment().getId()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    @Override
    public void setDeptId(String deptId) {

    }

    /**
     * 部门名称
     */
    public String getDeptName() {
        if (this.getSysIdentitys() != null) {
            return Func.join(this.getSysIdentitys().stream().filter(r -> r.getPmsDepartment() != null).map(r -> r.getPmsDepartment().getName()).distinct().collect(Collectors.toList()));
        } else {
            return "";
        }
    }


    /**
     * 中间表id
     */
    @Transient
    public List<String> getIdentityId() {
        if (this.getSysIdentitys() != null) {
            return this.getSysIdentitys().stream().map(SysIdentity::getId).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    @Transient
    /**
     * 岗位id
     */
    private String postId;

    @Transient
    /**
     * 实体数据ID
     */
    @ApiModelProperty(value = "实体数据ID")
    private String sourceId;


    @Transient
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 密码等级（计算方式： >=8位 1 字母（ 全大写或全小写 1 大小写 2） 其他字符 (1)  数字 1
     * 根据上述规则（各占2位)，二进制拼接 例如：  Abc1234@  01 10 01 01  =  101
     */
    @Transient
    @ApiModelProperty(value = "密码等级")
    private Integer pwdLevel;
    @Transient
    @ApiModelProperty(value = "密码修改时间")
    private LocalDateTime pwdModifyTime;

    @ApiModelProperty(value = "盐")
    @JsonIgnore
    private String salt;

    @Transient
    public String getTenantId() {
        return BladeConstant.ADMIN_TENANT_ID;
    }


    @Override

    @Transient
    public boolean isNormal() {
        return false;
    }


    @Id
    private String id;
    private String email;
    //    @Column(name = "FIRST_")
//    private String first;
    private Boolean isfreeze;
    //    @Column(name = "LAST_")
//    private String last;
    private String mobile;
    private String name;
    @Column(name = "password2")
    @JsonIgnore
    private String password;
    //    @Column(name = "PICTURE_ID_")
//    private String pictureId;
//    private String qq;
//    private String qqNumber;	//绑定qq账号
//    @Column(name = "REV_")
//    private BigDecimal rev;
    private BigDecimal sex;
    //    private String userdefinecolumn;
//    private String eid;
//    private Date lastlogindate;
//    private String lasttimeip;
    private String certificatetype;
    //    private String comeFromYpt; // 从云平台过来的标志
    private String userid;
    //    //这是此注解后该属性不会数据持久化
    @Transient
    private String userrolename;
    private Integer userstate;// 用户申请状态 0 表示申请 1 表示通过审查 2表示审核不通过 -1表示禁用 3表示评审专家账号回收;4表示账号冻结
    //    @Transient
//    private String currentIPAdress;//登陆ip临时字段
//    private String wxid;
//    private String organizationname;
//    private String contactname; //从云平台过来新注册的标志
//    private String contactphone;
//    private String contactdepartment;
//    private String contactwork;
//    private String contactmobile;
//    private String contactemail;
//    private String userinfoid;
//    private String adminname;
//    private String admincode;
//    private String departmentname;
//    private String departmentcode;
    private String useridentity; // 用户身份是否启用
    //    private String superusercode;
//    private String managerlevel;
//    private String enterprisename;
    private String approvestate; // 空：审核中，1:审核成功，0:审核不成功--hlj
    //    private String departmenttype; // 部门类型
    private Date registerdate; // 注册时间
    //    private String userapprovestate; // 用户审核节点id
//    private String memo;// 备注
//    private String rpassword;
//    @Transient
//    private String viewpassword;
    @Transient
    @Column(name = "STARTPAGE")
    private String startPage;
    //    private String sftjzj;
    private String certificateno;//身份证号码
    //    private String yhlx;
    @Transient
    private String userroleid;
    @Column(name = "DEFAULTSYSTEM")
    private String defaultSystem;
    @Column(name = "LASTLOGINROLE")
    private String lastLoginRole;// 用户上一次登录角色
    //    private String managebatch;
//    private String uniteUserid;
//    @Transient
//    public String enterpriseid;
//    @Transient
//    private String userShowRoleName;
//    private String zuserid;
    private String deptid;
//    private String dwid;
//    private String sfxz;
//    private String zrjjadmin;
//    private String judgefield;//判断必填字段是否完整，空：未完整，1：完整 --hlj
//    private String overage;//判断超龄用户是否可以申报项目，空：不可以申报，1：可以申报
//
//    private String zwwuuid;//黑龙江政务网uuid
//    private Double sciencescore;//诚信分数
//    private String drbj;//老数据导入标记  老数据=1
//
//
//    private String ywttoken;//一网通登录获取的token，用于登出
//
//    private String firstlogin;//用于判断是否第一次登录

    @Column(name = "IS_DELETE")
    private Integer isDeleted;

    /*----------------------------------下面是get/set方法---------------------------------*/


    // 获取用户身份角色 CODE
//    public String getUserRoleCode(String defaultSystem) {
//        String returnRoleCode="'";
//        if (this.getSysIdentitys() != null && this.getSysIdentitys().size() > 0) {
//            for (SysIdentity sysIdentity : sysIdentitys) {
//                returnRoleCode += sysIdentity.getSysRole().getRoleCode() + "','";
//            }
//            if("'".equals(returnRoleCode)){
//                returnRoleCode = this.getSysIdentitys().get(0).getSysRole().getRoleCode()+"','";
//            }
//            if(!"'".equals(returnRoleCode)){
//                this.userroleid = returnRoleCode.substring(0,returnRoleCode.length()-2);
//            }else{
//                this.userroleid = returnRoleCode;
//            }
//            return this.userroleid;
//        } else {
//            return this.userroleid;
//        }
//    }

    // bi-directional many-to-many association to SysRole
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "SYS_ROLE_USER", joinColumns = {@JoinColumn(name = "USERID")}, inverseJoinColumns = {@JoinColumn(name = "ROLEID")})
    private Set<SysRole> sysRoles;

    @OneToMany(mappedBy = "sysUser", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<SysIdentity> sysIdentitys;

    @Column(name = "lastidentitytype")
    private String lastIdentityType;


//

    /**
     * defaultSystem zj,sb,cg 对应专家、项目、成果系统
     *
     * @param isChange
     * @return
     */
    public String getUserroleid(String userLastSystem, String isChange) {
        this.userroleid = null;
        if (this.getSysIdentitys() != null && this.getSysIdentitys().size() > 0) {
            List<SysIdentity> tarIdes = new ArrayList<SysIdentity>();
            // 模块身份读取
            if (!Util.isEoN(userLastSystem) && !Util.isEoN(isChange)) {
                for (int i = 0; i < sysIdentitys.size(); i++) {
                    if (userLastSystem.equals(sysIdentitys.get(i).getIdentityType()) && this.lastLoginRole != null
                            && this.lastLoginRole.equals(sysIdentitys.get(i).getSysRole().getRoleCode())) {
                        tarIdes.add(sysIdentitys.get(i));
                    }
                }
                // 如果 默认
                if (tarIdes.size() < 1) {
                    tarIdes.add(sysIdentitys.get(0));
                    this.setLastLoginRole(sysIdentitys.get(0).getSysRole().getRoleCode());
                }
            } else {
                for (int i = 0; i < sysIdentitys.size(); i++) {
                    if (this.defaultSystem.equals(sysIdentitys.get(i).getIdentityType()) && this.lastLoginRole != null
                            && this.lastLoginRole.equals(sysIdentitys.get(i).getSysRole().getRoleCode())) {
                        tarIdes.add(sysIdentitys.get(i));
                    }
                }
                // 如果是从别的系统直接登陆进来，则不用管上次最后一次登陆的角色
                if (tarIdes.size() < 1) {
                    for (int i = 0; i < sysIdentitys.size(); i++) {
                        if (this.defaultSystem.equals(sysIdentitys.get(i).getIdentityType())) {
                            tarIdes.add(sysIdentitys.get(i));
                            break;
                        }
                    }
                }
                // 如果 默认
                if (tarIdes.size() < 1) {
                    tarIdes.add(sysIdentitys.get(0));
                    this.setLastLoginRole(sysIdentitys.get(0).getSysRole().getRoleCode());
                }
            }
            // 由于切换系统的时候 默认首页都是在前台js 写死的，顾这里无需
            if (tarIdes.size() > 0) {
                for (SysIdentity sysIdentity : tarIdes) {
                    String roleid = sysIdentity.getSysRole().getId();
                    // 登陆后首页
                    this.setStartPage(sysIdentity.getSysRole().getPageurl());
                    if (this.userroleid != null && this.userroleid.indexOf(roleid) == -1)
                        this.userroleid += "@" + roleid;
                    else if (this.userroleid == null)
                        this.userroleid = roleid;
                }
            }
            return this.userroleid;
        } else {
            return this.userroleid;
        }
    }

//    @ManyToMany(mappedBy = "sysUsers",fetch = FetchType.LAZY)
//    private List<SysUsergroup> sysUsergroups;
//
//    // bi-directional many-to-one association to SysUserMembership
//    @OneToMany(mappedBy = "sysUser",fetch = FetchType.LAZY)
//    private List<SysUserMembership> sysUserMemberships;


    //    public String getEnterpriseid(){
//        if (this.getSysIdentitys() != null && this.getSysIdentitys().size() > 0) {
//            for (SysIdentity sysIdentity : sysIdentitys) {
//                this.enterpriseid = sysIdentity.getPmsEnterprise().getId();
//                break;
//            }
//            return enterpriseid;
//        } else {
//            return enterpriseid;
//        }
//    }
//
//    public String getUserShowRoleName() {
//        this.userShowRoleName = null;
//        String userShowRoleName = "普通用户";
//        if (this.getSysIdentitys() != null && this.getSysIdentitys().size() > 0) {
//            for (SysIdentity sysIdentity : sysIdentitys) {
//                userShowRoleName = sysIdentity.getSysRole().getRoleName();
//                this.enterprisename = sysIdentity.getPmsEnterprise().getName();
//                break;
//            }
//            return userShowRoleName;
//        } else {
//            return userShowRoleName;
//        }
//    }
//
//    public void setUserShowRoleName(String userShowRoleName) {
//        this.userShowRoleName = userShowRoleName;
//    }
    //设置登陆后url首页
//    public String getUserrolename() {
//        this.userrolename = null;
//        String startPage = "home";
//        if (this.getSysIdentitys() != null && this.getSysIdentitys().size() > 0) {
//            for (SysIdentity sysIdentity : sysIdentitys) {
////				if(sysIdentity.getDefaultrole()!=null&&"default".equals(sysIdentity.getDefaultrole())){
//                if (!Util.isEoN(this.defaultSystem) && this.defaultSystem.equals(sysIdentity.getIdentityType())) {
//                    startPage = sysIdentity.getSysRole().getPageurl();
//                    return startPage;
//                }
//            }
//            // 如果没有该模块的身份 则 跳转到该页面开通功能
//            startPage = "openFunction";
//            return startPage;
//        } else {
//            return startPage;
//        }
//    }


    //    public List<SysUsergroup> getSysUsergroups() {
//        return this.sysUsergroups;
//    }
//
//    public void setSysUsergroups(List<SysUsergroup> sysUsergroups) {
//        this.sysUsergroups = sysUsergroups;
//    }
//
//    public List<SysUserMembership> getSysUserMemberships() {
//        return this.sysUserMemberships;
//    }
//
//    public void setSysUserMemberships(List<SysUserMembership> sysUserMemberships) {
//        this.sysUserMemberships = sysUserMemberships;
//    }
//
//    public SysUserMembership addSysUserMembership(
//            SysUserMembership sysUserMembership) {
//        getSysUserMemberships().add(sysUserMembership);
//        sysUserMembership.setSysUser(this);
//
//        return sysUserMembership;
//    }
//
//    public SysUserMembership removeSysUserMembership(
//            SysUserMembership sysUserMembership) {
//        getSysUserMemberships().remove(sysUserMembership);
//        sysUserMembership.setSysUser(null);
//
//        return sysUserMembership;
//    }


}
