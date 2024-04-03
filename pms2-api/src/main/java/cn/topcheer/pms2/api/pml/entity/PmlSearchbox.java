/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2020-11-10 9:04:01
 */
package cn.topcheer.pms2.api.pml.entity;


import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 列表搜索框
 *
 * @author GaoGongxin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PML_SEARCHBOX")
public class PmlSearchbox {


    /**
     * 主键
     */
    @FieldDes(name = "id", lenth = "50", type = "String", memo = "主键")
    private String id;


    /**
     * 名称
     */
    @FieldDes(name = "name", lenth = "255", type = "String", memo = "名称")
    private String name;


    /**
     * 业务类型
     */
    @FieldDes(name = "businesstype", lenth = "255", type = "String", memo = "业务类型")
    private String businesstype;


    /**
     * 是否为配置
     */
    @FieldDes(name = "isconfig", lenth = "50", type = "String", memo = "是否为配置")
    private String isconfig;


    /**
     * 脚本内容（直接编写HTML代码）
     */
    @FieldDes(name = "code", lenth = "4000", type = "String", memo = "脚本内容（直接编写HTML代码）")
    private String code;


    /**
     * 字段属性
     */
    @FieldDes(name = "ngmodel", lenth = "255", type = "String", memo = "字段属性")
    private String ngmodel;


    /**
     * 字段提示
     */
    @FieldDes(name = "tip", lenth = "255", type = "String", memo = "字段提示")
    private String tip;


    /**
     * 下拉选项
     */
    @FieldDes(name = "options", lenth = "255", type = "String", memo = "下拉选项")
    private String options;

    /**
     * 更改内容方法是否执行
     */
    @FieldDes(name = "havechangefunc", lenth = "255", type = "String", memo = "更改内容方法是否执行")
    private String havechangefunc;

    /**
     * 更改方法名称
     */
    @FieldDes(name = "changefuncname", lenth = "1000", type = "String", memo = "更改方法名称")
    private String changefuncname;

    /**
     * 初始化方法是否执行
     */
    @FieldDes(name = "haveinitfunc", lenth = "255", type = "String", memo = "初始化方法是否执行")
    private String haveinitfunc;

    /**
     * 初始化方法
     */
    @FieldDes(name = "initfuncname", lenth = "255", type = "String", memo = "初始化方法")
    private String initfuncname;

    /**
     * 点击方法是否执行
     */
    @FieldDes(name = "haveclickfunc", lenth = "255", type = "String", memo = "点击方法是否执行")
    private String haveclickfunc;

    /**
     * 点击方法
     */
    @FieldDes(name = "clickfuncname", lenth = "255", type = "String", memo = "点击方法")
    private String clickfuncname;

    /**
     * 获得焦点方法是否执行
     */
    @FieldDes(name = "haveblurfunc", lenth = "255", type = "String", memo = "获得焦点方法是否执行")
    private String haveblurfunc;

    /**
     * 获得焦点方法
     */
    @FieldDes(name = "blurfuncname", lenth = "255", type = "String", memo = "获得焦点方法")
    private String blurfuncname;


    /**
     * 占位宽度（1-12的整数）
     */
    @FieldDes(name = "width", type = "Integer", memo = "占位宽度（1-12的整数）")
    private Integer width;


    /**
     * 搜索框类型
     */
    @FieldDes(name = "boxtype", lenth = "255", type = "String", memo = "搜索框类型")
    private String boxtype;


    /**
     * 顺序
     */
    @FieldDes(name = "seq", type = "Integer", memo = "顺序")
    private Integer seq;


    /**
     * 特殊样式（通过json模式设置）
     */
    @FieldDes(name = "specialclass", lenth = "1000", type = "String", memo = "特殊样式（通过json模式设置）")
    private String specialclass;


    /**
     * 备注
     */
    @FieldDes(name = "memo", lenth = "255", type = "String", memo = "备注")
    private String memo;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    @FieldDes(name = "createdate", type = "Date", memo = "创建时间")
    private Date createdate;

    /**
     * 标签关闭方法名称
     */
    @FieldDes(name = "labelfuncname", lenth = "1000", type = "String", memo = "标签关闭方法名称")
    private String labelfuncname;

