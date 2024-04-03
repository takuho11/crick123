/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.api.pml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PML_BUTTON")
public class PmlButton {


    /**
     *
     */
    private String id;


    /**
     *  显示的按钮名称
     */
    private String buttonname;


    /**
     *  按钮类型：列表操作列上的按钮，批次处理按钮等
     */
    private String buttontype;

    /**
     * 是否一直显示，是：1，不需要showbuttonmethod；否：0，需要showbuttonmethod
     */
    private Boolean alwaysshow;

    /**
     * 是否判断显示，是：1；否：0
     */
    private Boolean judgerole;

    /**
     * 可以使用的角色
     */
    private String roledata;


    /**
     *  js方法名，判断是否显示按钮的方法，有个专门的js文件存放这些方法
     */
    private String showbuttonmethod;


    /**
     *  js方法名，点击按钮执行的方法，有个专门的js文件存放这些方法
     */
    private String buttonmethod;

    /**
     * 按钮颜色
     */
    private String color;

    /**
     * 图标
     */
    private String icon;


    /**
     *  创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date createdate;


    /**
     * 排序
     */
    private Integer seq;

    /**
     * 备注，主要用来区分按钮名称相同的不同按钮，方便列表选择
     */
    private String remarks;


    /**
     * 列表内按钮图标
     */
    private String gridbtnicon;

    /**
     * 图标还是文字
     */
    private String iconortext;

    /**
     * 业务类型
     */
    private String businesstype;

    /**
     * 手机端用到
     */
    private String fittype;


    /**
     *
     */

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     *  显示的按钮名称
     */

    public String getButtonname() {
        return buttonname;
    }

    public void setButtonname(String buttonname) {
        this.buttonname = buttonname;
    }


    /**
     *  按钮类型：列表操作列上的按钮，批次处理按钮等
     */

    public String getButtontype() {
        return buttontype;
    }

    public void setButtontype(String buttontype) {
        this.buttontype = buttontype;
    }


    /**
     *是否一直显示，是：1，不需要showbuttonmethod；否：0，需要showbuttonmethod
     */
    public Boolean getAlwaysshow() {
        return alwaysshow;
    }

    public void setAlwaysshow(Boolean alwaysshow) {
        this.alwaysshow = alwaysshow;
    }

    /**
     * 是否判断显示，是：1；否：0
     */
    public Boolean getJudgerole() {
        return judgerole;
    }

    public void setJudgerole(Boolean judgerole) {
        this.judgerole = judgerole;
    }

    /**
     * 可以使用的角色
     */
    public String getRoledata() {
        return roledata;
    }

    public void setRoledata(String roledata) {
        this.roledata = roledata;
    }

    /**
     *  js方法名，判断是否显示按钮的方法，有个专门的js文件存放这些方法
     */

    public String getShowbuttonmethod() {
        return showbuttonmethod;
    }

    public void setShowbuttonmethod(String showbuttonmethod) {
        this.showbuttonmethod = showbuttonmethod;
    }


    /**
     *  js方法名，点击按钮执行的方法，有个专门的js文件存放这些方法
     */

    public String getButtonmethod() {
        return buttonmethod;
    }

    public void setButtonmethod(String buttonmethod) {
        this.buttonmethod = buttonmethod;
    }

    /**
     * 按钮颜色
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 图标
     */
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *  创建时间
     */

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }


    /**
     * 排序
     */
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 备注，主要用来区分按钮名称相同的不同按钮，方便列表选择
     */
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGridbtnicon() {
        return gridbtnicon;
    }

    public void setGridbtnicon(String gridbtnicon) {
        this.gridbtnicon = gridbtnicon;
    }

    public String getIconortext() {
        return iconortext;
    }

    public void setIconortext(String iconortext) {
        this.iconortext = iconortext;
    }


    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getFittype() {
        return fittype;
    }

    public void setFittype(String fittype) {
        this.fittype = fittype;
    }
}