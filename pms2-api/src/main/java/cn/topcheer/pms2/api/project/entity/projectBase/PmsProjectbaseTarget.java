/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 13:58:57
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  计划项目-技术经济指标
 */
@Entity
@Table(name="PMS_PROJECTBASE_TARGET")
@ClassInfo(name = "计划项目-技术经济指标", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseTarget {

	/**
     *  固定字段:唯一标识
     */
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;
	/**
     *  固定字段:数据类型
     */
	private String type;
	/**
     *  固定字段:关联主表id
     */
	private String mainid;
	/**
     *  固定字段:关联子表id
     */
	private String sourceid;
	/**
     *  固定字段:第一次保存时间
     */
	@FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;
	/**
     *  固定字段:每次更新数据时间
     */
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;
	/**
     *  固定字段:排序
     */
	@FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
	private Integer seq;
	/**
     *  固定字段:备注
     */
	private String memo;
	/**
     *  国际核心期刊（篇）
     */
	@FieldDes(name = "gjhxqk", type = "Integer", memo = "国际核心期刊（篇）")
	private Integer gjhxqk;
	/**
     *  国内核心期刊（篇）
     */
	@FieldDes(name = "gnhxqk", type = "Integer", memo = "国内核心期刊（篇）")
	private Integer gnhxqk;
	/**
     *  省级核心期刊（篇）
     */
	@FieldDes(name = "sjhxqk", type = "Integer", memo = "省级核心期刊（篇）")
	private Integer sjhxqk;
	/**
     *  国际一般期刊（篇）
     */
	@FieldDes(name = "gjybqk", type = "Integer", memo = "国际一般期刊（篇）")
	private Integer gjybqk;
	/**
     *  国内一般期刊（篇）
     */
	@FieldDes(name = "gnybqk", type = "Integer", memo = "国内一般期刊（篇）")
	private Integer gnybqk;
	/**
     *  省级一般期刊（篇）
     */
	@FieldDes(name = "sjybqk", type = "Integer", memo = "省级一般期刊（篇）")
	private Integer sjybqk;
	/**
     *  科技报告（篇）
     */
	@FieldDes(name = "kjbg", type = "Integer", memo = "科技报告（篇）")
	private Integer kjbg;
	/**
     *  研究报告（篇）
     */
	@FieldDes(name = "yjbg", type = "Integer", memo = "研究报告（篇）")
	private Integer yjbg;
	/**
     *  科技成果转化报告（篇）
     */
	@FieldDes(name = "kjcgzhbg", type = "Integer", memo = "科技成果转化报告（篇）")
	private Integer kjcgzhbg;
	/**
     *  其他报告（篇）
     */
	@FieldDes(name = "qtbg", type = "Integer", memo = "其他报告（篇）")
	private Integer qtbg;
	/**
     *  专著（部）
     */
	@FieldDes(name = "zz", type = "Integer", memo = "专著（部）")
	private Integer zz;
	/**
     *  申请(受理)发明专利数
     */
	@FieldDes(name = "slfmzl", type = "Integer", memo = "申请(受理)发明专利数")
	private Integer slfmzl;
	/**
     *  申请(受理)实用新型专利数
     */
	@FieldDes(name = "slsyxxzl", type = "Integer", memo = "申请(受理)实用新型专利数")
	private Integer slsyxxzl;
	/**
     *  申请(受理)外观设计专利数
     */
	@FieldDes(name = "slwgsjzl", type = "Integer", memo = "申请(受理)外观设计专利数")
	private Integer slwgsjzl;
	/**
     *  申请(受理)PCT专利数
     */
	@FieldDes(name = "slpctzl", type = "Integer", memo = "申请(受理)PCT专利数")
	private Integer slpctzl;
	/**
     *  授权发明专利数
     */
	@FieldDes(name = "sqfmzl", type = "Integer", memo = "授权发明专利数")
	private Integer sqfmzl;
	/**
     *  授权实用新型专利数
     */
	@FieldDes(name = "sqsyxxzl", type = "Integer", memo = "授权实用新型专利数")
	private Integer sqsyxxzl;
	/**
     *  授权外观设计专利数
     */
	@FieldDes(name = "sqwgsjzl", type = "Integer", memo = "授权外观设计专利数")
	private Integer sqwgsjzl;
	/**
     *  授权PCT专利数
     */
	@FieldDes(name = "sqpctzl", type = "Integer", memo = "授权PCT专利数")
	private Integer sqpctzl;
	/**
     *  申请软件著作权数
     */
	@FieldDes(name = "sqrjzzq", type = "Integer", memo = "申请软件著作权数")
	private Integer sqrjzzq;
	/**
     *  申请集成电路布图设计数
     */
	@FieldDes(name = "sqjcdl", type = "Integer", memo = "申请集成电路布图设计数")
	private Integer sqjcdl;
	/**
     *  申请植物新品种数
     */
	@FieldDes(name = "sqzwxpz", type = "Integer", memo = "申请植物新品种数")
	private Integer sqzwxpz;
	/**
     *  登记软件著作权数
     */
	@FieldDes(name = "djrjzzq", type = "Integer", memo = "登记软件著作权数")
	private Integer djrjzzq;
	/**
     *  登记集成电路布图设计数
     */
	@FieldDes(name = "djjcdl", type = "Integer", memo = "登记集成电路布图设计数")
	private Integer djjcdl;
	/**
     *  登记植物新品种数
     */
	@FieldDes(name = "djzwxpz", type = "Integer", memo = "登记植物新品种数")
	private Integer djzwxpz;
	/**
     *  牵头国家标准
     */
	@FieldDes(name = "qtgjbz", type = "Integer", memo = "牵头国家标准")
	private Integer qtgjbz;
	/**
     *  牵头行业标准
     */
	@FieldDes(name = "qthybz", type = "Integer", memo = "牵头行业标准")
	private Integer qthybz;
	/**
     *  牵头地方标准
     */
	@FieldDes(name = "qtdfbz", type = "Integer", memo = "牵头地方标准")
	private Integer qtdfbz;
	/**
     *  牵头企业标准
     */
	@FieldDes(name = "qtqybz", type = "Integer", memo = "牵头企业标准")
	private Integer qtqybz;
	/**
     *  参与国家标准
     */
	@FieldDes(name = "cygjbz", type = "Integer", memo = "参与国家标准")
	private Integer cygjbz;
	/**
     *  参与行业标准
     */
	@FieldDes(name = "cyhybz", type = "Integer", memo = "参与行业标准")
	private Integer cyhybz;
	/**
     *  参与地方标准
     */
	@FieldDes(name = "cydfbz", type = "Integer", memo = "参与地方标准")
	private Integer cydfbz;
	/**
     *  参与企业标准
     */
	@FieldDes(name = "cyqybz", type = "Integer", memo = "参与企业标准")
	private Integer cyqybz;
	/**
     *  中试线（条）
     */
	@FieldDes(name = "zsx", type = "Integer", memo = "中试线（条）")
	private Integer zsx;
	/**
     *  生产线（条）
     */
	@FieldDes(name = "scx", type = "Integer", memo = "生产线（条）")
	private Integer scx;
	/**
     *  累计新增销售收入（万元）
     */
	@FieldDes(name = "newsalesrevenue", type = "BigDecimal", memo = "累计新增销售收入（万元）")
	private BigDecimal newsalesrevenue;
	/**
     *  累计新增利税（万元）
     */
	@FieldDes(name = "newtaxes", type = "BigDecimal", memo = "累计新增利税（万元）")
	private BigDecimal newtaxes;
	/**
     *  培养高级职称以上
     */
	@FieldDes(name = "pygj", type = "Integer", memo = "培养高级职称以上")
	private Integer pygj;
	/**
     *  培养中级职称以上
     */
	@FieldDes(name = "pyzj", type = "Integer", memo = "培养中级职称以上")
	private Integer pyzj;
	/**
     *  引进高级职称以上
     */
	@FieldDes(name = "yjgj", type = "Integer", memo = "引进高级职称以上")
	private Integer yjgj;
	/**
     *  引进新产品（种）
     */
	@FieldDes(name = "yjxcp", type = "Integer", memo = "引进新产品（种）")
	private Integer yjxcp;
	/**
     *  引进新技术（项）
     */
	@FieldDes(name = "yjxjs", type = "Integer", memo = "引进新技术（项）")
	private Integer yjxjs;
	/**
     *  引进新工艺（项）
     */
	@FieldDes(name = "yjxgy", type = "Integer", memo = "引进新工艺（项）")
	private Integer yjxgy;
	/**
     *  引进新材料（项）
     */
	@FieldDes(name = "yjxcl", type = "Integer", memo = "引进新材料（项）")
	private Integer yjxcl;
	/**
     *  支撑平台（个）
     */
	@FieldDes(name = "zcpt", type = "Integer", memo = "支撑平台（个）")
	private Integer zcpt;
	/**
     *  试验基地（个）
     */
	@FieldDes(name = "syjd", type = "Integer", memo = "试验基地（个）")
	private Integer syjd;
	/**
     *  示范点（区）（个）
     */
	@FieldDes(name = "sfd", type = "Integer", memo = "示范点（区）（个）")
	private Integer sfd;




	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
	return id;
	}
	public void setId (String id){
	this.id=id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
	return type;
	}
	public void setType (String type){
	this.type=type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
	return mainid;
	}
	public void setMainid (String mainid){
	this.mainid=mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
	return sourceid;
	}
	public void setSourceid (String sourceid){
	this.sourceid=sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
	return savedate;
	}
	public void setSavedate (Date savedate){
	this.savedate=savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
	return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
	this.updatelasttime=updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
	return seq;
	}
	public void setSeq (Integer seq){
	this.seq=seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
	return memo;
	}
	public void setMemo (String memo){
	this.memo=memo;
	}

	/**
	*  国际核心期刊（篇）
	*/
	public Integer getGjhxqk (){
	return gjhxqk;
	}
	public void setGjhxqk (Integer gjhxqk){
	this.gjhxqk=gjhxqk;
	}

	/**
	*  国内核心期刊（篇）
	*/
	public Integer getGnhxqk (){
	return gnhxqk;
	}
	public void setGnhxqk (Integer gnhxqk){
	this.gnhxqk=gnhxqk;
	}

	/**
	*  省级核心期刊（篇）
	*/
	public Integer getSjhxqk (){
	return sjhxqk;
	}
	public void setSjhxqk (Integer sjhxqk){
	this.sjhxqk=sjhxqk;
	}

	/**
	*  国际一般期刊（篇）
	*/
	public Integer getGjybqk (){
	return gjybqk;
	}
	public void setGjybqk (Integer gjybqk){
	this.gjybqk=gjybqk;
	}

	/**
	*  国内一般期刊（篇）
	*/
	public Integer getGnybqk (){
	return gnybqk;
	}
	public void setGnybqk (Integer gnybqk){
	this.gnybqk=gnybqk;
	}

	/**
	*  省级一般期刊（篇）
	*/
	public Integer getSjybqk (){
	return sjybqk;
	}
	public void setSjybqk (Integer sjybqk){
	this.sjybqk=sjybqk;
	}

	/**
	*  科技报告（篇）
	*/
	public Integer getKjbg (){
	return kjbg;
	}
	public void setKjbg (Integer kjbg){
	this.kjbg=kjbg;
	}

	/**
	*  研究报告（篇）
	*/
	public Integer getYjbg (){
	return yjbg;
	}
	public void setYjbg (Integer yjbg){
	this.yjbg=yjbg;
	}

	/**
	*  科技成果转化报告（篇）
	*/
	public Integer getKjcgzhbg (){
	return kjcgzhbg;
	}
	public void setKjcgzhbg (Integer kjcgzhbg){
	this.kjcgzhbg=kjcgzhbg;
	}

	/**
	*  其他报告（篇）
	*/
	public Integer getQtbg (){
	return qtbg;
	}
	public void setQtbg (Integer qtbg){
	this.qtbg=qtbg;
	}

	/**
	*  专著（部）
	*/
	public Integer getZz (){
	return zz;
	}
	public void setZz (Integer zz){
	this.zz=zz;
	}

	/**
	*  申请(受理)发明专利数
	*/
	public Integer getSlfmzl (){
	return slfmzl;
	}
	public void setSlfmzl (Integer slfmzl){
	this.slfmzl=slfmzl;
	}

	/**
	*  申请(受理)实用新型专利数
	*/
	public Integer getSlsyxxzl (){
	return slsyxxzl;
	}
	public void setSlsyxxzl (Integer slsyxxzl){
	this.slsyxxzl=slsyxxzl;
	}

	/**
	*  申请(受理)外观设计专利数
	*/
	public Integer getSlwgsjzl (){
	return slwgsjzl;
	}
	public void setSlwgsjzl (Integer slwgsjzl){
	this.slwgsjzl=slwgsjzl;
	}

	/**
	*  申请(受理)PCT专利数
	*/
	public Integer getSlpctzl (){
	return slpctzl;
	}
	public void setSlpctzl (Integer slpctzl){
	this.slpctzl=slpctzl;
	}

	/**
	*  授权发明专利数
	*/
	public Integer getSqfmzl (){
	return sqfmzl;
	}
	public void setSqfmzl (Integer sqfmzl){
	this.sqfmzl=sqfmzl;
	}

	/**
	*  授权实用新型专利数
	*/
	public Integer getSqsyxxzl (){
	return sqsyxxzl;
	}
	public void setSqsyxxzl (Integer sqsyxxzl){
	this.sqsyxxzl=sqsyxxzl;
	}

	/**
	*  授权外观设计专利数
	*/
	public Integer getSqwgsjzl (){
	return sqwgsjzl;
	}
	public void setSqwgsjzl (Integer sqwgsjzl){
	this.sqwgsjzl=sqwgsjzl;
	}

	/**
	*  授权PCT专利数
	*/
	public Integer getSqpctzl (){
	return sqpctzl;
	}
	public void setSqpctzl (Integer sqpctzl){
	this.sqpctzl=sqpctzl;
	}

	/**
	*  申请软件著作权数
	*/
	public Integer getSqrjzzq (){
	return sqrjzzq;
	}
	public void setSqrjzzq (Integer sqrjzzq){
	this.sqrjzzq=sqrjzzq;
	}

	/**
	*  申请集成电路布图设计数
	*/
	public Integer getSqjcdl (){
	return sqjcdl;
	}
	public void setSqjcdl (Integer sqjcdl){
	this.sqjcdl=sqjcdl;
	}

	/**
	*  申请植物新品种数
	*/
	public Integer getSqzwxpz (){
	return sqzwxpz;
	}
	public void setSqzwxpz (Integer sqzwxpz){
	this.sqzwxpz=sqzwxpz;
	}

	/**
	*  登记软件著作权数
	*/
	public Integer getDjrjzzq (){
	return djrjzzq;
	}
	public void setDjrjzzq (Integer djrjzzq){
	this.djrjzzq=djrjzzq;
	}

	/**
	*  登记集成电路布图设计数
	*/
	public Integer getDjjcdl (){
	return djjcdl;
	}
	public void setDjjcdl (Integer djjcdl){
	this.djjcdl=djjcdl;
	}

	/**
	*  登记植物新品种数
	*/
	public Integer getDjzwxpz (){
	return djzwxpz;
	}
	public void setDjzwxpz (Integer djzwxpz){
	this.djzwxpz=djzwxpz;
	}

	/**
	*  牵头国家标准
	*/
	public Integer getQtgjbz (){
	return qtgjbz;
	}
	public void setQtgjbz (Integer qtgjbz){
	this.qtgjbz=qtgjbz;
	}

	/**
	*  牵头行业标准
	*/
	public Integer getQthybz (){
	return qthybz;
	}
	public void setQthybz (Integer qthybz){
	this.qthybz=qthybz;
	}

	/**
	*  牵头地方标准
	*/
	public Integer getQtdfbz (){
	return qtdfbz;
	}
	public void setQtdfbz (Integer qtdfbz){
	this.qtdfbz=qtdfbz;
	}

	/**
	*  牵头企业标准
	*/
	public Integer getQtqybz (){
	return qtqybz;
	}
	public void setQtqybz (Integer qtqybz){
	this.qtqybz=qtqybz;
	}

	/**
	*  参与国家标准
	*/
	public Integer getCygjbz (){
	return cygjbz;
	}
	public void setCygjbz (Integer cygjbz){
	this.cygjbz=cygjbz;
	}

	/**
	*  参与行业标准
	*/
	public Integer getCyhybz (){
	return cyhybz;
	}
	public void setCyhybz (Integer cyhybz){
	this.cyhybz=cyhybz;
	}

	/**
	*  参与地方标准
	*/
	public Integer getCydfbz (){
	return cydfbz;
	}
	public void setCydfbz (Integer cydfbz){
	this.cydfbz=cydfbz;
	}

	/**
	*  参与企业标准
	*/
	public Integer getCyqybz (){
	return cyqybz;
	}
	public void setCyqybz (Integer cyqybz){
	this.cyqybz=cyqybz;
	}

	/**
	*  中试线（条）
	*/
	public Integer getZsx (){
	return zsx;
	}
	public void setZsx (Integer zsx){
	this.zsx=zsx;
	}

	/**
	*  生产线（条）
	*/
	public Integer getScx (){
	return scx;
	}
	public void setScx (Integer scx){
	this.scx=scx;
	}

	/**
	*  累计新增销售收入（万元）
	*/
	public BigDecimal getNewsalesrevenue (){
	return newsalesrevenue;
	}
	public void setNewsalesrevenue (BigDecimal newsalesrevenue){
	this.newsalesrevenue=newsalesrevenue;
	}

	/**
	*  累计新增利税（万元）
	*/
	public BigDecimal getNewtaxes (){
	return newtaxes;
	}
	public void setNewtaxes (BigDecimal newtaxes){
	this.newtaxes=newtaxes;
	}

	/**
	*  培养高级职称以上
	*/
	public Integer getPygj (){
	return pygj;
	}
	public void setPygj (Integer pygj){
	this.pygj=pygj;
	}

	/**
	*  培养中级职称以上
	*/
	public Integer getPyzj (){
	return pyzj;
	}
	public void setPyzj (Integer pyzj){
	this.pyzj=pyzj;
	}

	/**
	*  引进高级职称以上
	*/
	public Integer getYjgj (){
	return yjgj;
	}
	public void setYjgj (Integer yjgj){
	this.yjgj=yjgj;
	}

	/**
	*  引进新产品（种）
	*/
	public Integer getYjxcp (){
	return yjxcp;
	}
	public void setYjxcp (Integer yjxcp){
	this.yjxcp=yjxcp;
	}

	/**
	*  引进新技术（项）
	*/
	public Integer getYjxjs (){
	return yjxjs;
	}
	public void setYjxjs (Integer yjxjs){
	this.yjxjs=yjxjs;
	}

	/**
	*  引进新工艺（项）
	*/
	public Integer getYjxgy (){
	return yjxgy;
	}
	public void setYjxgy (Integer yjxgy){
	this.yjxgy=yjxgy;
	}

	/**
	*  引进新材料（项）
	*/
	public Integer getYjxcl (){
	return yjxcl;
	}
	public void setYjxcl (Integer yjxcl){
	this.yjxcl=yjxcl;
	}

	/**
	*  支撑平台（个）
	*/
	public Integer getZcpt (){
	return zcpt;
	}
	public void setZcpt (Integer zcpt){
	this.zcpt=zcpt;
	}

	/**
	*  试验基地（个）
	*/
	public Integer getSyjd (){
	return syjd;
	}
	public void setSyjd (Integer syjd){
	this.syjd=syjd;
	}

	/**
	*  示范点（区）（个）
	*/
	public Integer getSfd (){
	return sfd;
	}
	public void setSfd (Integer sfd){
	this.sfd=sfd;
	}

}