package cn.topcheer.halberd.app.api.framework.entity.sys;

import cn.topcheer.halberd.biz.modules.base.entity.HalberdRole;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="SYS_ROLE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SysRole   implements HalberdRole {

    /**
     * 父主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "父主键")
    //@Column(name="parentid")
    @Transient
    private String parentId;
 

    /**
     * 角色别名
     */
    @ApiModelProperty(value = "角色别名")
    @Transient
    public String getRoleAlias(){
        return this.roleName;
    }

    /**
     * 角色类型
     */
    @ApiModelProperty(value = "角色类型")
    @Transient
    public String getRoleType()
    {
        return this.systemtype;
    }




    /**
     *
     */
    @Id
    private String id;
    private Integer enable;
    /**
     *  角色编码
     */
    @Column(name="rolecode")
    private String roleCode;
    private String pageurl;
    private String systemtype;
    private Integer seq;
    /**
     *  角色名称
     */
    @Column(name="rolename")
    private String roleName;

    @OneToMany(mappedBy= "sysRole",cascade={CascadeType.ALL})
    @JsonIgnore
    private List<SysIdentity> sysIdentitys;
    //private List<PmMenu> pmMenus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name="SYS_ROLE_USER" ,joinColumns={ @JoinColumn(name="ROLEID")}, inverseJoinColumns={@JoinColumn(name="USERID")})
    private Set<SysUser> sysUsers;
   // private List<SysPurviewItem> syspurviewitems;
    //添加角色描述属性 modified by:jianliaoliang 2016-11-30
    private String description;
    private String fz;
    /*标签：all，所有可看，1可以赋权限*/
    private String bqlimit;

    private Integer logininlimitday;//超过多少天未登录受到限制

    private String logininlimitstate;//超过多少天未登录受到的限制类型

    private Integer changepwdlimitday;//超过多少天未改密受到限制

    private String changepwdlimitstate;//超过多少天未改密受到的限制类型



    @ApiModelProperty("所属单位")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENTER_PRISE_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private PmsEnterprise pmsEnterprise;


    /**
     *  是否公共（0否，1是）
     */
    @Column(name="IS_PUBLIC")
    private Integer isPublic;


    /*-------------------------------------------------下面是get/set方法------------------------------------------------------*/










    //bi-directional many-to-many association to SysUser

    //bi-directional many-to-many association to SysPurviewItem
//    @ManyToMany
//    @JoinTable( name="SYS_PURVIEW" ,joinColumns={ @JoinColumn(name="ROLEID")}, inverseJoinColumns={@JoinColumn(name="PURVIEWITEMID")})
//    public List<SysPurviewItem> getSyspurviewitems() {
//        return syspurviewitems;
//    }
//    public void setSyspurviewitems(List<SysPurviewItem> syspurviewitems) {
//        this.syspurviewitems = syspurviewitems;
//    }

//    @OneToMany(mappedBy= "sysRole",cascade={CascadeType.ALL})
//    public List<SysIdentity> getSysIdentitys(){
//        return sysIdentitys;
//    }
//    public void setSysIdentitys(List<SysIdentity> sysIdentitys) {
//        this.sysIdentitys = sysIdentitys;
//    }

    //bi-directional many-to-many association to Pmmenu
//    @ManyToMany
//    @JoinTable(name="SYS_link_ROLEMENU", joinColumns={@JoinColumn(name="ROLEID")}, inverseJoinColumns={@JoinColumn(name="MENUID")})
//    public List<PmMenu> getPmMenus() {
//        return pmMenus;
//    }
//    public void setPmMenus(List<PmMenu> pmMenus) {
//        this.pmMenus = pmMenus;
//    }




}
