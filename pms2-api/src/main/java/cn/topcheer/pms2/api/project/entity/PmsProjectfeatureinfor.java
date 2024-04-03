/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-26 16:54:57
 *
 */
package cn.topcheer.pms2.api.project.entity;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  特征信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ClassInfo(name = "特征信息", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@Entity
@Table(name="PMS_PROJECTFEATUREINFOR")
public class PmsProjectfeatureinfor {


	/**
	 *  主键
	 */
	@FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
	 *  主表关联ID
	 */
	@FieldDes(name="mainid",lenth="255",type="String",memo="主表关联ID")
	private String mainid;

	/**
	 *  顺序
	 */
	@FieldDes(name="seq",type="Integer",memo="顺序")
	private Integer seq;

	/**
	 *  质量指标1
	 */
	@FieldDes(name="zltarget1",type="Integer",memo="质量指标1")
	private Integer zltarget1;

	/**
	 *  质量指标2
	 */
	@FieldDes(name="zltarget2",type="Integer",memo="质量指标2")
	private Integer zltarget2;

	/**
	 *  质量指标3
	 */
	@FieldDes(name="zltarget3",type="Integer",memo="质量指标3")
	private Integer zltarget3;

	/**
	 *  时效指标1
	 */
	@FieldDes(name="sxtarget1",type="Integer",memo="时效指标1")
	private Integer sxtarget1;

	/**
	 *  时效指标2
	 */
	@FieldDes(name="sxtarget2",type="Integer",memo="时效指标2")
	private Integer sxtarget2;

	/**
	 *  时效指标3
	 */
	@FieldDes(name="sxtarget3",type="Integer",memo="时效指标3")
	private Integer sxtarget3;

	/**
	 *  成本指标1
	 */
	@FieldDes(name="cbtarget1",type="Integer",memo="成本指标1")
	private Integer cbtarget1;

	/**
	 *  成本指标2
	 */
	@FieldDes(name="cbtarget2",type="Integer",memo="成本指标2")
	private Integer cbtarget2;

	/**
	 *  成本指标3
	 */
	@FieldDes(name="cbtarget3",type="Integer",memo="成本指标3")
	private Integer cbtarget3;

	/**
	 *  经济效益指标1
	 */
	@FieldDes(name="jjxytarget1",type="Integer",memo="经济效益指标1")
	private Integer jjxytarget1;

	/**
	 *  经济效益指标2
	 */
	@FieldDes(name="jjxytarget2",type="Integer",memo="经济效益指标2")
	private Integer jjxytarget2;

	/**
	 *  经济效益指标3
	 */
	@FieldDes(name="jjxytarget3",type="Integer",memo="经济效益指标3")
	private Integer jjxytarget3;

	/**
	 *  社会效益指标1
	 */
	@FieldDes(name="shxytarget1",type="Integer",memo="社会效益指标1")
	private Integer shxytarget1;

	/**
	 *  社会效益指标2
	 */
	@FieldDes(name="shxytarget2",type="Integer",memo="社会效益指标2")
	private Integer shxytarget2;

	/**
	 *  社会效益指标3
	 */
	@FieldDes(name="shxytarget3",type="Integer",memo="社会效益指标3")
	private Integer shxytarget3;

	/**
	 *  生态效益指标1
	 */
	@FieldDes(name="stxytarget1",type="Integer",memo="生态效益指标1")
	private Integer stxytarget1;

	/**
	 *  生态效益指标2
	 */
	@FieldDes(name="stxytarget2",type="Integer",memo="生态效益指标2")
	private Integer stxytarget2;

	/**
	 *  生态效益指标3
	 */
	@FieldDes(name="stxytarget3",type="Integer",memo="生态效益指标3")
	private Integer stxytarget3;

	/**
	 *  可持续影响指标1
	 */
	@FieldDes(name="kcxtarget1",type="Integer",memo="可持续影响指标1")
	private Integer kcxtarget1;

	/**
	 *  可持续影响指标2
	 */
	@FieldDes(name="kcxtarget2",type="Integer",memo="可持续影响指标2")
	private Integer kcxtarget2;

	/**
	 *  可持续影响指标3
	 */
	@FieldDes(name="kcxtarget3",type="Integer",memo="可持续影响指标3")
	private Integer kcxtarget3;

	/**
	 *  服务对象满意度指标1
	 */
	@FieldDes(name="fwtarget1",type="Integer",memo="服务对象满意度指标1")
	private Integer fwtarget1;

	/**
	 *  服务对象满意度指标2
	 */
	@FieldDes(name="fwtarget2",type="Integer",memo="服务对象满意度指标2")
	private Integer fwtarget2;

	/**
	 *  服务对象满意度指标3
	 */
	@FieldDes(name="fwtarget3",type="Integer",memo="服务对象满意度指标3")
	private Integer fwtarget3;

	/**
	 *  类型
	 */
	@FieldDes(name="type",lenth="255",type="String",memo="类型")
	private String type;

	/**
	 *  父表关联ID
	 */
	@FieldDes(name="sourceid",lenth="255",type="String",memo="父表关联ID")
	private String sourceid;



	/**
	 *  主键
	 */

	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id=id;
	}


	/**
	 *  主表关联ID
	 */

	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid=mainid;
	}


	/**
	 *  顺序
	 */

	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq=seq;
	}


	/**
	 *  质量指标1
	 */

	public Integer getZltarget1 (){
		return zltarget1;
	}
	public void setZltarget1 (Integer zltarget1){
		this.zltarget1=zltarget1;
	}


	/**
	 *  质量指标2
	 */

	public Integer getZltarget2 (){
		return zltarget2;
	}
	public void setZltarget2 (Integer zltarget2){
		this.zltarget2=zltarget2;
	}


	/**
	 *  质量指标3
	 */

	public Integer getZltarget3 (){
		return zltarget3;
	}
	public void setZltarget3 (Integer zltarget3){
		this.zltarget3=zltarget3;
	}


	/**
	 *  时效指标1
	 */

	public Integer getSxtarget1 (){
		return sxtarget1;
	}
	public void setSxtarget1 (Integer sxtarget1){
		this.sxtarget1=sxtarget1;
	}


	/**
	 *  时效指标2
	 */

	public Integer getSxtarget2 (){
		return sxtarget2;
	}
	public void setSxtarget2 (Integer sxtarget2){
		this.sxtarget2=sxtarget2;
	}


	/**
	 *  时效指标3
	 */

	public Integer getSxtarget3 (){
		return sxtarget3;
	}
	public void setSxtarget3 (Integer sxtarget3){
		this.sxtarget3=sxtarget3;
	}


	/**
	 *  成本指标1
	 */

	public Integer getCbtarget1 (){
		return cbtarget1;
	}
	public void setCbtarget1 (Integer cbtarget1){
		this.cbtarget1=cbtarget1;
	}


	/**
	 *  成本指标2
	 */

	public Integer getCbtarget2 (){
		return cbtarget2;
	}
	public void setCbtarget2 (Integer cbtarget2){
		this.cbtarget2=cbtarget2;
	}


	/**
	 *  成本指标3
	 */

	public Integer getCbtarget3 (){
		return cbtarget3;
	}
	public void setCbtarget3 (Integer cbtarget3){
		this.cbtarget3=cbtarget3;
	}


	/**
	 *  经济效益指标1
	 */

	public Integer getJjxytarget1 (){
		return jjxytarget1;
	}
	public void setJjxytarget1 (Integer jjxytarget1){
		this.jjxytarget1=jjxytarget1;
	}


	/**
	 *  经济效益指标2
	 */

	public Integer getJjxytarget2 (){
		return jjxytarget2;
	}
	public void setJjxytarget2 (Integer jjxytarget2){
		this.jjxytarget2=jjxytarget2;
	}


	/**
	 *  经济效益指标3
	 */

	public Integer getJjxytarget3 (){
		return jjxytarget3;
	}
	public void setJjxytarget3 (Integer jjxytarget3){
		this.jjxytarget3=jjxytarget3;
	}


	/**
	 *  社会效益指标1
	 */

	public Integer getShxytarget1 (){
		return shxytarget1;
	}
	public void setShxytarget1 (Integer shxytarget1){
		this.shxytarget1=shxytarget1;
	}


	/**
	 *  社会效益指标2
	 */

	public Integer getShxytarget2 (){
		return shxytarget2;
	}
	public void setShxytarget2 (Integer shxytarget2){
		this.shxytarget2=shxytarget2;
	}


	/**
	 *  社会效益指标3
	 */

	public Integer getShxytarget3 (){
		return shxytarget3;
	}
	public void setShxytarget3 (Integer shxytarget3){
		this.shxytarget3=shxytarget3;
	}


	/**
	 *  生态效益指标1
	 */

	public Integer getStxytarget1 (){
		return stxytarget1;
	}
	public void setStxytarget1 (Integer stxytarget1){
		this.stxytarget1=stxytarget1;
	}


	/**
	 *  生态效益指标2
	 */

	public Integer getStxytarget2 (){
		return stxytarget2;
	}
	public void setStxytarget2 (Integer stxytarget2){
		this.stxytarget2=stxytarget2;
	}


	/**
	 *  生态效益指标3
	 */

	public Integer getStxytarget3 (){
		return stxytarget3;
	}
	public void setStxytarget3 (Integer stxytarget3){
		this.stxytarget3=stxytarget3;
	}


	/**
	 *  可持续影响指标1
	 */

	public Integer getKcxtarget1 (){
		return kcxtarget1;
	}
	public void setKcxtarget1 (Integer kcxtarget1){
		this.kcxtarget1=kcxtarget1;
	}


	/**
	 *  可持续影响指标2
	 */

	public Integer getKcxtarget2 (){
		return kcxtarget2;
	}
	public void setKcxtarget2 (Integer kcxtarget2){
		this.kcxtarget2=kcxtarget2;
	}


	/**
	 *  可持续影响指标3
	 */

	public Integer getKcxtarget3 (){
		return kcxtarget3;
	}
	public void setKcxtarget3 (Integer kcxtarget3){
		this.kcxtarget3=kcxtarget3;
	}


	/**
	 *  服务对象满意度指标1
	 */

	public Integer getFwtarget1 (){
		return fwtarget1;
	}
	public void setFwtarget1 (Integer fwtarget1){
		this.fwtarget1=fwtarget1;
	}


	/**
	 *  服务对象满意度指标2
	 */

	public Integer getFwtarget2 (){
		return fwtarget2;
	}
	public void setFwtarget2 (Integer fwtarget2){
		this.fwtarget2=fwtarget2;
	}


	/**
	 *  服务对象满意度指标3
	 */

	public Integer getFwtarget3 (){
		return fwtarget3;
	}
	public void setFwtarget3 (Integer fwtarget3){
		this.fwtarget3=fwtarget3;
	}


	/**
	 *  类型
	 */

	public String getType (){
		return type;
	}
	public void setType (String type){
		this.type=type;
	}


	/**
	 *  父表关联ID
	 */

	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid=sourceid;
	}

}