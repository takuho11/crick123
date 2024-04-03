/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2015-12-21 19:02:45
 */
package cn.topcheer.halberd.app.api.framework.entity.sys;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  身份表
 */
@Entity
@Table(name = "SYS_IDENTITY")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SysIdentity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    private String id;

    private Integer seq;


    @Column(name = "identitytype")
    public String identityType;


    /**
     * 用户
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private SysUser sysUser;


    /**
     *  角色
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLEID")
    private SysRole sysRole;


    /**
     * 单位，主要使用单位，用户可没有单位
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURVIEWORGANIZEID")
    private PmsEnterprise pmsEnterprise;


    /**
     * 部门，用户可没有部门，科技厅独属
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENTID")
    private PmsDepartment pmsDepartment;


    /**
     * 备注字段
     */
    private String memo;//目前用来存放变更前的单位id--hlj
    private Date memodate;
    /**
     *  创建日期
     */
    private Date createdate;
    /**
     *  0 表示申请 1 表示审核通过 2 表示审核不通过
     */
    private Integer enabled;


    /*----------------------------------------------get/set方法---------------------------------------------*/


}