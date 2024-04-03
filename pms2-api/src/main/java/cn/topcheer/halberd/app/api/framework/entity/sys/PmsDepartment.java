/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2016-1-27 11:50:08
 */
package cn.topcheer.halberd.app.api.framework.entity.sys;

import cn.topcheer.halberd.biz.modules.base.entity.HalberdDept;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "PMS_DEPARTMENT")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PmsDepartment implements HalberdDept, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    @Transient
    public String getDeptName() {
        return this.getName();
    }

    void setDeptName(String deptName) {

    }

    /**
     *
     */
    @Id
    private String id;


    /**
     *  部门名称
     */
    private String name;


    /**
     *  部门编号
     */
    private String departmentcode;


    /**
     *  部门简称
     */
    private String shortname;


    /**
     * 部门状态 1 执行部门，2查看部门，3不可用
     */
    private String enable;


    /**
     *  手机号码
     */
    private String telephone;


    /**
     *  备注
     */
    private String memo;


    /**
     * 排序
     */
    private Integer tjseq;

    /**
     * 排序
     */
    private Integer seq;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    /**
     *  上级部门名称
     */
    private String adminname;


    /**
     *  上级部门编码
     */
    private String admincode;


    /**
     *  部门负责人
     */
    private String leader;

    @OneToMany(mappedBy = "pmsDepartment", cascade = {CascadeType.ALL})
    private List<SysIdentity> sysIdentitys;


    /**
     *  所属企业ID(PMS_ENTERPRISE)
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENTERPRISEID")
    @NotFound(action = NotFoundAction.IGNORE)
    private PmsEnterprise pmsEnterprise;


}