    /**
     * 下拉选项编码
     */
    @FieldDes(name = "dropdowncode", lenth = "100", type = "String", memo = "下拉选项编码")
    private String dropdowncode;

    /**
     * 入参参数
     */
    @FieldDes(name = "dropdownparamfunc", lenth = "10000", type = "String", memo = "入参参数")
    private String dropdownparamfunc;

    /**
     * 级联属性
     */
    @FieldDes(name = "cascaderprop", lenth = "50", type = "String", memo = "级联属性")
    private String cascaderprop;

    /**
     * 属性
     */
    @FieldDes(name = "prop", lenth = "255", type = "String", memo = "属性")
    private String prop;


    /**
     * 主键
     */

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


    /**
     * 业务类型
     */

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }


    /**
     * 是否为配置
     */

    public String getIsconfig() {
        return isconfig;
    }

    public void setIsconfig(String isconfig) {
        this.isconfig = isconfig;
    }


    /**
     * 脚本内容（直接编写HTML代码）
     */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    /**
     * 字段属性
     */

    public String getNgmodel() {
        return ngmodel;
    }

    public void setNgmodel(String ngmodel) {
        this.ngmodel = ngmodel;
    }


    /**
     * 字段提示
     */

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * 下拉选项
     */
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * 更改内容方法是否执行
     */
    public String getHavechangefunc() {
        return havechangefunc;
    }

    public void setHavechangefunc(String havechangefunc) {
        this.havechangefunc = havechangefunc;
    }

    /**
     * 更改内容方法
     */
    public String getChangefuncname() {
        return changefuncname;
    }

    public void setChangefuncname(String changefuncname) {
        this.changefuncname = changefuncname;
    }

    /**
     * 初始化方法是否执行
     */
    public String getHaveinitfunc() {
        return haveinitfunc;
    }

    public void setHaveinitfunc(String haveinitfunc) {
        this.haveinitfunc = haveinitfunc;
    }

    /**
     * 初始化方法
     */
    public String getInitfuncname() {
        return initfuncname;
    }

    public void setInitfuncname(String initfuncname) {
        this.initfuncname = initfuncname;
    }

    /**
     * 点击方法是否执行
     */
    public String getHaveclickfunc() {
        return haveclickfunc;
    }

    public void setHaveclickfunc(String haveclickfunc) {
        this.haveclickfunc = haveclickfunc;
    }

    /**
     * 点击方法
     */
    public String getClickfuncname() {
        return clickfuncname;
    }

    public void setClickfuncname(String clickfuncname) {
        this.clickfuncname = clickfuncname;
    }

    /**
     * 获得焦点方法是否执行
     */
    public String getHaveblurfunc() {
        return haveblurfunc;
    }

    public void setHaveblurfunc(String haveblurfunc) {
        this.haveblurfunc = haveblurfunc;
    }

    /**
     * 获得焦点方法
     */
    public String getBlurfuncname() {
        return blurfuncname;
    }

    public void setBlurfuncname(String blurfuncname) {
        this.blurfuncname = blurfuncname;
    }

    /**
     * 占位宽度（1-12的整数）
     */

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 搜索框类型
     */

    public String getBoxtype() {
        return boxtype;
    }

    public void setBoxtype(String boxtype) {
        this.boxtype = boxtype;
    }


    /**
     * 顺序
     */

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    /**
     * 特殊样式（通过json模式设置）
     */

    public String getSpecialclass() {
        return specialclass;
    }

    public void setSpecialclass(String specialclass) {
        this.specialclass = specialclass;
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
     * 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLabelfuncname() {
        return labelfuncname;
    }

    public void setLabelfuncname(String labelfuncname) {
        this.labelfuncname = labelfuncname;
    }


    public String getDropdowncode() {
        return dropdowncode;
    }

    public void setDropdowncode(String dropdowncode) {
        this.dropdowncode = dropdowncode;
    }

    public String getDropdownparamfunc() {
        return dropdownparamfunc;
    }

    public void setDropdownparamfunc(String dropdownparamfunc) {
        this.dropdownparamfunc = dropdownparamfunc;
    }

    public String getCascaderprop() {
        return cascaderprop;
    }

    public void setCascaderprop(String cascaderprop) {
        this.cascaderprop = cascaderprop;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}